package jp.co.axisb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class SearchService {

	public static List<ItemsDTO> search(String keyword, int categoriesId) throws SQLException, ServletException {//スロー宣言でいい？

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			ItemsDAO dao = new ItemsDAO(conn);

			if (categoriesId == 0) {
				List<ItemsDTO> list1 = dao.findByItemName(keyword, 1);
				List<ItemsDTO> list2 = dao.findByItemName(keyword, 2);
				List<ItemsDTO> list = new ArrayList<>();

				list.addAll(list1);
				list.addAll(list2);

				return list;

			}

			else {
				List<ItemsDTO> list = dao.findByItemName(keyword, categoriesId);

				return list;

			}
		}
	}
}
