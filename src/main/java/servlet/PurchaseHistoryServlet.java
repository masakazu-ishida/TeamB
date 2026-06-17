package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.ramen.dto.PurchasesDTO;
import jp.co.ramen.service.PurchaseHistoryService;

/**
 * Servlet implementation class PurchaseHistoryServlet
 */
@WebServlet("/purchaseHistory")
public class PurchaseHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		session.setAttribute("loginUser", "user1");//後で消す

		String userId = (String) session.getAttribute("loginUser");

		if (userId == null) {
			response.sendRedirect("noSession.html");//ログイン画面できたらmainから変更
			return;
		}
		try {
			PurchaseHistoryService hService = new PurchaseHistoryService();
			List<PurchasesDTO> list = hService.getHistory(userId);
			request.setAttribute("list", list);
			String value = "/WEB-INF/purchaseHistory.jsp";
			request.getRequestDispatcher(value).forward(request, response);
		} catch (Exception e) {
			System.out.println("kokodeerror");
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
