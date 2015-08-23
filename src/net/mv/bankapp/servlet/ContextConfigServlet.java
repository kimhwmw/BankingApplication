package net.mv.bankapp.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.mv.bankapp.util.ConnUtil;

/**
 * Deprecated as we no longer use the DriverManager
 * to get connection objects.
 * @author Minwoo
 *
 */
@Deprecated
public class ContextConfigServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4095380683692389988L;

	@Override
	public void init() throws ServletException {
		
		ServletContext ctx = getServletContext();
		
		String driver = ctx.getInitParameter("dbDriver");
		String url = ctx.getInitParameter("dbUrl");
		String username = ctx.getInitParameter("username");
		String password = ctx.getInitParameter("password");
		
		try {
			ConnUtil.setUpUtil(url, driver, username, password);
		} catch (ClassNotFoundException e) {
			// this will stop if connection is established
			throw new ServletException(e);
		}
		
		try(Connection conn = ConnUtil.getUtil().createConnection(); 
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM APP_USER")){
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getLong(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
				
	}
	
}
