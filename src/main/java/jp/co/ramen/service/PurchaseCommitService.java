package jp.co.ramen.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.ramen.dao.ItemsDAO;
import jp.co.ramen.dao.ItemsInCartDAO;
import jp.co.ramen.dao.PurchaseDetailsDAO;
import jp.co.ramen.dao.PurchasesDAO;
import jp.co.ramen.dto.ItemsInCartDTO;
import jp.co.ramen.dto.PurchaseDetailsDTO;
import jp.co.ramen.dto.PurchasesDTO;
import jp.co.ramen.util.ConnectionUtil;

/**
 * 認証業務クラス
 * サービスを作る粒度は「処理ごと」がよい
 */
public class PurchaseCommitService {

	/**
	 * 認証業務を実行するサービスメソッド
	 * このメソッドにより、コントローラーは認証業務の詳細を知る必要がない
	 * @param userId 画面から入力されたユーザID
	 * @param password 画面から入力されたパスワード
	 * @return ユーザのDTO。認証に成功すれば非NULL、認証に失敗すればNULL
	 * @throws ECSiteException
	 */
	public void execute(List<ItemsInCartDTO> cartList, String loguinId, String adress)
			throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {

			try {
				conn.setAutoCommit(false);

				//	日付取得
				LocalDate localDate = LocalDate.now();

				//	PurchseDBにデータ挿入
				PurchasesDTO purchasesDTO = new PurchasesDTO();
				purchasesDTO.setPurchased_user(loguinId);
				purchasesDTO.setPurchased_date(localDate);
				purchasesDTO.setDestination(adress);

				PurchasesDAO purchasesDAO = new PurchasesDAO(conn);
				purchasesDAO.purchaseInsert(purchasesDTO);

				// PurchaseDetailsにデータ挿入
				for (ItemsInCartDTO cartItem : cartList) {
					PurchaseDetailsDTO purchaseDetailsDTO = new PurchaseDetailsDTO();
					purchaseDetailsDTO.setPurchase_id(purchasesDTO.getPurchase_id());
					purchaseDetailsDTO.setItem_id(cartItem.getItem_id());
					purchaseDetailsDTO.setAmount(cartItem.getAmount());

					PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
					purchaseDetailsDAO.purchaseInsert(purchaseDetailsDTO);
				}

				// Itemsの在庫更新
				for (ItemsInCartDTO cartItem : cartList) {
					int amount = cartItem.getAmount();
					int itemId = cartItem.getItem_id();
					ItemsDAO itemsDAO = new ItemsDAO(conn);
					itemsDAO.purchaseUpdate(amount, itemId);
				}

				// ItemsInCart内の商品を全件削除
				ItemsInCartDAO itemsInCartDAO = new ItemsInCartDAO(conn);
				itemsInCartDAO.deleteAllCartItem(loguinId);

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
