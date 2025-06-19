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

		String userId = (String) request.getParameter("会員Id");
		String password = (String) request.getParameter("パスワード");
		String a = (String) request.getParameter("遷移元で設定するパラメータ");

		UsersDTO dto = LoginService.login(userId, password);

		if (dto != null) {
			if ("カート一覧画面に行くときのパラメータ".equals(a)) {
				session.setAttribute("会員Id", userId);

				RequestDispatcher rd = request.getRequestDispatcher("ショッピングカート一覧画面のサーブレットのパス");
				rd.forward(request, response);

			} else if ("メイン画面からのパラメータ".equals(a)) {
				session.setAttribute("会員Id", userId);

				RequestDispatcher rd = request.getRequestDispatcher("メイン画面のサーブレットのパス");
				rd.forward(request, response);

			} else if ("カート追加のときのパラメータ".equals(a)) {
				session.setAttribute("会員Id", userId);

				RequestDispatcher rd = request.getRequestDispatcher("カート追加のためのサーブレットのパス");
				rd.forward(request, response);
			}

		} else {
			request.setAttribute("message", "会員IDまたはパスワードが違います。");

			request.setAttribute("遷移元", a);

			RequestDispatcher rd = request.getRequestDispatcher("/axis_b/LoginController");
			rd.forward(request, response);

		}
	}

}
