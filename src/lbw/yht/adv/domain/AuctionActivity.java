
/** 
 * Project Name:adv_web 
 * File Name:AuctionActivity.java 
 * Package Name:lbw.yht.adv.domain 
 * @author @lbw001.com 
 * Date:2015年5月20日下午3:11:21 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.domain;  

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
 
/** 
 * ClassName: AuctionActivity <br/> 
 * Function: 竞拍活动 <br/> 
 * date: 2015年5月20日 下午3:11:21 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
public class AuctionActivity implements java.io.Serializable{

	private static final long serialVersionUID = 8664368184540804381L;

	//竞拍活动编号
	private String activityId;
	
	//广告类型编号
	private String advTypeCode;
	
	//广告位id
	private Integer advId;
	
	//发表人
	private Integer operatorId;
	
	//创建时间
	private Date createDate;
	
	//活动描述
	private String desc;
	
	//活动状态
	private int status;
	
	//关联活动id
	private String preActivityId;
	
	//是否签合同
	private Integer isPact;
	
	//是否结清尾款
	private Integer isBalance;
	
	//广告类型名称
	private String advTypeName;
	
	//广告位位置
	private String advPosition;
	
	private String createDateStr;

	private List<AuctionAttribute> attrsList;
	
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getAdvTypeCode() {
		return advTypeCode;
	}

	public void setAdvTypeCode(String advTypeCode) {
		this.advTypeCode = advTypeCode;
	}

	public Integer getAdvId() {
		return advId;
	}

	public void setAdvId(Integer advId) {
		this.advId = advId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		this.setCreateDateStr(DateFormatUtils.format(createDate, "yyyyMMdd"));
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPreActivityId() {
		return preActivityId;
	}

	public void setPreActivityId(String preActivityId) {
		this.preActivityId = preActivityId;
	}

	public Integer getIsPact() {
		return isPact;
	}

	public void setIsPact(Integer isPact) {
		this.isPact = isPact;
	}

	public Integer getIsBalance() {
		return isBalance;
	}

	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}

	public List<AuctionAttribute> getAttrsList() {
		return attrsList;
	}

	public void setAttrsList(List<AuctionAttribute> attrsList) {
		this.attrsList = attrsList;
	}

  public String getAdvTypeName() {
    return advTypeName;
  }

  public void setAdvTypeName(String advTypeName) {
    this.advTypeName = advTypeName;
  }

  public String getAdvPosition() {
    return advPosition;
  }

  public void setAdvPosition(String advPosition) {
    this.advPosition = advPosition;
  }

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }
	
}
 