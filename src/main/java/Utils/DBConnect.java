package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/doro?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "102030";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

        return con;
    }
}