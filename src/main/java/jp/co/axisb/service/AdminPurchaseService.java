package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class AdminPurchaseService {

	//	キャンセル確認画面のサービス処理
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

	//	 キャンセル完了画面のサービス処理
	public int purchasesCancelCommitServise(int purchaseId) {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			conn.setAutoCommit(false);
			try {
				//			DAOクラスのインスタンス化

				PurchasesDAO dao = new PurchasesDAO(conn);
				//			DTOクラスのインスタンス化
				//PurchasesDTO dto = new PurchasesDTO();

				PurchasesDTO dto = dao.findById(purchaseId);

				ItemsDAO dao1 = new ItemsDAO(conn);

				int count = 0; //update件数

				dto.setCancel(true);
				//			ItemsのstockとItems_in_cartのamountを取得して足す
				for (PurchasesDetailsDTO pd : dto.getPurchaseDetailDTO()) {
					ItemsDTO dto1 = dao1.findById(pd.getItemId());
					int stock = dto1.getStock();
					stock = stock + pd.getAmount();
					dto1.setStock(stock);

					count += 1;

				}
				conn.commit();
				//			DAO処理の更新処理を呼び出す
				return dao.update(dto);
				//				return count;
			} catch (Exception e) {
				// TODO: handle exception
				conn.rollback();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;

	}

}
