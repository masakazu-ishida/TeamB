package jp.co.ramen.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.ramen.service.CartInsertService;

/**
 * Servlet implementation class CartInsertServlet
 */
@WebServlet("/CartInsertServlet")
public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 1. 商品IDと指定数量を取得
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int order = Integer.parseInt(request.getParameter("order"));

		// 2. セッションチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			request.setAttribute("requestFrom", "/CartInsertServlet");
			request.setAttribute("itemId", itemId);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		String userId = (String) session.getAttribute("userId");
		// 3. カート追加処理
		CartInsertService service = new CartInsertService();
		try {
			service.addCart(userId, itemId, order);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		// 4. ショッピングカート一覧表示処理へ
		//response.sendRedirect(request.getContextPath() + "/ItemsInCartReference");
		request.getRequestDispatcher("/ItemsInCartReference").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
