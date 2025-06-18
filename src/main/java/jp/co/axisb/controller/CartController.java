package jp.co.axisb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.service.CartService;

/**
 * Servlet implementation class CartController
 */
@WebServlet(name = "cart", urlPatterns = { "/cart" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
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
		doGet(request, response);

		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("usedId");

		if (userId == null) {
			response.sendRedirect("/WEB-INF/login.jsp");

		} else {
			CartService cs = new CartService();
			List<ItemsInCartDTO> cs = CartService.getCartItems(userId);
			List<ItemsInCartDTO> dtoList = cs.findById(userId);
			int sum = CartService.cartSum(userId);

			request.setAttribute("dtoList", dtoList);
			request.setAttribute("sum", sum);

		}

		String path = "/WEB-INF/cart.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
