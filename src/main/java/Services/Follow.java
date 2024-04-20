package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.DBConnect;

public class Follow {
    private static final String followUserQuery = "INSERT INTO followers (user, follows) VALUES (?, ?)";
    private static final String unfollowUserQuery = "DELETE FROM followers WHERE user = ? AND follows = ?";
    private static final String getAllPostsOfFollowedUserQuery = "SELECT p.id, p.title, p.content, p.user, u.first_name, u.last_name, u.username, u.role FROM posts p INNER JOIN users u ON u.id = p.user WHERE user IN (SELECT follows FROM followers WHERE user = ?) ORDER BY p.id DESC";

    public static int followUser(int userId, int followsId) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement followUserStatement = con.prepareStatement(followUserQuery);) {
            followUserStatement.setInt(1, userId);
            followUserStatement.setInt(2, followsId);

            return followUserStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int unFollowUser(int userId, int followsId) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement unFollowUserStatement = con.prepareStatement(unfollowUserQuery);) {
            unFollowUserStatement.setInt(1, userId);
            unFollowUserStatement.setInt(2, followsId);

            return unFollowUserStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static List<Models.Post> getAllPostsOfFollowedUser(int userId) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement getAllPostsOfFollowedUserStatement = con
                        .prepareStatement(getAllPostsOfFollowedUserQuery);) {
            getAllPostsOfFollowedUserStatement.setInt(1, userId);
            ResultSet resultSet = getAllPostsOfFollowedUserStatement.executeQuery();
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
