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

import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.PurchaseSearchService;

/**
 * Servlet implementation class PurchaseSearchService
 */
@WebServlet("/PurchaseSearchService")
public class PurchaseSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseSearchController() {
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

		//注文一覧表示画面へのパス
		String path = "/WEB-INF/adminPurchaseSearch.jsp";

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//mainJSPの管理者IDを取得
		String userid = "user";
		session.setAttribute("userId", userid);
		String userId = (String) session.getAttribute("userId");

		//mainJSPの注文IDを取得
		String purchaseid = "1";
		session.setAttribute("purchaseId", purchaseid);
		String purchaseId = (String) session.getAttribute("purchaseId");

		List<PurchasesDTO> list = PurchaseSearchService.search(purchaseId);

		//ユーザーID、注文ID、リストの情報をそれぞれセット
		session.setAttribute("userId", userId);
		session.setAttribute("purchaseId", purchaseId);
		request.setAttribute("list", list);

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
