package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dao.ItemsDAO;
import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.util.ConnectionUtil;
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
class SearchServletServiceTest extends TestBase {

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

	@Test
	//カテゴリ：すべて　キーワード：空
	void testfindByCondision1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> itemsList = dao.findByCondision(3, "");

			assertNotNull(itemsList);
			assertEquals(20, itemsList.size());

			for (ItemsDTO item : itemsList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.getRecommended());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	//カテゴリ：すべて　キーワード：あり
	void tesfindByCondision2() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> itemsList = dao.findByCondision(3, "麦");

			assertNotNull(itemsList);
			assertEquals(2, itemsList.size());

			for (ItemsDTO item : itemsList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.getRecommended());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	//カテゴリ：1　キーワード：空
	void testfindByCondision3() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> itemsList = dao.findByCondision(1, "");

			assertNotNull(itemsList);
			assertEquals(11, itemsList.size());

			for (ItemsDTO item : itemsList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.getRecommended());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	//カテゴリ：1　キーワード：あり
	void testfindByCondision4() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);

			List<ItemsDTO> itemsList = dao.findByCondision(1, "麦");

			assertNotNull(itemsList);

			for (ItemsDTO item : itemsList) {
				assertEquals(1, item.getItem_id());
				assertEquals("麦わら帽子", item.getName());
				assertEquals("日本帽子製造", item.getManufacturer());
				assertEquals(1, item.getCategory_id());
				assertEquals("黄色", item.getColor());
				assertEquals(4980, item.getPrice());
				assertEquals(12, item.getStock());
				assertEquals(false, item.getRecommended());
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

}
