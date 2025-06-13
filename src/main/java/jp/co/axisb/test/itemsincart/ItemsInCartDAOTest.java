package jp.co.axisb.test.itemsincart;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;

import org.junit.Test;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.ConnectionUtil;

public class ItemsInCartDAOTest {
	
	 @Test
	   public void testFindById() {
		 try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
			 ItemsInCartDAO dao  = new ItemsInCartDAO(conn);
	            
	         //正しくDTOにレコードの値を詰めてるか確認する
	         ItemsInCartDTO itemsDTO = dao.findById(1);
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

}
