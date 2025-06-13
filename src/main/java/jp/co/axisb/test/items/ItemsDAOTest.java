package jp.co.axisb.test.items;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.ConnectionUtil;

public class ItemsDAOTest {
	
	 @Test
	   public void testFindById() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsDAO dao  = new ItemsDAO(conn);
	            
	         //正しくDTOにレコードの値を詰めてるか確認する
	         ItemsDTO itemsDTO = dao.findById(1);
	         assertEquals(1, itemsDTO.getItemId());
	         assertEquals("麦わら帽子", itemsDTO.getName());
	         assertEquals("日本帽子製造", itemsDTO.getManufacturer());
	         assertEquals(1, itemsDTO.getCategoryId());
	         assertEquals("黄色", itemsDTO.getColor());
	         assertEquals(4980, itemsDTO.getPrice());
	         assertEquals(12, itemsDTO.getStock());
	         assertEquals(false, itemsDTO.isRecommended());
	            
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
			 int i = 1;
	         List<ItemsDTO> itemsDTO = dao.findByItemName("麦わら");

	         
	         assertEquals(2, itemDTO.size());
		         assertEquals(1, idto.getItemId());
		         assertEquals("麦わら帽子", idto.getName());
		         assertEquals("日本帽子製造", idto.getManufacturer());
		         assertEquals(1, idto.getCategoryId());
		         assertEquals("黄色", idto.getColor());
		         assertEquals(4980, idto.getPrice());
		         assertEquals(12, idto.getStock());
		         assertEquals(false, idto.isRecommended());
	            
	         //存在しない主キーを指定した場合、NULLを返す事を確認する
	         itemsDTO = dao.findByItemName("田中");
	         assertNull(itemsDTO);
	           
		 }catch(Exception e) {
	            fail(e.getMessage());
	        }
	 }
	 
	 @Test
	 public void testUpdate() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 
		 }
	 }

}
