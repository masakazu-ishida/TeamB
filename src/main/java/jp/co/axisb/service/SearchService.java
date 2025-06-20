package jp.co.axisb.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.axisb.dao.ItemsDAO;
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class SearchService {

	public static List<ItemsDTO> search(String keyword, int categoriesId) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			ItemsDAO dao = new ItemsDAO(conn);

			if (categoriesId == 0) {
				List<ItemsDTO> list1 = dao.findByItemName(keyword, 1);
				List<ItemsDTO> list2 = dao.findByItemName(keyword, 2);
				List<ItemsDTO> list = new ArrayList<>();

				list.addAll(list1);
				list.addAll(list2);

				int max = 9;
				if (list.size() < max) {
					max = list.size();
				}

				return list.subList(0, max);

			}

			else {
				List<ItemsDTO> list = dao.findByItemName(keyword, categoriesId);

				int max = 9;
				if (list.size() < max) {
					max = list.size();
				}

				return list.subList(0, max);

			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}

	}

	//２-５ページ
	public static List<ItemsDTO> search2(String keyword, int categoriesId, int page) {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {

			ItemsDAO dao = new ItemsDAO(conn);

			if (categoriesId == 0) {
				List<ItemsDTO> list1 = dao.findByItemName(keyword, 1);
				List<ItemsDTO> list2 = dao.findByItemName(keyword, 2);
				List<ItemsDTO> list = new ArrayList<>();

				list.addAll(list1);
				list.addAll(list2);

				return list.subList(10 * (page - 1), 10 * page - 1);

			}

			else {
				List<ItemsDTO> list = dao.findByItemName(keyword, categoriesId);

				return list.subList(10 * (page - 1), 10 * page - 1);

			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}

}
