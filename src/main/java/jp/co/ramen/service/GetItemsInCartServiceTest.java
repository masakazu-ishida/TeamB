package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.ramen.dto.ItemsInCartDTO;
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
class GetItemsInCartServiceTest extends TestBase {

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
	 * ショッピングカート一覧の情報が合致しているかを確認
	 */
	@Test
	void testExecute() {

		//		ユーザーID設定
		String LoginID = "user1";

		//		サービスインスタンス化
		GetItemsInCartService getItemsInCartService = new GetItemsInCartService();

		//		ユーザーID渡してカート情報取得
		try {
			List<ItemsInCartDTO> cartList = getItemsInCartService.execute(LoginID);
			assertNotNull(cartList);
			assertEquals(3, cartList.size());

			//			取得したリストの1行目を検証

			for (ItemsInCartDTO item : cartList) {
				assertEquals("子ども用麦わら帽子", item.getItemsDto().getName());
				assertEquals("赤色", item.getItemsDto().getColor());
				assertEquals("東京帽子店", item.getItemsDto().getManufacturer());
				assertEquals(2980, item.getItemsDto().getPrice());
				assertEquals(3, item.getAmount());
				//先頭だけDTOの中身をチェック
				break;
			}

		} catch (ServletException e) {
			e.printStackTrace();
			fail(e);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

}
