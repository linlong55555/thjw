package lbw.yht.adv.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao {

	/**
	 * 表数据总量
	 * 
	 * @return
	 */
	int queryCount(Map map);

	/**
	 * 键值对 查询
	 * 
	 * @param map
	 * @return
	 */
	List comboboxLoad(Map map);
}
