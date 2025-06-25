package jp.co.axisb.test.PurchasesServise;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.AdminPurchaseService;
import jp.co.axisb.util.CommonConstants;
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

	//	キャンセル確認画面テスト
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

	//キャンセル完了画面
	//	@Test
	//	void purchaseCancelCommittest() {
	//
	//		ConnectionUtil.mode = MODE.TEST;
	//		//		AdminPurchaseServiceのインスタンス化
	//		AdminPurchaseService aps = new AdminPurchaseService();
	//
	//		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
	//
	//			ItemsDAO dao = new ItemsDAO(conn);
	//			ItemsDTO item = dao.findById(1);
	//
	//			//		在庫数を確認するテストをしたい
	//			assertEquals(12, item.getStock());
	//
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		//purchaseDTOのCancelをfalseからtrueにする（trueにするとnullが戻り値として返される）
	//		aps.purchasesCancelCommitServise(1);
	//		//findByIdと同じ処理をしている
	//		PurchasesDTO dto = aps.purchasesCancelComfirmServise(1);
	//
	//		assertNull(dto);
	//
	//	}

	@Test
	void purchaseCancelCommittest() {

		ConnectionUtil.mode = MODE.TEST;
		//		AdminPurchaseServiceのインスタンス化
		AdminPurchaseService aps = new AdminPurchaseService();

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			ItemsDAO dao = new ItemsDAO(conn);
			ItemsDTO item = dao.findById(19);
			//在庫数を確認するテストをしたい
			assertEquals(2, item.getStock());

			//更新した件数の比較
			int count = aps.purchasesCancelCommitServise(1);
			assertEquals(1, count);

			item = dao.findById(19);
			assertEquals(3, item.getStock());

			//purchaseIdがないとき、dtoにはnullが入る
			PurchasesDTO dto = aps.purchasesCancelComfirmServise(1);
			assertNull(dto);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
