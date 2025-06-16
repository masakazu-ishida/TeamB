package jp.co.axisb.test.Categories;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.CategoriesDAO;
import jp.co.axisb.dto.CategoriesDTO;
import jp.co.axisb.util.ConnectionUtil;

class CategoriesDAOTest {

	//キー検索
	@Test
	void testfindById() {
		try(Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			CategoriesDAO dao = new CategoriesDAO(conn);
			
			//正しくDTOにレコードの値を詰めてるか確認する
			CategoriesDTO categoriesdto = dao.findById(0);
			
			//入力値がNULLではないことを検証
			assertNotNull(categoriesdto);
			
			//findById(０)で指定した行を取得
			assertEquals(0, categoriesdto.getCotegoryId());
			assertEquals("すべて", categoriesdto.getName());
			
			//存在しない主キーを指定した場合、NULLを返す事を確認する
			categoriesdto = dao.findById(6);
			assertNull(categoriesdto);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail(e.getMessage());
		}	
	}
	
	//全検索
	@Test
	void  testFindAll() {
		try(Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			CategoriesDAO dao = new CategoriesDAO(conn);
			
			try {
				List<CategoriesDTO> list = dao.findAll();
				assertEquals(3, list.size());
				CategoriesDTO categoriesdto = list.get(0);
				
				assertEquals(0, categoriesdto.getCotegoryId());
				assertEquals("すべて", categoriesdto.getName());
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				fail(e.getMessage());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			fail(e.getMessage());
		}
	}
}

