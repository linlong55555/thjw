package lbw.yht.adv.domain;

public class AdvplaceRelease implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer advplaceId;// 广告位发布主键
	private AdvAttribute advAttribute;// 广告位
	private String startingPrice;// 起拍价
	private Integer increase;// 加价幅度
	private String assessmentPrice;// 评估价
	private String startBiddingPeriod;// 开始竞拍时间
	private String endBiddingPeriod;// 结束竞拍时间
	private String startUseTime;// 开始展示时间
	private String endUseTime;// 结束展示时间
	private String biddingNotice;// 竞价须知
	private String biddingBulletin;// 竞拍公告
	private Integer advplaceState;// 发布状态 0：发布中 -1：发布结束 1:已售出

	public Integer getAdvplaceId() {
		return advplaceId;
	}

	public void setAdvplaceId(Integer advplaceId) {
		this.advplaceId = advplaceId;
	}

	public AdvAttribute getAdvAttribute() {
		return advAttribute;
	}

	public void setAdvAttribute(AdvAttribute advAttribute) {
		this.advAttribute = advAttribute;
	}

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Integer getIncrease() {
		return increase;
	}

	public void setIncrease(Integer increase) {
		this.increase = increase;
	}

	public String getAssessmentPrice() {
		return assessmentPrice;
	}

	public void setAssessmentPrice(String assessmentPrice) {
		this.assessmentPrice = assessmentPrice;
	}

	public String getStartBiddingPeriod() {
		return startBiddingPeriod;
	}

	public void setStartBiddingPeriod(String startBiddingPeriod) {
		this.startBiddingPeriod = startBiddingPeriod;
	}

	public String getEndBiddingPeriod() {
		return endBiddingPeriod;
	}

	public void setEndBiddingPeriod(String endBiddingPeriod) {
		this.endBiddingPeriod = endBiddingPeriod;
	}

	public String getStartUseTime() {
		return startUseTime;
	}

	public void setStartUseTime(String startUseTime) {
		this.startUseTime = startUseTime;
	}

	public String getEndUseTime() {
		return endUseTime;
	}

	public void setEndUseTime(String endUseTime) {
		this.endUseTime = endUseTime;
	}

	public String getBiddingNotice() {
		return biddingNotice;
	}

	public void setBiddingNotice(String biddingNotice) {
		this.biddingNotice = biddingNotice;
	}

	public String getBiddingBulletin() {
		return biddingBulletin;
	}

	public void setBiddingBulletin(String biddingBulletin) {
		this.biddingBulletin = biddingBulletin;
	}

	public Integer getAdvplaceState() {
		return advplaceState;
	}

	public void setAdvplaceState(Integer advplaceState) {
		this.advplaceState = advplaceState;
	}

}