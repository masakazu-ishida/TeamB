package jp.co.axisb.service;

<<<<<<< HEAD
=======
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
import java.util.List;

<<<<<<< HEAD
=======
import javax.servlet.ServletException;

import jp.co.axisb.dao.ItemsDAO;
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
import jp.co.axisb.dto.ItemsDTO;
import jp.co.axisb.util.CommonConstants;
import jp.co.axisb.util.ConnectionUtil;

public class SearchService {

	public static List<ItemsDTO> search(String keyword, int categoriesId) throws SQLException, ServletException {//スロー宣言でいい？

<<<<<<< HEAD
		//daoのfindbyItemnameの呼び出し
		//ItemsDAO dao = new ItemsDAO();

		//引数を渡す
		//引数の渡し方がわからん！
=======
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git

			ItemsDAO dao = new ItemsDAO(conn);

<<<<<<< HEAD
		return null;

=======
			if (categoriesId == 0) {
				List<ItemsDTO> list1 = dao.findByItemName(keyword, 1);
				List<ItemsDTO> list2 = dao.findByItemName(keyword, 2);
				List<ItemsDTO> list = new ArrayList<>();

				list.addAll(list1);
				list.addAll(list2);

				return list;

			}

			else {
				List<ItemsDTO> list = dao.findByItemName(keyword, categoriesId);

				return list;

			}
		}
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
	}
}
