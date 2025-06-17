package jp.co.axisb.test.items;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.ConnectionUtil;

public class ItemsDAOTest {
	
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
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsDAO dao  = new ItemsDAO(conn);
	            
	         //正しくDTOにレコードの値を詰めてるか確認する
	         ItemsDTO itemsDTO = dao.findById(1);
	         
	         assertNotNull(itemsDTO);
	         
	         assertEquals(1, itemsDTO.getItemId());
	         assertEquals("麦わら帽子", itemsDTO.getName());
	         assertEquals("日本帽子製造", itemsDTO.getManufacturer());
	         assertEquals(1, itemsDTO.getCategoryId());
	         assertEquals("黄色", itemsDTO.getColor());
	         assertEquals(4980, itemsDTO.getPrice());
	         assertEquals(12, itemsDTO.getStock());
	         assertEquals(false, itemsDTO.isRecommended());
	         assertEquals("帽子", itemsDTO.getCategories().getName());
	            
	         //存在しない主キーを指定した場合、NULLを返す事を確認する
	         itemsDTO = dao.findById(9999);
	         assertNull(itemsDTO);
	           
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	 
	 
	 
	 @Test
	 public void testFindByItemName() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsDAO dao  = new ItemsDAO(conn);
	            
	         //正しくDTOにレコードの値を詰めてるか確認する
	         List<ItemsDTO> itemsDTO = dao.findByItemName("麦わら", 1);

	         
	         assertEquals(2, itemsDTO.size());
	         
	         ItemsDTO dto = itemsDTO.get(0);
		     assertEquals(1, dto.getItemId());
		     assertEquals("麦わら帽子", dto.getName());
		     assertEquals("日本帽子製造", dto.getManufacturer());
		     assertEquals(1, dto.getCategoryId());
	         assertEquals("黄色", dto.getColor());
	         assertEquals(4980, dto.getPrice());
	         assertEquals(12, dto.getStock());
	         assertEquals(false, dto.isRecommended());
	         assertEquals("帽子", dto.getCategories().getName());
	            
	         //存在しない主キーを指定した場合、NULLを返す事を確認する
	         itemsDTO = dao.findByItemName("田中", 1);
	         assertEquals(0, itemsDTO.size());
	         
	       //存在しない主キーを指定した場合、NULLを返す事を確認する
	         itemsDTO = dao.findByItemName("麦わら", 9999);
	         assertEquals(0, itemsDTO.size());
	         
	         //キーワードがない場合
	         itemsDTO = dao.findByItemName("", 1);
	         
	         assertEquals(11, itemsDTO.size());
	         
	         ItemsDTO dto2 = itemsDTO.get(0);
		     assertEquals(1, dto2.getItemId());
		     assertEquals("麦わら帽子", dto2.getName());
		     assertEquals("日本帽子製造", dto2.getManufacturer());
		     assertEquals(1, dto2.getCategoryId());
	         assertEquals("黄色", dto2.getColor());
	         assertEquals(4980, dto2.getPrice());
	         assertEquals(12, dto2.getStock());
	         assertEquals(false, dto2.isRecommended());
	         assertEquals("帽子", dto2.getCategories().getName());
	         
	           
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	 }
	 
	 @Test
	 public void testUpdate() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsDAO dao = new ItemsDAO(conn);
			 
			 ItemsDTO dto = new ItemsDTO();
			 dto.setStock(1);
			 dto.setItemId(1);
			 
			 int result = dao.update(dto);
			 
			 assertEquals(1, result);
			 
			 ItemsDTO serch = dao.findById(1);
			 
			 assertEquals(1, serch.getItemId());
			 assertEquals(11, serch.getStock());
			 
			 
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	 }

}
