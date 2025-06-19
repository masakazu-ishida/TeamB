package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class RemoveFromCartCommitService {

	public int deleteItem(String userId, int itemId) {

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			//DAOクラスのインスタンス化
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			ItemsInCartDTO dto = new ItemsInCartDTO();
			dto.setUserId(userId);
			dto.setItemId(itemId);
			//findByIdメソッドに実引数を渡して、返す
			return dao.delete(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
