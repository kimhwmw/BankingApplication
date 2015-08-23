package net.mv.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.mv.bankapp.domain.AppUser;
import net.mv.bankapp.util.DSConnUtil;

public class AppUserDao {
	private DSConnUtil util = DSConnUtil.getUtil();
	
public void createAppUser(AppUser user) throws Exception{
		
		String query = "INSERT INTO APP_USER (USERNAME, PASSWORD) VALUES (?,?)";
		//String query2 = "INSERT INTO ACCOUNT ()";
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("The user alreday exists", e);
		}
	}
	
	/**
	 * This method  will retrieve an AppUser by username or 
	 * it will return null if no user is found
	 * @param username
	 * @return AppUser
	 */
	public AppUser retrieveAppUser(String username){
		String query = "SELECT * FROM APP_USER WHERE USERNAME = ?";
		
		// set user to null in order to check later if there is a user found
		AppUser user = null;
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setString(1,username);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				user = new AppUser(
						rs.getLong("A_U_ID"),
						rs.getString("USERNAME"), 
						rs.getString("PASSWORD"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
