package jp.co.axisb.service;

import java.sql.Connection;
import java.util.List;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class CartService {
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

	public static int CartSum(String userId) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			List<ItemsInCartDTO> dto = null;
			int sum = 0;
			dto = dao.findById(userId);

			return sum;

		} catch (Exception e) {
			return 0;

		}

	}

}
