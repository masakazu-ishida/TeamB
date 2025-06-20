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
import jp.co.axisb.service.PurchaseCommitService;

/**
 * Servlet implementation class purchaseCommitController
 */
@WebServlet("/purchaseCommitController")
public class purchaseCommitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public purchaseCommitController() {
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
		//		String userid = "user";
		//		session.setAttribute("userId", userid);

		String userId = (String) session.getAttribute("userId");
		String payment = request.getParameter("payment");
		String destination = request.getParameter("destination");
		String address = request.getParameter("address");
		if (destination == "another") {
			request.setAttribute("address", address);
		}

		if (userId == null) {

		} else {
			List<ItemsInCartDTO> dtoList = PurchaseCommitService.getCartItems(userId);
			int sum = PurchaseCommitService.cartSum(userId);
			int cart = PurchaseCommitService.commitCartPurchase(userId, destination);

			request.setAttribute("payment", payment);
			request.setAttribute("destination", destination);
			request.setAttribute("dtoList", dtoList);
			request.setAttribute("sum", sum);

			String path = "/WEB-INF/purchaseCommit.jsp";

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}

	}
}
