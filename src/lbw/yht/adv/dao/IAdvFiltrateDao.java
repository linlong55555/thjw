package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.AdvFiltrate;
import lbw.yht.adv.domain.param.AdvFiltrateParam;

public interface IAdvFiltrateDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvFiltrateParam advFiltrateParam);
	
	
	/**
	 * 分页查询
	 * 
	 * @param advFiltrateParam
	 * @return
	 */
	public List<AdvFiltrate> queryAllpageList(AdvFiltrateParam advFiltrateParam);
	
}
