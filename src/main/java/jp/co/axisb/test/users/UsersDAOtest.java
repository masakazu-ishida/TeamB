package jp.co.axisb.test.users;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.UsersDAO;
import jp.co.axisb.dto.UsersDTO;
import jp.co.axisb.util.ConnectionUtil;

public class UsersDAOtest {

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

	//存在しないUser
	@Test
	public void testFindByIdNull() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			UsersDAO dao = new UsersDAO(conn);

			try {
				UsersDTO dto = dao.findById("999");
				assertNull(dto);

			} catch (Exception e) {
				fail(e.getMessage());
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindById() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			UsersDAO dao = new UsersDAO(conn);

			//正しくDTOにレコードの値を詰めてるか確認する
			UsersDTO usersDTO = dao.findById("user1");
			assertEquals("user1", usersDTO.getUserId());
			assertEquals("userpass1", usersDTO.getPassword());
			assertEquals("鳥取一郎", usersDTO.getName());
			assertEquals("鳥取県鳥取市河原町６丁目１０７", usersDTO.getAddress());

			//存在しない主キーを指定した場合、NULLを返す事を確認する

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test

	public void testFindAll() {

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			UsersDAO dao = new UsersDAO(conn);

			try {
				List<UsersDTO> list = dao.findAll();
				assertEquals(4, list.size());
				UsersDTO dto = list.get(0);

				assertEquals("user1", dto.getUserId());
				assertEquals("userpass1", dto.getPassword());
				assertEquals("鳥取一郎", dto.getName());
				assertEquals("鳥取県鳥取市河原町６丁目１０７", dto.getAddress());

			} catch (Exception e) {
				// TODO: handle exception
				fail(e.getMessage());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

			fail(e.getMessage());
		}
	}
}
