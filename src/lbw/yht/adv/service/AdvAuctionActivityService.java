
/** 
 * Project Name:adv_web 
 * File Name:AdvAuctionActivityService.java 
 * Package Name:lbw.yht.adv.service 
 * @author @lbw001.com 
 * Date:2015年5月21日上午10:09:19 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.service;  

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lbw.yht.adv.dao.IAuctionActivityDao;
import lbw.yht.adv.domain.AuctionActivity;
import lbw.yht.util.IdBuildor;
 
/** 
 * ClassName: AdvAuctionActivityService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年5月21日 上午10:09:19 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
@Service
public class AdvAuctionActivityService {

	@Autowired
	private IAuctionActivityDao auctionActivityDao;
	
	/**
	 * 获取竞拍活动列表信息
	 */
	public List<AuctionActivity> queryAuctionActivity(Map<String, Object> paramsMap){	
	  return this.auctionActivityDao.queryAuctionActivityList(paramsMap);
	}
	
	/**
	 * 获取竞拍活动总数
	 * */
	public Integer queryCount(Map<String, Object> paramsMap){
	  return this.auctionActivityDao.queryAuctionActivityCount(paramsMap);
	}
	
	/**
	 * 保存竞拍活动信息
	 * */
	public void saveAuctionActivity(AuctionActivity autionActivity){
	  //获取当天的最大id
	  String id = this.auctionActivityDao.getMaxId(DateFormatUtils.format(new Date(), "yyyyMMdd"));
	  id = IdBuildor.buildFromDateAndSeq(id);
	  //给活动设置id
	  autionActivity.setActivityId(id);
	  this.auctionActivityDao.saveAuctionActivity(autionActivity);
	}
}
 