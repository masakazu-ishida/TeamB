package jp.co.ramen.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.util.ConnectionUtil;
import jp.co.ramen.util.TestBase;

/**
 * DAOの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 * 
 * 単体テストの観点
 * ・網羅率：ブランチカバレッジ
 * ・検索系：DTOのフィールドに値が期待どおり格納されるか？Listの場合期待する件数が格納されるか？
 * ・更新系：DBの状態が期待どおりか？
 * 
 */
class ItemsInCartDAOTest extends TestBase {

	/**
	 * 
	 * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	public void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	以下の後始末メソッドは何も考えずまるごとコピー
	 * テストメソッドが全て完了した後、必ず実行され、
	 * DBのコネクションがクローズされる。なお、クローズされるのは
	 * /init_data.sqlで初期化するコネクション
	 * */
	@AfterAll
	public static void cleanUp() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Test
	//	カート内商品を取得できているかを確認
	void testFindAll() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			List<ItemsInCartDTO> allItemInCart = dao.findAll("user1");

			assertNotNull(allItemInCart);
			assertEquals(3, allItemInCart.size());

			for (ItemsInCartDTO item : allItemInCart) {
				assertEquals(3, item.getItemsDto().getItem_id());
				assertEquals("子ども用麦わら帽子", item.getItemsDto().getName());
				assertEquals("東京帽子店", item.getItemsDto().getManufacturer());
				assertEquals(1, item.getItemsDto().getCategory_id());
				assertEquals("赤色", item.getItemsDto().getColor());
				assertEquals(2980, item.getItemsDto().getPrice());
				assertEquals(3, item.getItemsDto().getStock());
				assertEquals(false, item.getItemsDto().getRecommended());
				assertEquals("user1", item.getUser_id());
				assertEquals(3, item.getAmount());
				assertEquals("2026-06-19", item.getBooked_date().toString());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//カート内から削除対象の行を取り出す ←テストクリア

	void testFindById() {
		try {
			String userId = "user1";
			int itemId = 5;
			ItemsInCartDAO dao = new ItemsInCartDAO();
			ItemsInCartDTO result = dao.findById(userId, itemId);
			assertNotNull(result);
			assertEquals(userId, result.getUser_id());
			assertEquals(itemId, result.getItem_id());

		} catch (SQLException e) {
			e.printStackTrace();
			fail("例外");
		}
	}

	//カート内商品削除確認
	void testDeleteCartItem() {
		try {
			String userId = "user1";
			int itemId = 5;

			ItemsInCartDAO dao = new ItemsInCartDAO();
			dao.deleteCartItem(userId, itemId);
			ItemsInCartDTO result2 = dao.deleteCartItem(userId, itemId);

			assertNotNull(result2);
			assertEquals(userId, result2.getUser_id());
			assertEquals(itemId, result2.getItem_id());

		} catch (SQLException e) {
			e.printStackTrace();
			fail("例外発生");
		}
	}

	//カートから特定のユーザーの特定の商品のカート内情報の取得
	// 完全一致
	@Test
	void testFindByUserIdAndItemId1() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO result = dao.findByUserIdAndItemId("user1", 3);

			assertNotNull(result);
			assertEquals("user1", result.getUser_id());
			assertEquals(3, result.getItem_id());
			assertEquals(3, result.getAmount());
			assertEquals("2026-06-19", result.getBooked_date().toString());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	// 商品違い：nullが返る

	@Test
	void testFindByUserIdAndItemId2() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO result = dao.findByUserIdAndItemId("user1", 999);

			assertNull(result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//ユーザー違い：nullが返る
	@Test
	void testFindByUserIdAndItemId3() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO result = dao.findByUserIdAndItemId("wrong_user", 3);

			assertNull(result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//両方違い：nullが返る
	@Test
	void testFindByUserIdAndItemId4() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO result = dao.findByUserIdAndItemId("wrong_user", 999);

			assertNull(result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//カート内商品の数量と日付更新
	@Test
	void testUpdate1() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			int newAmount = 10;
			java.time.LocalDate newDate = java.time.LocalDate.of(2026, 6, 20);

			int row = dao.update("user1", 3, newAmount, newDate);
			assertEquals(1, row);

			// 検証
			ItemsInCartDTO actual = dao.findByUserIdAndItemId("user1", 3);
			assertNotNull(actual);
			assertEquals("user1", actual.getUser_id());
			assertEquals(3, actual.getItem_id());
			assertEquals(10, actual.getAmount());
			assertEquals("2026-06-20", actual.getBooked_date().toString());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	//カートへの商品追加
	@Test
	void testInsert1() {

		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			ItemsInCartDTO newDto = new ItemsInCartDTO();
			newDto.setUser_id("user1");
			newDto.setItem_id(10);
			newDto.setAmount(5);
			newDto.setBooked_date(java.time.LocalDate.of(2026, 6, 19));

			int row = dao.insert(newDto);
			assertEquals(1, row);

			ItemsInCartDTO actual = dao.findByUserIdAndItemId("user1", 10);
			assertNotNull(actual);
			assertEquals("user1", actual.getUser_id());
			assertEquals(10, actual.getItem_id());
			assertEquals(5, actual.getAmount());
			assertEquals("2026-06-19", actual.getBooked_date().toString());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}
}
