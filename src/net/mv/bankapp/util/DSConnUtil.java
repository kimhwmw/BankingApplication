package net.mv.bankapp.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Datasource connection utility class. gets rid of the old way
 * of creating connections manually within the application. That
 * way is no bueno.
 * @author Minwoo
 *
 */

public class DSConnUtil {
	
	private static DSConnUtil util;
	private DataSource ds;
	
	private DSConnUtil(DataSource ds){
		this.ds = ds;
	}
	
	/**\
	 * Call from Servlet to set our Datasource.
	 * @param ds
	 */
	public static void setUpDSConnUtil(DataSource ds){
		if(util == null){
			util = new DSConnUtil(ds);
		}
	}
	
	/**
	 * Call from a DAO to get our DSConnUtil object.
	 * @return
	 */
	public static DSConnUtil getUtil(){
		return util;
	}
	
	/**
	 * Use the Datasource to get our connection object.
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}
