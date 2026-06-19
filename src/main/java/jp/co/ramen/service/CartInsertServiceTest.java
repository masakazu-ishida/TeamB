package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.util.ConnectionUtil;
import jp.co.ramen.util.TestBase;

/**
 * サービスの単体テスト
 * スーパークラスに必ずTestBaseを指定する。
 */
class CartInsertServiceTest extends TestBase {

	/**
	 * * 以下の初期化メソッドは何も考えずまるごとコピー
	 * テストメソッド実行の度に毎回init_data.sqlが実行され、
	 * DBの中身が初期化される
	 * */
	@BeforeEach
	void setUp() throws Exception {
		String sqlFilePath = "/init_data.sql";
		super.initSQLFiles(sqlFilePath);
	}

	/**
	 * カートに商品がない場合は新規登録されること
	 */
	@Test
	void testAddCart1() {

		try {
			String userId = "user1";
			int itemId = 7;
			int order = 3;

			CartInsertService service = new CartInsertService();
			service.addCart(userId, itemId, order);

			try (Connection conn = ConnectionUtil.getConnection(null)) {
				ItemsInCartDAO dao = new ItemsInCartDAO(conn);
				ItemsInCartDTO actual = dao.findByUserIdAndItemId(userId, itemId);

				assertNotNull(actual);
				assertEquals(userId, actual.getUser_id());
				assertEquals(itemId, actual.getItem_id());
				assertEquals(3, actual.getAmount());
				assertEquals(LocalDate.now(), actual.getBooked_date());
			}

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 既にカートに商品がある場合は数量が加算されること
	 */
	@Test
	void testAddCart2() {

		try {
			String userId = "user1";
			int itemId = 3;
			int order = 2;

			CartInsertService service = new CartInsertService();
			service.addCart(userId, itemId, order);

			try (Connection conn = ConnectionUtil.getConnection(null)) {
				ItemsInCartDAO dao = new ItemsInCartDAO(conn);
				ItemsInCartDTO actual = dao.findByUserIdAndItemId(userId, itemId);

				assertNotNull(actual);
				assertEquals(userId, actual.getUser_id());
				assertEquals(itemId, actual.getItem_id());
				assertEquals(5, actual.getAmount());
				assertEquals(LocalDate.now(), actual.getBooked_date());
			}

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}
}