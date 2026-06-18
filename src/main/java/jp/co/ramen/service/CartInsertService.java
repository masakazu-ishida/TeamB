package jp.co.ramen.service;

import java.sql.SQLException;

import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dto.ItemsInCartDTO;

public class CartInsertService {

	public void addCart(String userId, int itemId, int order, java.sql.Date bookedDate) throws SQLException {

		ItemsInCartDAO cartDAO = new ItemsInCartDAO();

		// カート内に同じ商品があるかチェック
		ItemsInCartDTO cartDto = cartDAO.findByUserIdAndItemId(userId, itemId);

		if (cartDto != null) {
			// 既にカートにある場合は、元々の数量にorderを加算する
			int newAmount = cartDto.getAmount() + order;

			// カート内の数量と更新日を更新
			cartDAO.update(userId, itemId, newAmount, bookedDate);

		} else {
			// カートにない場合は、orderを数量として新規登録する
			ItemsInCartDTO insertDto = new ItemsInCartDTO();
			insertDto.setUser_id(userId);
			insertDto.setItem_id(itemId);
			insertDto.setAmount(order);

			cartDAO.insert(insertDto);
		}
	}

}
