package com.mylearnings.msalwebsample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.aad.msal4j.IAuthenticationResult;

/**
 * Servlet implementation class aadCallbackServlet
 */
@WebServlet("/aadCallbackServlet")
public class aadCallbackServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public aadCallbackServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute(AuthHelper.PRINCIPAL_SESSION_NAME) != null)
		{
			IAuthenticationResult result = (IAuthenticationResult) request.getSession().getAttribute(AuthHelper.PRINCIPAL_SESSION_NAME);
			System.out.println("User " + result.account().username() +" is authenticated");
		}
		else
		{
			System.out.println("User is not authenticated");
		}
		return;
	}

}
