package jp.co.axisb.test.users;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import jp.co.axisb.dao.UsersDAO;
import jp.co.axisb.dto.UsersDTO;
import jp.co.axisb.util.ConnectionUtil;

public class UsersDAOtest {
	
	

	    @Test
	  public  void testFindById() {
	        
	        try(Connection conn = ConnectionUtil.getConnectionForJUnit()){
	            
	            UsersDAO dao  = new UsersDAO(conn);
	            
	            //正しくDTOにレコードの値を詰めてるか確認する
	            UsersDTO usersDTO = dao.findById("user1");
	            assertEquals("user1", usersDTO.getUserId());
	            assertEquals("userpass1", usersDTO.getPassword());
	            assertEquals("鳥取一郎", usersDTO.getName());
	            assertEquals("鳥取県鳥取市河原町６丁目１０７", usersDTO.getAddress());
	            
	            //存在しない主キーを指定した場合、NULLを返す事を確認する
	            usersDTO = dao.findById("user9999");
	            assertNull(usersDTO);
	           
	        }catch(Exception e) {
	            fail(e.getMessage());
	        }
	    }
	}


