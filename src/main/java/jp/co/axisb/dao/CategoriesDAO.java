package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.CategoriesDTO;

public class CategoriesDAO extends BaseDAO{

	public CategoriesDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CategoriesDTO findById(int categoryId) throws SQLException {
		
		CategoriesDTO categoriesdto = null;
	
	CategoriesDTO dto = null;
	
	public CategoriesDTO findById(int categoryId) throws SQLException{
		

		String sql = "SELECT * FROM categories WHERE category_id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
<<<<<<< HEAD
				categoriesdto = new CategoriesDTO();

				categoriesdto.setCotegoryId(rs.getInt("category_id"));
				categoriesdto.setName(rs.getString("name"));
=======
				dto =new CategoriesDTO();
				
				dto.setCotegoryId(rs.getInt("category_id"));
				dto.setName(rs.getString("name"));	
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
			}
		}
<<<<<<< HEAD
		return categoriesdto;
=======
		return dto;	
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
	}
<<<<<<< HEAD

	public List<CategoriesDTO> findAll() throws SQLException {
		CategoriesDTO categoriesdto = null;
		
=======
	public List<CategoriesDTO>findAll() throws SQLException{
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
		String sql = "SELECT * FROM categories";
		
		List<CategoriesDTO> list = new ArrayList<>();
<<<<<<< HEAD

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				categoriesdto = new CategoriesDTO();

				categoriesdto.setCotegoryId(rs.getInt("category_id"));
				categoriesdto.setName(rs.getString("name"));

				list.add(categoriesdto);
=======
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			 ResultSet rs = ps.executeQuery();
			 
			 if (rs.next()) {
				 dto = new CategoriesDTO();
				 
				dto.setCotegoryId(rs.getInt("category_id"));
				dto.setName(rs.getString("name"));	
				
			list.add(dto);
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
			}
		}
		return list;
	}
}
