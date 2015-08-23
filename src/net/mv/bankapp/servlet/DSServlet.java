package net.mv.bankapp.servlet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import net.mv.bankapp.util.DSConnUtil;

/**
 * Servlet to set up a DataSource in our DSConnUtil class
 * via JNDI lookup
 * @author Minwoo
 *
 */
public class DSServlet extends HttpServlet{
	
	/**
	 * The @Resource annotation does a JNDI lookup within
	 * the server's JNDI tree for the name provided. It
	 * will return our DataSource object.
	 */
	@Resource(name="jdbc/oracle10g")
	private DataSource ds;
	
	@Override
	public void init() throws ServletException {
		DSConnUtil.setUpDSConnUtil(ds);
	}
}
