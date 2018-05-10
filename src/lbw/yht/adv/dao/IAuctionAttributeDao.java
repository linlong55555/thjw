
/** 
 * Project Name:adv_web 
 * File Name:IAutionAttributeDao.java 
 * Package Name:lbw.yht.adv.dao 
 * @author @lbw001.com 
 * Date:2015年5月21日上午10:02:32 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.dao;  

import java.util.List;
import java.util.Map;

import lbw.yht.adv.domain.AuctionAttribute;
 
/** 
 * ClassName: IAuctionAttributeDao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年5月21日 上午10:02:32 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
public interface IAuctionAttributeDao {

	/**
	 * 添加竞拍属性
	 */
	void saveAutionAttribute(AuctionAttribute auctionAttr);
	
	/**
	 * 查询竞拍属性
	 */
	List<AuctionAttribute> queryAuctionAttributeList(Map<String, Object> map);
	
	/**
	 * 查询竞拍属性数量
	 */
	int queryCount(Map<String, Object> map);
	
	/**
	 * 修改竞拍属性
	 */
	void updateAuctionAttribute(AuctionAttribute auctionAttr);
	
	/**
	 * 判断编号是否存在
	 */
	int getCountById(String id);
	
	/**
	 * 根据广告类型查询对应的竞拍属性
	 * */
	List<AuctionAttribute> queryAuctionAttrByTypeCode(String typeCode);
}
 