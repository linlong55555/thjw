package lbw.yht.adv.service;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IBiddingDepositDao;
import lbw.yht.adv.domain.AdvRelease;
import lbw.yht.adv.domain.BiddingDeposit;
import lbw.yht.adv.domain.Region;
import lbw.yht.adv.domain.param.BiddingDepositParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiddingDepositService {

	@Autowired
	private IBiddingDepositDao biddingDepositDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(BiddingDepositParam biddingDepositParam) {
		return biddingDepositDao.queryCount(biddingDepositParam);
	}

	/**
	 * 分页查询
	 * 
	 * @param biddingDepositParam
	 * @return
	 */
	public List<BiddingDeposit> queryAllpageList(
			BiddingDepositParam biddingDepositParam) {
		return biddingDepositDao.queryAllpageList(biddingDepositParam);
	}

	/**
	 * 通过用户id查询没有归还的押金记录
	 * 
	 * @param biddingDepositParam
	 * @return
	 */
	public BiddingDeposit findOneByMemberId(String memberId) {
		return biddingDepositDao.findOneByMemberId(memberId);
	}

	/**
	 * 增加一条押金记录
	 * 
	 * @param map
	 */
	public void addBiddingdeposit(Map<String, Object> map) {
		biddingDepositDao.addBiddingdeposit(map);
	}

	/**
	 * 通过用户id修改押金记录
	 * 
	 * @param biddingDepositParam
	 * @return
	 */
	public void updateOneByMemberId(String memberId) {
		biddingDepositDao.updateOneByMemberId(memberId);
	}

	/**
	 * 修改为违约
	 * 
	 * @param depositId
	 */
	public void modifyDeposit(String depositId) {
		biddingDepositDao.modifyDeposit(depositId);
	}

}
