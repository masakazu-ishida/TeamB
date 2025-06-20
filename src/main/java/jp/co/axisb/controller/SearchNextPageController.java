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
 * Servlet implementation class Searchcontroller2
 */
@WebServlet("/SearchNextPageController")
public class SearchNextPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchNextPageController() {
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

		//パス
		String path = "/WEB-INF/search.jsp";

		//リクエストパラメータを取り出す
		String keyword = request.getParameter("keyword");

		String next = request.getParameter("next");

		int page = Integer.parseInt(request.getParameter("page"));

		int categoriesId = Integer.parseInt(request.getParameter("categoriesId"));

		if (next == "0") { //nextがおされたとき

			page = page + 1;

		}

		//サーチサービスのサーチメソッドを呼び出す
		//引数には検索キーワードとカテゴリIDを渡す
		//サービスからの戻り値をセットアトリビュートする
		List<ItemsDTO> list = SearchService.search2(keyword, categoriesId, page);

		request.setAttribute("value1", categoriesId);
		request.setAttribute("value2", keyword);
		request.setAttribute("value3", list);

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
		doGet(request, response);
	}

}
