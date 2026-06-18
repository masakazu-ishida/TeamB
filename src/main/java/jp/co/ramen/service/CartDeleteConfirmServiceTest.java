package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.ItemsInCartDTO;
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
class CartDeleteConfirmServiceTest extends TestBase {

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
	 * 商品IDありの場合のテスト
	 */
	@Test
	void testGetHistory1() {

		try {
			String userId = "user1";
			int itemId = 5;
			CartDeleteConfirmService service = new CartDeleteConfirmService();
			ItemsInCartDTO result = service.getCartItemForDelete(userId, itemId);

			assertNotNull(result);

			assertEquals(userId, result.getUser_id());
			assertEquals(itemId, result.getItem_id());
			assertEquals(2, result.getAmount());

			assertNotNull(result.getItemsDto());

			assertEquals("野球帽", result.getItemsDto().getName());
			assertEquals("日本帽子製造", result.getItemsDto().getManufacturer());
			assertEquals(2500, result.getItemsDto().getPrice());

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 商品IDなしの場合のテスト
	 */
	@Test
	void testGetHistory2() {

		try {
			String userId = "user1";
			int itemId = 999;
			CartDeleteConfirmService service = new CartDeleteConfirmService();
			ItemsInCartDTO result = service.getCartItemForDelete(userId, itemId);

			assertNull(result);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}
	}
}
