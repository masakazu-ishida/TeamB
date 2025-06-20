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
import jp.co.axisb.service.RemoveFromCartConfirmService;

/**
 * Servlet implementation class RemoveFromCartConfirmController
 */
@WebServlet("/RemoveFromCartConfirmController")
public class RemoveFromCartConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCartConfirmController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//
		//削除確認画面JSPへのパス
		String path = "/WEB-INF/removefromcartconfirm.jsp";

		//RemoveFromCartConfirmServiceをインスタンス化
		RemoveFromCartConfirmService rfccservice = new RemoveFromCartConfirmService();

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//カートJSPのユーザーIDを取得
		String userId = (String) session.getAttribute("userId");

		//カートJSPの商品IDを取得
		//int itemId = Integer.parseInt(request.getParameter ("itemId"));
		int itemId = 1;

		//RemoveFromCartConfirmServiceのメソッドを呼び出し、DTOに詰める
		ItemsInCartDTO dto = rfccservice.getItem(userId, itemId);

		/*ItemsInCartDTOに詰められているカート内商品が存在すれば、
		ユーザーID、商品ID、DTOをキーと値で登録する
		nullであれば、カート内一覧画面に遷移し、エラーメッセージを出力する*/
		if (dto != null) {
			session.setAttribute("userId", userId);
			session.setAttribute("dto", dto);

			//フォワード
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		} else {
			response.sendRedirect("/axis_b/CartController");
			request.setAttribute("error", "対象商品はすでにカートから削除されています");
		}

	}
}
