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
				assertEquals("子ども用麦わら帽子", item.getItemsDto().getName());
				assertEquals("赤色", item.getItemsDto().getColor());
				assertEquals("東京帽子店", item.getItemsDto().getManufacturer());
				assertEquals(2980, item.getItemsDto().getPrice());
				assertEquals(3, item.getAmount());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
		}

	}

}
