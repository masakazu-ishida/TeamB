package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dao.ItemsDAO;
import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dao.PurchaseDetailsDAO;
import jp.co.ramen.dao.PurchasesDAO;
import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.dto.PurchaseDetailsDTO;
import jp.co.ramen.dto.PurchasesDTO;
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
class PurchaseCommitServiceTest extends TestBase {

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

	/**
	 *　各DBに全ての処理が反映されている
	 */
	@Test
	void testExecute() throws ServletException {

		//		user1のカート情報を取得
		GetItemsInCartService getItemsInCartService = new GetItemsInCartService();
		List<ItemsInCartDTO> cartList = getItemsInCartService.execute("user1");

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			try {
				conn.setAutoCommit(false);

				//				テストユーザー
				String testUser = "user1";

				//	日付取得
				LocalDate localDate = LocalDate.now();

				//	PurchseDBにデータ挿入
				PurchasesDTO purchasesDTO = new PurchasesDTO();
				purchasesDTO.setPurchased_user(testUser);
				purchasesDTO.setPurchased_date(localDate);
				purchasesDTO.setDestination("テスト住所");

				PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
				int result = purchasesDAO.purchaseInsert(purchasesDTO);

				assertEquals(1, result);

				PurchasesDTO list = purchasesDAO.findHistoryById(4);
				assertEquals(testUser, list.getPurchased_user());
				assertEquals(localDate, list.getPurchased_date());
				assertEquals("テスト住所", list.getDestination());
				assertEquals(false, list.getCancel());

				// PurchaseDetailsにデータ挿入
				for (ItemsInCartDTO cartItem : cartList) {
					PurchaseDetailsDTO purchaseDetailsDTO = new PurchaseDetailsDTO();
					purchaseDetailsDTO.setPurchase_id(purchasesDTO.getPurchase_id());
					purchaseDetailsDTO.setItem_id(cartItem.getItem_id());
					purchaseDetailsDTO.setAmount(cartItem.getAmount());

					PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
					int purchaseDetailsInsertResult = purchaseDetailsDAO.purchaseInsert(purchaseDetailsDTO);

					assertEquals(1, purchaseDetailsInsertResult);

					assertEquals(9, purchaseDetailsDTO.getPurchase_detail_id());
					assertEquals(4, purchaseDetailsDTO.getPurchase_id());
					assertEquals(3, purchaseDetailsDTO.getItem_id());
					assertEquals(3, purchaseDetailsDTO.getAmount());
					break;

				}

				// Itemsの在庫更新
				for (ItemsInCartDTO cartItem : cartList) {
					int amount = cartItem.getAmount();
					int itemId = cartItem.getItem_id();
					ItemsDAO itemsDAO = new ItemsDAO(conn);
					int purchaseUpdateResult = itemsDAO.purchaseUpdate(amount, itemId);

					assertEquals(1, purchaseUpdateResult);

					ItemsDTO ItemsIdDto = itemsDAO.findById(itemId);
					assertEquals(0, ItemsIdDto.getStock());

					break;
				}

				// ItemsInCart内の商品を全件削除
				ItemsInCartDAO itemsInCartDAO = new ItemsInCartDAO(conn);
				int deleteAllCartItemResult = itemsInCartDAO.deleteAllCartItem(testUser);

				assertEquals(3, deleteAllCartItemResult);

				ItemsInCartDTO itemsInCartDto = itemsInCartDAO.findBySingleID(testUser);
				assertEquals(null, itemsInCartDto);

				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
				conn.rollback();
				// TODO: handle exception
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				// TODO: handle exception
			}

		} catch (Exception e) {

			//発生した例外は全部独自例外に変換してスロー
			//コントローラクラスでは常に独自例外のみキャッチさせる事で、コントローラーの記述を単純化させる
			throw new ServletException(e.getCause());
		}
	}

}
