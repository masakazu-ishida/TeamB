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
@WebServlet("/AdminLoginConfirmController")
public class AdminLoginConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginConfirmController() {
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

		String adminId = (String) request.getParameter("id");
		String password = (String) request.getParameter("password");

		AdministratorsDTO dto = AdminMainService.login(adminId, password);

		if (dto != null) {
			session.setAttribute("管理Id", adminId);

			response.sendRedirect("/axis_b/AdminMainController");
		} else {
			request.setAttribute("message", "管理者IDまたはパスワードが違います。");

			RequestDispatcher rd = request.getRequestDispatcher("/axis_b/AdminLoginController");
			rd.forward(request, response);
		}
	}

}
