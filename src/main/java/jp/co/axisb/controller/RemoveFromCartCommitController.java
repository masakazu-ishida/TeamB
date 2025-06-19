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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//	doGet(request, response);

		//削除完了画面JSPへのパス
		String path = "/WEB-INF/removefromcartcommit.jsp";

		//RemoveFromCartCommitServiceをインスタンス化
		RemoveFromCartCommitService commitservice = new RemoveFromCartCommitService();

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//confirmJSPのユーザーIDと商品IDを取得
		String userId = (String) session.getAttribute("userId");
		ItemsInCartDTO dto = (ItemsInCartDTO) session.getAttribute("dto");

		/*RemoveFromCartcommitServiceのメソッドを呼び出し、
		DELETEメソッドで該当商品を削除*/
		commitservice.deleteItem(userId, dto.getItemId());

		//sessionの商品情報のみポイする。
		session.removeAttribute("dto");

		request.setAttribute("dto", dto);

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
}
