package jp.co.axisb.test.PurchasesServise;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.AdminPurchaseService;
import jp.co.axisb.util.ConnectionUtil;
import jp.co.axisb.util.ConnectionUtil.MODE;

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
	void purchaseCancelConfirmtest() {
		ConnectionUtil.mode = MODE.TEST;
		AdminPurchaseService aps = new AdminPurchaseService();

		PurchasesDTO dto = aps.purchasesCancelComfirmServise(1);
		assertEquals("user", dto.getUsers().getUserId());
		assertEquals(1, dto.getPurchaseId());
		assertEquals(java.sql.Date.valueOf("2025-06-17"), dto.getPurchasedDate());
		assertEquals("テスト", dto.getDestination());
		assertEquals(false, dto.isCancel());

	}

	@Test
	void purchaseCancelCommittest() {

		ConnectionUtil.mode = MODE.TEST;

		AdminPurchaseService aps = new AdminPurchaseService();

		aps.purchasesCancelCommitServise(1);

		PurchasesDTO dto = aps.purchasesCancelComfirmServise(1);

		//		serviceに在庫を戻す作業がいる
		assertNull(dto);

	}

}
