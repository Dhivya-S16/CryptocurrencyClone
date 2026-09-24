public class Transaction {

    private int transactionId;
    private int userId;
    private int cryptoId;
    private String transactionType;
    private double quantity;
    private double price;
    private String transactionDate;

    public Transaction(int transactionId, int userId, int cryptoId,
                       String transactionType, double quantity,
                       double price, String transactionDate) {

        this.transactionId = transactionId;
        this.userId = userId;
        this.cryptoId = cryptoId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.price = price;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCryptoId() {
        return cryptoId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTransactionDate() {
        return transactionDate;
    }
}
