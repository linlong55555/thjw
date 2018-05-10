package lbw.yht.adv.dao;

import lbw.yht.adv.domain.ReleaseTime;

public interface IReleaseTimeDao {

	/**
	 * 插入一条播放时间段
	 * 
	 * @param releaseTime
	 */
	public void save(ReleaseTime releaseTime);

	/**
	 * 通过发布id删除播放时间段
	 * 
	 * @param releaseId
	 */
	public void deleteByReleaseId(String releaseId);

}
