package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class RemoveFromCartConfirmService {

	public ItemsInCartDTO getItem(String userId, int itemId) {

		//ItemsInCartDTO dto = null;

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			//DAOクラスのインスタンス化
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			//findByIdメソッドに実引数を渡して、返す
			return dao.findById(userId, itemId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
