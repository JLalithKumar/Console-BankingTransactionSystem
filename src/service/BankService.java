package service;

import dao.AccountDAO;
import dao.TransactionDAO;
import db.DBConnection;

import java.sql.Connection;

public class BankService {

    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public void transferMoney(String fromAcc, String toAcc, double amount) {

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false); // START TRANSACTION

            boolean withdrawn =
                    accountDAO.withdraw(fromAcc, amount, con);

            if (!withdrawn) {
                throw new RuntimeException("Insufficient balance");
            }

            boolean deposited =
                    accountDAO.deposit(toAcc, amount, con);

            if (!deposited) {
                throw new RuntimeException("Deposit failed");
            }

            transactionDAO.saveTransaction(
                    fromAcc, toAcc, amount, "TRANSFER", con);

            con.commit();
            System.out.println("Transfer successful!");

        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); // clean user message
        } catch (Exception e) {
            System.out.println("Transfer failed due to system error.");
            e.printStackTrace(); // keep for unexpected errors
        }
    }
}
