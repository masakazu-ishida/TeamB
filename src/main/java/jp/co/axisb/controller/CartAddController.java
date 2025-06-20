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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession(true);

		String userId = (String) session.getAttribute("会員ID");
		int itemId = (int) request.getAttribute("商品ID");
		Date bookedDate = (Date) request.getAttribute("購入日");
		int amount = (int) request.getAttribute("数量");

		//ItemsDTO dto = (ItemsDTO) request.getAttribute("dto");

		if (userId == null) {
			response.sendRedirect("/axis_b/LoginController");

		} else {

			CartService.Cartadd(userId, amount, bookedDate, itemId);

			session.setAttribute("userId", userId);
			session.setAttribute("itemId", itemId);

			RequestDispatcher rd = request.getRequestDispatcher("/axis_b/CartController");
			rd.forward(request, response);

		}
	}

}
