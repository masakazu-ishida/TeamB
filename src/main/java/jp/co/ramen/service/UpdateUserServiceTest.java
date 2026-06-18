package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.ramen.dto.UserDTO;
import jp.co.ramen.util.TestBase;

class UpdateUserServiceTest {

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
	class ItemDetailServiceTest extends TestBase {

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
		 * ユーザーIDがuser1の場合のテスト
		 */
		@Test
		void testFindById1() {

			String testUserId = "user1";
			UpdateUserService svc = new UpdateUserService();
			try {
				UserDTO result = svc.getUserInformation(testUserId);

				assertNotNull(result);

				assertEquals("user1", result.getUserId());
				assertEquals("password10", result.getPassword());
				assertEquals("鳥取太郎", result.getName());
				assertEquals("鳥取県日南町茶屋", result.getAddress());

				assertNotNull(result);

			} catch (Exception e) {

				e.printStackTrace();
				fail(e);
			}
		}
	}
}
