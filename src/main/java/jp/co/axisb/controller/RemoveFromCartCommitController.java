package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.service.RemoveFromCartCommitService;

/**
 * Servlet implementation class RemoveFromCartCommitController
 */
@WebServlet("/RemoveFromCartCommitController")
public class RemoveFromCartCommitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCartCommitController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//	response.getWriter().append("Served at: ").append(request.getContextPath());

		//削除確認画面JSPへのパス
		String path = "/WEB-INF/removefromcartcommit.jsp";

		//RemoveFromCartConfirmServiceをインスタンス化
		RemoveFromCartCommitService commitservice = new RemoveFromCartCommitService();

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//confirmJSPのユーザーIDを取得
		//String "ユーザーIDのセッション、キー名" = (String) session.getAttribute("userId");
		String userId = (String) session.getAttribute("userId");
		//confirmJSPの商品IDを取得
		//int "カート内JSPのパラメータ、itemId" = Integer.parseInt(request.getParameter("itemId"));
		int itemId = Integer.parseInt(request.getParameter("itemId"));

		//RemoveFromCartcommitServiceのメソッドを呼び出し、DTOに詰める
		ItemsInCartDTO dto = commitservice.deleteItem(userId, itemId);

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
