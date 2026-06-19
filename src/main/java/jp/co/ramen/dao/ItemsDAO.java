package jp.co.ramen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.dto.UserDTO;

public class ItemsDAO {
	private Connection con;

	public ItemsDAO(Connection con) {
		this.con = con;
	}

	public int insert(ItemsDTO user) throws SQLException {
		String sql = "INSERT INTO public.items(item_id, name, manufacturer, category_id, color, price, stock, recommended) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, user.getItem_id());
			ps.setString(2, user.getName());
			ps.setString(3, user.getManufacturer());
			ps.setInt(4, user.getCategory_id());
			ps.setString(5, user.getColor());
			ps.setInt(6, user.getPrice());
			ps.setInt(7, user.getStock());
			//			ps.setString(8, user.getRecommended());
			return ps.executeUpdate();
		}
	}

	//-----------------以下、UserDAOをコピペ----------------------------
	// 主キーによる検索 
	public ItemsDTO findById(int itemId) throws SQLException {
		String sql = "SELECT item_id, name, color, manufacturer, price, stock FROM items WHERE item_id = ?";
		ItemsDTO Idto = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, itemId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					Idto = new ItemsDTO();

					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					Idto.setItem_id(rs.getInt("item_id"));
					Idto.setName(rs.getString("name"));
					Idto.setColor(rs.getString("color"));
					Idto.setManufacturer(rs.getString("manufacturer"));
					Idto.setPrice(rs.getInt("price"));
					Idto.setStock(rs.getInt("stock"));

				}
			}
		}
		return Idto; // 見つからない場合はnullを返す
	}

	// 全件検索 
	public List<ItemsDTO> findByCondision(int categoryId, String name) throws SQLException {
		String sql = null;

		if (categoryId == 3) {
			if (name == null || name.isEmpty()) {
				sql = "select item_id,category_id,name, color, manufacturer, price,stock, recommended from items;";
			} else {
				sql = "select item_id,category_id,name, color, manufacturer, price ,stock, recommended from items where name like ?;";
			}
		} else {
			if (name == null || name.isEmpty()) {
				sql = "select item_id,category_id,name, color, manufacturer, price ,stock, recommended from items where category_id = ?;";
			} else {
				sql = "select item_id,category_id,name, color, manufacturer, price,stock, recommended from items where category_id = ? and name like ?;";
			}
		}

		List<ItemsDTO> itemsList = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			if (categoryId == 0) {
				if (name != null && !name.isEmpty()) {
					ps.setString(1, "%" + name + "%");
				}
			} else {
				if (name == null || name.isEmpty()) {
					ps.setInt(1, categoryId);
				} else {
					ps.setInt(1, categoryId);
					ps.setString(2, "%" + name + "%");
				}
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ItemsDTO item = new ItemsDTO();
					item.setItem_id(rs.getInt("item_id"));
					item.setCategory_id(rs.getInt("category_id"));
					item.setName(rs.getString("name"));
					item.setColor(rs.getString("color"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setPrice(rs.getInt("price"));
					item.setStock(rs.getInt("stock"));
					item.setRecommended(rs.getBoolean("recommended"));
					itemsList.add(item);
				}
			}
		}
		return itemsList;
	}

	// データの更新 
	public int purchaseUpdate(int amount, int itemId) throws SQLException {
		String sql = "update items set stock = (sotck - ?) where items.item_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, amount);
			ps.setInt(2, itemId);
			return ps.executeUpdate();
		}
	}

	// データの削除
	public int delete(String userId) throws SQLException {
		String sql = "DELETE FROM public.users WHERE user_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			return ps.executeUpdate();
		}
	}

	// ResultSetからエンティティへのマッピングを行う共通メソッド
	//主キー検索と全件検索で重複を防ぐため
	private UserDTO mapRow(ResultSet rs) throws SQLException {
		UserDTO user = new UserDTO();
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		return user;
	}
}
