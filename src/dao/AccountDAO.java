package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {
    public String openAccount(int customerId, double initialBalance) {

        String accountNumber = generateAccountNumber();

        String sql =
                "INSERT INTO accounts(account_number, customer_id, balance) " +
                        "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            ps.setInt(2, customerId);
            ps.setDouble(3, initialBalance);

            ps.executeUpdate();
            return accountNumber;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateAccountNumber() {
        int random = (int) (Math.random() * 900000) + 100000;
        return "BANK2026" + random;
    }

    public double getBalance(String accountNumber, Connection con) {

        String sql =
                "SELECT balance FROM accounts WHERE account_number = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean deposit(String accountNumber, double amount, Connection con) {

        String sql =
                "UPDATE accounts SET balance = balance + ? " +
                        "WHERE account_number = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount, Connection con) {

        String sql =
                "UPDATE accounts SET balance = balance - ? " +
                        "WHERE account_number = ? AND balance >= ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            ps.setDouble(3, amount);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
