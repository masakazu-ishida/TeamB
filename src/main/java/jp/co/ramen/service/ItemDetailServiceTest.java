package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.ItemsDTO;
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
class ItemDetailServiceTest extends TestBase {

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
	 * 商品IDが正しい場合のテスト
	 */
	@Test
	void testFindById1() {

		int testItemId = 1;
		ItemDetailService svc = new ItemDetailService();
		try {
			ItemsDTO result = svc.getItemDetail(testItemId);

			assertNotNull(result);

			assertEquals("麦わら帽子", result.getName());
			assertEquals("黄色", result.getColor());
			assertEquals("日本帽子製造", result.getManufacturer());
			assertEquals(4980, result.getPrice());
			assertEquals(12, result.getStock());

			assertNotNull(result);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 間違っている場合のテスト
	 */
	@Test
	void testFindById2() {
		int fakeItemId = 999;
		ItemDetailService svc = new ItemDetailService();
		try {
			//間違い

			ItemsDTO result = svc.getItemDetail(fakeItemId);
			assertNull(result);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}
	}
}
