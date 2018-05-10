package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.AdvRelease;
import lbw.yht.adv.domain.param.AdvReleaseParam;

public interface IAdvReleaseDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvReleaseParam advReleaseParam);

	/**
	 * 分页查询
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvRelease> queryAllpageList(AdvReleaseParam advReleaseParam);

	/**
	 * 插入一条发布记录
	 * 
	 * @param advRelease
	 */
	public void save(AdvRelease advRelease);

	/**
	 * 通过发布id删除发布记录
	 * 
	 * @param releaseId
	 */
	public void deleteByReleaseId(String releaseId);

	/**
	 * 通过主键id找一条记录
	 * 
	 * @param releaseId
	 * @return
	 */
	public AdvRelease findByReleaseId(String releaseId);

	/**
	 * 通过发布名找一条记录
	 * 
	 * @param releaseId
	 * @return
	 */
	public List<AdvRelease> findByReleaseName(String releaseName);


	/**
	 * 修改一条记录
	 * 
	 * @param advRelease
	 */
	public void modify(AdvRelease advRelease);

}
