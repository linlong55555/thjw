package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvBidding;

public class AdvBiddingParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	private String biddingStatus;// 默认值0 【0:竞价中、1：竞价成功、2 : 已发布、-1：竞价失败】
	private Pagination pagination;
	private AdvBidding advBidding;// 发布

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public AdvBiddingParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advBidding = new AdvBidding();
	}

	public String getBiddingStatus() {
		return biddingStatus;
	}

	public void setBiddingStatus(String biddingStatus) {
		this.biddingStatus = biddingStatus;
	}

	public AdvBidding getAdvBidding() {
		return advBidding;
	}

	public void setAdvBidding(AdvBidding advBidding) {
		this.advBidding = advBidding;
	}

}
