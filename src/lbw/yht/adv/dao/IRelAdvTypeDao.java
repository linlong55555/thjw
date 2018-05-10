package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.RelAdvType;
import lbw.yht.adv.domain.param.RelAdvTypeParam;

public interface IRelAdvTypeDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(RelAdvTypeParam relAdvTypeParam);
	
	
	/**
	 * 分页查询
	 * 
	 * @param relAdvTypeParam
	 * @return
	 */
	public List<RelAdvType> queryAllpageList(RelAdvTypeParam relAdvTypeParam);
	
}
