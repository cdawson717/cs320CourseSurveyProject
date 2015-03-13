package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.controller.AccountCreationController;

public class AccountCreationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Just forward to the accountCreation
		req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Institution nInstitution = new Institution (req.getParameter("institutionName"));
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String passwordCheck = req.getParameter("passwordConfirm");
			
		AccountCreationController controller = new AccountCreationController();
		controller.setInstitution(nInstitution);
			
			
		//make sure password is as intended then create account
		controller.createAccount(accountName, password, passwordCheck);
		
		req.setAttribute("create", controller);
		
		req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
		
		if (controller.done()){
			System.out.println("done");
			controller = null;
		}
	}
}
