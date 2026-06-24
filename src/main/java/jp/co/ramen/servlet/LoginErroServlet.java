package jp.co.ramen.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginShowServlet
 */
@WebServlet("/LoginErroServlet")
public class LoginErroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginErroServlet() {
		super();
		// TODO Auto- generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = "/WEB-INF/login.jsp";
		request.setAttribute("itemId", request.getParameter("itemId"));
		request.setAttribute("order", request.getParameter("order"));
		request.setAttribute("requestFrom", request.getParameter("requestFrom"));

		request.getRequestDispatcher(path).forward(request, response);

	}

	/**
	 * @see Htt pServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
