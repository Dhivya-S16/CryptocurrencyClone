CREATE DATABASE IF NOT EXISTS crypto_db;
USE crypto_db;

-- Users
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL
);

-- Cryptocurrencies
CREATE TABLE cryptocurrencies (
                                  crypto_id INT AUTO_INCREMENT PRIMARY KEY,
                                  coin_name VARCHAR(100),
                                  symbol VARCHAR(20) UNIQUE,
                                  price DECIMAL(15,2),
                                  market_cap DECIMAL(20,2),
                                  change_24h DECIMAL(5,2)
);

-- Watchlist
CREATE TABLE watchlist (
                           watchlist_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT,
                           crypto_id INT,
                           added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           UNIQUE(user_id, crypto_id),
                           FOREIGN KEY (user_id) REFERENCES users(user_id),
                           FOREIGN KEY (crypto_id) REFERENCES cryptocurrencies(crypto_id)
);

-- Portfolio
CREATE TABLE portfolio (
                           portfolio_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT,
                           crypto_id INT,
                           quantity DECIMAL(18,8),
                           average_price DECIMAL(15,2),
                           UNIQUE(user_id, crypto_id),
                           FOREIGN KEY (user_id) REFERENCES users(user_id),
                           FOREIGN KEY (crypto_id) REFERENCES cryptocurrencies(crypto_id)
);

-- Transactions
CREATE TABLE transactions (
                              transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                              user_id INT,
                              crypto_id INT,
                              transaction_type VARCHAR(10),
                              quantity DECIMAL(18,8),
                              price DECIMAL(15,2),
                              transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(user_id),
                              FOREIGN KEY (crypto_id) REFERENCES cryptocurrencies(crypto_id)
);

-- Sample cryptocurrency data
INSERT INTO cryptocurrencies
(coin_name, symbol, price, market_cap, change_24h)
VALUES
    ('Bitcoin', 'BTC', 95000.00, 1880000000000.00, 2.45),
    ('Ethereum', 'ETH', 3400.00, 410000000000.00, 1.82),
    ('Solana', 'SOL', 220.00, 105000000000.00, 3.15),
    ('Cardano', 'ADA', 0.85, 30000000000.00, -1.25),
    ('Dogecoin', 'DOGE', 0.25, 37000000000.00, 0.95);