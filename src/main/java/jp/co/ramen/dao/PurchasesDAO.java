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

	public int purchaseInsert(PurchasesDTO dto) throws SQLException {
		String sql = "INSERT INTO public.purchases( purchased_user, purchased_date, destination, cancel) VALUES ( ?, ?, ?, false)";
		try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, dto.getPurchased_user());
			ps.setDate(2, java.sql.Date.valueOf(dto.getPurchased_date()));
			ps.setString(3, dto.getDestination());
			int result = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				int purchaseId = rs.getInt("purchase_id");
				dto.setPurchase_id(purchaseId);

			}
			return result;
		}
	}

	//-----------------以下、UserDAOをコピペ----------------------------
	// ユーザーIDによる検索 
	public List<PurchasesDTO> findHistoryByUserId(String userId) throws SQLException {
		String sql = "SELECT p.purchase_id, p.purchased_user,p.purchased_date, p.destination, p.cancel, "
				+ "d.purchase_detail_id,d.purchase_id, d.amount, "
				+ "i.item_id, i.name, i.price, i.category_id, i.color, i.manufacturer, i.stock, i.recommended "
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
						currentPdto.setPurchased_user(rs.getString("purchased_user"));
						currentPdto.setPurchased_date(rs.getDate("purchased_date").toLocalDate());
						currentPdto.setDestination(rs.getString("destination"));
						currentPdto.setCancel(rs.getBoolean("cancel"));

						currentPdto.setPurchaseDetailsDto((new ArrayList<PurchaseDetailsDTO>()));
						list.add(currentPdto);
					}

					ItemsDTO Idto = new ItemsDTO();

					Idto.setItem_id(rs.getInt("item_id"));
					Idto.setName(rs.getString("name"));
					Idto.setColor(rs.getString("color"));
					Idto.setManufacturer(rs.getString("manufacturer"));
					Idto.setPrice(rs.getInt("price"));
					Idto.setStock(rs.getInt("stock"));
					Idto.setCategory_id(rs.getInt("category_id"));
					Idto.setRecommended(rs.getBoolean("recommended"));

					PurchaseDetailsDTO Ddto = new PurchaseDetailsDTO();
					Ddto.setPurchase_detail_id(rs.getInt("purchase_detail_id"));
					Ddto.setPurchase_id(rs.getInt("purchase_id"));
					Ddto.setItem_id(rs.getInt("item_id"));
					Ddto.setAmount(rs.getInt("amount"));

					Ddto.setIdto(Idto);
					//List<PurchaseDetailsDTO> dList = new ArrayList<>();

					currentPdto.getPurchaseDetailsDto().add(Ddto);

				}
			}
		}
		return list;
	}

	// 主キーによる検索 
	public PurchasesDTO findHistoryById(int purchase_id) throws SQLException {
		String sql = "SELECT p.purchase_id, p.purchased_user,p.purchased_date, p.destination, p.cancel "
				+ "FROM purchases p "
				+ "WHERE p.purchase_id = ? ";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchase_id);
			try (ResultSet rs = ps.executeQuery()) {
				PurchasesDTO purchasesDTO = null;

				if (rs.next()) {
					purchasesDTO = new PurchasesDTO();
					purchasesDTO.setPurchase_id(rs.getInt("purchase_id"));
					purchasesDTO.setPurchased_user(rs.getString("purchased_user"));
					purchasesDTO.setPurchased_date(rs.getDate("purchased_date").toLocalDate());
					purchasesDTO.setDestination(rs.getString("destination"));
					purchasesDTO.setCancel(rs.getBoolean("cancel"));

				}
				return purchasesDTO;

			}
		}

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
