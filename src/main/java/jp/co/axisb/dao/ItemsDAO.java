package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.axisb.dto.ItemsDTO;

public class ItemsDAO extends BaseDAO{
	
	public ItemsDAO(Connection conn) {
		super(conn);
	}

		public ItemsDTO findById(int itemId) throws SQLException {

			String sql = "SELECT * FROM items WHERE itemId = ?";

			ItemsDTO dto = null;

				try (PreparedStatement ps = conn.prepareStatement(sql)) {

					ps.setInt(1, itemId);
					
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						dto = new ItemsDTO();

						dto.setItemId(rs.getInt("item_id"));
					}
				}

			return dto;

		}

}
