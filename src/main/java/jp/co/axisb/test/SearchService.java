package jp.co.axisb.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import jp.co.axisb.dao.BaseDAO;
import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.util.ConnectionUtil;

public class SearchService {

	@BeforeEach
	void init() {
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			BaseDAO dao = new BaseDAO(conn);
			try {
				dao.insertBatch("sqlFiles/init.sql");
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}

	@Test
	public void testFindById() {
		
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
		
		
		
	}

}
