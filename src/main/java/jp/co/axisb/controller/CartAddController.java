package jp.co.axisb.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axisb.service.CartService;

/**
 * Servlet implementation class CartAddController
 */
@WebServlet("/CartAddController")
public class CartAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartAddController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(true);

		//String userid = "user";
		//session.setAttribute("userId", userid);

		String userId = (String) session.getAttribute("userId");
		int itemId = 0;
		if (request.getParameter("itemId") != null) {
			itemId = Integer.parseInt(request.getParameter("itemId"));
		} else {
			itemId = (int) request.getAttribute("itemId");
		}

		Date bookedDate = java.sql.Date.valueOf(java.time.LocalDate.now());
		int amount = 0;
		if (request.getParameter("amount") != null) {
			amount = Integer.parseInt(request.getParameter("amount"));
		} else {
			itemId = (int) request.getAttribute("amount");
		}

		//ItemsDTO dto = (ItemsDTO) request.getAttribute("dto");

		if (userId == null) {
			request.setAttribute("h", "cartadd");
			request.setAttribute("itemId", itemId);
			request.setAttribute("amount", amount);
			RequestDispatcher rd = request.getRequestDispatcher("/LoginController");
			rd.forward(request, response);

		} else {

			CartService.Cartadd(userId, amount, bookedDate, itemId);

			session.setAttribute("userId", userId);
			session.setAttribute("itemId", itemId);

			RequestDispatcher rd = request.getRequestDispatcher("/CartController");
			rd.forward(request, response);

		}
	}

}
