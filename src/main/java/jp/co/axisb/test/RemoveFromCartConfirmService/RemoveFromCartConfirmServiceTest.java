package jp.co.axisb.test.RemoveFromCartConfirmService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.ConnectionUtil;

class RemoveFromCartConfirmServiceTest {

	//	@BeforeEach
	//	void init() {
	//		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
	//		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
	//			BaseDAO dao = new BaseDAO(conn);
	//			try {
	//				dao.insertBatch("sqlFiles/init.sql");
	//			} catch (Exception e) {
	//				throw e;
	//			}
	//		} catch (Exception e) {
	//			fail(e.getMessage());
	//		}
	//	}

	@Test
	void testgetItem() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			//カート内に正しく値が詰め込まれているかを確認する。
			ItemsInCartDTO itemsDTO = dao.findById("user", 1);
			assertNotNull(itemsDTO);

			//カート内に存在しないユーザーIDと商品IDを指定した場合、NULLを返すことを確認する
			itemsDTO = dao.findById("hfii", 8);
			assertNull(itemsDTO);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			fail(e.getMessage());
		}
	}

}
