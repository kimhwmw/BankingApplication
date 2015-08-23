package net.mv.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Do not this class. We are no longer using
 * the DriverManager to get Connection objects.
 * @author Minwoo
 *
 */
@Deprecated
public class ConnUtil {
	
	private static ConnUtil util;
	
	private final String url;
	private final String username;
	private final String password;

	
	public ConnUtil(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static void setUpUtil(String url, String driver, String username, String password) throws ClassNotFoundException{
		if (util ==null){
			util = new ConnUtil(url,username,password);
			Class.forName(driver);
		}
	}
	@Deprecated
	public static ConnUtil getUtil(){
		return util;
	}
	@Deprecated
	public Connection createConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
}
