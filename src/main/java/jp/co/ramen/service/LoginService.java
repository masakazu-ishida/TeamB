package jp.co.ramen.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.ramen.dao.UserDAO;
import jp.co.ramen.dto.UserDTO;
import jp.co.ramen.util.ConnectionUtil;

/**
 * 認証業務クラス
 * サービスを作る粒度は「処理ごと」がよい
 */
public class LoginService {

	/**
	 * 認証業務を実行するサービスメソッド
	 * このメソッドにより、コントローラーは認証業務の詳細を知る必要がない
	 * @param userId 画面から入力されたユーザID
	 * @param password 画面から入力されたパスワード
	 * @return ユーザのDTO。認証に成功すれば非NULL、認証に失敗すればNULL
	 * @throws ECSiteException
	 */
	public int execute(String userId, String passward) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			UserDAO userDao = new UserDAO(conn);
			UserDTO userDto = userDao.findById(userId);

			if (userDto != null) {
				if (userDto.getPassword() == passward) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 2;
			}

		} catch (Exception e) {

			//発生した例外は全部独自例外に変換してスロー
			//コントローラクラスでは常に独自例外のみキャッチさせる事で、コントローラーの記述を単純化させる
			throw new ServletException(e.getCause());
		}
	}

}
