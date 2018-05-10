package lbw.yht.adv.domain;

/**
 * 广告位竞价中间表
 */

public class AdvRelBidding implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer advBiddingId;// 中间主键
	private Integer advplaceId;// 广告位发布id
	private Integer biddingId;// 竞价id

	public Integer getAdvBiddingId() {
		return advBiddingId;
	}

	public void setAdvBiddingId(Integer advBiddingId) {
		this.advBiddingId = advBiddingId;
	}

	public Integer getBiddingId() {
		return biddingId;
	}

	public Integer getAdvplaceId() {
		return advplaceId;
	}

	public void setAdvplaceId(Integer advplaceId) {
		this.advplaceId = advplaceId;
	}

	public void setBiddingId(Integer biddingId) {
		this.biddingId = biddingId;
	}

}