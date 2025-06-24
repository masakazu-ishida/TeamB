package jp.co.axisb.service;

import java.sql.Connection;
import java.util.List;

import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class AdminPurchaseSearchService {

	public static List<PurchasesDTO> search(String purchaseId) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			PurchasesDAO dao = new PurchasesDAO(conn);

			return dao.findListByUserId(purchaseId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

}
