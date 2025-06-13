package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.AdministratorsDTO;

public class AdministratorsDAO extends BaseDAO{

		public AdministratorsDAO(Connection conn) {
			super(conn);
			// TODO 自動生成されたコンストラクター・スタブ
		}
		
		AdministratorsDTO dto = null;
		
	//キー検索
	public AdministratorsDTO findById(String adminId) throws SQLException {
			
		String sql = "SELECT * FROM administrators WHERE admin_id = ?";			
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
				
			ps.setString(1, adminId);
				
			ResultSet rs = ps.executeQuery();
				
				if (rs.next()) { 
				dto = new AdministratorsDTO();
				dto.setAdminId("adminId");
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
			}
		}
		return dto;
	}
	
	//全検索
	public List<AdministratorsDTO>findAll() throws SQLException{
	
		String sql = "SELECT * FROM administrators";
		List<AdministratorsDTO> list = new ArrayList<>();
		
		try(PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				AdministratorsDTO dto = new AdministratorsDTO();
				
				dto.setAdminId(rs.getString("admin_id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				
				list.add(dto);
				
			}
		}
		return list;
	}
}
