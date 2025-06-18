package jp.co.axisb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dto.UsersDTO;

public class UsersDAO extends BaseDAO {

	public UsersDAO(Connection conn) {
		super(conn);
	}
	// TODO 自動生成されたコンストラクター・スタブ

	public UsersDTO findById(String userId) throws SQLException {

		String sql = "SELECT * FROM users WHERE user_id=?";

		UsersDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto = new UsersDTO();

				dto.setUserId(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));

			}

		}

		return dto;
	}

	public UsersDTO findById(String userId, String password) throws SQLException {

		String sql = "SELECT * FROM users WHERE user_id=? AND password=?";

		UsersDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, userId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dto = new UsersDTO();

				dto.setUserId(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));

			}

		}

		return dto;
	}

	public List<UsersDTO> findAll() throws SQLException {

		String sql = "SELECT * FROM users";

		List<UsersDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				UsersDTO dto = new UsersDTO();

				dto.setUserId(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));

				list.add(dto);

			}
			return list;

		}
	}

}
