package lbw.yht.adv.domain;

import java.util.List;

/**
 * Region entity. @author MyEclipse Persistence Tools
 */

public class AdvRelease implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer releaseId;// 发布主键
	private String releaseName;// 广告发布名
	private String biddingId;// 竞价id
	private String startDate;// 开始使用时间
	private String endDate;// 结束使用时间
	private String releaseContent;// 发布说明
	private List<ReleaseTime> releaseTimeList;// 发布时间段
	private List<AdvAttribute> advAttributeList;// 广告位

	public String getReleaseContent() {
		return releaseContent;
	}

	public void setReleaseContent(String releaseContent) {
		this.releaseContent = releaseContent;
	}

	public Integer getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(String biddingId) {
		this.biddingId = biddingId;
	}

	public List<ReleaseTime> getReleaseTimeList() {
		return releaseTimeList;
	}

	public void setReleaseTimeList(List<ReleaseTime> releaseTimeList) {
		this.releaseTimeList = releaseTimeList;
	}

	public List<AdvAttribute> getAdvAttributeList() {
		return advAttributeList;
	}

	public void setAdvAttributeList(List<AdvAttribute> advAttributeList) {
		this.advAttributeList = advAttributeList;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

}