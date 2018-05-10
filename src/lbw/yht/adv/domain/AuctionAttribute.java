
/** 
 * Project Name:adv_web 
 * File Name:AuctionAttribute.java 
 * Package Name:lbw.yht.adv.domain 
 * @author @lbw001.com 
 * Date:2015年5月20日下午5:41:21 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.domain;  
 
/** 
 * ClassName: AuctionAttribute <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年5月20日 下午5:41:21 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
public class AuctionAttribute implements java.io.Serializable{
 
	private static final long serialVersionUID = -4658922548694137561L;

	//属性编号
	private String attrId;
	
	//属性名称
	private String attrName;
	
	//是否使用
	private int isUse;
	
	//是否必选
	private int isMustSelect;
	
	//元素类型
	private String eleType;
	
	//默认值
	private String defaultValue;

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public int getIsMustSelect() {
		return isMustSelect;
	}

	public void setIsMustSelect(int isMustSelect) {
		this.isMustSelect = isMustSelect;
	}

	public String getEleType() {
		return eleType;
	}

	public void setEleType(String eleType) {
		this.eleType = eleType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
 