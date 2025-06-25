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

		//doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		HttpSession session = request.getSession(true);
		//String userid = "user";
		//session.setAttribute("userId", userid);

		String userId = (String) session.getAttribute("userId");

		String payment = request.getParameter("payment");
		if (payment.equals("daikin")) {
			payment = "代金引換";
			request.setAttribute("payment", payment);
		}

		String destination = request.getParameter("distination");
		String address = request.getParameter("address");
		if (destination.equals("another")) {
			destination = "配送先を指定";
			request.setAttribute("setdestination", destination);
			request.setAttribute("address", address);

		} else {
			destination = "ご自宅";
			request.setAttribute("setdestination", destination);
		}

		List<ItemsInCartDTO> dtoList = PurchaseCommitService.getCartItems(userId);
		int sum = PurchaseCommitService.cartSum(userId);
		request.setAttribute("sum", sum);

		int errorMsg = PurchaseCommitService.commitCartPurchase(userId, destination);
		if (errorMsg == 0) {
			String error = "在庫が不足しています";
			request.setAttribute("error", error);
			request.setAttribute("dtoList", dtoList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/purchaseConfirm.jsp");
			rd.forward(request, response);

			return;
		}

		//List<ItemsInCartDTO> dtoList = PurchaseCommitService.getCartItems(userId);
		request.setAttribute("dtoList", dtoList);

		//int cart = PurchaseCommitService.commitCartPurchase(userId, destination);

		String path = "/WEB-INF/purchaseCommit.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
}
