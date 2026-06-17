package jp.co.ramen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.ramen.dto.ItemsDTO;
import jp.co.ramen.dto.PurchaseDetailsDTO;
import jp.co.ramen.dto.PurchasesDTO;
import jp.co.ramen.dto.UserDTO;

public class PurchasesDAO {
	private Connection con;

	public PurchasesDAO(Connection con) {
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
	public List<PurchasesDTO> findHistoryByUserId(String userId) throws SQLException {
		String sql = "SELECT p.purchase_id, p.purchased_date, p.destination, "
				+ "d.purchase_detail_id, d.amount, "
				+ "i.item_id, i.name, i.price, i.color, i.manufacturer, i.stock "
				+ "FROM purchases p "
				+ "INNER JOIN purchase_details d ON p.purchase_id = d.purchase_id "
				+ "INNER JOIN items i ON d.item_id = i.item_id "
				+ "WHERE p.purchased_user = ? "
				+ "ORDER BY p.purchased_date DESC, p.purchase_id DESC";
		List<PurchasesDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					int currentPurchasedId = rs.getInt("purchase_id");
					PurchasesDTO currentPdto = null;
					for (PurchasesDTO p : list) {
						if (p.getPurchase_id() == currentPurchasedId) {
							currentPdto = p;
							break;
						}

					}
					if (currentPdto == null) {
						currentPdto = new PurchasesDTO();
						currentPdto.setPurchase_id(currentPurchasedId);
						currentPdto.setPurchased_date(rs.getDate("purchased_date"));
						currentPdto.setDestination(rs.getString("destination"));

						currentPdto.setPurchaseDetaillsDto((new ArrayList<PurchaseDetailsDTO>()));
						list.add(currentPdto);
					}

					ItemsDTO Idto = new ItemsDTO();

					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					Idto.setItem_id(rs.getInt("item_id"));
					Idto.setName(rs.getString("name"));
					Idto.setColor(rs.getString("color"));
					Idto.setManufacturer(rs.getString("manufacturer"));
					Idto.setPrice(rs.getInt("price"));
					Idto.setStock(rs.getInt("stock"));

					PurchaseDetailsDTO Ddto = new PurchaseDetailsDTO();
					Ddto.setPurchase_detail_id(rs.getInt("purchase_detail_id"));
					Ddto.setPurchase_id(rs.getInt("purchase_id"));
					Ddto.setItem_id(rs.getInt("item_id"));
					Ddto.setAmount(rs.getInt("amount"));

					Ddto.setIdto(Idto);
					//List<PurchaseDetailsDTO> dList = new ArrayList<>();

					currentPdto.getPurchaseDetaillsDto().add(Ddto);

				}
			}
		}
		return list;
	}

	// 全件検索 
	public List<ItemsDTO> findAll(int categoryId, String name) throws SQLException {
		String sql = null;

		if (categoryId == 3) {
			if (name == null || name.isEmpty()) {
				sql = "select name, color, manufacturer, price from items;";
			} else {
				sql = "select name, color, manufacturer, price from items where name like ?;";
			}
		} else {
			if (name == null || name.isEmpty()) {
				sql = "select name, color, manufacturer, price from items where category_id = ?;";
			} else {
				sql = "select name, color, manufacturer, price from items where category_id = ? and name like ?;";
			}
		}

		List<ItemsDTO> itemsList = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			if (categoryId == 3) {
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
					item.setName(rs.getString("name"));
					item.setColor(rs.getString("color"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setPrice(rs.getInt("price"));
					itemsList.add(item);
				}
			}
		}
		return itemsList;
	}

	// データの更新 
	public int update(UserDTO user) throws SQLException {
		String sql = "UPDATE public.users SET password = ?, name = ?, address = ? WHERE user_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getUserId());
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
