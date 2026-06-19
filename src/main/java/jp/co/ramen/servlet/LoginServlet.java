package jp.co.ramen.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.ramen.service.LoginService;

/**
 * Servlet implementation class LoginShowServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto- generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		JSP整備前のためコメントアウト
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String requestFrom = request.getParameter("requestFrom");

		//		手入力での動作確認1(DB参照失敗 fromメイン画面）
		//		String id = "user0";
		//		String password = "userpass1";
		//		String requestFrom = null;

		//		手入力での動作確認2(ログイン成功 from /ItemsInCartReferenceServlet）
		//		String id = "user1";
		//		String password = "userpass1";
		//		String requestFrom = "/ItemsInCartReferenceServlet";

		LoginService loginService = new LoginService();
		int authenticate = loginService.execute(id, password);

		String path = null;

		if (authenticate == 0) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loginUser", id);

			if (requestFrom == null) {
				path = "/WEB-INF/main.jsp";
				request.setAttribute("itemId", request.getParameter("itemId"));
				request.setAttribute("order", request.getParameter("order"));
			} else {
				path = requestFrom;
			}

		} else {
			request.setAttribute("errorMsg", "ログインに失敗しました");
			request.setAttribute("itemId", request.getParameter("itemId"));
			request.setAttribute("order", request.getParameter("order"));
			path = "/WEB-INF/login.jsp";
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see Htt pServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
