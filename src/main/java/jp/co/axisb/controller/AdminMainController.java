package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axisb.dto.AdministratorsDTO;
import jp.co.axisb.service.AdminMainService;

/**
 * Servlet implementation class AdminMainController
 */
@WebServlet("/AdminMainController")
public class AdminMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMainController() {
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

		String adminId = (String) request.getParameter("管理Id");
		String password = (String) request.getParameter("パスワード");

		AdministratorsDTO dto = AdminMainService.login(adminId, password);

		if (dto != null) {
			session.setAttribute("管理Id", adminId);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adminMain.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("message", "管理者IDまたはパスワードが違います。");

			RequestDispatcher rd = request.getRequestDispatcher("/axis_b/LoginController");
			rd.forward(request, response);
		}
	}

}
