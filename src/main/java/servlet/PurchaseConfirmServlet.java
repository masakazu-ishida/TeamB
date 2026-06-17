package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.service.GetItemsInCartService;

/**
 * Servlet implementation class PurchaseConfirmServlet
 */
@WebServlet("/purchaseConfirm")
public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			//			HttpSession session = request.getSession();
			//			String loginId = (String) session.getAttribute("loginUser");

			try {
				GetItemsInCartService getItemsInCartService = new GetItemsInCartService();
				List<ItemsInCartDTO> cartList = getItemsInCartService.execute("user1");

				try {
					request.setAttribute("cartList", cartList);
					String path = "/WEB-INF/purchaseConfirm.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(path);
					rd.forward(request, response);

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
