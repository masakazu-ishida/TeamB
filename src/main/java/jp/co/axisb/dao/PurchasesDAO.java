package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.dto.UsersDTO;

public class PurchasesDAO extends BaseDAO {
	public PurchasesDAO(Connection conn) {
		super(conn);
	}

	// 主キー検索
	public PurchasesDTO findById(int purchaseId) throws SQLException {

		String sql = "select * from purchases where purchase_id = ?";

		PurchasesDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, purchaseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto = new PurchasesDTO();
				UsersDTO users = new UsersDTO();
				PurchasesDetailsDAO pddto = new PurchasesDetailsDAO(conn);
				List<PurchasesDetailsDTO> purchasesDetails = pddto.findById(purchaseId);

				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));

				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
				dto.setPurchaseDetailDTO(purchasesDetails);

			}
		}
		return dto;
	}

	// 全件検索
	public List<PurchasesDTO> findAll() throws SQLException {
		String sql = "select * from purchases";

		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PurchasesDTO dto = new PurchasesDTO();

				UsersDTO users = new UsersDTO();

				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));

				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
			}
		}
		return list;
	}

	// 追加
	public int insert(PurchasesDTO dto) throws SQLException {
		String sql = "insert into purchases(purchase_id, purchased_user, purchased_date, destination, cancel) values(?, ?, ?, ?, ?)";
		int updateNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, dto.getPurchaseId());
			ps.setString(2, dto.getPurchasedUser());
			ps.setDate(3, dto.getPurchasedDate());
			ps.setString(4, dto.getDestination());
			ps.setBoolean(5, dto.isCancel());
			updateNum = ps.executeUpdate();
		}
		return updateNum;

	}

	// 更新
	public int update(PurchasesDTO dto) throws SQLException {
		String sql = "update purchases set purchase_id = ?, purchased_user = ?,  purchased_date = ?, destination = ?, cancel = ? where purchase_id = ?";

		int updateNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, dto.getPurchaseId());
			ps.setString(2, dto.getPurchasedUser());
			ps.setDate(3, dto.getPurchasedDate());
			ps.setString(4, dto.getDestination());
			ps.setBoolean(5, dto.isCancel());
			updateNum = ps.executeUpdate();

		}
		return updateNum;
	}
	
	public int delete(PurchasesDTO dto) throws SQLException {
		String sql = "DELETE FROM purchases WHERE purchase_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, dto.getPurchaseId());

			return ps.executeUpdate();
		}

	}

}