
/** 
 * Project Name:adv_web 
 * File Name:AuctionAttributeService.java 
 * Package Name:lbw.yht.adv.service 
 * @author @lbw001.com 
 * Date:2015年5月21日下午5:20:21 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.service;  

import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IAuctionAttributeDao;
import lbw.yht.adv.domain.AuctionAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
/** 
 * ClassName: AuctionAttributeService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年5月21日 下午5:20:21 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
@Service
public class AuctionAttributeService {

  @Autowired
  private IAuctionAttributeDao auctionAttributeDao;
  
  /**
   * 保存竞拍属性
   * */
  public void saveAuctionAttribute(AuctionAttribute auctionAttr){
    this.auctionAttributeDao.saveAutionAttribute(auctionAttr);
  }
  
  /**
   * 修改竞拍属性
   * */
  public void updateAuctionAttribute(AuctionAttribute auctionAttr){
    this.auctionAttributeDao.updateAuctionAttribute(auctionAttr);
  }
  
  /**
   * 查询竞拍属性
   * */
  public List<AuctionAttribute> queryAuctionAttributeList(Map<String, Object> map){
    return this.auctionAttributeDao.queryAuctionAttributeList(map);
  }
  
  public int queryCount(Map<String, Object> map){
    return this.auctionAttributeDao.queryCount(map);
  }
  
  /**
   * 根据id查询竞拍属性的数量
   * */
  public int getCountById(String id){
    return this.auctionAttributeDao.getCountById(id);
  }
  
  /**
   * 根据广告类型查询竞拍属性
   * */
  public List<AuctionAttribute> queryAuctionAttrByTypeCode(String advTypeCode){
    return this.auctionAttributeDao.queryAuctionAttrByTypeCode(advTypeCode);
  }
}
 