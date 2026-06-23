package jp.co.ramen.service;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;

import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.util.ConnectionUtil;

public class CartDeleteCommitService {
	public ItemsInCartDTO getCartItemDelete(String userId, int itemId) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		ItemsInCartDTO deleteItem = null;
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			try {
				conn.setAutoCommit(false);
				ItemsInCartDAO dao = new ItemsInCartDAO(conn);

				deleteItem = dao.findById(userId, itemId);

				dao.delete(userId, itemId);
				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			}

		} catch (Exception e) {

			//発生した例外は全部独自例外に変換してスロー
			//コントローラクラスでは常に独自例外のみキャッチさせる事で、コントローラーの記述を単純化させる
			throw new ServletException(e.getCause());
		}

		return deleteItem;
	}
}