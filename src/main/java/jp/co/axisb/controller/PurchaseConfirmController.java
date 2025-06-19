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
 * Servlet implementation class PurchaseConfirmController
 */
@WebServlet("/PurchaseConfirmController")
public class PurchaseConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseConfirmController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		HttpSession session = request.getSession(true);
		String userid = "user";
		session.setAttribute("userId", userid);

		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect("/axis_b/LoginController");

		} else {
			List<ItemsInCartDTO> dtoList = CartService.getCartItems(userId);
			int sum = CartService.CartSum(userId);

			request.setAttribute("dtoList", dtoList);
			request.setAttribute("sum", sum);

			String path = "/WEB-INF/purchaseConfirm.jsp";

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}
}
