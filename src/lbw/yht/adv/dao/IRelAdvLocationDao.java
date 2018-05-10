package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.RelAdvLocation;
import lbw.yht.adv.domain.param.RelAdvLocationParam;

public interface IRelAdvLocationDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(RelAdvLocationParam relAdvLocationParam);
	
	
	/**
	 * 分页查询
	 * 
	 * @param advFiltrateParam
	 * @return
	 */
	public List<RelAdvLocation> queryAllpageList(RelAdvLocationParam relAdvLocationParam);
	
}
