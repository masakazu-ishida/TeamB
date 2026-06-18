package jp.co.ramen.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.service.CartDeleteConfirmService;

/**
 * Servlet implementation class CartDeleteConfirmServlet
 */
@WebServlet("/cartDeleteConfirm")
public class CartDeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDeleteConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int itemId = Integer.parseInt(request.getParameter("itemId"));
		int itemId = 5;
		//HttpSession session = request.getSession();
		//String userId = (String) session.getAttribute("userId");
		String userId = "user1";
		CartDeleteConfirmService service = new CartDeleteConfirmService();
		ItemsInCartDTO targetItem = service.getCartItemForDelete(userId, itemId);

		request.setAttribute("deleteTarget", targetItem);
		request.getRequestDispatcher("/WEB-INF/removeFromCartConfirm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub・・
		doGet(request, response);
	}

}
