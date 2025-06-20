package jp.co.axisb.test.PurchasesServise;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.util.ConnectionUtil;

public class PurchasesServiseTest {

	@BeforeEach
	void init() {
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			BaseDAO dao = new BaseDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void PurchaseServiseTest() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			//注文確認一覧に正しく値が詰め込まれているかを確認する。
			PurchasesDTO dto = dao.findById(1);
			assertNotNull(dto);

			//注文確認一覧に存在しない注文IDを指定した場合、NULLを返すことを確認する
			dto = dao.findById(8);
			assertNull(dto);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			fail(e.getMessage());
		}
	}

}
