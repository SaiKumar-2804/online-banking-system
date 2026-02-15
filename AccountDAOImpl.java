package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.exceptions.AccountNotFoundException;
import bank.model.Account;
import util.factory.DBConnection;

public class AccountDAOImpl implements AccountDAO {

	
	public void save(Account account) {
		  try (Connection con = DBConnection.getConnection()) {

	            String sql = "INSERT INTO account (accountNo, name, password, balance) VALUES (?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setLong(1, account.getAccountNo());
	            ps.setString(2, account.getName());
	            ps.setString(3, account.getPassword());
	            ps.setDouble(4, account.getBalance());

	            ps.executeUpdate();
	            System.out.println("Account Saved Successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

	
	public List<Account> findAll() {
		 List<Account> list = new ArrayList<>();

	        try (Connection con = DBConnection.getConnection()) {

	            String sql = "SELECT * FROM account";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Account acc = new Account(
	                        rs.getLong("accountNo"),
	                        rs.getString("name"),
	                        rs.getString("password"),
	                        rs.getDouble("balance")
	                );
	                list.add(acc);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return list;
	}

	
	public Account findById(long accountNo) throws AccountNotFoundException {
		try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM account WHERE accountNo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getLong("accountNo"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getDouble("balance")
                );
            } else {
                throw new AccountNotFoundException("Account Not Found with ID: " + accountNo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public void deleteById(long accountNo) throws AccountNotFoundException {
		try (Connection con = DBConnection.getConnection()) {

            String sql = "DELETE FROM account WHERE accountNo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, accountNo);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new AccountNotFoundException("Account Not Found to Delete!");
            }

            System.out.println("Account Deleted Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	
	public void update(Account account) throws AccountNotFoundException {
		try (Connection con = DBConnection.getConnection()) {

            String sql = "UPDATE account SET name=?, password=?, balance=? WHERE accountNo=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, account.getName());
            ps.setString(2, account.getPassword());
            ps.setDouble(3, account.getBalance());
            ps.setLong(4, account.getAccountNo());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new AccountNotFoundException("Account Not Found to Update!");
            }

            System.out.println("Account Updated Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	

}
