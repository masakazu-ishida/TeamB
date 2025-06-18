package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class PurchaseServise {

	public PurchasesDTO PurchasesServise(int purchaseId) {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			//			DAOのインスタンス化
			PurchasesDAO dao = new PurchasesDAO(conn);
			//			findByIdメソッドに実引数を渡して返す
			return dao.findById(purchaseId);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
