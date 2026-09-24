import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransactionDAO {

    public boolean addTransaction(int userId, int cryptoId,
                                  String transactionType,
                                  double quantity, double price) {

        String sql = """
                INSERT INTO transactions
                (user_id, crypto_id, transaction_type, quantity, price)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, cryptoId);
            statement.setString(3, transactionType);
            statement.setDouble(4, quantity);
            statement.setDouble(5, price);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Unable to save transaction!");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Transaction> getTransactions(int userId) {

        ArrayList<Transaction> list = new ArrayList<>();

        String sql = """
                SELECT * FROM transactions
                WHERE user_id = ?
                ORDER BY transaction_date DESC
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Transaction transaction = new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("crypto_id"),
                        resultSet.getString("transaction_type"),
                        resultSet.getDouble("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("transaction_date")
                );

                list.add(transaction);
            }

        } catch (Exception e) {
            System.out.println("Unable to load transactions!");
            e.printStackTrace();
        }

        return list;
    }
}
