import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PortfolioDAO {

    public boolean addOrUpdatePortfolio(int userId, int cryptoId,
                                        double quantity, double price) {

        String checkSql =
                "SELECT * FROM portfolio WHERE user_id = ? AND crypto_id = ?";

        String insertSql =
                "INSERT INTO portfolio " +
                        "(user_id, crypto_id, quantity, average_price) " +
                        "VALUES (?, ?, ?, ?)";

        String updateSql =
                "UPDATE portfolio " +
                        "SET quantity = quantity + ?, " +
                        "average_price = ? " +
                        "WHERE user_id = ? AND crypto_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement check =
                     connection.prepareStatement(checkSql)) {

            check.setInt(1, userId);
            check.setInt(2, cryptoId);

            ResultSet resultSet = check.executeQuery();

            if (resultSet.next()) {

                try (PreparedStatement update =
                             connection.prepareStatement(updateSql)) {

                    update.setDouble(1, quantity);
                    update.setDouble(2, price);
                    update.setInt(3, userId);
                    update.setInt(4, cryptoId);

                    update.executeUpdate();
                }

            } else {

                try (PreparedStatement insert =
                             connection.prepareStatement(insertSql)) {

                    insert.setInt(1, userId);
                    insert.setInt(2, cryptoId);
                    insert.setDouble(3, quantity);
                    insert.setDouble(4, price);

                    insert.executeUpdate();
                }
            }

            return true;

        } catch (Exception e) {

            System.out.println("Unable to update portfolio!");
            e.printStackTrace();

            return false;
        }
    }


    // Sell cryptocurrency from portfolio
    public boolean sellFromPortfolio(int userId, int cryptoId,
                                     double quantity) {

        String checkSql =
                "SELECT quantity FROM portfolio " +
                        "WHERE user_id = ? AND crypto_id = ?";

        String updateSql =
                "UPDATE portfolio SET quantity = quantity - ? " +
                        "WHERE user_id = ? AND crypto_id = ?";

        String deleteSql =
                "DELETE FROM portfolio " +
                        "WHERE user_id = ? AND crypto_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement check =
                     connection.prepareStatement(checkSql)) {

            check.setInt(1, userId);
            check.setInt(2, cryptoId);

            ResultSet resultSet = check.executeQuery();

            if (!resultSet.next()) {
                return false;
            }

            double currentQuantity =
                    resultSet.getDouble("quantity");

            if (quantity <= 0 || quantity > currentQuantity) {
                return false;
            }

            if (quantity == currentQuantity) {

                try (PreparedStatement delete =
                             connection.prepareStatement(deleteSql)) {

                    delete.setInt(1, userId);
                    delete.setInt(2, cryptoId);

                    delete.executeUpdate();
                }

            } else {

                try (PreparedStatement update =
                             connection.prepareStatement(updateSql)) {

                    update.setDouble(1, quantity);
                    update.setInt(2, userId);
                    update.setInt(3, cryptoId);

                    update.executeUpdate();
                }
            }

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Unable to sell cryptocurrency!"
            );

            e.printStackTrace();

            return false;
        }
    }


    public ArrayList<Portfolio> getPortfolio(int userId) {

        ArrayList<Portfolio> list = new ArrayList<>();

        String sql =
                "SELECT * FROM portfolio WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Portfolio portfolio = new Portfolio(
                        resultSet.getInt("portfolio_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("crypto_id"),
                        resultSet.getDouble("quantity"),
                        resultSet.getDouble("average_price")
                );

                list.add(portfolio);
            }

        } catch (Exception e) {

            System.out.println(
                    "Unable to load portfolio!"
            );

            e.printStackTrace();
        }

        return list;
    }
}