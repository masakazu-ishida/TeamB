package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axisb.service.AdminPurchaseService;

/**
 * Servlet implementation class AdminPurchaseCancelCommitController
 */
@WebServlet(name = "adminPurchaseCancelCommitController", urlPatterns = { "/adminPurchaseCancelCommitController" })
public class AdminPurchaseCancelCommitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPurchaseCancelCommitController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		String path = "/WEB-INF/purchaseCancelCommit.jsp";

		request.setCharacterEncoding("UTF-8");

		AdminPurchaseService aps = new AdminPurchaseService();

		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));

		//PurchasesDTO dto = aps.purchasesCancelComfirmServise(purchaseId);
		//request.setAttribute("dto", dto);

		//int result = aps.purchasesCancelCommitServise(purchaseId);

		//		request.setAttribute("result", result);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
