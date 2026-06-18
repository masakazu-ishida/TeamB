package jp.co.ramen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.dto.ItemsInCartDTO;

public class ItemsInCartDAO {

	private Connection con;

	public ItemsInCartDAO(Connection con) {
		this.con = con;
	}

	public ItemsInCartDAO() {

	}

	public List<ItemsInCartDTO> findAll(String SESSION) throws SQLException {
		String sql = "SELECT items.item_id,items.name,items.manufacturer, items.category_id, items.color,"
				+ " items.price, items.stock, items.recommended,"
				+ "items_in_cart.user_id, items_in_cart.item_id,"
				+ " items_in_cart.amount, items_in_cart.booked_date \n"
				+ "FROM items_in_cart \n"
				+ "INNER JOIN items ON items_in_cart.item_id = items.item_id\n"
				+ "WHERE items_in_cart.user_id = ?";
		ArrayList<ItemsInCartDTO> iicList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, SESSION);
			System.out.println(ps);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					ItemsInCartDTO iicDto = new ItemsInCartDTO();
					ItemsDTO idto = new ItemsDTO();

					idto.setItem_id(rs.getInt("item_id"));
					idto.setName(rs.getString("name"));
					idto.setManufacturer(rs.getString("manufacturer"));
					idto.setCategory_id(rs.getInt("category_id"));
					idto.setColor(rs.getString("color"));
					idto.setPrice(rs.getInt("price"));
					idto.setStock(rs.getInt("stock"));
					idto.setRecommended(rs.getBoolean("recommended"));
					iicDto.setItemsDto(idto);

					iicDto.setUser_id(rs.getString("user_id"));
					iicDto.setItem_id(rs.getInt("item_id"));
					iicDto.setAmount(rs.getInt("amount"));
					iicDto.setBooked_date(rs.getDate("booked_date"));
					iicList.add(iicDto);
				}
			}

			return iicList;// 見つからない場合はnullを返す
		}
	}

	//	以下、開発途中（最上担当？）
	public ItemsInCartDTO findBySingleID(String SESSION) throws SQLException {
		String sql = "SELECT items.name, items.manufacturer, items.price, items_in_cart.amount \n"
				+ "FROM items_in_cart \n"
				+ "INNER JOIN items ON items_in_cart.item_id = items.item_id\n"
				+ "WHERE items_in_cart.user_id = ?";
		ItemsInCartDTO IicDto = null;
		ItemsDTO Idto = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, SESSION);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					Idto.setName(rs.getString("name"));
					Idto.setManufacturer(rs.getString("manufacturer"));
					Idto.setPrice(rs.getInt("amount"));
					IicDto.setItemsDto(Idto);
					IicDto.setAmount(rs.getInt("amount"));
					return IicDto;
				}
			}
		}
		return null; // 見つからない場合はnullを返す
	}

	//カート内から削除対象の行を取り出す　←テストクリア

	public ItemsInCartDTO findById(String userId, int itemId) throws SQLException {
		String sql = "SELECT * FROM items_in_cart WHERE user_id = ? AND item_id = ?";
		ItemsInCartDTO dto = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			ps.setInt(2, itemId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					dto.setUser_id(rs.getString("user_id"));
					dto.setItem_id(rs.getInt("item_id"));
					dto.setAmount(rs.getInt("amount"));

				}
			}
		}
		return dto;
	}

	//カート内削除完了
	public void deleteCartItem(String userId, int itemId) throws SQLException {
		String sql = "delete from items_in_cart where user_id=? and item_id=?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		}
	}

}
