package jp.co.axisb.test.itemsincart;

import static org.junit.Assert.assertNotNull;
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
	         
	         assertNotNull(itemsDTO);
	         
	         assertEquals("user", itemsDTO.getUserId());
	         assertEquals(1, itemsDTO.getItemId());
	         assertEquals(5, itemsDTO.getAmount());
	         assertEquals(java.sql.Date.valueOf("2020-10-20"), itemsDTO.getBookedDate());
	         assertEquals("userpass", itemsDTO.getUsers().getPassword());
	         assertEquals("鳥取一郎", itemsDTO.getUsers().getName());
	         assertEquals("鳥取県鳥取市河原町６丁目１０７", itemsDTO.getUsers().getAddress());
	         assertEquals("麦わら帽子", itemsDTO.getItems().getName());
	         assertEquals("日本帽子製造", itemsDTO.getItems().getManufacturer());
	         assertEquals(1, itemsDTO.getItems().getCategoryId());
	         assertEquals("黄色", itemsDTO.getItems().getColor());
	         assertEquals(4980, itemsDTO.getItems().getPrice());
	         assertEquals(12, itemsDTO.getItems().getStock());
	         assertEquals(false, itemsDTO.getItems().isRecommended());
	            
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
	         assertEquals("userpass", dto.getUsers().getPassword());
	         assertEquals("鳥取一郎", dto.getUsers().getName());
	         assertEquals("鳥取県鳥取市河原町６丁目１０７", dto.getUsers().getAddress());
	         assertEquals("麦わら帽子", dto.getItems().getName());
	         assertEquals("日本帽子製造", dto.getItems().getManufacturer());
	         assertEquals(1, dto.getItems().getCategoryId());
	         assertEquals("黄色", dto.getItems().getColor());
	         assertEquals(4980, dto.getItems().getPrice());
	         assertEquals(12, dto.getItems().getStock());
	         assertEquals(false, dto.getItems().isRecommended());

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
	         assertEquals("userpass", dto.getUsers().getPassword());
	         assertEquals("鳥取一郎", dto.getUsers().getName());
	         assertEquals("鳥取県鳥取市河原町６丁目１０７", dto.getUsers().getAddress());
	         assertEquals("麦わら帽子", dto.getItems().getName());
	         assertEquals("日本帽子製造", dto.getItems().getManufacturer());
	         assertEquals(1, dto.getItems().getCategoryId());
	         assertEquals("黄色", dto.getItems().getColor());
	         assertEquals(4980, dto.getItems().getPrice());
	         assertEquals(12, dto.getItems().getStock());
	         assertEquals(false, dto.getItems().isRecommended());
	         
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
