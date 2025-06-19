package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axisb.dto.UsersDTO;
import jp.co.axisb.service.LoginService;

/**
 * Servlet implementation class LoginConfirmController
 */
@WebServlet("/LoginConfirmController")
public class LoginConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginConfirmController() {
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

		HttpSession session = request.getSession(true);

		String userId = request.getParameter("会員Id");
		String password = request.getParameter("パスワード");
		String a = request.getParameter("遷移元で設定するパラメータ");

		UsersDTO dto = LoginService.login(userId, password);

		if (dto != null) {
			session.setAttribute("会員Id", userId);

			if ("カート一覧画面に行くときのパラメータ".equals(a)) {

				response.sendRedirect("ショッピングカート一覧画面のサーブレットのパス");

			} else if ("メイン画面からのパラメータ".equals(a)) {

				response.sendRedirect("メイン画面のサーブレットのパス");

			} else if ("カート追加のときのパラメータ".equals(a)) {
				//商品IDと数量をセット
				request.setAttribute("商品Id", request.getParameter("商品Id"));
				request.setAttribute("数量", request.getParameter("数量"));

				RequestDispatcher rd = request.getRequestDispatcher("カート追加のためのサーブレットのパス");
				rd.forward(request, response);
			}

		} else {
			request.setAttribute("message", "会員IDまたはパスワードが違います。");

			request.setAttribute("遷移元", a);
			//商品IDと数量をセット
			request.setAttribute("商品Id", request.getParameter("商品Id"));
			request.setAttribute("数量", request.getParameter("数量"));

			response.sendRedirect("/axis_b/LoginController");

		}
	}

}
