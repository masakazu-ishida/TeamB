package jp.co.axisb.test.RemoveFromCartCommitService;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.ConnectionUtil;

class RemoveFromCartCommitServiceTest {

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

	//カート内商品が正しく削除されているかの確認
	@Test
	void testdeleteItem() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			//ItemsInCartDAOとDTOをインスタンス化
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO dto = new ItemsInCartDTO();

			//カート内に商品を入れる
			dto.setUserId("user");
			dto.setItemId(2);

			//該当データの削除
			int result = dao.delete(dto);

			//削除した件数が期待値と一緒か確認
			assertEquals(1, result);

			//データが正しく削除されているか確認
			ItemsInCartDTO search = dao.findById("user", 2);
			assertNull(search);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
