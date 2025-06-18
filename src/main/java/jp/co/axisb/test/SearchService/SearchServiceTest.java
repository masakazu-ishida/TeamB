package jp.co.axisb.test.SearchService;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.service.SearchService;
import jp.co.axisb.util.ConnectionUtil;

public class SearchServiceTest {

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

		//キーワード＝NULL　カテゴリ＝すべて
		List<ItemsDTO> dto = SearchService.search("", 0);
		assertEquals(20, dto.size());

		//キーワード＝麦わら　カテゴリ＝すべて
		List<ItemsDTO> dto1 = SearchServiceTest.search("麦わら", 0);
		assertEquals(2, dto1.size());

		List<ItemsDTO> dto2 = dao1.findByItemName("", 1);
		assertEquals(11, dto2.size());

		List<ItemsDTO> dto3 = dao1.findByItemName("", 2);
		assertEquals(9, dto3.size());

	}

}
