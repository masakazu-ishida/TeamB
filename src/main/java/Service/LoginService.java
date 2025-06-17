package Service;

import java.sql.Connection;

import jp.co.axisb.dao.UsersDAO;
import jp.co.axisb.dto.UsersDTO;
import jp.co.axisb.util.ConnectionUtil;

public class LoginService {

	public static UsersDTO search(String userId) {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			UsersDAO dao = new UsersDAO(conn);

			return dao.findById(userId);

		} catch (Exception e) {
			return null;

		}

	}

}
