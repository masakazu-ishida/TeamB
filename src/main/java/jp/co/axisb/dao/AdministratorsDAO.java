package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.axisb.dto.AdministratorsDTO;

public class AdministratorsDAO extends BaseDAO{

		public AdministratorsDAO(Connection conn) {
			super(conn);
			// TODO 自動生成されたコンストラクター・スタブ
		}
		
		AdministratorsDTO dto = null;
		
	public AdministratorsDTO findById(String adminId) throws SQLException {
			
		String sql = "SELECT * FROM administrators WHERE admin_id = ?";			
		try(PreparedStatement ps = conn.prepareStatement(sql)){
				
			ps.setString(1, adminId);
				
			ResultSet rs = ps.executeQuery();
				
				if (rs.next()) { 
				dto = new AdministratorsDTO();
				dto.setAdminId("adminId");
			}
		}
		return dto;
	}
}
