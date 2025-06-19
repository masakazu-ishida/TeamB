package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.PurchaseServise;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.service.PurchaseConfirmService;

/**
 * Servlet implementation class PurchaseConfirmController
 */
@WebServlet("/PurchaseConfirmController")
public class PurchaseConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseConfirmController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);


		//		String userid = "user";
		//		session.setAttribute("userId", userid);

		String userId = (String) session.getAttribute("userId");
		String path = "注文キャンセル確認画面JSPへのパス";

		request.setCharacterEncoding("UTF-8");
		//PurchaseServiseをインスタンス化
		PurchaseServise purchaseservice = new PurchaseServise();
		if (userId == null) {
			response.sendRedirect("/axis_b/LoginController");

		//purcahseCancelComfirmJSPの注文IDを取得
		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));

		//RemoveFromCartConfirmServiceのメソッドを呼び出し、注文DTOに詰める
		PurchasesDTO dto = purchaseservice.PurchasesCancelComfirmServise(purchaseId);

		/*PurchasesDTOに詰められているキャンセル一覧内に注文内容が存在すれば、
		注文ID、DTOをキーと値で登録する
		nullであれば、キャンセル一覧画面に遷移し、エラーメッセージを出力する*/
		if (dto != null) {
			request.setAttribute("purchaseId", purchaseId);
			request.setAttribute("dto", dto);
		} else {
			path = "注文一覧表示画面JSPのパス";
			request.setAttribute("error", "対象商品はすでに注文がキャンセルされています");
			List<ItemsInCartDTO> dtoList = PurchaseConfirmService.getCartItems(userId);
			int sum = PurchaseConfirmService.CartSum(userId);

			request.setAttribute("dtoList", dtoList);
			request.setAttribute("sum", sum);

			String path = "/WEB-INF/purchaseConfirm.jsp";

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		}

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
}
