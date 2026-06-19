package jp.co.ramen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.ramen.dto.PurchaseDetailsDTO;
import jp.co.ramen.dto.UserDTO;

public class PurchaseDetailsDAO {
	private Connection con;

	public PurchaseDetailsDAO(Connection con) {
		this.con = con;
	}

	// データの挿入 
	public int purchaseInsert(PurchaseDetailsDTO pdDto) throws SQLException {
		String sql = "INSERT INTO public.purchase_details( purchase_id, item_id, amount)	VALUES (?, ?, ?);";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, pdDto.getPurchase_id());
			ps.setInt(2, pdDto.getItem_id());
			ps.setInt(3, pdDto.getAmount());
			return ps.executeUpdate();
		}
	}

	// 主キーによる検索 
	public PurchaseDetailsDTO findById(int purchase_detail_id) throws SQLException {
		String sql = "SELECT purchase_detail_id, purchase_id, item_id, amount\r\n"
				+ "	FROM public.purchase_details WHERE purchase_detail_id = ?";
		PurchaseDetailsDTO pdDto = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, purchase_detail_id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					pdDto = new PurchaseDetailsDTO();
					pdDto.setPurchase_detail_id(rs.getInt("purchase_detail_id"));
					pdDto.setPurchase_id(rs.getInt("purchase_id"));
					pdDto.setItem_id(rs.getInt("item_id"));
					pdDto.setAmount(rs.getInt("amount"));

				}
			}
		}
		return pdDto; // 見つからない場合はnullを返す
	}

	// 全件検索 
	public List<UserDTO> findAll() throws SQLException {
		String sql = "SELECT user_id, password, name, address FROM public.users";
		List<UserDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
				UserDTO user = mapRow(rs);
				list.add(user);
			}
		}
		return list;
	}

	// データの更新 
	public int update(UserDTO user) throws SQLException {
		String sql = "UPDATE public.users SET password = ?, name = ?, address = ? WHERE user_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getAddress());
			//	ps.setString(4, user.getUserId());
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
		//	user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		return user;
	}
}
