package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.AdministratorsDTO;

public class AdministratorsDAO extends BaseDAO {

	public AdministratorsDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//キー検索
	public AdministratorsDTO findById(String adminId) throws SQLException {

		AdministratorsDTO admindto = null;

		String sql = "SELECT admin_id, password, name FROM administrators WHERE admin_id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, adminId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				admindto = new AdministratorsDTO();
				admindto.setAdminId(rs.getString("admin_id"));
				admindto.setPassword(rs.getString("password"));
				admindto.setName(rs.getString("name"));

			}
		}
		return admindto;
	}

	//キー検索
	public AdministratorsDTO findById(String adminId, String password) throws SQLException {

		AdministratorsDTO admindto = null;

		String sql = "SELECT admin_id, password, name FROM administrators WHERE admin_id = ? and password = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, adminId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				admindto = new AdministratorsDTO();
				admindto.setAdminId(rs.getString("admin_id"));
				admindto.setPassword(rs.getString("password"));
				admindto.setName(rs.getString("name"));

			}
		}
		return admindto;
	}

	//全検索

	public List<AdministratorsDTO> findAll() throws SQLException {
		//AdministratorsDTO admindto = null;

		String sql = "SELECT admin_id, password, name FROM public.administrators";

		List<AdministratorsDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AdministratorsDTO admindto = new AdministratorsDTO();

				admindto.setAdminId(rs.getString("admin_id"));
				admindto.setPassword(rs.getString("password"));
				admindto.setName(rs.getString("name"));

				list.add(admindto);
			}
		}
		return list;
	}

}
