package jp.co.ramen.service;

import java.sql.Connection;
import java.util.List;

import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.util.ConnectionUtil;

public class ItemsInCartReferenceService {

	public List<ItemsInCartDTO> getCartList(String userId) throws Exception {

		try (Connection con = ConnectionUtil.getConnection("java:comp/env/jdbc/ecsite")) {

			ItemsInCartDAO IICdao = new ItemsInCartDAO(con);
			return IICdao.findAll(userId);

		}
	}
}
