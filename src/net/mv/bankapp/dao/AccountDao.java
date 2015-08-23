package net.mv.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.mv.bankapp.domain.Account;
import net.mv.bankapp.util.DSConnUtil;

public class AccountDao {
	private DSConnUtil util = DSConnUtil.getUtil();
	
	public void createAccount(Account acc) throws Exception{
		String query = "INSERT INTO ACCOUNT (NAME, BALANCE, A_U_ID) VALUES (?,?,?)";
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setString(1, acc.getName());
			pstmt.setDouble(2, acc.getBalance());
			pstmt.setLong(3, acc.getUser_id());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("The account alreday exists", e);
		}
	}
	
	public Account retrieveAccount(long A_U_ID){
		String query = "SELECT * FROM APP_USER, ACCOUNT WHERE ACCOUNT.A_U_ID = ?";
		
		// set user to null in order to check later if there is a user found
		Account acc = null;
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setLong(1,A_U_ID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				acc = new Account(
						rs.getLong("ACC_ID"),
						rs.getString("NAME"), 
						rs.getDouble("BALANCE"),
						rs.getLong("A_U_ID"));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}
	
	public Account retrieveAccountByAccId(long ACC_ID){
		String query = "SELECT * FROM APP_USER, ACCOUNT WHERE ACCOUNT.ACC_ID = ?";
		
		// set user to null in order to check later if there is a user found
		Account acc = null;
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setLong(1,ACC_ID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				acc = new Account(
						rs.getLong("ACC_ID"),
						rs.getString("NAME"), 
						rs.getDouble("BALANCE"),
						rs.getLong("A_U_ID"));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acc;
	}

	public void updateAccount(Account acc){
		String query = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACC_ID = ?";
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setDouble(1,acc.getBalance());
			pstmt.setLong(2, acc.getId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
