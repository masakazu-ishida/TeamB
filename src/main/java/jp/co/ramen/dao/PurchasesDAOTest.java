package jp.co.ramen.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.dto.PurchaseDetailsDTO;
import jp.co.ramen.dto.PurchasesDTO;
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
class PurchasesDAOTest extends TestBase {

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
	//ログイン済みで購入履歴あり
	void testFindHistoryByUserId1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			List<PurchasesDTO> list = dao.findHistoryByUserId("user1");

			assertNotNull(list);
			assertEquals(1, list.size());

			PurchasesDTO Pdto = list.get(0);

			assertEquals(1, Pdto.getPurchase_id());
			assertEquals(Date.valueOf("2026-06-17"), Pdto.getPurchased_date());
			assertEquals(false, Pdto.getCancel());
			assertEquals("user1", Pdto.getPurchased_user());

			//注文箱の中にある商品明細の数確認
			List<PurchaseDetailsDTO> detailsList = Pdto.getPurchaseDetaillsDto();
			assertNotNull(detailsList);
			assertEquals(3, detailsList.size());

			//商品名確認
			assertEquals("ハンチング帽", detailsList.get(0).getIdto().getName());
			assertEquals("ニットキャップ", detailsList.get(1).getIdto().getName());
			assertEquals("野球帽", detailsList.get(2).getIdto().getName());

			//数量の確認
			assertEquals(4, detailsList.get(0).getAmount());
			assertEquals(1, detailsList.get(1).getAmount());
			assertEquals(2, detailsList.get(2).getAmount());

			assertEquals(1, detailsList.get(0).getIdto().getCategory_id());
			assertEquals(false, detailsList.get(0).getIdto().getRecommended());

			assertEquals(1, detailsList.get(1).getIdto().getCategory_id());
			assertEquals(false, detailsList.get(1).getIdto().getRecommended());

			assertEquals(1, detailsList.get(2).getIdto().getCategory_id());
			assertEquals(true, detailsList.get(2).getIdto().getRecommended());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	@Test
	//ログイン済みだが購入履歴なし
	void testFindHistoryByUserId2() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			PurchasesDAO dao = new PurchasesDAO(conn);

			List<PurchasesDTO> list = dao.findHistoryByUserId("no_purchase_user");

			assertNotNull(list);
			assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

	/**
	 * 主キーが存在する場合のテスト
	 */
	@Test
	void testFindById1() {

		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);
			int testItemId = 1;
			ItemsDTO result = dao.findById(testItemId);

			assertNotNull(result);
			assertEquals(1, result.getItem_id());
			assertEquals("麦わら帽子", result.getName());
			assertEquals("黄色", result.getColor());
			assertEquals("日本帽子製造", result.getManufacturer());
			assertEquals(4980, result.getPrice());
			assertEquals(12, result.getStock());

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 主キーが存在しない場合のテスト
	 */
	@Test
	void testFindById2() {
		//JUnitテストでは引数はNULLでよい。
		try (Connection conn = ConnectionUtil.getConnection(null)) {
			ItemsDAO dao = new ItemsDAO(conn);
			int fakeItemId = 999;
			ItemsDTO result = dao.findById(fakeItemId);

			assertNull(result);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}
	}

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
