import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DashboardGUI extends JFrame {

    private User loggedInUser;
    private CryptoDAO cryptoDAO;
    private WatchlistDAO watchlistDAO;
    private PortfolioDAO portfolioDAO;
    private TransactionDAO transactionDAO;

    private JTextArea outputArea;

    public DashboardGUI(User user) {

        this.loggedInUser = user;

        cryptoDAO = new CryptoDAO();
        watchlistDAO = new WatchlistDAO();
        portfolioDAO = new PortfolioDAO();
        transactionDAO = new TransactionDAO();

        setTitle("Cryptocurrency Clone - Dashboard");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel =
                new JLabel("Welcome, " + user.getUsername());

        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Button Panel
        JPanel buttonPanel =
                new JPanel(new GridLayout(3, 5, 10, 10));

        JButton viewCoinsButton =
                new JButton("View Coins");

        JButton coinDetailsButton =
                new JButton("Coin Details");

        JButton marketOverviewButton =
                new JButton("Market Overview");

        JButton searchButton =
                new JButton("Search Coins");

        JButton watchlistButton =
                new JButton("My Watchlist");

        JButton addWatchlistButton =
                new JButton("Add to Watchlist");

        JButton removeWatchlistButton =
                new JButton("Remove from Watchlist");

        JButton portfolioButton =
                new JButton("My Portfolio");

        JButton buyButton =
                new JButton("Buy Crypto");

        JButton sellButton =
                new JButton("Sell Crypto");

        JButton historyButton =
                new JButton("Transaction History");

        JButton logoutButton =
                new JButton("Logout");

        // Add buttons
        buttonPanel.add(viewCoinsButton);
        buttonPanel.add(coinDetailsButton);
        buttonPanel.add(marketOverviewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(watchlistButton);

        buttonPanel.add(addWatchlistButton);
        buttonPanel.add(removeWatchlistButton);
        buttonPanel.add(portfolioButton);
        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);

        buttonPanel.add(historyButton);
        buttonPanel.add(logoutButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        // Layout
        setLayout(new BorderLayout(10, 10));

        add(welcomeLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions

        viewCoinsButton.addActionListener(
                e -> viewCoins()
        );

        coinDetailsButton.addActionListener(
                e -> showCoinDetails()
        );

        marketOverviewButton.addActionListener(
                e -> showMarketOverview()
        );

        searchButton.addActionListener(
                e -> searchCoins()
        );

        watchlistButton.addActionListener(
                e -> viewWatchlist()
        );

        addWatchlistButton.addActionListener(
                e -> addToWatchlist()
        );

        removeWatchlistButton.addActionListener(
                e -> removeFromWatchlist()
        );

        portfolioButton.addActionListener(
                e -> viewPortfolio()
        );

        buyButton.addActionListener(
                e -> buyCrypto()
        );

        sellButton.addActionListener(
                e -> sellCrypto()
        );

        historyButton.addActionListener(
                e -> viewTransactions()
        );

        logoutButton.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        setVisible(true);
    }


    // View all cryptocurrencies
    private void viewCoins() {

        ArrayList<Cryptocurrency> coins =
                cryptoDAO.getAllCryptocurrencies();

        outputArea.setText("");

        if (coins.isEmpty()) {

            outputArea.setText(
                    "No cryptocurrencies available."
            );

            return;
        }

        for (Cryptocurrency coin : coins) {

            outputArea.append(
                    "ID: " + coin.getCryptoId() +
                            " | " + coin.getCoinName() +
                            " (" + coin.getSymbol() + ")" +
                            " | Price: ₹" + coin.getPrice() +
                            " | Market Cap: ₹" +
                            coin.getMarketCap() +
                            " | 24h: " +
                            coin.getChange24h() +
                            "%\n"
            );
        }
    }


    // Coin details
    private void showCoinDetails() {

        try {

            int cryptoId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Crypto ID:"
                            )
                    );

            ArrayList<Cryptocurrency> coins =
                    cryptoDAO.getAllCryptocurrencies();

            Cryptocurrency selectedCoin = null;

            for (Cryptocurrency coin : coins) {

                if (coin.getCryptoId() == cryptoId) {

                    selectedCoin = coin;
                    break;
                }
            }

            if (selectedCoin == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cryptocurrency not found."
                );

                return;
            }

            outputArea.setText(
                    "========== COIN DETAILS ==========\n\n" +
                            "Crypto ID     : " +
                            selectedCoin.getCryptoId() + "\n" +
                            "Coin Name     : " +
                            selectedCoin.getCoinName() + "\n" +
                            "Symbol        : " +
                            selectedCoin.getSymbol() + "\n" +
                            "Price         : ₹" +
                            selectedCoin.getPrice() + "\n" +
                            "Market Cap    : ₹" +
                            selectedCoin.getMarketCap() + "\n" +
                            "24h Change    : " +
                            selectedCoin.getChange24h() + "%\n"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Crypto ID."
            );
        }
    }


    // Market Overview
    private void showMarketOverview() {

        ArrayList<Cryptocurrency> coins =
                cryptoDAO.getAllCryptocurrencies();

        if (coins.isEmpty()) {

            outputArea.setText(
                    "No market data available."
            );

            return;
        }

        double totalMarketCap = 0;
        Cryptocurrency highestPriceCoin = coins.get(0);
        Cryptocurrency highestChangeCoin = coins.get(0);

        for (Cryptocurrency coin : coins) {

            totalMarketCap += coin.getMarketCap();

            if (coin.getPrice() >
                    highestPriceCoin.getPrice()) {

                highestPriceCoin = coin;
            }

            if (coin.getChange24h() >
                    highestChangeCoin.getChange24h()) {

                highestChangeCoin = coin;
            }
        }

        outputArea.setText(
                "========== MARKET OVERVIEW ==========\n\n" +

                        "Total Cryptocurrencies : " +
                        coins.size() + "\n\n" +

                        "Total Market Cap       : ₹" +
                        totalMarketCap + "\n\n" +

                        "Highest Priced Coin    : " +
                        highestPriceCoin.getCoinName() +
                        " (" +
                        highestPriceCoin.getSymbol() +
                        ")" +
                        "\nPrice                  : ₹" +
                        highestPriceCoin.getPrice() +
                        "\n\n" +

                        "Highest 24h Gainer     : " +
                        highestChangeCoin.getCoinName() +
                        " (" +
                        highestChangeCoin.getSymbol() +
                        ")" +
                        "\n24h Change             : " +
                        highestChangeCoin.getChange24h() +
                        "%\n"
        );
    }


    // Search cryptocurrencies
    private void searchCoins() {

        String keyword =
                JOptionPane.showInputDialog(
                        this,
                        "Enter coin name or symbol:"
                );

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            return;
        }

        ArrayList<Cryptocurrency> coins =
                cryptoDAO.searchCryptocurrencies(
                        keyword.trim()
                );

        outputArea.setText("");

        for (Cryptocurrency coin : coins) {

            outputArea.append(
                    "ID: " + coin.getCryptoId() +
                            " | " + coin.getCoinName() +
                            " (" + coin.getSymbol() + ")" +
                            " | Price: ₹" + coin.getPrice() +
                            " | 24h: " +
                            coin.getChange24h() +
                            "%\n"
            );
        }

        if (coins.isEmpty()) {

            outputArea.setText(
                    "No matching cryptocurrency found."
            );
        }
    }


    // View watchlist
    private void viewWatchlist() {

        ArrayList<Cryptocurrency> coins =
                watchlistDAO.getWatchlist(
                        loggedInUser.getUserId()
                );

        outputArea.setText(
                "MY WATCHLIST\n\n"
        );

        for (Cryptocurrency coin : coins) {

            outputArea.append(
                    coin.getCoinName() +
                            " (" + coin.getSymbol() + ")" +
                            " - ₹" + coin.getPrice() +
                            "\n"
            );
        }

        if (coins.isEmpty()) {

            outputArea.append(
                    "Watchlist is empty."
            );
        }
    }


    // Add cryptocurrency to watchlist
    private void addToWatchlist() {

        try {

            int cryptoId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Crypto ID:"
                            )
                    );

            boolean success =
                    watchlistDAO.addToWatchlist(
                            loggedInUser.getUserId(),
                            cryptoId
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Added to watchlist successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to add to watchlist."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Crypto ID."
            );
        }
    }


    // Remove cryptocurrency from watchlist
    private void removeFromWatchlist() {

        try {

            int cryptoId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Crypto ID:"
                            )
                    );

            boolean success =
                    watchlistDAO.removeFromWatchlist(
                            loggedInUser.getUserId(),
                            cryptoId
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Removed from watchlist successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to remove from watchlist."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Crypto ID."
            );
        }
    }


    // View portfolio
    private void viewPortfolio() {

        ArrayList<Portfolio> portfolios =
                portfolioDAO.getPortfolio(
                        loggedInUser.getUserId()
                );

        outputArea.setText(
                "MY PORTFOLIO\n\n"
        );

        for (Portfolio portfolio : portfolios) {

            outputArea.append(
                    "Crypto ID: " +
                            portfolio.getCryptoId() +
                            " | Quantity: " +
                            portfolio.getQuantity() +
                            " | Average Price: ₹" +
                            portfolio.getAveragePrice() +
                            "\n"
            );
        }

        if (portfolios.isEmpty()) {

            outputArea.append(
                    "Portfolio is empty."
            );
        }
    }


    // Buy cryptocurrency
    private void buyCrypto() {

        try {

            int cryptoId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Crypto ID:"
                            )
                    );

            double quantity =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Quantity:"
                            )
                    );

            double price =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Current Price:"
                            )
                    );

            boolean portfolioSuccess =
                    portfolioDAO.addOrUpdatePortfolio(
                            loggedInUser.getUserId(),
                            cryptoId,
                            quantity,
                            price
                    );

            boolean transactionSuccess =
                    transactionDAO.addTransaction(
                            loggedInUser.getUserId(),
                            cryptoId,
                            "BUY",
                            quantity,
                            price
                    );

            if (portfolioSuccess &&
                    transactionSuccess) {

                JOptionPane.showMessageDialog(
                        this,
                        "Crypto purchased successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Purchase failed."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid values."
            );
        }
    }


    // Sell cryptocurrency
    private void sellCrypto() {

        try {

            int cryptoId =
                    Integer.parseInt(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Crypto ID:"
                            )
                    );

            double quantity =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Quantity to Sell:"
                            )
                    );

            double price =
                    Double.parseDouble(
                            JOptionPane.showInputDialog(
                                    this,
                                    "Enter Current Price:"
                            )
                    );

            boolean portfolioSuccess =
                    portfolioDAO.sellFromPortfolio(
                            loggedInUser.getUserId(),
                            cryptoId,
                            quantity
                    );

            if (!portfolioSuccess) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sell failed. Check Crypto ID or available quantity."
                );

                return;
            }

            boolean transactionSuccess =
                    transactionDAO.addTransaction(
                            loggedInUser.getUserId(),
                            cryptoId,
                            "SELL",
                            quantity,
                            price
                    );

            if (transactionSuccess) {

                JOptionPane.showMessageDialog(
                        this,
                        "Crypto sold successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Crypto sold, but transaction history could not be saved."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid values."
            );
        }
    }


    // View transaction history
    private void viewTransactions() {

        ArrayList<Transaction> transactions =
                transactionDAO.getTransactions(
                        loggedInUser.getUserId()
                );

        outputArea.setText(
                "TRANSACTION HISTORY\n\n"
        );

        for (Transaction transaction :
                transactions) {

            outputArea.append(
                    transaction.getTransactionType() +
                            " | Crypto ID: " +
                            transaction.getCryptoId() +
                            " | Quantity: " +
                            transaction.getQuantity() +
                            " | Price: ₹" +
                            transaction.getPrice() +
                            " | Date: " +
                            transaction.getTransactionDate() +
                            "\n"
            );
        }

        if (transactions.isEmpty()) {

            outputArea.append(
                    "No transactions found."
            );
        }
    }
}