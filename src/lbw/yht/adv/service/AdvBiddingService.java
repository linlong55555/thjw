package lbw.yht.adv.service;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IAdvBiddingDao;
import lbw.yht.adv.domain.AdvBidding;
import lbw.yht.adv.domain.param.AdvBiddingParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvBiddingService {

	@Autowired
	private IAdvBiddingDao advBiddingDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvBiddingParam advBiddingParam) {
		return advBiddingDao.queryCount(advBiddingParam);
	}

	/**
	 * 分页查询
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvBidding> queryAllpageList(AdvBiddingParam advBiddingParam) {
		return advBiddingDao.queryAllpageList(advBiddingParam);
	}

	/**
	 * 通过主键查询竞价详细记录
	 * 
	 * @param biddingId
	 * @return
	 */
	public AdvBidding findByBiddingId(String biddingId) {
		return advBiddingDao.findByBiddingId(biddingId);
	}

	/**
	 * 修改合同信息
	 * 
	 * @param advBidding
	 */
	public void modifyAgreement(AdvBidding advBidding) {
		advBiddingDao.modifyAgreement(advBidding);
	}

	/**
	 * 修改竞价状态
	 * 
	 * @param advBidding
	 */
	public void modifyAdvbiddingStatus(Map<String, String> map) {
		advBiddingDao.modifyAdvbiddingStatus(map);
	}

	/**
	 * 修改文件名
	 * 
	 * @param advBidding
	 */
	public void modifyFile(AdvBidding advBidding) {
		advBiddingDao.modifyFile(advBidding);
	}

	/**
	 * 查询最新的竞价信息
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvBidding> queryNewAdvbidding(Map<String, Object> map) {
		return advBiddingDao.queryNewAdvbidding(map);
	}

	/**
	 * 根据用户id、竞拍状态和分页信息查找竞拍分页
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvBidding> queryBiddingByMember(Map<String, Object> map) {
		return advBiddingDao.queryBiddingByMember(map);
	}

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryBidByMemCount(Map<String, Object> map) {
		return advBiddingDao.queryBidByMemCount(map);
	}

	/**
	 * 查询竞价的记录总数
	 * 
	 * @return
	 */
	public Integer queryMaxBiddingId() {
		return advBiddingDao.queryMaxBiddingId();
	}

	/**
	 * 增加一条竞价记录
	 * 
	 * @param advBidding
	 */
	public void addAdvBidding(AdvBidding advBidding) {
		advBiddingDao.addAdvBidding(advBidding);
	}


}
