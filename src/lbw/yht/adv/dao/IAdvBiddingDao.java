package lbw.yht.adv.dao;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.AdvBidding;
import lbw.yht.adv.domain.param.AdvBiddingParam;

public interface IAdvBiddingDao {

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvBiddingParam advBiddingParam);

	/**
	 * 分页查询
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvBidding> queryAllpageList(AdvBiddingParam advBiddingParam);

	/**
	 * 通过主键查询竞价详细记录
	 * 
	 * @param biddingId
	 * @return
	 */
	public AdvBidding findByBiddingId(String biddingId);

	/**
	 * 修改合同信息
	 * 
	 * @param advBidding
	 */
	public void modifyAgreement(AdvBidding advBidding);

	/**
	 * 修改竞价状态
	 * 
	 * @param advBidding
	 */
	public void modifyAdvbiddingStatus(Map<String, String> map);

	/**
	 * 修改文件名
	 * 
	 * @param advBidding
	 */
	public void modifyFile(AdvBidding advBidding);

	/**
	 * 查询最新的竞价信息
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvBidding> queryNewAdvbidding(Map<String, Object> map);

	/**
	 * 根据用户id、竞拍状态和分页信息查找竞拍分页
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvBidding> queryBiddingByMember(Map<String, Object> map);

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryBidByMemCount(Map<String, Object> map);

	/**
	 * 查询最大的竞价id
	 * 
	 * @return
	 */
	public Integer queryMaxBiddingId();

	/**
	 * 增加一条竞价记录
	 * 
	 * @param advBidding
	 */
	public void addAdvBidding(AdvBidding advBidding);

}
