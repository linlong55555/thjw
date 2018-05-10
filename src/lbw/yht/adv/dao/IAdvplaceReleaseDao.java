package lbw.yht.adv.dao;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.AdvplaceRelease;
import lbw.yht.adv.domain.param.AdvplaceReleaseParam;

public interface IAdvplaceReleaseDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvplaceReleaseParam advplaceReleaseParam);

	/**
	 * 分页查询
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvplaceRelease> queryAllpageList(
			AdvplaceReleaseParam advplaceReleaseParam);

	/**
	 * 通过advId、开始使用时间、结束使用时间找没有结束的发布记录
	 * 
	 * @param advplaceRelease
	 * @return
	 */
	public List<AdvplaceRelease> findByDate(AdvplaceRelease advplaceRelease);

	/**
	 * 插入一条发布记录
	 * 
	 * @param advRelease
	 */
	public void save(AdvplaceRelease advplaceRelease);

	/**
	 * 通过主键id找一条记录
	 * 
	 * @param advplaceId
	 * @return
	 */
	public AdvplaceRelease findByAdvplaceId(String advplaceId);

	/**
	 * 修改一条记录
	 * 
	 * @param advRelease
	 */
	public void modify(AdvplaceRelease advplaceRelease);

	/**
	 * 修改广告位发布记录状态为已出售
	 */
	public void modifyAdvplaReSta(String advplaceId);

	/**
	 * 修改广告位发布记录状态为结束
	 */
	public void modifyAdvplaReSta2(String advplaceId);

	/**
	 * 查询所有正在发布状态的发布信息
	 * 
	 * @return
	 */
	public List<AdvplaceRelease> queryAll();

	/**
	 * 条件分页查找总数
	 * 
	 * @return
	 */
	public int queryAttributeCount(Map<String, Object> map);

	/**
	 * 条件分页查询
	 * 
	 * @param advAttributeParam
	 * @return
	 */
	public List<AdvAttribute> queryAttributeList(Map<String, Object> map);

}
