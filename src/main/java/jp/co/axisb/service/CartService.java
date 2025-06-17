package jp.co.axisb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.axisb.dao.ItemsInCartDAO;
import jp.co.axisb.dto.ItemsInCartDTO;
import jp.co.axisb.util.ConnectionUtil;

public class CartService {

	String lookupString = "java:comp/env/jdbc/ecsite";try(
	Connection conn = ConnectionUtil.getConnection(lookupString)){
		
			//DAOクラスのインスタンス化
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);
			
			//daoからfindByIdを呼び出す
			

	//カート内の合計金額を計算する
	public int CartSum() {
		int sum = 0;

		return sum;

	}
