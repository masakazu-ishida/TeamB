package jp.co.axisb.test.PurchaseSearchService;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.PurchaseSearchService;
import jp.co.axisb.util.ConnectionUtil;

class PurchaseSearchServiceTest {

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
	void searchtest() {
		PurchaseSearchService.search("user");
		List<PurchasesDTO> list = PurchaseSearchService.search("user");
		assertNotNull(list);

		list = PurchaseSearchService.search("aaa");
		assertEquals(0, list.size());
	}

}
