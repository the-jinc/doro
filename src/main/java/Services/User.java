package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.DBConnect;

public class User {
    private static final String selectUserByIdQuery = "SELECT * FROM users WHERE id = ?";
    private static final String selectUserByUsernameQuery = "SELECT * FROM users WHERE username = ?";
    private static final String selectAllUsersQueryExcept = "SELECT * FROM users WHERE id <> ?";
    private static final String insertUserQuery = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
    private static final String updateUserQuery = "UPDATE users SET first_name = ?, last_name = ?, username = ?, role = ? WHERE id = ?";

    public static Models.User getUserById(int id) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement selectUser = con.prepareStatement(selectUserByIdQuery);) {
            selectUser.setInt(1, id);
            try (ResultSet resultSet = selectUser.executeQuery();) {
                if (resultSet.next()) {
                    return new Models.User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Models.User getUserByUsername(String username) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement selectUser = con.prepareStatement(selectUserByUsernameQuery);) {
            selectUser.setString(1, username);
            try (ResultSet resultSet = selectUser.executeQuery();) {
                if (resultSet.next()) {
                    return new Models.User(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static List<Models.User> getAllUsers(int id) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement selectUser = con.prepareStatement(selectAllUsersQueryExcept);) {
            selectUser.setInt(1, id);
            ResultSet resultSet = selectUser.executeQuery();
            List<Models.User> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(new Models.User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }

            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static int addUser(String firstName, String lastName, String username, String password) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement insertUser = con.prepareStatement(insertUserQuery);) {
            insertUser.setString(1, firstName);
            insertUser.setString(2, lastName);
            insertUser.setString(3, username);
            insertUser.setString(4, Models.User.hashPassword(password));

            return insertUser.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static int modifyUser(int id, Models.User user) {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement updateUser = con.prepareStatement(updateUserQuery);) {
            updateUser.setString(1, user.getFirstName());
            updateUser.setString(2, user.getLastName());
            updateUser.setString(3, user.getUsername());
            // updateUser.setString(4, user.getPassword());
            updateUser.setString(4, user.getRole());
            updateUser.setInt(5, id);

            return updateUser.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

}