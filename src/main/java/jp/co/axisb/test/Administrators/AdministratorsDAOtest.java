package jp.co.axisb.test.Administrators;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.AdministratorsDAO;
import jp.co.axisb.dto.AdministratorsDTO;
import jp.co.axisb.util.ConnectionUtil;

class AdministratorsDAOtest {

	@Test
	void testFindById() {
		System.out.println("testFindById");	
		try(Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			AdministratorsDAO dao = new AdministratorsDAO(conn);

				AdministratorsDTO dto = dao.findById("admin");
				
				assertEquals("admin", dto.getAdminId());
				assertEquals("admin", dto.getPassword());
				assertEquals("管理者", dto.getName());
			
		} catch (Exception e) {
			// TODO: handle exception
			fail(e.getMessage());
		}
	}
	
	
	
	
	@Test
	//
	void testFindAll() {
		System.out.println("testFindAll");
		try(Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			AdministratorsDAO dao = new AdministratorsDAO(conn);
			
		try {
			List<AdministratorsDTO> list = dao.findAll();
			assertEquals(1, list.size());
			AdministratorsDTO dto = list.get(0);
			
			assertEquals("admin", dto.getAdminId());
			assertEquals("admin", dto.getPassword());
			assertEquals("管理者", dto.getName());
			
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
	
	void testFindByIdNull() {
		try(Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			AdministratorsDAO dao = new AdministratorsDAO(conn);
			
			try {
				AdministratorsDTO dto = dao.findById("afj");
				assertNull(dto);
				
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
