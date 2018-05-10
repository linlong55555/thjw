package lbw.yht.adv.dao;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.AdvRelBidding;

public interface IAdvRelBiddingDao {

	/**
	 * 插入一条竞价-广告位中间记录
	 * 
	 * @param advRelBidding
	 */
	public void save(AdvRelBidding advRelBidding);

	/**
	 * 通过竞价id删除竞价-广告位中间记录
	 * 
	 * @param biddingId
	 */
	public void deleteByBiddingId(String biddingId);

	/**
	 * 查询热门竞拍的广告位
	 * 
	 * @param number
	 * @return
	 */
	public List<String> queryHotAdvattribute(Map<String,Object> map);

}
