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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = "/WEB-INF/removefromcartconfirm.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String path = "/WEB-INF/removefromcartconfirm.jsp";

		//RemoveFromCartConfirmServiceをインスタンス化
		RemoveFromCartConfirmService rfccservice = new RemoveFromCartConfirmService();

		//セッションオブジェクトの取得
		HttpSession session = request.getSession(true);

		//	String key = "userId";

		//カートJSPのユーザーIDを取得
		String userId = (String) session.getAttribute("userId");

		//カートJSPの商品IDを取得
		int itemId = Integer.parseInt(request.getParameter("itemId"));

		//RemoveFromCartConfirmServiceのメソッドを呼び出す
		ItemsInCartDTO dto = rfccservice.getItem(userId, itemId);

		if (dto != null) {
			session.setAttribute("userId", userId);
			session.setAttribute("itemId", itemId);
			session.setAttribute("dto", dto);
		} else {
			request.setAttribute("error", "対象商品はすでにカートから削除されています");
		}

		//		//RemoveFromCartConfirmServiceからの戻り値をセットアトリビュートする
		//		request.setAttribute(dto.getUserId(), dto.getItemId());

		//session.setAttribute(key, attribute);

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
}
