package net.mv.bankapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mv.bankapp.dao.AccountDao;
import net.mv.bankapp.dao.AppUserDao;
import net.mv.bankapp.dao.receiptDao;
import net.mv.bankapp.domain.Account;
import net.mv.bankapp.domain.AppUser;
import net.mv.bankapp.domain.Receipt;

@WebServlet(urlPatterns= {"/login","/register","/logout","/transaction","/Receipt"})

public class MainServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AppUserDao userDao = new AppUserDao();
		AccountDao accDao = new AccountDao();
		receiptDao reDao = new receiptDao();
		PrintWriter out = response.getWriter();
		Account currentAccount = null;
		String currentUser = null;
		
		String action = request.getServletPath();
		

		Date date = Calendar.getInstance().getTime();
		java.sql.Date myDate = new java.sql.Date(date.getTime());
		
		if (action.equals("/login")){
			String username =request.getParameter("username");
			String password = request.getParameter("password");
			
			RequestDispatcher rd = null;
			AppUser user = userDao.retrieveAppUser(username);
			
			if(username == null){
				out.write("<h1>NO USER !!!</h1>");
			}
			
			else if (username != null && user.getPassword().equals(password)){
				currentAccount = accDao.retrieveAccount(user.getId());
				currentUser = username;
			    HttpSession session = request.getSession();
			    session.setAttribute("Username", username);
			    // setting session to expire in 30 mins
			    session.setMaxInactiveInterval(180);
			    Cookie userName = new Cookie("Username", username);
			    response.addCookie(userName);

			    rd = getServletContext().getRequestDispatcher("/home.jsp");
			    request.setAttribute("AccountNum", currentAccount.getId());
			    request.setAttribute("AccountBalance", currentAccount.getBalance());
			    rd.forward(request, response);
			    //response.sendRedirect("home.jsp");
			}
			else{
				rd = getServletContext().getRequestDispatcher("/login.jsp");
	            out.println("<font color=red>Either user name or password is wrong.</font>");
	            rd.include(request, response);
			}
		}
		
		else if (action.equals("/register")){
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
	        String contextPath = request.getContextPath();
	        
			AppUser user = new AppUser(username,password);
			try {
				userDao.createAppUser(user);
				//out.write("<h1>Success!!! </h1>");
				 response.sendRedirect(response.encodeRedirectURL(contextPath + "/login.jsp"));
			} catch (Exception e) {
				out.write("<script>window.alert('Sorry!! That username is taken. Try other name!!');</script>");
				RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
				rd.include(request,response);
			}
			
			AppUser temp = userDao.retrieveAppUser(username);
			Account acc = new Account(temp.getUsername(), 0.0, temp.getId());
			try {
				accDao.createAccount(acc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (action.equals("/logout")){
			response.setContentType("text/html");
	        Cookie[] cookies = request.getCookies();
	        if(cookies != null){
	        for(Cookie cookie : cookies){
	            if(cookie.getName().equals("JSESSIONID")){
	                System.out.println("JSESSIONID="+cookie.getValue());
	                break;
	            }
	        }
	        }
	        //invalidate the session if exists
	        HttpSession session = request.getSession(false);
	        System.out.println("User="+session.getAttribute("Username"));
	        if(session != null){
	            session.invalidate();
	        }
	        response.sendRedirect("login.jsp");
		}
		
		else if (action.equals("/transaction")){
			
			RequestDispatcher rd = null;
			
			if (request.getParameter("deposit") != null && request.getParameter("withdraw") != null){
				out.write("<script>window.alert('Please select only one box!!');</script>");

			}
			else if (request.getParameter("deposit") != null){
				double amount = Double.parseDouble(request.getParameter("amount"));
				long acc_id = Long.parseLong(request.getParameter("AccountNum"));
				Account acc = accDao.retrieveAccountByAccId(acc_id);
				double oldBal = acc.getBalance();
				
				acc.setBalance(acc.getBalance()+amount);
				accDao.updateAccount(acc);
				
				Receipt re = new Receipt(acc_id, acc.getName(), acc.getBalance(), oldBal, myDate ,"D");
				try {
					reDao.createReceipt(re);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				rd = getServletContext().getRequestDispatcher("/Receipt.jsp");
			    request.setAttribute("AccountNum", acc_id);
			    request.setAttribute("AccountOldBalance", oldBal);
			    request.setAttribute("AccountNewBalance", acc.getBalance());
			    rd.forward(request, response);
			}
			else if (request.getParameter("withdraw") != null){
				double amount = Double.parseDouble(request.getParameter("amount"));
				long acc_id = Long.parseLong(request.getParameter("AccountNum"));
				Account acc = accDao.retrieveAccountByAccId(acc_id);
				double oldBal = acc.getBalance();


				if (acc.getBalance() < amount){
//					out.write("<script>window.alert('You do not have enough balance to withdraw!!');</script>");
//					RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
//					rd.forward(request,response);					
					
				}
				else{
					acc.setBalance(acc.getBalance()-amount);
					accDao.updateAccount(acc);
					
					Receipt re = new Receipt(acc_id, acc.getName(), acc.getBalance(), oldBal, myDate ,"D");
					try {
						reDao.createReceipt(re);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					rd = getServletContext().getRequestDispatcher("/Receipt.jsp");
				    request.setAttribute("AccountNum", acc_id);
				    request.setAttribute("AccountOldBalance", oldBal);
				    request.setAttribute("AccountNewBalance", acc.getBalance());
				    rd.forward(request, response);
				}
			}
			
			else{
				out.write("<script>window.alert('Please select a checkbox');</script>");
			}
		}
	}
}
