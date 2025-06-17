package jp.co.axisb.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.sql.rowset.serial.SerialException;

import org.junit.platform.commons.function.Try;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.ConnectionUtil;

public class RemoveFromCartConfirmService {

	public List<ItemsInCartDTO> removefromcartconfirmservice() throws SQLException, ServletException{
		String lookupString = "java:comp/env/jdbc/ecsite";
		try(Connection conn = ConnectionUtil.getConnection(lookupString)){
		
			
			//DAOクラスのインスタンス化
			ItemsInCartDAO itemsDao = new ItemsInCartDAO(conn);
			

			//DTOのインスタンス化とDAOクラスのfindByIdメソッド呼び出し
			ItemsInCartDTO itemsDto = itemsDao.findById(null, searhItemId);
			
			
			


			//リターンでDTOを返す

			}
			
		
	}
	
	
	
		
		

}

}
