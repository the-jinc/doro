package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.DBConnect;

public class Like {

    private static final String likePostQuery = "INSERT INTO likes (user, post) VALUES (?, ?)";
    private static final String unLikePostQuery = "DELETE FROM likes WHERE user = ? AND post = ?";
    private static final String getAllPostILikedQuery = "SELECT p.id, p.title, p.content, p.image, p.user, u.first_name, u.last_name, u.username, u.role FROM posts p JOIN likes l ON p.id = l.post JOIN users u ON l.user = u.id WHERE l.user = ?";

    public static int likePost(int userId, int postId) {
        try (Connection con = DBConnect.getConnection();
                PreparedStatement likePostStatement = con.prepareStatement(likePostQuery);) {
            likePostStatement.setInt(1, userId);
            likePostStatement.setInt(2, postId);

            return likePostStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int unLikePost(int userId, int postId) {
        try (Connection con = DBConnect.getConnection();
                PreparedStatement unLikePostStatement = con.prepareStatement(unLikePostQuery)) {
            unLikePostStatement.setInt(1, userId);
            unLikePostStatement.setInt(2, postId);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static List<Models.Post> getAllPostsILiked(int userId) {
        try (Connection con = DBConnect.getConnection();
                PreparedStatement getAllPostsILikedStatement = con.prepareStatement(getAllPostILikedQuery);) {
            getAllPostsILikedStatement.setInt(1, userId);
            ResultSet resultSet = getAllPostsILikedStatement.executeQuery();
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
}
