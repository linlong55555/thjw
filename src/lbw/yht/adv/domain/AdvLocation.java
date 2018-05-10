package lbw.yht.adv.domain;

import java.util.Date;


/**
 *
 */

public class AdvLocation implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer advId;// 属性id
	private AdvType typeCode;// 广告类型编码
	private String advPosition;// 广告位置地理 位置
	private String advFormat;// 文件格式(视频：mp4 ,avi..非视频：gif，jpg，png)
	private String advSize;// 非视频:尺寸(长宽);视频:分辨率
	private String advFilesize;// 文件大小
	private String advNo;// 非视频广告使用,编号
	private Date advDate;// 创建日期（ yyyy-mm-dd）
	private String advState;// 状态(默认值0 【0:可用、1:已坏】)
	private String advContent;// 竞买详情(图片，描述)
	private String advNotice;// 竞买须知
	private String advAffiche;// 竞买公告

	public Integer getAdvId() {
		return advId;
	}

	public String getAdvFilesize() {
		return advFilesize;
	}

	public void setAdvFilesize(String advFilesize) {
		this.advFilesize = advFilesize;
	}

	public void setAdvId(Integer advId) {
		this.advId = advId;
	}

	public AdvType getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(AdvType typeCode) {
		this.typeCode = typeCode;
	}

	public String getAdvPosition() {
		return advPosition;
	}

	public void setAdvPosition(String advPosition) {
		this.advPosition = advPosition;
	}

	public String getAdvFormat() {
		return advFormat;
	}

	public void setAdvFormat(String advFormat) {
		this.advFormat = advFormat;
	}

	public String getAdvSize() {
		return advSize;
	}

	public void setAdvSize(String advSize) {
		this.advSize = advSize;
	}

	public String getAdvNo() {
		return advNo;
	}

	public void setAdvNo(String advNo) {
		this.advNo = advNo;
	}

	public Date getAdvDate() {
		return advDate;
	}

	public void setAdvDate(Date advDate) {
		this.advDate = advDate;
	}

	public String getAdvState() {
		return advState;
	}

	public void setAdvState(String advState) {
		this.advState = advState;
	}

	public String getAdvContent() {
		return advContent;
	}

	public void setAdvContent(String advContent) {
		this.advContent = advContent;
	}

	public String getAdvNotice() {
		return advNotice;
	}

	public void setAdvNotice(String advNotice) {
		this.advNotice = advNotice;
	}

	public String getAdvAffiche() {
		return advAffiche;
	}

	public void setAdvAffiche(String advAffiche) {
		this.advAffiche = advAffiche;
	}

}