package jp.co.axisb.test.LoginService;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dto.UsersDTO;
import jp.co.axisb.service.LoginService;
import jp.co.axisb.util.ConnectionUtil;

public class LoginServiceTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			BaseDAO dao = new BaseDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");

			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindById() {
		UsersDTO search = LoginService.login("user", "userpass");
		assertNotNull(search);

		search = LoginService.login("aaaaaaaaa", "userpass");
		assertNull(search);

		search = LoginService.login("user", "aaaaaaaaa");
		assertNull(search);

		search = LoginService.login("aaaaaaaaa", "aaaaaaaaa");
		assertNull(search);
	}

}
