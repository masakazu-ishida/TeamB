package jp.co.axisb.test.PurchasesDetails;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import jp.co.axisb.dao.PurchasesDetailsDAO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.util.ConnectionUtil;

public class PurchasesDetailsDAOtest {

	@Test
	void testFindById() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

			try {
				List<PurchasesDetailsDTO> list = dao.findById(1);
				assertEquals(1, list.size());

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
	void testInsert() {
		System.out.println("testInsert");
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);
			try {
				//登録するデータ
				PurchasesDetailsDTO dto = new PurchasesDetailsDTO();
				dto.setPurchasesDetailsId(1);
				dto.setPurchasesId(1);
				dto.setItemId(21);
				dto.setAmount(1);

				int result = dao.insert(dto);

				//SQLのinsertが成功しているかをテスト。件数が1なら◎
				assertEquals(1, result);

				List<PurchasesDetailsDTO> searchDTO = dao.findById(1);

				//登録直後のデータを取得し、レコードに正しく反映されているかを確認
				assertEquals(1, dto.getPurchasesDetailsId());
				assertEquals(1), dto.getPurchasesId());
				assertEquals(21, dto.getItemId());
				assertEquals(1, dto.getAmount());
				

			} catch (SQLException e) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
