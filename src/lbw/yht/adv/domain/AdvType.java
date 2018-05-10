package lbw.yht.adv.domain;

import java.util.Date;

/**
 *
 */

public class AdvType implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	
	private String typeCode; //广告类型编码
	private String parentTypeCode; //父类型编码
	private String typeName; //广告类型名称
	private Date typeDate; //创建时间(yyyy-mm-dd)
	private String typeContent; //备注
	private String typeState; //状态,默认值0 【0:可用、1:已坏】
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getParentTypeCode() {
		return parentTypeCode;
	}
	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getTypeDate() {
		return typeDate;
	}
	public void setTypeDate(Date typeDate) {
		this.typeDate = typeDate;
	}
	public String getTypeContent() {
		return typeContent;
	}
	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}
	public String getTypeState() {
		return typeState;
	}
	public void setTypeState(String typeState) {
		this.typeState = typeState;
	}

}