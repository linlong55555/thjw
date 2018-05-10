package lbw.yht.adv.dao;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.param.AdvAttributeParam;

public interface IAdvAttributeDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvAttributeParam advAttributeParam);

	/**
	 * 分页查询
	 * 
	 * @param advAttributeParam
	 * @return
	 */
	public List<AdvAttribute> queryAllpageList(AdvAttributeParam advAttributeParam);

	/**
	 * 通过主键查询详细信息
	 * 
	 * @param advId
	 * @return
	 */
	public AdvAttribute findOneByAdvId(String advId);

	public void save(AdvAttribute advAttribute);

	public void modify(AdvAttribute advAttribute);

	public void delete(String advIds);

	/**
	 * 修改广告位状态
	 * 
	 * @param map
	 */
	public void updateAdvStatus(AdvAttribute advAttribute);

	// 外部接口
	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryAttributeCount(Map<String, Object> map);

	/**
	 * 分页查询
	 * 
	 * @param advAttributeParam
	 * @return
	 */
	public List<AdvAttribute> queryAttributeList(Map<String, Object> map);

}
