package jp.co.ramen.service;

import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.util.ConnectionUtil;

/**
 * 認証業務クラス
 * サービスを作る粒度は「処理ごと」がよい
 */
public class CartDeleteConfirmService {

	/**
	 * 認証業務を実行するサービスメソッド
	 * このメソッドにより、コントローラーは認証業務の詳細を知る必要がない
	 * @param userId 画面から入力されたユーザID
	 * @param password 画面から入力されたパスワード
	 * @return ユーザのDTO。認証に成功すれば非NULL、認証に失敗すればNULL
	 * @throws ECSiteExceptionSer
	 */

	public ItemsInCartDTO getCartItemForDelete(String userId, int itemId) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO targetItem = null;

			List<ItemsInCartDTO> iicList = dao.findAll(userId);

			for (ItemsInCartDTO dto : iicList) {
				if (dto.getItem_id() == itemId) {
					targetItem = dto; // 見つけたらキープ！
					break;
				}
			}

			return targetItem;

		} catch (Exception e) {

			//発生した例外は全部独自例外に変換してスロー確認
			//コントローラクラスでは常に独自例外のみキャッチさせる事で、コントローラーの記述を単純化させる
			throw new ServletException(e.getCause());
		}
	}

}
