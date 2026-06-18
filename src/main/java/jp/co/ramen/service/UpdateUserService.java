package jp.co.ramen.service;

import java.sql.Connection;

import jp.co.ramen.dao.UserDAO;
import jp.co.ramen.dto.UserDTO;
import jp.co.ramen.util.ConnectionUtil;

public class UpdateUserService {

	public UserDTO getUserInformation(String userId) throws Exception {

		try (Connection con = ConnectionUtil.getConnection("java:comp/env/jdbc/ecsite")) {

			UserDAO Udao = new UserDAO(con);
			UserDTO Udto = Udao.findById(userId);
			return Udto;

		}

	}

}
