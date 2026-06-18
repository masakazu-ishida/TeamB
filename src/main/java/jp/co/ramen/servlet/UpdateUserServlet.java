package jp.co.ramen.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.ramen.dto.UserDTO;
import jp.co.ramen.service.UpdateUserService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");
		//		if (userId == null) {
		//			userId = "user1";
		//		}
		//String userId = "user1";

		// 2. ユーザー情報を取得
		UpdateUserService service = new UpdateUserService();
		UserDTO user = null;
		try {
			user = service.getUserInformation(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 3. 画面出力項目の設定
		request.setAttribute("user", user);

		// 4. 会員情報変更画面へフォワード
		request.getRequestDispatcher("/WEB-INF/updateUser.jsp").forward(request, response);
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
