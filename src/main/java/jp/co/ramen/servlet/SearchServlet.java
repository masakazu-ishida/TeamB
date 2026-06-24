package jp.co.ramen.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.service.SearchServletService;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		int categoryId = Integer.parseInt(request.getParameter("category"));
		String name = request.getParameter("keyword");

		SearchServletService itemSerch = new SearchServletService();

		List<ItemsDTO> itemsList = itemSerch.execute(categoryId, name);

		String categoryName = "";
		if (categoryId == 1) {
			categoryName = "すべて";
		} else if (categoryId == 2) {
			categoryName = "帽子";
		} else {
			categoryName = "鞄";
		}

		request.setAttribute("category", categoryName);
		request.setAttribute("keyword", name);
		request.setAttribute("itemsList", itemsList);

		String path = "/WEB-INF/searchResult.jsp";
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
