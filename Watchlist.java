public class Watchlist {

    private int watchlistId;
    private int userId;
    private int cryptoId;

    public Watchlist(int watchlistId, int userId, int cryptoId) {
        this.watchlistId = watchlistId;
        this.userId = userId;
        this.cryptoId = cryptoId;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCryptoId() {
        return cryptoId;
    }
}
