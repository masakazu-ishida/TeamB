package jp.co.ramen.servlet;

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
@WebServlet("/purchaseCommit")
public class PurchaseCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseCommitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//パラメータ取得
		try {
			HttpServletRequest session = request;
			String loginId = (String) session.getAttribute("loginUser");

			String payment = request.getParameter("payment");

			//カート情報の取得
			try {
				GetItemsInCartService getItemsInCartService = new GetItemsInCartService();
				List<ItemsInCartDTO> cartList = getItemsInCartService.execute(loginId);

				//購入確定サービスの実行
				try {

					try {

					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
					session.setAttribute("cartList", cartList);
					String path = "/WEB-INF/purchaseConfirm.jsp";
					RequestDispatcher rd = session.getRequestDispatcher(path);
					rd.forward(session, response);

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
