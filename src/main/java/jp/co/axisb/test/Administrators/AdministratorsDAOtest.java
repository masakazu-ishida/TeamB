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

			 //正しくDTOにレコードの値を詰めてるか確認する
				AdministratorsDTO dto = dao.findById("admin");
				
				assertEquals("admin", dto.getAdminId());//ここ怪しい
				assertEquals("admin", dto.getPassword());
				assertEquals("管理者", dto.getName());
			//存在しない主キーを指定した場合、NULLを返す事を確認する
				dto = dao.findById("aaaa");
				assertNull(dto);
				
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
			
			assertEquals("admin", dto.getAdminId());//ここ怪しい
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
}
