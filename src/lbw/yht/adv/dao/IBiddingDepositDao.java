package lbw.yht.adv.dao;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.BiddingDeposit;
import lbw.yht.adv.domain.param.BiddingDepositParam;

public interface IBiddingDepositDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(BiddingDepositParam biddingDepositParam);

	/**
	 * 分页查询
	 * 
	 * @param biddingDepositParam
	 * @return
	 */
	public List<BiddingDeposit> queryAllpageList(
			BiddingDepositParam biddingDepositParam);

	/**
	 * 通过用户id查询没有归还的押金记录
	 * 
	 * @param biddingDepositParam
	 * @return
	 */
	public BiddingDeposit findOneByMemberId(String memberId);

	/**
	 * 增加一条押金记录
	 * 
	 * @param map
	 */
	public void addBiddingdeposit(Map<String, Object> map);

	/**
	 * 通过用户id修改押金记录
	 * 
	 * @param biddingDepositParam
	 * @return
	 */
	public void updateOneByMemberId(String memberId);

	/**
	 * 修改为违约
	 * 
	 * @param depositId
	 */
	public void modifyDeposit(String depositId);

}
