import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WatchlistDAO {

    public boolean addToWatchlist(int userId, int cryptoId) {

        String sql = "INSERT INTO watchlist (user_id, crypto_id) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, cryptoId);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Unable to add to watchlist!");
            return false;
        }
    }

    public boolean removeFromWatchlist(int userId, int cryptoId) {

        String sql = "DELETE FROM watchlist WHERE user_id = ? AND crypto_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, cryptoId);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Unable to remove from watchlist!");
            return false;
        }
    }

    public ArrayList<Cryptocurrency> getWatchlist(int userId) {

        ArrayList<Cryptocurrency> list = new ArrayList<>();

        String sql = """
                SELECT c.*
                FROM cryptocurrencies c
                INNER JOIN watchlist w
                ON c.crypto_id = w.crypto_id
                WHERE w.user_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Cryptocurrency crypto = new Cryptocurrency(
                        resultSet.getInt("crypto_id"),
                        resultSet.getString("coin_name"),
                        resultSet.getString("symbol"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("market_cap"),
                        resultSet.getDouble("change_24h")
                );

                list.add(crypto);
            }

        } catch (Exception e) {
            System.out.println("Unable to load watchlist!");
            e.printStackTrace();
        }

        return list;
    }
}
