package jp.co.ramen.service;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.ramen.dto.ItemsInCartDTO;

/**
 * Servlet implementation class ItemsInCartReferenceServlet
 */
@WebServlet("/ItemsInCartReferenceServlet")
public class ItemsInCartReferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemsInCartReferenceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("loginUser");

		//if (userId == null) {
		//	response.sendRedirect(request.getContextPath() + "/login");
		//	return;
		//}

		GetItemsInCartService service = new GetItemsInCartService();
		try {
			// サービスからカートリストを取得
			List<ItemsInCartDTO> cartList = service.execute(userId);

			request.setAttribute("cartList", cartList);

			// カート一覧画面（JSP）へフォワード
			request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
