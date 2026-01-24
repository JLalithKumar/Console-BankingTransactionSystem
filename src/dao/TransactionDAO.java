package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDAO {

    public void saveTransaction(String fromAcc,
                                String toAcc,
                                double amount,
                                String type,
                                Connection con) {

        String sql = "INSERT INTO transactions(from_account, to_account, amount, txn_type) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fromAcc);
            ps.setString(2, toAcc);
            ps.setDouble(3, amount);
            ps.setString(4, type);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
