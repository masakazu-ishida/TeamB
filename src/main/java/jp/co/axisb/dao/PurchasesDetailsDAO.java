package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.dto.PurchasesDetailsDTO;

public class PurchasesDetailsDAO extends BaseDAO {

	public PurchasesDetailsDAO(Connection conn) {
		super(conn);
	}

	public List<PurchasesDetailsDTO> findById(int purchasesId) throws SQLException {

		String sql = "select purchase_detail_id,purchase_id,item_id,amount from purchase_details where purchase_id = ?";

		List<PurchasesDetailsDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, purchasesId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PurchasesDetailsDTO dto = new PurchasesDetailsDTO();
				ItemsDTO item = new ItemsDTO();

				dto.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
				dto.setPurchasesId(rs.getInt("purchase_id"));
				dto.setItemId(rs.getInt("item_id"));
				dto.setAmount(rs.getInt("amount"));
				dto.setItems(item);
				list.add(dto);
			}
		}
		return list;
	}

	public int insert(PurchasesDetailsDTO dto) throws SQLException {

		String sql = "insert into purchase_details(purchase_id,item_id,amount) values(?,?,?)";

		int updateLowNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, dto.getPurchasesId());
			ps.setInt(2, dto.getItemId());
			ps.setInt(3, dto.getAmount());

			updateLowNum = ps.executeUpdate();

			return updateLowNum;

		}

	}

}
