public class Portfolio {

    private int portfolioId;
    private int userId;
    private int cryptoId;
    private double quantity;
    private double averagePrice;

    public Portfolio(int portfolioId, int userId, int cryptoId,
                     double quantity, double averagePrice) {
        this.portfolioId = portfolioId;
        this.userId = userId;
        this.cryptoId = cryptoId;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCryptoId() {
        return cryptoId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }
}
