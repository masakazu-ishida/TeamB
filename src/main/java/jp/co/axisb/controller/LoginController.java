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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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

		String userId = (String) request.getParameter("userId");
		String password = (String) request.getParameter("password");
		String a = (String) request.getParameter("遷移元で設定するパラメータ");

		UsersDTO dto = LoginService.login(userId, password);

		HttpSession session = request.getSession(true);

		if (dto != null) {
			if ("カート追加時のパラメータ".equals(a)) {
				session.setAttribute("userId", userId);

				RequestDispatcher rd = request.getRequestDispatcher("ショッピングカート一覧画面のサーブレットのパス");
				rd.forward(request, response);
			} else if ("メイン画面からのパラメータ".equals(a)) {
				session.setAttribute("userId", userId);

				RequestDispatcher rd = request.getRequestDispatcher("メイン画面のコントロールのパス");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("message", "会員IDまたはパスワードが違います。");

			request.setAttribute("遷移元で設定するパラメータ", a);
			RequestDispatcher rd = request.getRequestDispatcher("ログイン画面のコントロールのパス");
			rd.forward(request, response);

		}

	}

}
