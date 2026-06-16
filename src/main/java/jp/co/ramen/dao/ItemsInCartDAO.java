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

	public List<ItemsInCartDTO> findAll(String SESSION) throws SQLException {
		String sql = "SELECT items.name, items.color, items.manufacturer, items.price, items_in_cart.amount \n"
				+ "FROM items_in_cart \n"
				+ "INNER JOIN items ON items_in_cart.item_id = items.item_id\n"
				+ "WHERE items_in_cart.user_id = ?";
		ArrayList<ItemsInCartDTO> iicList = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, SESSION);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					ItemsInCartDTO iicDto = new ItemsInCartDTO();
					ItemsDTO idto = new ItemsDTO();
					idto.setName(rs.getString("name"));
					idto.setColor(rs.getString("color"));
					idto.setManufacturer(rs.getString("manufacturer"));
					idto.setPrice(rs.getInt("price"));
					iicDto.setItemsDto(idto);
					iicDto.setAmount(rs.getInt("amount"));
					iicList.add(iicDto);
				}
			} catch (Exception e) {
				System.out.println("SQLエラー");
			}
		} catch (Exception e) {
			System.out.println("２でエラー");
		}
		return iicList; // 見つからない場合はnullを返す
	}

	//	以下、開発途中
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

	//	以下、開発途中(？2つ）（村尾担当）
	public ItemsInCartDTO findByDoubleID(String SESSION) throws SQLException {
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

}
