package jp.co.axisb.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.ConnectionUtil;

public class SearchService {

	public static List<ItemsDTO> search(String keyword, int categoriesId) {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			if (categoriesId == 0) {
				List<ItemsDTO> list1 = dao.findByItemName(keyword, 1);
				List<ItemsDTO> list2 = dao.findByItemName(keyword, 2);
				List<ItemsDTO> list3 = new ArrayList<>();

				list3.addAll(list1);
				list3.addAll(list2);

				return list3.subList(0, 9);

			}

			else {
				List<ItemsDTO> list4 = dao.findByItemName(keyword, categoriesId);

				return list4.subList(0, 9);

			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}

	}

	//２-5ページ
	public static List<ItemsDTO> search2(String keyword, int categoriesId, int page) {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			if (categoriesId == 0) {
				List<ItemsDTO> list1 = dao.findByItemName(keyword, 1);
				List<ItemsDTO> list2 = dao.findByItemName(keyword, 2);
				List<ItemsDTO> list3 = new ArrayList<>();

				list3.addAll(list1);
				list3.addAll(list2);

				return list3.subList(10 * (page - 1), 10 * page - 1);

			}

			else {
				List<ItemsDTO> list4 = dao.findByItemName(keyword, categoriesId);

				return list4.subList(10 * (page - 1), 10 * page - 1);

			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}

}
