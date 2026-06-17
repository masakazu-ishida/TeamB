package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.PurchaseDetailsDTO;
import jp.co.ramen.dto.PurchasesDTO;
import jp.co.ramen.util.TestBase;

/**
 * サービスの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 * 単体テストの観点
 * ・網羅率：ブランチカバレッジ
 * ・検索系：DTOのフィールドに値が期待どおり格納されるか？Listの場合期待する件数が格納されるか？
 * ・更新系：DBの状態が期待どおりか？
 * 
 * サービスの場合、処理内容によっては単純に検索メソッドを読んで戻り値を返すだけなど）単体テストと
 * ほぼ同じになるケースもあるが、それでも実施する。
 * DAOテストのテストコードの流用ができるはず。
 * 
 */
class PurchaseHistoryServiceTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * 購入履歴ありの場合のテスト
	 */
	@Test
	void testGetHistory1() {

		try {
			String userId = "user1";
			PurchaseHistoryService hService = new PurchaseHistoryService();
			List<PurchasesDTO> result = hService.getHistory(userId);

			assertNotNull(result);
			assertEquals(1, result.size());

			PurchasesDTO purchase = result.get(0);
			assertEquals(1, purchase.getPurchase_id());

			assertEquals(Date.valueOf("2026-06-17"), purchase.getPurchased_date());

			assertNull(purchase.getDestination());

			List<PurchaseDetailsDTO> detailsList = purchase.getPurchaseDetailsDto();
			assertNotNull(detailsList);
			assertEquals(3, detailsList.size());

			PurchaseDetailsDTO detail1 = detailsList.get(0);
			assertEquals(1, detail1.getPurchase_detail_id());
			assertEquals(4, detail1.getAmount());
			assertEquals("ハンチング帽", detail1.getIdto().getName());
			assertEquals(1980, detail1.getIdto().getPrice());
			assertEquals("黄色", detail1.getIdto().getColor());
			assertEquals("日本帽子製造", detail1.getIdto().getManufacturer());

			PurchaseDetailsDTO detail2 = detailsList.get(1);
			assertEquals(2, detail2.getPurchase_detail_id());
			assertEquals(1, detail2.getAmount());
			assertEquals("ニットキャップ", detail2.getIdto().getName());
			assertEquals(1800, detail2.getIdto().getPrice());
			assertEquals("紺色", detail2.getIdto().getColor());
			assertEquals("日本帽子製造", detail2.getIdto().getManufacturer());

			PurchaseDetailsDTO detail3 = detailsList.get(2);
			assertEquals(3, detail3.getPurchase_detail_id());
			assertEquals(2, detail3.getAmount());
			assertEquals("野球帽", detail3.getIdto().getName());
			assertEquals(2500, detail3.getIdto().getPrice());
			assertEquals("緑色", detail3.getIdto().getColor());
			assertEquals("日本帽子製造", detail3.getIdto().getManufacturer());

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 購入履歴なしの場合のテスト
	 */
	@Test
	void testGetHistory2() {

		try {
			String userId = "no_order_user";

			PurchaseHistoryService hService = new PurchaseHistoryService();
			List<PurchasesDTO> result = hService.getHistory(userId);

			assertNotNull(result);//空にリストオブジェクトが返るべき
			assertTrue(result.isEmpty());

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}
	}
}
