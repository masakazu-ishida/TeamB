package jp.co.ramen.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.service.CartDeleteCommitService;

/**
 * Servlet implementation class CartDeleteCommitServlet
 */
@WebServlet("/cartDeleteCommit")
public class CartDeleteCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDeleteCommitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("loginUser");

		CartDeleteCommitService itemsDelete = new CartDeleteCommitService();
		System.out.println("★itemid" + itemId);
		System.out.println("★userId" + userId);
		ItemsInCartDTO Delete = itemsDelete.getCartItemDelete(userId, itemId);

		request.setAttribute("itemDelete", Delete);
		request.getRequestDispatcher("/WEB-INF/removeFromCartCommit.jsp").forward(request, response);
	}

}
