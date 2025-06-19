package jp.co.axisb.service;

import java.sql.Connection;
import java.sql.Date;
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

	public static int Cartadd(String userId, int amount, Date bookedDate, int itemId) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			ItemsInCartDTO dto = new ItemsInCartDTO();

			dto.setUserId(userId);
			dto.setAmount(amount);
			dto.setBookedDate(bookedDate);
			dto.setItemId(itemId);

			return dao.insert(dto);

		} catch (Exception e) {
			return 0;
		}
	}

}
