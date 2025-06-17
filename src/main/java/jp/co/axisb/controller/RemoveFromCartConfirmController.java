package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String path = "";
		//セッションパラメータ
		HttpSession session = request.getSession(true);
		//カートJSPの商品IDを取得
		String parameter = request.getParameter(itemId);
		//カートJSPのユーザーIDを取得
		String attribute = (String) session.getAttribute("userId");
		//RemoveFromCartConfirmServiceをインスタンス化
		RemoveFromCartConfirmService rfccservice = new RemoveFromCartConfirmService();

		//RemoveFromCartConfirmServiceのメソッドを呼び出す

		//RemoveFromCartConfirmServiceからの戻り値をセットアトリビュートする

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
