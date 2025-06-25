package jp.co.axisb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dao.PurchasesDetailsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class PurchaseCommitService {
	public static List<ItemsInCartDTO> getCartItems(String userId) {
		List<ItemsInCartDTO> dto = null;

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			return dao.findById(userId);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static int cartSum(String userId) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			int sum = 0;
			List<ItemsInCartDTO> dto = dao.findById(userId);

			for (ItemsInCartDTO itemscart : dto) {
				int price = itemscart.getItems().getPrice();
				int amount = itemscart.getAmount();
				int total = price * amount;
				sum += total;
			}
			return sum;

		} catch (Exception e) {
			return 0;

		}

	}

	public static int commitCartPurchase(String userId, String distination) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			//トランザクションオフ
			conn.setAutoCommit(false);
			try {
				ItemsDAO itemsdao = new ItemsDAO(conn);
				ItemsInCartDAO itemsincartdao = new ItemsInCartDAO(conn);
				PurchasesDAO purchasesdao = new PurchasesDAO(conn);
				PurchasesDetailsDAO purchasesdetaildao = new PurchasesDetailsDAO(conn);

				int commitcount = 0;

				//カート内の情報取得
				List<ItemsInCartDTO> itemsincartdto = itemsincartdao.findById(userId);

				//注文テーブルに登録
				PurchasesDTO purchasesdto = new PurchasesDTO();
				purchasesdto.setPurchasedUser(userId);
				purchasesdto.setPurchasedDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
				purchasesdto.setDestination(distination);
				purchasesdto.setCancel(false);
				purchasesdao.insert(purchasesdto);
				int purchaseId = purchasesdto.getPurchaseId();//自動で作られた番号

				//注文明細に登録
				for (ItemsInCartDTO incart : itemsincartdto) {
					PurchasesDetailsDTO detailsdto = new PurchasesDetailsDTO();
					detailsdto.setPurchasesId(purchaseId);
					detailsdto.setItemId(incart.getItemId());
					detailsdto.setAmount(incart.getAmount());
					purchasesdetaildao.insert(detailsdto);

					//在庫の更新
					ItemsDTO itemsdto = new ItemsDTO();
					itemsdto.setItemId(incart.getItemId());
					itemsdto.setStock(incart.getAmount());
					itemsdao.update(itemsdto);

					//カート削除
					itemsincartdao.delete(incart);

					commitcount++;
				}
				conn.commit();//コミット
				return commitcount;

			} catch (SQLException e) {
				e.printStackTrace();

				conn.rollback(); // ロールバック
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}

	}

}
