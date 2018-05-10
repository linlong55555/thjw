package lbw.yht.adv.domain;

import java.util.Date;

/**
 *
 */

public class AdvFiltrate implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	
	private String filtrateCode; //筛选条件编码
	private String filtratePid; //父id
	private String filtrateName; //筛选条件内容
	private String filtrateState; //状态,默认值0【0：可用，1：已坏】
	private Date filtrateDate; //时间
	private String content; //备注
	
	public String getFiltrateCode() {
		return filtrateCode;
	}
	public void setFiltrateCode(String filtrateCode) {
		this.filtrateCode = filtrateCode;
	}
	public String getFiltratePid() {
		return filtratePid;
	}
	public void setFiltratePid(String filtratePid) {
		this.filtratePid = filtratePid;
	}
	public String getFiltrateName() {
		return filtrateName;
	}
	public void setFiltrateName(String filtrateName) {
		this.filtrateName = filtrateName;
	}
	public String getFiltrateState() {
		return filtrateState;
	}
	public void setFiltrateState(String filtrateState) {
		this.filtrateState = filtrateState;
	}
	public Date getFiltrateDate() {
		return filtrateDate;
	}
	public void setFiltrateDate(Date filtrateDate) {
		this.filtrateDate = filtrateDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}