package jp.co.ramen.service;

import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.ramen.dao.ItemsDAO;
import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.util.ConnectionUtil;

public class SearchServletService {
	public List<ItemsDTO> execute(int categoryId, String name) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection con = ConnectionUtil.getConnection(jndiName)) {

			ItemsDAO dao = new ItemsDAO(con);

			List<ItemsDTO> itemsList = dao.findByCondision(categoryId, name);

			return itemsList;

		} catch (Exception e) {
			throw new ServletException(e.getCause());
		}
	}
}
