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
		HttpSession session = request.getSession(true);

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String a = request.getParameter("h");

		UsersDTO dto = LoginService.login(userId, password);

		if (dto != null) {
			session.setAttribute("userId", userId);

			if ("cart".equals(a)) {

				RequestDispatcher rd = request.getRequestDispatcher("/CartController");
				rd.forward(request, response);

			} else if ("main".equals(a)) {

				RequestDispatcher rd = request.getRequestDispatcher("/mainController");
				rd.forward(request, response);

			} else if ("cartadd".equals(a)) {
				//商品IDと数量をセット
				request.setAttribute("itemId", request.getParameter("itemId"));
				request.setAttribute("amount", request.getParameter("amount"));

				RequestDispatcher rd = request.getRequestDispatcher("/CartAddController");
				rd.forward(request, response);
			} else if ("cartdelete".equals(a)) {
				request.setAttribute("itemId", 1);

				RequestDispatcher rd = request.getRequestDispatcher("/RemoveFromCartConfirmController");
				rd.forward(request, response);
			}

		} else {
			request.setAttribute("message", "会員IDまたはパスワードが違います。");

			request.setAttribute("h", a);
			//商品IDと数量をセット
			request.setAttribute("userId", request.getParameter("userId"));
			request.setAttribute("amount", request.getParameter("amount"));

			RequestDispatcher rd = request.getRequestDispatcher("/LoginController");
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session = request.getSession(true);

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String a = request.getParameter("h");

		UsersDTO dto = LoginService.login(userId, password);

		if (dto != null) {
			session.setAttribute("userId", userId);

			if ("cart".equals(a)) {

				response.sendRedirect("/axis_b/CartController");

			} else if ("main".equals(a)) {

				response.sendRedirect("/axis_b/mainController");

			} else if ("cartadd".equals(a)) {
				//商品IDと数量をセット
				request.setAttribute("itemId", request.getParameter("itemId"));
				request.setAttribute("amount", request.getParameter("amount"));

				RequestDispatcher rd = request.getRequestDispatcher("/CartAddController");
				rd.forward(request, response);
			} else if ("cartdelete".equals(a)) {
				request.setAttribute("itemId", 1);

				RequestDispatcher rd = request.getRequestDispatcher("/RemoveFromCartConfirmController");
				rd.forward(request, response);
			}

		} else {
			request.setAttribute("message", "会員IDまたはパスワードが違います。");

			request.setAttribute("h", a);
			//商品IDと数量をセット
			request.setAttribute("itemId", request.getParameter("itemId"));
			request.setAttribute("amount", request.getParameter("amount"));

			RequestDispatcher rd = request.getRequestDispatcher("/LoginController");
			rd.forward(request, response);

		}
	}

}
