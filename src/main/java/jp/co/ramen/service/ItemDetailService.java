package jp.co.ramen.service;

import java.sql.Connection;

import jp.co.ramen.dao.ItemsDAO;
import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.util.ConnectionUtil;

public class ItemDetailService {

	public ItemsDTO getItemDetail(int itemId) throws Exception {

		try (Connection con = ConnectionUtil.getConnection("java:comp/env/jdbc/ecsite")) {

			ItemsDAO Idao = new ItemsDAO(con);
			ItemsDTO Idto = Idao.findById(itemId);
			return Idto;
		}

	}

}
