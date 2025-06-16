package jp.co.axisb.test.itemsincart;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.ConnectionUtil;

public class ItemsInCartDAOTest {
	
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
	   public void testFindById2() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao  = new ItemsInCartDAO(conn);
	            
	         //正しくDTOにレコードの値を詰めてるか確認する
	         ItemsInCartDTO itemsDTO = dao.findById("user", 1);
	         assertEquals("user", itemsDTO.getUserId());
	         assertEquals(1, itemsDTO.getItemId());
	         assertEquals(5, itemsDTO.getAmount());
	         assertEquals(java.sql.Date.valueOf("2020-10-20"), itemsDTO.getBookedDate());

	            
	         //存在しない主キーを指定した場合、NULLを返す事を確認する
	         itemsDTO = dao.findById("user", 9999);
	         assertNull(itemsDTO);
	         
	         itemsDTO = dao.findById("aaaaaaaa", 1);
	         assertNull(itemsDTO);
	         
	         itemsDTO = dao.findById("aaaaaaaa", 9999);
	         assertNull(itemsDTO);
	           
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	 
	 @Test
	   public void testFindById1() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao  = new ItemsInCartDAO(conn);
			 List<ItemsInCartDTO> itemsDTO = dao.findById("user"); 
			 
			 assertEquals(3, itemsDTO.size());
			 
	         //正しくDTOにレコードの値を詰めてるか確認する
			 ItemsInCartDTO dto = itemsDTO.get(0);
	         assertEquals("user", dto.getUserId());
	         assertEquals(1, dto.getItemId());
	         assertEquals(5, dto.getAmount());
	         assertEquals(java.sql.Date.valueOf("2020-10-20"), dto.getBookedDate());

	           //サイズ確認
	         itemsDTO = dao.findById("aaaaaaa"); 
	         assertEquals(0, itemsDTO.size());

	           
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	 
	 @Test
	   public void testFindAll() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao  = new ItemsInCartDAO(conn);
			 List<ItemsInCartDTO> itemsDTO = dao.findALL(); 
			 
			 assertEquals(3, itemsDTO.size());
			 
	         //正しくDTOにレコードの値を詰めてるか確認する
			 ItemsInCartDTO dto = itemsDTO.get(0);
	         assertEquals("user", dto.getUserId());
	         assertEquals(1, dto.getItemId());
	         assertEquals(5, dto.getAmount());
	         assertEquals(java.sql.Date.valueOf("2020-10-20"), dto.getBookedDate());

	         //サイズ確認
	         for (int i = 1; i < 4; i++) {
					dto.setItemId(i);

					dao.delete(dto);
				}
	         itemsDTO = dao.findALL();
	         assertEquals(0, itemsDTO.size());

	           
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	 
	 @Test
	 public void testInsert() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			 
			 ItemsInCartDTO dto = new ItemsInCartDTO();
			 
			 java.sql.Date sqlDate= java.sql.Date.valueOf("2020-10-20");
			 
			 dto.setUserId("user");
			 dto.setItemId(4);
			 dto.setAmount(7);
			 dto.setBookedDate(sqlDate);
			 
			 int result = dao.insert(dto);
			 
			 assertEquals(1, result);
			 
			 ItemsInCartDTO serch = dao.findById("user", 4);
			 
			 assertEquals("user", serch.getUserId());
	         assertEquals(4, serch.getItemId());
	         assertEquals(7, serch.getAmount());
	         assertEquals(sqlDate, serch.getBookedDate());
			 
			 
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	 }
	 
	 @Test
	 public void testUpdate() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			 
			 ItemsInCartDTO dto = new ItemsInCartDTO();
			 
			 java.sql.Date sqlDate= java.sql.Date.valueOf("2020-10-21");
			 
			 dto.setUserId("user");
			 dto.setItemId(3);
			 dto.setAmount(2);
			 dto.setBookedDate(sqlDate);
			 
			 int result = dao.update(dto);
			 
			 assertEquals(1, result);
			 
			 ItemsInCartDTO serch = dao.findById("user", 3);
			 
			 assertEquals("user", serch.getUserId());
	         assertEquals(3, serch.getItemId());
	         assertEquals(1, serch.getAmount());
	         assertEquals(sqlDate, serch.getBookedDate());
			 
			 
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	 }
	 
	 @Test
	 public void testDelete() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			 
			 ItemsInCartDTO dto = new ItemsInCartDTO();
			 
			 dto.setUserId("user");
			 dto.setItemId(3);
			 
			 int result = dao.delete(dto);
			 
			 assertEquals(1, result);
			 
			 ItemsInCartDTO serch = dao.findById("user", 3);
			 
			 assertNull(serch);
			 
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	 }
	 
	 

}
