package lbw.yht.adv.service;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IAdvRelBiddingDao;
import lbw.yht.adv.domain.AdvRelBidding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvRelBiddingService {

	@Autowired
	private IAdvRelBiddingDao advRelBiddingDao;

	/**
	 * 插入一条竞价-广告位中间记录
	 * 
	 * @param advRelBidding
	 */
	public void save(AdvRelBidding advRelBidding) {
		advRelBiddingDao.save(advRelBidding);
	}

	/**
	 * 通过竞价id删除竞价-广告位中间记录
	 * 
	 * @param biddingId
	 */
	public void deleteBybBiddingId(String biddingId) {
		advRelBiddingDao.deleteByBiddingId(biddingId);
	}

	/**
	 * 查询热门竞拍的广告位
	 * 
	 * @param number
	 * @return
	 */
	public List<String> queryHotAdvattribute(Map<String,Object> map) {
		return advRelBiddingDao.queryHotAdvattribute(map);
	}

}
