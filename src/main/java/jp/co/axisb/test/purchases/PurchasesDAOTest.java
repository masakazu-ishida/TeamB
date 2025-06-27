package jp.co.axisb.test.purchases;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.util.ConnectionUtil;

class PurchasesDAOTest {

	@BeforeEach
	void init() {
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);
			try {
				dao.insertBatch("sqlFiles/init.sql");

				//connection.commit();
			} catch (Exception e) {
				//connection.rollback();
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//全件検索
	@Test
	void testFindAll() {

		System.out.println("testFindAll");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);

			try {

				List<PurchasesDTO> list = dao.findAll();

				//				finddAllで返すデータの件数が全件（今回は４件）なら〇
				assertEquals(1, list.size());

				//				DTOのフィールド値が正しくレコードを反映していれば〇
				PurchasesDTO dto = list.get(0);
				assertEquals(1, dto.getPurchaseId());
				assertEquals("user", dto.getPurchasedUser());
				assertEquals(java.sql.Date.valueOf("2025-06-17"), dto.getPurchasedDate());
				assertEquals("テスト", dto.getDestination());
				assertEquals(false, dto.isCancel());

				List<PurchasesDetailsDTO> purchasesDetails = dto.getPurchasesDetails();
				PurchasesDetailsDTO pd = purchasesDetails.get(0);
				assertEquals(1, pd.getPurchasesDetailsId());
				assertEquals(1, pd.getPurchasesId());
				assertEquals(20, pd.getItemId());
				assertEquals(4, pd.getAmount());

				//				for (int i = 1; i < 4; i++) {
				//					dto.setPurchaseId(i);
				//					dao.delete(dto);
				//				}
				//				list = dao.findAll();
				//				assertEquals(0, list.size());

			} catch (Exception e) {
				//				failとは、テスト結果が失敗したら赤い線が出るようにする
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());

		}
	}

	//	主キーリスト検索
	@Test
	void testfindListById() {

		System.out.println("testfindListById");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);

			try {

				List<PurchasesDTO> list = dao.findListById(1);

				//				finddAllで返すデータの件数が全件（今回は４件）なら〇
				assertEquals(1, list.size());

				//				DTOのフィールド値が正しくレコードを反映していれば〇
				PurchasesDTO dto = list.get(0);
				assertEquals(1, dto.getPurchaseId());
				assertEquals(java.sql.Date.valueOf("2025-06-17"), dto.getPurchasedDate());
				assertEquals("テスト", dto.getDestination());
				assertEquals(false, dto.isCancel());

				List<PurchasesDetailsDTO> purchasesDetails = dto.getPurchasesDetails();
				PurchasesDetailsDTO pd = purchasesDetails.get(0);
				assertEquals(1, pd.getPurchasesDetailsId());
				assertEquals(1, pd.getPurchasesId());
				assertEquals(20, pd.getItemId());
				assertEquals(4, pd.getAmount());

				//				for (int i = 1; i < 4; i++) {
				//					dto.setPurchaseId(i);
				//					dao.delete(dto);
				//				}
				//				list = dao.findAll();
				//				assertEquals(0, list.size());

			} catch (Exception e) {
				//				failとは、テスト結果が失敗したら赤い線が出るようにする
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());

		}
	}

	//主キー検索
	@Test
	void testFindById() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			PurchasesDAO dao = new PurchasesDAO(conn);

			try {
				//正しくDTOにレコードの値を詰めてるか確認する
				PurchasesDTO dto = dao.findById(1);
				assertNotNull(dto);

				assertEquals(1, dto.getPurchaseId());
				assertEquals(java.sql.Date.valueOf("2025-06-17"), dto.getPurchasedDate());
				assertEquals("テスト", dto.getDestination());
				assertEquals(false, dto.isCancel());

				List<PurchasesDetailsDTO> purchasesDetails = dto.getPurchasesDetails();
				PurchasesDetailsDTO pd = purchasesDetails.get(0);
				assertEquals(1, pd.getPurchasesDetailsId());
				assertEquals(1, pd.getPurchasesId());
				assertEquals(20, pd.getItemId());
				assertEquals(4, pd.getAmount());

			} catch (Exception e) {
				//				failとは、テスト結果が失敗したら赤い線が出るようにする
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());

		}
	}

	//	追加
	@Test
	void testInsert() {
		System.out.println("testInsert");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);

			try {
				Date date = Date.valueOf("2025-10-21");
				PurchasesDTO purchases = new PurchasesDTO();
				//				UsersDTO users = new UsersDTO();

				purchases.setPurchaseId(2);
				purchases.setPurchasedUser("user");
				purchases.setPurchasedDate(date);
				purchases.setDestination("自宅");
				purchases.setCancel(false);
				//				users.setUserId("user2");
				//				users.setPassword("user2");
				//				users.setName("user2");
				//				users.setAddress("鳥取県鳥取市河原町６丁目１０８");

				int result = dao.insert(purchases);

				//				SQLのinsertが成功しているのかをテスト。件数が1ならテスト〇
				assertEquals(1, result);

				PurchasesDTO search = dao.findById(result);

				//				登録直後のデータを取得し、レコードに正しく反映されているか
				assertEquals(1, search.getPurchaseId());
				assertEquals("user", search.getPurchasedUser());
				assertEquals(date, search.getPurchasedDate());
				assertEquals("自宅", search.getDestination());
				assertEquals(false, search.isCancel());

			} catch (SQLException e) {

			}
		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());

		}
	}

	//	更新
	@Test
	void testUpdate() {
		System.out.println("testUpdate");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);

			try {
				Date date = Date.valueOf("2025-10-31");
				PurchasesDTO purchases = new PurchasesDTO();
				//				UsersDTO users = new UsersDTO();
				//				PurchasesDetailsDTO pd = new PurchasesDetailsDTO();

				purchases.setPurchasedUser("user3");
				purchases.setPurchasedDate(date);
				purchases.setDestination("自宅");
				purchases.setCancel(false);
				purchases.setPurchaseId(1);
				//				users.setUserId("user3");
				//				users.setPassword("user3");
				//				users.setName("user3");
				//				users.setAddress("鳥取県鳥取市河原町６丁目300");
				//				pd.setPurchasesDetailsId(1);
				//				pd.setPurchasesId(90);
				//				pd.setItemId(1);
				//				pd.setAmount(2);

				int result = dao.update(purchases);

				//				SQLのinsertが成功しているのかをテスト。件数が1ならテスト〇
				assertEquals(1, result);

				PurchasesDTO search = dao.findById(result);

				//				登録直後のデータを取得し、レコードに正しく反映されているか

				assertEquals("user3", search.getPurchasedUser());
				assertEquals(date, search.getPurchasedDate());
				assertEquals("自宅", search.getDestination());
				assertEquals(false, search.isCancel());
				assertEquals(1, search.getPurchaseId());

			} catch (SQLException e) {

			}
		} catch (SQLException e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());

		}
	}

}
