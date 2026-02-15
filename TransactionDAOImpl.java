package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.exceptions.TransactionNotFoundException;
import bank.model.Transaction;
import util.factory.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

    
    public void save(Transaction transaction) {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "INSERT INTO transactions(accountNo, type, amount) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setLong(1, transaction.getAccountNo());
            ps.setString(2, transaction.getType());
            ps.setDouble(3, transaction.getAmount());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public List<Transaction> findAll() {

        List<Transaction> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM transactions";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getLong("accountNo"),
                        rs.getString("type"),
                        rs.getDouble("amount")
                );
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
    public List<Transaction> findByAccountNo(long accountNo)
            throws TransactionNotFoundException {

        List<Transaction> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM transactions WHERE accountNo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction(
                        rs.getLong("accountNo"),
                        rs.getString("type"),
                        rs.getDouble("amount")
                );
                list.add(t);
            }

            if (list.isEmpty()) {
                throw new TransactionNotFoundException(
                        "No transactions found for account: " + accountNo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteByAccountNo(long accountNo)
            throws TransactionNotFoundException {

        try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM transactions WHERE accountNo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNo);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new TransactionNotFoundException(
                        "No transactions found to delete for account: " + accountNo);
            }

            System.out.println("Transactions Deleted Successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

