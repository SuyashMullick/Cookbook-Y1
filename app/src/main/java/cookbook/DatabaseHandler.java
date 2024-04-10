package cookbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHandler {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost/cookbook";
    private static final String USERNAME = "bnw";
    private static final String PASSWORD = "7k73KDs4cAEN@";

    public static boolean authenticateUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve hashed password from database
                        String hashedPassword = resultSet.getString("password");
                        // Check if password matches hashed password using BCrypt
                        return BCrypt.checkpw(password, hashedPassword);
                    } else {
                        return false; // User not found
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
