package jp.co.axisb.test.CartServicetest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.service.CartService;
import jp.co.axisb.util.ConnectionUtil;

public class CartServicetest {

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
			e.printStackTrace();
		}
	}

	@Test
	public void testFindById() {

		init();
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			//カート内に正しく値が詰め込まれているかを確認する。
			List<ItemsInCartDTO> dto = dao.findById("user");
			assertEquals(3, dto.size());

			ItemsInCartDTO searchDTO = dto.get(0);

			//登録直後のデータを取得し、レコードに正しく反映されているかを確認
			assertEquals("user", searchDTO.getUserId());
			assertEquals(1, searchDTO.getItemId());
			assertEquals(5, searchDTO.getAmount());

			//日付の期待結果が2020-10-20である事を確認する場合
			String strDate = "2020-10-20";
			java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date expected = dateFormat.parse(strDate);

			//JUnitで妥当性の確認
			assertEquals(expected, searchDTO.getBookedDate());

			//カート内に存在しないユーザーIDを指定した場合
			dto = dao.findById("a");
			assertEquals(0, dto.size());

		} catch (SQLException | ParseException e) {
			// TODO 自動生成された catch ブロック
			fail(e.getMessage());
		}
	}

	//金額が正しいか確認
	@Test
	public void testCartSum() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			//idがuserのカート内を取得する
			int sum = CartService.CartSum("user");
			assertEquals(37320, sum);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			fail(e.getMessage());
		}
	}
}
