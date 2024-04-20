package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.DBConnect;

public class Post {
    private static final String selectPostByIdQuery = "SELECT p.id, p.title, p.content, p.user, u.first_name FROM posts p INNER JOIN users u ON u.id = p.user WHERE p.id = ?";
    private static final String selectPostByUserQuery = "SELECT p.id, p.title, p.content, p.user, u.first_name, u.last_name, u.username, u.role FROM posts p INNER JOIN users u ON u.id = p.user WHERE p.user = ? ORDER BY p.id DESC";
    private static final String selectAllPostsQuery = "SELECT p.id, p.title, p.content, p.user, u.first_name, u.last_name, u.username, u.role FROM posts p INNER JOIN users u ON u.id = p.user ORDER BY p.id DESC";
    private static final String insertPostQuery = "INSERT INTO posts (title, content, user) VALUES (?, ?, ?)";
    private static final String updatePostQuery = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
    private static final String deletePostQuery = "DELETE FROM posts WHERE id = ?";

    public static Models.Post getPostById(int id) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement selectPost = con.prepareStatement(selectPostByIdQuery);) {
            selectPost.setInt(1, id);
            try (ResultSet resultSet = selectPost.executeQuery();) {
                if (resultSet.next()) {
                    return new Models.Post(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            new Models.User(
                                    resultSet.getInt("user"),
                                    resultSet.getString("first_name"),
                                    null, null, null, null));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Models.Post> getPostsByUser(int userId) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement selectPost = con.prepareStatement(selectPostByUserQuery);) {
            selectPost.setInt(1, userId);
            try (ResultSet resultSet = selectPost.executeQuery();) {
                List<Models.Post> results = new ArrayList<>();
                while (resultSet.next()) {
                    results.add(new Models.Post(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            new Models.User(
                                    resultSet.getInt("user"),
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"), resultSet.getString("username"), null,
                                    resultSet.getString("role"))));
                }

                return results;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Models.Post> getAllPosts() {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement selectPost = con.prepareStatement(selectAllPostsQuery);
                ResultSet resultSet = selectPost.executeQuery();) {
            List<Models.Post> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(new Models.Post(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        new Models.User(
                                resultSet.getInt("user"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"), resultSet.getString("username"), null,
                                resultSet.getString("role"))));
            }

            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static int addPost(String title, String content, int userId) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement insertPost = con.prepareStatement(insertPostQuery);) {
            insertPost.setString(1, title);
            insertPost.setString(2, content);
            insertPost.setInt(3, userId);

            return insertPost.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int modifyPost(int id, Models.Post post) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement updatePost = con.prepareStatement(updatePostQuery);) {
            updatePost.setInt(1, post.getId());
            updatePost.setString(2, post.getTitle());
            updatePost.setString(3, post.getContent());
            updatePost.setInt(4, post.getUser().getId());

            return updatePost.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int removePost(int id) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement deletePost = con.prepareStatement(deletePostQuery);) {
            deletePost.setInt(1, id);

            return deletePost.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
