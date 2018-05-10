package lbw.yht.adv.service;

import lbw.yht.adv.dao.IBiddingTimeDao;
import lbw.yht.adv.domain.BiddingTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiddingTimeService {

	@Autowired
	private IBiddingTimeDao biddingTimeDao;

	/**
	 * 插入一条竞价时间段
	 * 
	 * @param releaseTime
	 */
	public void save(BiddingTime biddingTime) {
		biddingTimeDao.save(biddingTime);
	}

	/**
	 * 通过发布id删除竞价时间段
	 * 
	 * @param biddingId
	 */
	public void deleteByBiddingId(String biddingId) {
		biddingTimeDao.deleteByBiddingId(biddingId);
	}
}
