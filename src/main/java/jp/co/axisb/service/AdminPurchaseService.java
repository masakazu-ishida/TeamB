package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class AdminPurchaseService {

	public PurchasesDTO purchasesCancelComfirmServise(int purchaseId) {

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

	public int purchasesCancelCommitServise(int purchaseId) {
		
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
//			DAOクラスのインスタンス化
			PurchasesDAO dao = new PurchasesDAO(conn);
//			DTOクラスのインスタンス化
			PurchasesDTO dto = new PurchasesDTO();
			dto.setPurchaseId(purchaseId);
			return dao.
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;

		
	}

}
