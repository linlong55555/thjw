package lbw.yht.adv.domain;

import java.util.List;

/**
 * 广告竞价属性表
 */

public class AdvBidding implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer biddingId;// 竞价主键
	private Integer memberId;// 旺乐用户id
	private String memberName;// 旺乐用户名
	private String memberNo;// 旺乐卡号
	private String avgbiddingPrice;// 竞价平均价格
	private String biddingPrice;// 竞价总价格
	private String adverContent;// 广告内容描述
	private Integer biddingStatus;// 默认值0 【0:竞价中、1：竞价成功、2 : 已发布、-1：竞价失败】
	private String agreementNo;// 合同编号
	private String agreementName;// 合同名称
	private String fileName;// 文件名
	private List<BiddingTime> biddingTimeList;// 竞价时间段
	private List<AdvplaceRelease> advplaceReleaseList;// 广告位发布信息

	public String getAvgbiddingPrice() {
		return avgbiddingPrice;
	}

	public void setAvgbiddingPrice(String avgbiddingPrice) {
		this.avgbiddingPrice = avgbiddingPrice;
	}

	public Integer getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(Integer biddingId) {
		this.biddingId = biddingId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	

	public List<AdvplaceRelease> getAdvplaceReleaseList() {
		return advplaceReleaseList;
	}

	public void setAdvplaceReleaseList(List<AdvplaceRelease> advplaceReleaseList) {
		this.advplaceReleaseList = advplaceReleaseList;
	}

	public String getBiddingPrice() {
		return biddingPrice;
	}

	public void setBiddingPrice(String biddingPrice) {
		this.biddingPrice = biddingPrice;
	}

	public String getAdverContent() {
		return adverContent;
	}

	public void setAdverContent(String adverContent) {
		this.adverContent = adverContent;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getBiddingStatus() {
		return biddingStatus;
	}

	public void setBiddingStatus(Integer biddingStatus) {
		this.biddingStatus = biddingStatus;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<BiddingTime> getBiddingTimeList() {
		return biddingTimeList;
	}

	public void setBiddingTimeList(List<BiddingTime> biddingTimeList) {
		this.biddingTimeList = biddingTimeList;
	}

}