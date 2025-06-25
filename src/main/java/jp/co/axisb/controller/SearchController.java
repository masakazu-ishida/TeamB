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

		//		String path = "/WEB-INF/search.jsp";
		//		RequestDispatcher rd = request.getRequestDispatcher(path);
		//		rd.forward(request, response);
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);]

		//path
		String path = "/WEB-INF/search.jsp";

		//リクエストパラメータを取り出す
		String keyword = request.getParameter("keyword");

		int categoriesId = Integer.parseInt(request.getParameter("categoriesId"));

		//サーチサービスのサーチメソッドを呼び出す
		//引数には検索キーワードとカテゴリIDを渡す
		//サービスからの戻り値をセットアトリビュートする
		List<ItemsDTO> list = SearchService.search(keyword, categoriesId);

		if (categoriesId == 0) {
			String japanesecategories = "すべて";
			request.setAttribute("japanesecategories", japanesecategories);
			request.setAttribute("categoriesId", categoriesId);

			request.setAttribute("keyword", keyword);
			request.setAttribute("list", list);

			//フォワード
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} else if (categoriesId == 1) {
			String japanesecategories = "帽子";
			request.setAttribute("japanesecategories", japanesecategories);
			request.setAttribute("categoriesId", categoriesId);

			request.setAttribute("keyword", keyword);
			request.setAttribute("list", list);

			//フォワード
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} else {
			String japanesecategories = "鞄";
			request.setAttribute("japanesecategories", japanesecategories);
			request.setAttribute("categoriesId", categoriesId);

			request.setAttribute("keyword", keyword);
			request.setAttribute("list", list);
			//フォワード
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}

	}

}
