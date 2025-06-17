package jp.co.axisb.test.purchases;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.PurchasesDAO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.util.ConnectionUtil;

class PurchasesDAOTest {

	@BeforeEach
	void init() {
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
				assertEquals(4, list.size());

				//				DTOのフィールド値が正しくレコードを反映していれば〇
				PurchasesDTO dto = list.get(0);

				assertEquals(1, dto.getPurchaseId());

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
	/*
	@Test
	void testFindAllEmpty() {
		System.out.println("testFindAllEmpty");
		System.out.println("testFindAllEmpty");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);
	
			try {
	
				for (int i = 0; i < 4; i++) {
					PurchasesDTO dto = new PurchasesDTO();
					dto.setPurchaseId(i + 1);
	
					dao.delete(dto);
				}
	
				connection.commit();
	
				//				テーブル空の状態で件数が０件であることを確認する
				List<PurchasesDTO> list = dao.findAll();
				assertEquals(0, list.size());
	
			} catch (Exception e) {
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
				assertEquals("user1", dto.getPurchasedUser());
	
				conn.commit();
	
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
	
	@Test
	void testFindByIdNull() {
		System.out.println("testFindByIdNull");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);
	
			try {
	
				PurchasesDTO dto = dao.findById(5);
				//中身がnullのものをtrueで返す
				assertNull(dto);
	
			} catch (Exception e) {
				fail(e.getMessage());
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
	
			//例外が発生したらテストは結果は×
			fail(e.getMessage());
	
		}
	}
	//	追加
	
	void testInsert() {
		System.out.println("testInsert");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);
	
			try {
				Date date = Date.valueOf("2025-10-21");
				PurchasesDTO dto = new PurchasesDTO();
				dto.setPurchaseId(2);
				dto.setPurchasedUser("森尾");
				dto.setPurchasedDate(date);
				dto.setDestination("自宅");
				dto.setCancel(false);
	
				int result = dao.insert(dto);
	
				//				SQLのinsertが成功しているのかをテスト。件数が1ならテスト〇
				assertEquals(1, result);
	
				PurchasesDTO searchDTO = dao.findById(2);
	
				//				登録直後のデータを取得し、レコードに正しく反映されているか
				assertEquals(2, searchDTO.getPurchaseId());
				assertEquals("森尾", searchDTO.getPurchasedUser());
	
			} catch (SQLException e) {
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
	
			//例外が発生したらテストは結果は×
			fail(e.getMessage());
	
		}
	}
	
	//	更新
	
	void testUpdate() {
		System.out.println("testUpdate");
		try (Connection connection = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDAO dao = new PurchasesDAO(connection);
	
			try {
				Date date = Date.valueOf("2025-10-23");
				PurchasesDTO dto = new PurchasesDTO();
				dto.setPurchaseId(2);
				dto.setPurchasedUser("田中");
				dto.setPurchasedDate(date);
				dto.setDestination("自宅");
				dto.setCancel(false);
	
				int result = dao.update(dto);
	
				//				SQLのinsertが成功しているのかをテスト。件数が1ならテスト〇
				assertEquals(1, result);
	
				PurchasesDTO serch = dao.findById(2);
	
				//				登録直後のデータを取得し、レコードに正しく反映されているか
				assertEquals(2, serch.getPurchaseId());
				assertEquals("田中", serch.getPurchasedUser());
	
			} catch (SQLException e) {
	
			}
		} catch (SQLException e) {
			e.printStackTrace();
	
			//例外が発生したらテストは結果は×
			fail(e.getMessage());
	
		}
	}
	*/

}
