package jp.co.ramen.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
class LoginServiceTest extends TestBase {

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
	 * 会員IDもパスワードもDBと合致
	 */
	@Test
	void testExecute1() {

		try {
			String userId = "user1";
			String passward = "userpass1";

			LoginService loginService = new LoginService();
			int authenticate = loginService.execute(userId, passward);

			assertNotNull(authenticate);
			assertEquals(0, authenticate);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 会員IDは合致したが、パスワードは間違っている
	 */
	@Test
	void testExecute2() {

		try {
			String userId = "user1";
			String passward = "xxxxx";

			LoginService loginService = new LoginService();
			int authenticate = loginService.execute(userId, passward);

			assertNotNull(authenticate);
			assertEquals(1, authenticate);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}

	/**
	 * 会員IDが存在しない
	 */
	@Test
	void testExecute3() {

		try {
			String userId = "xxxxx";
			String passward = "xxxxx";

			LoginService loginService = new LoginService();
			int authenticate = loginService.execute(userId, passward);

			assertNotNull(authenticate);
			assertEquals(1, authenticate);

		} catch (Exception e) {

			e.printStackTrace();
			fail(e);
		}

	}
}
