package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.CategoriesDTO;
import jp.co.axisb.dto.ItemsDTO;

public class ItemsDAO extends BaseDAO{
	
	public ItemsDAO(Connection conn) {
		super(conn);
	}

	public ItemsDTO findById(int itemId) throws SQLException {
		
		String sql = "SELECT * FROM items WHERE item_id = ?";
		ItemsDTO dto = null;
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, itemId);
				
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				dto = new ItemsDTO();
				CategoriesDTO categoriesDTO = new CategoriesDTO();

				dto.setItemId(rs.getInt("item_id"));
				dto.setName(rs.getString("name"));
				dto.setManufacturer(rs.getString("manufacuture"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setColor(rs.getString("color"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setRecommended(rs.getBoolean("recommended"));
				dto.setCategories(categoriesDTO);
			}
		}

		return dto;

	}
		
	public List<ItemsDTO> findByItemName(String keyword) throws SQLException{
		//実行するSQL文
		String sql = "SELECT * FROM items WHERE name LIKE '%' || '?' || '%'";

		List<ItemsDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, keyword);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemsDTO dto = new ItemsDTO();
				CategoriesDTO categoriesDTO = new CategoriesDTO();
				
				dto.setItemId(rs.getInt("item_id"));
				dto.setName(rs.getString("name"));
				dto.setManufacturer(rs.getString("manufacuture"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setColor(rs.getString("color"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setRecommended(rs.getBoolean("recommended"));
				dto.setCategories(categoriesDTO);

				list.add(dto);
			}

		}

		return list;
	}
	
	//降順にソートした一覧情報を表示
		public List<ItemsDTO> findALL() throws SQLException {

			String sql = "SELECT * FROM items";

			List<ItemsDTO> list = new ArrayList<>();

			try (PreparedStatement ps = conn.prepareStatement(sql)) {

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					ItemsDTO dto = new ItemsDTO();
					CategoriesDTO categoriesDTO = new CategoriesDTO();

					dto.setItemId(rs.getInt("item_id"));
					dto.setName(rs.getString("name"));
					dto.setManufacturer(rs.getString("manufacuture"));
					dto.setCategoryId(rs.getInt("category_id"));
					dto.setColor(rs.getString("color"));
					dto.setPrice(rs.getInt("price"));
					dto.setStock(rs.getInt("stock"));
					dto.setRecommended(rs.getBoolean("recommended"));
					dto.setCategories(categoriesDTO);
					
					list.add(dto);
				}
			}

			return list;

		}
		
		public int update(ItemsDTO dto) throws SQLException {

			String sql = "UPDATE items SET stock = ? where item_id = ?";

			int updateLowNum = 0;

			try (PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, dto.getStock());
				ps.setInt(2, dto.getItemId());

				updateLowNum = ps.executeUpdate();
			}
			
			return updateLowNum;

		}

}
