import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean registerUser(String username, String password, String email) {

        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Registration failed!");
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("Login failed!");
            e.printStackTrace();
        }

        return null;
    }
}
