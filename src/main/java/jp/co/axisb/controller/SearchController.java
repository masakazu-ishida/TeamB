package jp.co.axisb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.service.SearchService;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);]

		String path = "/main/WEB-INF/search.jsp";

		//リクエストパラメータを取り出す
		String keyword = request.getParameter("keyword");
		String categoriesId = request.getParameter("categoriesId");

		//サーチサービスのサーチメソッドを呼び出す
		//引数には検索キーワードとカテゴリIDを渡す
		SearchService.search(keyword, categoriesId);

		//サービスからの戻り値をセットアトリビュートする
		List<ItemsDTO> s = SearchService.search(keyword, categoriesId);
		なんでDTOのリスト？
		

		request.setAttribute("value", s);

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
