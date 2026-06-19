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
import jp.co.ramen.service.PurchaseCommitService;

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
			//			セッション処理スルー用コメントアウト
			//			HttpServletRequest session = request;
			//			String loginId = (String) session.getAttribute("loginUser");
			//			String payment = request.getParameter("payment");

			String loginId = "user1";
			String payment = "代金引換";

			//カート情報の取得
			try {
				GetItemsInCartService getItemsInCartService = new GetItemsInCartService();
				List<ItemsInCartDTO> cartList = getItemsInCartService.execute(loginId);

				//購入確定サービスの実行

				try {
					PurchaseCommitService purchaseCommitService = new PurchaseCommitService();
					purchaseCommitService.execute(cartList, loginId, payment);

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				request.setAttribute("cartList", cartList);
				String path = "/WEB-INF/purchaseCommit.jsp";
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
