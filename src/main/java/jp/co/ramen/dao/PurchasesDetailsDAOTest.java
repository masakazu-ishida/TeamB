package jp.co.ramen.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.PurchaseDetailsDTO;
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
class PurchasesDetailsDAOTest extends TestBase {

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
	//purchaseid新規生成, purchasedUser, date, destination, cancelが想定通り入るか確認
	void testPurchaseInsert() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {

			PurchaseDetailsDTO purchaseDetailsDTO = new PurchaseDetailsDTO();
			purchaseDetailsDTO.setPurchase_id(3);
			purchaseDetailsDTO.setItem_id(3);
			purchaseDetailsDTO.setAmount(99);

			PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
			int result = purchaseDetailsDAO.purchaseInsert(purchaseDetailsDTO);

			assertEquals(1, result);

			PurchaseDetailsDTO pdDto = purchaseDetailsDAO.findById(9);

			assertNotNull(pdDto);

			assertEquals(9, pdDto.getPurchase_detail_id());
			assertEquals(3, pdDto.getPurchase_id());
			assertEquals(3, pdDto.getItem_id());
			assertEquals(99, pdDto.getAmount());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * 主キーで検索できる
	 */
	//	@Test
	//	void testFindById1() {
	//		//JUnitテストでは引数はNULLでよい。
	//		try (Connection conn = ConnectionUtil.getConnection(null)) {
	//			PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
	//
	//			int testPurchase_id = 1;
	//
	//			PurchasesDTO result = purchasesDAO.findHistoryById(testPurchase_id);
	//
	//			assertNotNull(result);
	//			assertEquals(1, result.getPurchase_id());
	//			assertEquals("user1", result.getPurchased_user());
	//			assertEquals(LocalDate.of(2026, 06, 19), result.getPurchased_date());
	//			assertEquals(null, result.getDestination());
	//			assertEquals(false, result.getCancel());
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			fail(e);
	//		}
	//	}

	/**
	 * 存在しない主キーは検索できない
	 */
	//	@Test
	//	void testFindHistoryById2() {
	//		//JUnitテストでは引数はNULLでよい。
	//		try (Connection conn = ConnectionUtil.getConnection(null)) {
	//			PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
	//
	//			int testPurchase_id = 9999;
	//
	//			PurchasesDTO result = purchasesDAO.findHistoryById(testPurchase_id);
	//
	//			assertNull(result);
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			fail(e);
	//		}
	//	}

	//	@Test
	//	void testInsert() {
	//		try (Connection conn = ConnectionUtil.getConnection(null)) {
	//			UserDAO dao = new UserDAO(conn);
	//			UserDTO user = new UserDTO();
	//			user.setUserId("user10");
	//			user.setPassword("password10");
	//			user.setName("島根一郎");
	//			user.setAddress("島根県奥出雲町");
	//
	//			int result = dao.insert(user);
	//
	//			assertEquals(1, result);
	//
	//			user = dao.findById("user10");
	//
	//			assertNotNull(user);
	//			assertEquals("user10", user.getUserId());
	//			assertEquals("password10", user.getPassword());
	//			assertEquals("島根一郎", user.getName());
	//			assertEquals("島根県奥出雲町", user.getAddress());
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			fail(e);
	//		}
	//
	//	}
	//
	//	@Test
	//	void testUpdate() {
	//		try (Connection conn = ConnectionUtil.getConnection(null)) {
	//			UserDAO dao = new UserDAO(conn);
	//			UserDTO user = new UserDTO();
	//			user.setUserId("user1");
	//			user.setPassword("password10");
	//			user.setName("鳥取太郎");
	//			user.setAddress("鳥取県日南町茶屋");
	//
	//			int result = dao.update(user);
	//
	//			assertEquals(1, result);
	//
	//			user = dao.findById("user1");
	//
	//			assertNotNull(user);
	//			assertEquals("user1", user.getUserId());
	//			assertEquals("password10", user.getPassword());
	//			assertEquals("鳥取太郎", user.getName());
	//			assertEquals("鳥取県日南町茶屋", user.getAddress());
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			fail(e);
	//		}
	//
	//	}
	//
	//	@Test
	//	void testDelete() {
	//		try (Connection conn = ConnectionUtil.getConnection(null)) {
	//			UserDAO dao = new UserDAO(conn);
	//			UserDTO user = new UserDTO();
	//			user.setUserId("user4");
	//
	//			int result = dao.delete("user4");
	//
	//			assertEquals(1, result);
	//
	//			user = dao.findById("user4");
	//
	//			assertNull(user);
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			fail(e);
	//		}
	//
	//	}
}
