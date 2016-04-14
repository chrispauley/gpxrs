package com.gpxdb.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public abstract class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/contact");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> errorsList = new ArrayList<String>();
		String email = request.getParameter("email");
		//EmailValidator emailValidator = new EmailValidator();
		
//		if(!emailValidator.validate(email)){
//			errorsList.add("Invalid email -->" + email);
//		}
		
		String name = request.getParameter("name");
		if (name == null || name.length() == 0) {
			errorsList.add("missing name");
		}
		
		String message = request.getParameter("message");
		if (message == null || message.length() == 0) {
			errorsList.add("missing message");
		}
		
//		String errors = new ObjectMapper().writeValueAsString(errorsList); 
		String errors = "";
		request.getSession().setAttribute("errorsJson", errors);
		request.getSession().setAttribute("errorList", errorsList);
		response.sendRedirect("/contact");
	}

}
