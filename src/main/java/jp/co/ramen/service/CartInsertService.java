package jp.co.ramen.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;

import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.util.ConnectionUtil;

public class CartInsertService {

	public void addCart(String userId, int itemId, int order) throws SQLException, Exception {

		//ItemsInCartDAO cartDAO = new ItemsInCartDAO();
		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			ItemsInCartDAO cartDAO = new ItemsInCartDAO(conn);

			// カート内に同じ商品があるかチェック
			ItemsInCartDTO cartDto = cartDAO.findByUserIdAndItemId(userId, itemId);
			//日付	
			LocalDate bookedDate = LocalDate.now();

			conn.setAutoCommit(false);
			try {
				if (cartDto != null) {
					// 既にカートにある場合は、元々の数量にorderを加算
					int newAmount = cartDto.getAmount() + order;

					// カート内の数量と更新日を更新
					cartDAO.update(userId, itemId, newAmount, bookedDate);

				} else {
					// カートにない場合は、orderを数量として新規登録
					ItemsInCartDTO insertDto = new ItemsInCartDTO();
					insertDto.setUser_id(userId);
					insertDto.setItem_id(itemId);
					insertDto.setAmount(order);

					insertDto.setBooked_date(bookedDate);

					cartDAO.insert(insertDto);
				}

				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}

		} catch (Exception e) {

			throw new ServletException(e.getCause());

		}

	}
}
