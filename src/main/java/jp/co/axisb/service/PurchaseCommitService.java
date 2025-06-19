package jp.co.axisb.service;

import java.sql.Connection;
import java.util.List;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dao.PurchasesDetailsDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class PurchaseCommitService {

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

	public static int insertCart(String userId, String payment, String distination) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			ItemsDAO itemsdao = new ItemsDAO(conn);
			ItemsInCartDAO itemsincartdao = new ItemsInCartDAO(conn);
			PurchasesDAO purchasesdao = new PurchasesDAO(conn);
			PurchasesDetailsDAO purchasesdetaildao = new PurchasesDetailsDAO(conn);

			//トランザクションオフ

			conn.setAutoCommit(false);

			PurchasesDTO purchasesdto = new PurchasesDTO();
			purchasesdto.set
			List<ItemsInCartDTO> cartitemsdto = itemsincartdao
					.findById(purchasesdto.getUsers().getUserId());

		} catch (Exception e) {

		}
	}

}
