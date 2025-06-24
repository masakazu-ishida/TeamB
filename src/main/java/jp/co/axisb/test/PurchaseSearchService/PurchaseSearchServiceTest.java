package jp.co.axisb.test.PurchaseSearchService;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.service.AdminPurchaseSearchService;
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
		//カート内に正しく値が詰め込まれているかを確認する。
		AdminPurchaseSearchService.search("user");
		List<PurchasesDTO> list = AdminPurchaseSearchService.search("user");
		assertNotNull(list);

<<<<<<< HEAD
		//カート内に存在しないユーザーIDを指定した場合
=======
		PurchasesDTO searchDTO = list.get(0);
		String strDate = "2025-06-17";
		java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date expected;
		try {
			expected = dateFormat.parse(strDate);
			//JUnitで妥当性の確認
			assertEquals(expected, searchDTO.getPurchasedDate());
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assertEquals(1, searchDTO.getPurchaseId());
		assertEquals("user", searchDTO.getPurchasedUser());
		//assertEquals("2025-06-17", searchDTO.getPurchasedDate());
		assertEquals("テスト", searchDTO.getDestination());
		assertEquals(false, searchDTO.isCancel());
		assertEquals(1, searchDTO.getPurchasesDetails().get(0).getPurchasesId());
		assertEquals(19, searchDTO.getPurchasesDetails().get(0).getItemId());
		assertEquals(4, searchDTO.getPurchasesDetails().get(0).getAmount());
		assertEquals(1, searchDTO.getPurchasesDetails().get(0).getPurchasesDetailsId());
		assertEquals(19, searchDTO.getItems().getItemId());
		assertEquals("鞄D", searchDTO.getItems().getName());
		assertEquals("東京鞄店", searchDTO.getItems().getManufacturer());
		assertEquals(2, searchDTO.getItems().getCategoryId());
		assertEquals("user", searchDTO.getUsers().getUserId());
		assertEquals("緑色", searchDTO.getItems().getColor());
		assertEquals(1980, searchDTO.getItems().getPrice());

>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
		list = AdminPurchaseSearchService.search("aaa");
		assertEquals(0, list.size());
	}

}
