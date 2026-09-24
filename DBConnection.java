import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/crypto_db";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_PASSWORD";

    public static Connection getConnection() {

        try {
            Connection connection =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database connected successfully!");

            return connection;

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
