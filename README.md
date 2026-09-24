# Cryptocurrency Clone

## Project Overview

Cryptocurrency Clone is a Java-based application that allows users to explore cryptocurrency information, manage their watchlist, track their portfolio, and maintain transaction history.

The project is developed using Core Java, Java Swing, JDBC, and MySQL.

## Features

- User Registration and Login
- Cryptocurrency Listings
- Coin Details
- Search and Filter Cryptocurrencies
- Watchlist Management
- Portfolio Tracking
- Buy Cryptocurrency
- Sell Cryptocurrency
- Transaction History
- Market Overview Dashboard
- Logout

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- MySQL Connector/J

## Java Concepts Used

- Classes and Objects
- Encapsulation
- Constructors
- Methods
- ArrayList
- Exception Handling
- JDBC
- Event Handling
- Swing GUI
- SQL Queries

## Database

Database Name:

`crypto_db`

### Tables

- users
- cryptocurrencies
- watchlist
- portfolio
- transactions

## Project Structure

- DBConnection.java
- User.java
- Cryptocurrency.java
- Watchlist.java
- Portfolio.java
- Transaction.java
- UserDAO.java
- CryptoDAO.java
- WatchlistDAO.java
- PortfolioDAO.java
- TransactionDAO.java
- LoginGUI.java
- RegisterGUI.java
- DashboardGUI.java

## How to Run

1. Install Java JDK.
2. Install MySQL.
3. Create the `crypto_db` database.
4. Create the required tables.
5. Add MySQL Connector/J to the project.
6. Update the database username and password in `DBConnection.java`.
7. Run `LoginGUI.java`.
8. Register a new user or use an existing account.
9. Explore the cryptocurrency features.

## Database Integration

JDBC is used to connect the Java application with MySQL.

The application performs database operations such as:

- User registration and login
- Loading cryptocurrency data
- Adding and removing watchlist items
- Updating portfolio
- Recording transactions
- Retrieving transaction history

## Learning Experience

This project helped in understanding practical Java application development, Swing GUI design, JDBC connectivity, MySQL database handling, CRUD operations, event handling, and building a real-world style application.

## Internship

Developed as part of the Java Programming Internship at **SQROCK IT SOLUTIONS**.