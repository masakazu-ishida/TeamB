package jp.co.axisb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.AdminPurchaseService;

/**
 * Servlet implementation class PurchaseConfirmController
 */
@WebServlet("/adminPurchasesCancelController")
public class AdminPurchasesCancelConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPurchasesCancelConfirmController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = "/WEB-INF/purchaseCancelComfirm.jsp";

		request.setCharacterEncoding("UTF-8");
		//PurchaseServiseをインスタンス化
		AdminPurchaseService purchaseservice = new AdminPurchaseService();

		//purcahseCancelComfirmJSPの注文IDを取得
		HttpSession session = request.getSession(true);
		int purchaseid = 1;
		session.setAttribute("purchaseId", purchaseid);

		//String purchaseId = (String) session.getAttribute("purchaseId");

		//RemoveFromCartConfirmServiceのメソッドを呼び出し、注文DTOに詰める
		PurchasesDTO dto = purchaseservice.purchasesCancelComfirmServise(purchaseid);

		/*PurchasesDTOに詰められているキャンセル一覧内に注文内容が存在すれば、
		注文ID、DTOをキーと値で登録する
		nullであれば、キャンセル一覧画面に遷移し、エラーメッセージを出力する*/
		if (dto != null) {
			request.setAttribute("dto", dto);
		} else {
			path = "/WEB-INF/adminPurchaseSearch.jsp";
			request.setAttribute("error", "対象商品はすでに注文がキャンセルされています");

		}

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
	}
}
