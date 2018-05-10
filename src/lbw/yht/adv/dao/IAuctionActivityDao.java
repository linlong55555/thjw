
/** 
 * Project Name:adv_web 
 * File Name:IAuctionActivityDao.java 
 * Package Name:lbw.yht.adv.dao 
 * @author @lbw001.com 
 * Date:2015年5月20日下午3:21:43 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.dao;  

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.AuctionActivity;
 
/** 
 * ClassName: IAuctionActivityDao <br/> 
 * Function: 竞拍活动Dao接口. <br/> 
 * date: 2015年5月20日 下午3:21:43 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
public interface IAuctionActivityDao {

	/**
	 * 保存竞拍活动
	 */
	void saveAuctionActivity(AuctionActivity autionActivity);
	
	/**
	 * 保存历史竞拍活动
	 */
	void saveAuctionActivityHistory(AuctionActivity autionActivity);
	
	/**
	 * 查询竞拍活动信息
	 */
	List<AuctionActivity> queryAuctionActivityList(Map<String, Object> map);
	
	/**
	 * 查询竞拍活动信息数量 
	 */
	int queryAuctionActivityCount(Map<String, Object> map);
	
	/**
	 * 查询竞拍活动信息
	 */
	List<AuctionActivity> queryAuctionActivityHistoryList(Map<String, Object> map);
	
	/**
	 * 查询竞拍活动信息数量 
	 */
	int queryAuctionActivityHistoryCount(Map<String, Object> map);
	
	/**
	 * 根据传入的日期yyyyMMdd判断表中id是否存在
	 */
	String getMaxId(String ymd);
}
 