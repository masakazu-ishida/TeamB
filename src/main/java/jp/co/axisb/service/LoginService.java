package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.UsersDAO;
import jp.co.axisb.dto.UsersDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class LoginService {

	public static UsersDTO login(String userId, String password) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			UsersDAO dao = new UsersDAO(conn);

			return dao.findById(userId, password);

		} catch (Exception e) {
			return null;

		}

	}

}
