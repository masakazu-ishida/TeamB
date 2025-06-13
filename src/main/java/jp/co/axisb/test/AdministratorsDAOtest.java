package jp.co.axisb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.sql.Connection;

import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.AdministratorsDAO;
import jp.co.axisb.dto.AdministratorsDTO;

class AdministratorsDAOtest {

	@Test
	void testFindById() {
		System.out.println("testFindById");	
		try(Connection conn = ConnectionUtil.getConnectionForJUnit() {
			AdministratorsDAO dao = new AdministratorsDAO(connection);

			try {
				AdministratorsDTO dto = dao.findById(1);
				assertNotNull(dto);
				
				assertEquals(1, dto.getAdminId());
				assertEquals(null, null)
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	}

}
