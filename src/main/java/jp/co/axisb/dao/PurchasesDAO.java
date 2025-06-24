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
				+ "purchase_details.purchase_detail_id, purchase_details.item_id AS purchaseDetailsItemId, purchase_details.amount, purchase_details.purchase_id AS purchaseDetailsPurchaseId,\n"
				+ "items.item_id AS itemsItemId, items.name, items.manufacturer,items.category_id,\n"
				+ "users.user_id\n"
				+ "FROM purchases inner join purchase_details\n"
				+ "ON purchases.purchase_id = purchase_details.purchase_id\n"
				+ "inner join items\n"
				+ "ON purchase_details.item_id = items.item_id\n"
				+ "inner join users\n"
				+ "ON purchases.purchased_user = users.user_id\n"
				+ "where purchases.cancel = false and purchases.purchase_id = ?";

		PurchasesDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

				dto = new PurchasesDTO();

				ItemsDTO items = new ItemsDTO();
				UsersDTO users = new UsersDTO();

				List<PurchasesDetailsDTO> list2 = dao.findById(rs.getInt("purchase_id"));

				items.setCategoryId(rs.getInt("category_id"));
				items.setItemId(rs.getInt("itemsItemId"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setName(rs.getString("name"));

				users.setUserId(rs.getString("user_id"));

				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedUser(rs.getString("user_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));
				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
				dto.setItems(items);

				for (PurchasesDetailsDTO pd : list2) {
					PurchasesDetailsDTO purchasesDetails = new PurchasesDetailsDTO();
					pd.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
					pd.setPurchasesId(rs.getInt("purchase_id"));
					pd.setItemId(rs.getInt("purchaseDetailsItemId"));
					pd.setAmount(rs.getInt("amount"));
					pd.setItems(items);

				}
				dto.setPurchaseDetailDTO(list2);

			}
		}
		return dto;
	}

	// 主キーのリスト検索
	public List<PurchasesDTO> findListById(int purchaseId) throws SQLException {

		String sql = "SELECT purchases.purchase_id, purchases.purchased_user, purchases.purchased_date, purchases.destination, purchases.cancel,\n"
				+ "purchase_details.purchase_detail_id, purchase_details.item_id AS purchaseDetailsItemId, purchase_details.amount, purchase_details.purchase_id AS purchaseDetailsPurchaseId,\n"
				+ "items.item_id AS itemsItemId, items.name, items.manufacturer,items.category_id,\n"
				+ "users.user_id\n"
				+ "FROM purchases inner join purchase_details\n"
				+ "ON purchases.purchase_id = purchase_details.purchase_id\n"
				+ "inner join items\n"
				+ "ON purchase_details.item_id = items.item_id\n"
				+ "inner join users\n"
				+ "ON purchases.purchased_user = users.user_id\n"
				+ "where purchases.cancel = false and purchases.purchase_id = ?";

		PurchasesDTO dto = null;
		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, purchaseId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

				dto = new PurchasesDTO();

				ItemsDTO items = new ItemsDTO();
				UsersDTO users = new UsersDTO();

				List<PurchasesDetailsDTO> list2 = dao.findById(rs.getInt("purchase_id"));

				items.setCategoryId(rs.getInt("category_id"));
				items.setItemId(rs.getInt("itemsItemId"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setName(rs.getString("name"));

				users.setUserId(rs.getString("user_id"));

				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedUser(rs.getString("user_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));
				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
				dto.setItems(items);

				for (PurchasesDetailsDTO pd : list2) {
					PurchasesDetailsDTO purchasesDetails = new PurchasesDetailsDTO();
					pd.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
					pd.setPurchasesId(rs.getInt("purchase_id"));
					pd.setItemId(rs.getInt("purchaseDetailsItemId"));
					pd.setAmount(rs.getInt("amount"));
					pd.setItems(items);

				}
				dto.setPurchaseDetailDTO(list2);

				list.add(dto);
			}
		}
		return list;
	}

	// 主キーのリスト検索
	public List<PurchasesDTO> findListByUserId(String userId) throws SQLException {

		String sql = "SELECT purchases.purchase_id, purchases.purchased_user, purchases.purchased_date, purchases.destination, purchases.cancel,\n"
				+ "purchase_details.purchase_detail_id, purchase_details.item_id AS purchaseDetailsItemId, purchase_details.amount, purchase_details.purchase_id AS purchaseDetailsPurchaseId,\n"
				+ "items.item_id AS itemsItemId, items.name, items.manufacturer,items.category_id, items.color,items.price,\n"
				+ "users.user_id\n"
				+ "FROM purchases inner join purchase_details\n"
				+ "ON purchases.purchase_id = purchase_details.purchase_id\n"
				+ "inner join items\n"
				+ "ON purchase_details.item_id = items.item_id\n"
				+ "inner join users\n"
				+ "ON purchases.purchased_user = users.user_id\n"
				+ "where purchases.cancel = false and purchases.purchased_user = ?";

		PurchasesDTO dto = null;
		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userId);
			System.out.println(ps);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

				dto = new PurchasesDTO();

				ItemsDTO items = new ItemsDTO();
				UsersDTO users = new UsersDTO();

				List<PurchasesDetailsDTO> list2 = dao.findById(rs.getInt("purchase_id"));

				items.setCategoryId(rs.getInt("category_id"));
				items.setItemId(rs.getInt("itemsItemId"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setName(rs.getString("name"));
				items.setColor(rs.getString("color"));
				items.setPrice(rs.getInt("price"));

				users.setUserId(rs.getString("user_id"));

				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedUser(rs.getString("purchased_user"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));
				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
				dto.setItems(items);

				for (PurchasesDetailsDTO pd : list2) {
					PurchasesDetailsDTO purchasesDetails = new PurchasesDetailsDTO();
					pd.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
					pd.setPurchasesId(rs.getInt("purchase_id"));
					pd.setItemId(rs.getInt("purchaseDetailsItemId"));
					pd.setAmount(rs.getInt("amount"));
					pd.setItems(items);

				}
				dto.setPurchaseDetailDTO(list2);

				list.add(dto);
			}
		}
		return list;
	}

	// 全件検索
	public List<PurchasesDTO> findAll() throws SQLException {
		String sql = "SELECT purchases.purchase_id, purchases.purchased_user, purchases.purchased_date, purchases.destination, purchases.cancel,\n"
				+ "purchase_details.purchase_detail_id, purchase_details.item_id AS purchaseDetailsItemId, purchase_details.amount, purchase_details.purchase_id AS purchaseDetailsPurchaseId, \n"
				+ "items.item_id AS itemsItemId, items.name, items.manufacturer,items.category_id, \n"
				+ "users.user_id \n"
				+ "FROM purchases inner join purchase_details\n"
				+ "ON purchases.purchase_id = purchase_details.purchase_id\n"
				+ "inner join items\n"
				+ "ON purchase_details.item_id = items.item_id\n"
				+ "inner join users\n"
				+ "ON purchases.purchased_user = users.user_id\n"
				+ "where purchases.cancel = false";

		List<PurchasesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				PurchasesDetailsDAO dao = new PurchasesDetailsDAO(conn);

				PurchasesDTO dto = new PurchasesDTO();

				ItemsDTO items = new ItemsDTO();
				UsersDTO users = new UsersDTO();

				List<PurchasesDetailsDTO> list2 = dao.findById(rs.getInt("purchase_id"));

				items.setCategoryId(rs.getInt("category_id"));
				items.setItemId(rs.getInt("itemsItemId"));
				items.setManufacturer(rs.getString("manufacturer"));
				items.setName(rs.getString("name"));

				users.setUserId(rs.getString("user_id"));

				dto.setPurchaseId(rs.getInt("purchase_id"));
				dto.setPurchasedUser(rs.getString("user_id"));
				dto.setPurchasedDate(rs.getDate("purchased_date"));
				dto.setDestination(rs.getString("destination"));
				dto.setCancel(rs.getBoolean("cancel"));
				dto.setUsers(users);
				dto.setItems(items);

				for (PurchasesDetailsDTO pd : list2) {
					PurchasesDetailsDTO purchasesDetails = new PurchasesDetailsDTO();
					pd.setPurchasesDetailsId(rs.getInt("purchase_detail_id"));
					pd.setPurchasesId(rs.getInt("purchase_id"));
					pd.setItemId(rs.getInt("purchaseDetailsItemId"));
					pd.setAmount(rs.getInt("amount"));
					pd.setItems(items);

				}
				dto.setPurchaseDetailDTO(list2);

				list.add(dto);

			}
		}
		return list;
	}

	// 追加
	public int insert(PurchasesDTO dto) throws SQLException {
		String sql = "insert into purchases(purchased_user, purchased_date, destination, cancel) values(?, ?, ?, ?)";
		int updateNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			//			ps.setInt(1, dto.getPurchaseId());
			ps.setString(1, dto.getPurchasedUser());
			ps.setDate(2, dto.getPurchasedDate());
			ps.setString(3, dto.getDestination());
			ps.setBoolean(4, dto.isCancel());

			//			UsersDTO users = new UsersDTO();
			//			ps.setString(1, users.getUserId());
			//			ps.setString(2, users.getPassword());
			//			ps.setString(3, users.getName());
			//			ps.setString(4, users.getAddress());
			//
			//			PurchasesDetailsDTO pd = new PurchasesDetailsDTO();
			//			ps.setInt(1, pd.getPurchasesDetailsId());
			//			ps.setInt(2, pd.getPurchasesId());
			//			ps.setInt(3, pd.getItemId());
			//			ps.setInt(4, pd.getAmount());

			updateNum = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int purchaseId = -1;//適当な初期値
			if (rs.next()) {
				// シーケンス名を指定して取得（プロジェクト指定）
				purchaseId = rs.getInt("purchase_id");
				dto.setPurchaseId(purchaseId);
			}

		}
		return updateNum;

	}

	// 更新
	public int update(PurchasesDTO dto) throws SQLException {
		String sql = "update purchases set purchased_user = ?,  purchased_date = ?, destination = ?, cancel = ? where purchase_id = ?";

		int updateNum = 0;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			//			ps.setInt(1, dto.getPurchaseId());
			ps.setString(1, dto.getPurchasedUser());
			ps.setDate(2, dto.getPurchasedDate());
			ps.setString(3, dto.getDestination());
			ps.setBoolean(4, dto.isCancel());
			ps.setInt(5, dto.getPurchaseId());

			//			UsersDTO users = new UsersDTO();
			//			ps.setString(1, users.getUserId());
			//			ps.setString(2, users.getPassword());
			//			ps.setString(3, users.getName());
			//			ps.setString(4, users.getAddress());
			//
			//			PurchasesDetailsDTO pd = new PurchasesDetailsDTO();
			//			ps.setInt(1, pd.getPurchasesDetailsId());
			//			ps.setInt(2, pd.getPurchasesId());
			//			ps.setInt(3, pd.getItemId());
			//			ps.setInt(4, pd.getAmount());

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