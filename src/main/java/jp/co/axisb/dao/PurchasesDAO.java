package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.dto.PurchasesDTO;
import jp.co.axisb.dto.PurchasesDetailsDTO;
import jp.co.axisb.dto.UsersDTO;

public class PurchasesDAO extends BaseDAO {

	public PurchasesDAO(Connection conn) {
		super(conn);
	}

	// 主キー検索
	public PurchasesDTO findById(int purchaseId) throws SQLException {

		String sql = "SELECT purchases.purchase_id, purchases.purchased_user, purchases.purchased_date, purchases.destination, purchases.cancel,\n"
				+ "purchase_details.purchase_detail_id, purchase_details.item_id, purchase_details.amount,\n"
				+ "items.item_id, items.name, items.manufacturer,items.category_id\n"
				+ "FROM purchases inner join purchase_details\n"
				+ "ON purchases.purchase_id = purchase_details.purchase_id\n"
				+ "\n"
				+ "inner join items\n"
				+ "ON purchase_details.item_id = items.item_id\n"
				+ "where purchases.cancel = false";

		PurchasesDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto = new PurchasesDTO();
				UsersDTO users = new UsersDTO();
				PurchasesDetailsDAO pddto = new PurchasesDetailsDAO(conn);
				List<PurchasesDetailsDTO> purchasesDetails = pddto.findById(purchaseId);

				dto.setUserId(rs.getString("user_id"));
				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));

				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
				dto.setPurchaseDetailDTO(purchasesDetails);

				for (PurchasesDetailsDTO pd : purchasesDetails) {
					dto = new PurchasesDTO();
					ItemsDTO items = new ItemsDTO();
					pd.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
					pd.setPurchasesId(rs.getInt("purchase_id"));
					pd.setItemId(rs.getInt("item_id"));
					pd.setAmount(rs.getInt("amount"));
					pd.setItems(items);
				}

			}
		}
		return dto;
	}

	//public PurchasesDTO findByUserId(String userId) throws SQLException {
	//		
	//		String sql = "SELECT purchases.purchase_id, purchases.purchased_user, purchases.purchased_date, purchases.destination, purchases.cancel, --purchasesの取得列\n"
	//				+ "       purchase_details.purchase_detail_id, purchase_details.item_id, purchase_details.amount,			 	   --purchase_detailsの取得列	\n"
	//				+ "       items.name, items.manufacturer, items.category_id, items.color, items.price, items.stock, items.recommended         --itemsの取得列\n"
	//				+ "	\n"
	//				+ "	FROM public.purchases inner join purchase_details             --purchasesとpurchase_detailsの結合\n"
	//				+ "	ON purchases.purchase_id = purchase_details.purchase_id\n"
	//				+ "	\n"
	//				+ "	inner join items					      --purchase_detailsとitemsの結合\n"
	//				+ "	ON purchase_details.item_id = items.item_id";
	//		PurchasesDTO dto = null;
	//		
	//		try (PreparedStatement ps = conn.prepareStatement(sql)) {
	//			ps.setString(1, userId);
	//				
	//			ResultSet rs = ps.executeQuery();
	//			
	//			if (rs.next()) {
	//				dto = new PurchasesDTO();
	//				UsersDTO users = new UsersDTO();
	//				PurchasesDetailsDAO pddto = new PurchasesDetailsDAO(conn);
	//				List<PurchasesDetailsDTO> purchasesDetails = pddto.findByUserId(userId);
	//
	//				dto.setUserId(rs.getString("user_id"));
	//				dto.setPurchaseId(rs.getInt("purchase_id"));
	//				dto.setPurchasedDate(rs.getDate("purchased_date"));
	//
	//				dto.setDestination(rs.getString("destination"));
	//				dto.setCancel(rs.getBoolean("cancel"));
	//				dto.setUsers(users);
	//				dto.setPurchaseDetailDTO(purchasesDetails);
	//
	//			}
	//		}
	//
	//		return dto;
	//
	//	}

	// 全件検索
	public List<PurchasesDTO> findAll() throws SQLException {
		String sql = "SELECT purchases.purchase_id, purchases.purchased_user, purchases.purchased_date, purchases.destination, purchases.cancel,\n"
				+ "purchase_details.purchase_detail_id, purchase_details.item_id, purchase_details.amount,\n"
				+ "items.item_id, items.name, items.manufacturer,items.category_id\n"
				+ "FROM purchases inner join purchase_details\n"
				+ "ON purchases.purchase_id = purchase_details.purchase_id\n"
				+ "inner join items\n"
				+ "ON purchase_details.item_id = items.item_id\n"
				+ "where purchases.cancel = false";

		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

				PurchasesDTO dto = new PurchasesDTO();

				UsersDTO users = new UsersDTO();

				List<PurchasesDetailsDTO> list2 = dao.findById(rs.getInt("purchase_id"));
				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));
				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);

				for (PurchasesDetailsDTO pd : list2) {
					ItemsDTO items = new ItemsDTO();
					pd.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
					pd.setPurchasesId(rs.getInt("purchase_id"));
					pd.setItemId(rs.getInt("item_id"));
					pd.setAmount(rs.getInt("amount"));
					pd.setItems(items);
				}

				list.add(dto);

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

			UsersDTO users = new UsersDTO();
			ps.setString(1, users.getUserId());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getName());
			ps.setString(4, users.getAddress());

			PurchasesDetailsDTO pd = new PurchasesDetailsDTO();
			ps.setInt(1, pd.getPurchasesDetailsId());
			ps.setInt(2, pd.getPurchasesId());
			ps.setInt(3, pd.getItemId());
			ps.setInt(4, pd.getAmount());

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