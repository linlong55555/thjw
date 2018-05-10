package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.AdvLocation;
import lbw.yht.adv.domain.param.AdvLocationParam;

public interface IAdvLocationDao {

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<AdvLocation> queryAdvLocationAll();
	
	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvLocationParam advLocationParam);
	
	
	/**
	 * 分页查询
	 * 
	 * @param advLocationParam
	 * @return
	 */
	public List<AdvLocation> queryAllpageList(AdvLocationParam advLocationParam);
	
	/**
	 *添加广告位
	 * 
	 * @param advLocations
	 * @return
	 */
	public void save(AdvLocation advLocation);

	
	/**
	 * 通过主键查询详细信息
	 * 
	 * @param typeCode
	 * @return
	 */
	public AdvLocation findOneByadvId(String advId);
	
	/**
	 *修改广告位
	 * 
	 * @param advLocation
	 * @return
	 */
	public void modify(AdvLocation advLocation);
	
	/**
	 * 修改广告位状态
	 * 
	 * @param advLocation
	 */
	public void updateAdvStatus(AdvLocation advLocation);
	
	/**
	 * 删除广告位
	 * 
	 * @param advLocations
	 */
	public void delete(String advLocations);
	
}
