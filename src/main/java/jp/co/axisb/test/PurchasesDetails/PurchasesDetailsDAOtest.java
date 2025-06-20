package jp.co.axisb.test.PurchasesDetails;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.PurchasesDetailsDAO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.util.ConnectionUtil;

public class PurchasesDetailsDAOtest {
	@BeforeEach
	void init() {
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			BaseDAO dao = new BaseDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");

			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test //Idがある場合
	public void testFindById() {
		init();
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

			try {
				List<PurchasesDetailsDTO> list = dao.findById(1);
				assertEquals(1, list.size());

				//DTOのフィールド値が正しくレコードを反映していれば◎
				PurchasesDetailsDTO dto = list.get(0);
				assertNotNull(dto);
				//期待している値、実際に格納されている値
				assertEquals(1, dto.getPurchasesDetailsId());
				assertEquals(1, dto.getPurchasesId());
				assertEquals(20, dto.getItemId());
				assertEquals(4, dto.getAmount());

			} catch (SQLException e) {
				fail(e.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();

			//例外が発生したらテストは結果は×
			fail(e.getMessage());

		}
	}

	@Test
	public void testFindByIdNull() {
		init();
		System.out.println("testFindByIdNull");
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

			try {
				//5は存在しない主キー
				List<PurchasesDetailsDTO> list = dao.findById(5);
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

	@Test //更新
	public void testInsert() {
		init();
		System.out.println("testInsert");
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);
			try {
				//登録するデータ
				PurchasesDetailsDTO dto = new PurchasesDetailsDTO();
				dto.setPurchasesId(1);
				dto.setItemId(20);
				dto.setAmount(4);

				int result = dao.insert(dto);

				//SQLのinsertが成功しているかをテスト。件数が1なら◎
				assertEquals(1, result);

				//PurchasesDetailsDTO searchDTO = new PurchasesDetailsDTO();

				List<PurchasesDetailsDTO> list = dao.findById(1);

				PurchasesDetailsDTO searchDTO = list.get(0);

				//登録直後のデータを取得し、レコードに正しく反映されているかを確認
				assertEquals(1, searchDTO.getPurchasesDetailsId());
				assertEquals(1, searchDTO.getPurchasesId());
				assertEquals(20, searchDTO.getItemId());
				assertEquals(4, searchDTO.getAmount());

			} catch (SQLException e) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
