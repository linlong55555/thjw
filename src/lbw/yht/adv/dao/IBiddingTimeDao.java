package lbw.yht.adv.dao;

import lbw.yht.adv.domain.BiddingTime;

public interface IBiddingTimeDao {

	/**
	 * 插入一条竞价时间段
	 * 
	 * @param releaseTime
	 */
	public void save(BiddingTime biddingTime);

	/**
	 * 通过发布id删除竞价时间段
	 * 
	 * @param biddingId
	 */
	public void deleteByBiddingId(String biddingId);
}
