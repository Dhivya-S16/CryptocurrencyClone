public class Cryptocurrency {

    private int cryptoId;
    private String coinName;
    private String symbol;
    private double price;
    private double marketCap;
    private double change24h;

    public Cryptocurrency(int cryptoId, String coinName, String symbol,
                          double price, double marketCap, double change24h) {
        this.cryptoId = cryptoId;
        this.coinName = coinName;
        this.symbol = symbol;
        this.price = price;
        this.marketCap = marketCap;
        this.change24h = change24h;
    }

    public int getCryptoId() {
        return cryptoId;
    }

    public String getCoinName() {
        return coinName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public double getChange24h() {
        return change24h;
    }
}