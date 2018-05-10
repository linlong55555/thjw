
/** 
 * Project Name:adv_web 
 * File Name:ActivityAttribute.java 
 * Package Name:lbw.yht.adv.domain 
 * @author @lbw001.com 
 * Date:2015年5月20日下午5:54:43 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.domain;  
 
/** 
 * ClassName: ActivityAttribute <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * date: 2015年5月20日 下午5:54:43 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
public class ActivityAttribute implements java.io.Serializable{
 
	private static final long serialVersionUID = 6868801577436562291L;

	//活动从表id
	private int activitySubId;
	
	//竞拍活动编号
	private String activityId;
	
	//属性编号
	private String attrId;
	
	//竞拍属性值
	private String attrValue;
	
	//竞拍属性值类型
	private String attrValueType;
	
	//竞拍属性名称
	private String attrName;

	public int getActivitySubId() {
		return activitySubId;
	}

	public void setActivitySubId(int activitySubId) {
		this.activitySubId = activitySubId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getAttrId() {
		return attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getAttrValueType() {
		return attrValueType;
	}

	public void setAttrValueType(String attrValueType) {
		this.attrValueType = attrValueType;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

}
 