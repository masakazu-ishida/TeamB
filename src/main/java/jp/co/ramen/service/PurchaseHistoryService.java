package jp.co.ramen.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.ramen.dao.PurchasesDAO;
import jp.co.ramen.dto.PurchasesDTO;
import jp.co.ramen.util.ConnectionUtil;

public class PurchaseHistoryService {

	public List<PurchasesDTO> getHistory(String userId) {
		List<PurchasesDTO> list = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection("java:comp/env/jdbc/ecsite")) {

			PurchasesDAO Pdao = new PurchasesDAO(con);
			list = Pdao.findHistoryByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
