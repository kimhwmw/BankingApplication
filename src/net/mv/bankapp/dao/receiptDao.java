package net.mv.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.mv.bankapp.domain.Receipt;
import net.mv.bankapp.util.DSConnUtil;

public class receiptDao {
	private DSConnUtil util = DSConnUtil.getUtil();

	public void createReceipt(Receipt re) throws Exception{
		String query = "INSERT INTO RECEIPT (NAME, ACC_ID, NEW_BALANCE, OLD_BALANCE, OPDATE, OPERATION) VALUES (?,?,?,?,?,?)";
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setString(1, re.getName());
			pstmt.setLong(2, re.getAcc_Id());
			pstmt.setDouble(3, re.getNew_Balance());
			pstmt.setDouble(4, re.getOld_Balance());
			pstmt.setDate(5, re.getOpDate());
			pstmt.setString(6, re.getOperation());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("The receipt alreday exists", e);
		}
	}
	
	public Receipt retrieveReceipt(long Acc_Id){
		String query = "SELECT * FROM RECEIPT WHERE ACC_ID = ? ORDER BY R_ID ";
		Receipt re = null;
		
		try(Connection conn = util.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);){
			
			pstmt.setLong(1,Acc_Id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				re = new Receipt(rs.getLong("R_ID"),
						rs.getLong("ACC_ID"), 
						rs.getString("NAME"),
						rs.getDouble("NEW_BALANCE"), 
						rs.getDouble("OLD_BALANCE"), 
						rs.getDate("OPDATE"), 
						rs.getString("OPERATION"));
				System.out.println(re.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}
}
