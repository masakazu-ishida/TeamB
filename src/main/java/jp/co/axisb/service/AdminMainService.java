package jp.co.axisb.service;

import java.sql.Connection;

import jp.co.axisb.dao.AdministratorsDAO;
import jp.co.axisb.dto.AdministratorsDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class AdminMainService {

	public static AdministratorsDTO login(String userId, String password) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			AdministratorsDAO dao = new AdministratorsDAO(conn);

			return dao.findById(userId, password);

		} catch (Exception e) {
			return null;

		}

	}

}
