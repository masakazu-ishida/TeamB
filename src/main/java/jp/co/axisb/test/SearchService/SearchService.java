package jp.co.axisb.test.SearchService;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.ConnectionUtil;

public class SearchService {

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
			e.printStackTrace();
		}

	}

	@Test
	public void testFindById() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsDAO dao = new ItemsDAO(conn);

			//キーワードとカテゴリを入力したとき
			List<ItemsDTO> dto = dao.findByItemName("麦わら帽子", 1);
			assertEquals(2, dto.size());//麦わら帽子と子ども用麦わら帽子の２つ

			//カテゴリのみ入力したとき
			List<ItemsDTO> dto2 = dao.findByItemName("", 1);
			assertEquals(3, dto2.size());

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
