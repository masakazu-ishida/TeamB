package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.dto.UsersDTO;

public class ItemsInCartDAO extends BaseDAO {

	public ItemsInCartDAO(Connection conn) {
		super(conn);
	}

	public ItemsInCartDTO findById(String userId, int itemId) throws SQLException {

		String sql = "SELECT items_in_cart.user_id AS itemsInCartUserId, items_in_cart.item_id AS itemsInCartItemId, items_in_cart.amount, items_in_cart.booked_date, \n"
				+ "users.user_id AS usersUserId, users.password, users.name AS usersName, users.address, \n"
				+ "items.item_id AS itemsItemId, items.name AS itemsName, items.manufacturer, items.category_id, items.color, items.price, items.stock, items.recommended \n"
				+ "FROM items_in_cart INNER JOIN users ON items_in_cart.user_id = users.user_id INNER JOIN items ON items_in_cart.item_id = items.item_id "
				+ "WHERE items_in_cart.user_id = ? and items_in_cart.item_id = ?";
		ItemsInCartDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userId);
			ps.setInt(2, itemId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto = new ItemsInCartDTO();
				UsersDTO users = new UsersDTO();
				ItemsDTO items = new ItemsDTO();

				users.setUserId(rs.getString("itemsInCartUserId"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("usersName"));
				users.setAddress(rs.getString("address"));

				items.setItemId(rs.getInt("itemsItemId"));
				items.setName(rs.getString("itemsName"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setCategoryId(rs.getInt("category_id"));
				items.setColor(rs.getString("color"));
				items.setPrice(rs.getInt("price"));
				items.setStock(rs.getInt("stock"));
				items.setRecommended(rs.getBoolean("recommended"));

				dto.setUserId(rs.getString("itemsInCartUserId"));
				dto.setItemId(rs.getInt("itemsInCartItemId"));
				dto.setAmount(rs.getInt("amount"));
				dto.setBookedDate((java.util.Date) rs.getDate("booked_date"));
				dto.setUsers(users);
				dto.setItems(items);

			}
		}

		return dto;

	}

	public List<ItemsInCartDTO> findById(String userId) throws SQLException {

		String sql = "SELECT items_in_cart.user_id AS itemsInCartUserId, items_in_cart.item_id AS itemsInCartItemId, items_in_cart.amount, items_in_cart.booked_date, \n"
				+ "users.user_id AS usersUserId, users.password, users.name AS usersName, users.address, \n"
				+ "items.item_id AS itemsItemId, items.name AS itemsName, items.manufacturer, items.category_id, items.color, items.price, items.stock, items.recommended \n"
				+ "FROM items_in_cart INNER JOIN users ON items_in_cart.user_id = users.user_id INNER JOIN items ON items_in_cart.item_id = items.item_id "
				+ "WHERE items_in_cart.user_id = ?";
		ItemsInCartDTO dto = null;
		List<ItemsInCartDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				dto = new ItemsInCartDTO();
				UsersDTO users = new UsersDTO();
				ItemsDTO items = new ItemsDTO();

				users.setUserId(rs.getString("itemsInCartUserId"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("usersName"));
				users.setAddress(rs.getString("address"));

				items.setItemId(rs.getInt("itemsItemId"));
				items.setName(rs.getString("itemsName"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setCategoryId(rs.getInt("category_id"));
				items.setColor(rs.getString("color"));
				items.setPrice(rs.getInt("price"));
				items.setStock(rs.getInt("stock"));
				items.setRecommended(rs.getBoolean("recommended"));

				dto.setUserId(rs.getString("itemsInCartUserId"));
				dto.setItemId(rs.getInt("itemsInCartItemId"));
				dto.setAmount(rs.getInt("amount"));
				dto.setBookedDate((java.util.Date) rs.getDate("booked_date"));
				dto.setUsers(users);
				dto.setItems(items);

				list.add(dto);
			}
		}

		return list;

	}

	public List<ItemsInCartDTO> findALL() throws SQLException {

		String sql = "SELECT items_in_cart.user_id AS itemsInCartUserId, items_in_cart.item_id AS itemsInCartItemId, items_in_cart.amount, items_in_cart.booked_date, \n"
				+ "users.user_id AS usersUserId, users.password, users.name AS usersName, users.address, \n"
				+ "items.item_id AS itemsItemId, items.name AS itemsName, items.manufacturer, items.category_id, items.color, items.price, items.stock, items.recommended \n"
				+ "FROM items_in_cart INNER JOIN users ON items_in_cart.user_id = users.user_id INNER JOIN items ON items_in_cart.item_id = items.item_id";

		List<ItemsInCartDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemsInCartDTO dto = new ItemsInCartDTO();
				UsersDTO users = new UsersDTO();
				ItemsDTO items = new ItemsDTO();

				users.setUserId(rs.getString("itemsInCartUserId"));
				users.setPassword(rs.getString("password"));
				users.setName(rs.getString("usersName"));
				users.setAddress(rs.getString("address"));

				items.setItemId(rs.getInt("itemsItemId"));
				items.setName(rs.getString("itemsName"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setCategoryId(rs.getInt("category_id"));
				items.setColor(rs.getString("color"));
				items.setPrice(rs.getInt("price"));
				items.setStock(rs.getInt("stock"));
				items.setRecommended(rs.getBoolean("recommended"));

				dto.setUserId(rs.getString("itemsInCartUserId"));
				dto.setItemId(rs.getInt("itemsInCartItemId"));
				dto.setAmount(rs.getInt("amount"));
				dto.setBookedDate((java.util.Date) rs.getDate("booked_date"));
				dto.setUsers(users);
				dto.setItems(items);

				list.add(dto);
			}
		}

		return list;

	}

	public int insert(ItemsInCartDTO dto) throws SQLException {

		String sql = "INSERT INTO items_in_cart values(?, ?, ?, ?)";

		int updateLowNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dto.getUserId());
			ps.setInt(2, dto.getItemId());
			ps.setInt(3, dto.getAmount());
			ps.setDate(4, (java.sql.Date) dto.getBookedDate());

			updateLowNum = ps.executeUpdate();
		}

		return updateLowNum;
	}

	public int update(ItemsInCartDTO dto) throws SQLException {

		String sql = "UPDATE items_in_cart SET amount = (amount - ?), booked_date = ? where user_id = ? AND item_id = ?";

		int updateLowNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, dto.getAmount());
			ps.setDate(2, (java.sql.Date) dto.getBookedDate());
			ps.setString(3, dto.getUserId());
			ps.setInt(4, dto.getItemId());

			updateLowNum = ps.executeUpdate();
		}

		return updateLowNum;

	}

	public int delete(ItemsInCartDTO dto) throws SQLException {

		String sql = "DELETE FROM items_in_cart where user_id = ? and item_id = ?";

		int updateLowNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, dto.getUserId());
			ps.setInt(2, dto.getItemId());

			updateLowNum = ps.executeUpdate();
		}

		return updateLowNum;
	}

}
