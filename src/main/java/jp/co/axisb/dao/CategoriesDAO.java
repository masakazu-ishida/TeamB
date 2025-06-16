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
	
		String sql = "SELECT * FROM categories WHERE category_id = ?";
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				categoriesdto = new CategoriesDTO();

				categoriesdto.setCotegoryId(rs.getInt("category_id"));
				categoriesdto.setName(rs.getString("name"));
			
			}
		}
		return categoriesdto;
	}



	public List<CategoriesDTO>findAll() throws SQLException{
		//CategoriesDTO categoriesdto = null;➡５４行目の書き方が正しい
		
		String sql = "SELECT category_id, name FROM public.categories";
		
		List<CategoriesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CategoriesDTO categoriesdto = new CategoriesDTO();

				categoriesdto.setCotegoryId(rs.getInt("category_id"));
				categoriesdto.setName(rs.getString("name"));

				list.add(categoriesdto);

			}
		}
		return list;
	}
}
	