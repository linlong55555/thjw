package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.BiddingDeposit;

public class BiddingDepositParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	private Pagination pagination;
	private String memberName;// 旺乐用户名称
	private Integer isReturn;// 是否归还
	private BiddingDeposit biddingDeposit;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public BiddingDepositParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		biddingDeposit = new BiddingDeposit();
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}

	public BiddingDeposit getBiddingDeposit() {
		return biddingDeposit;
	}

	public void setBiddingDeposit(BiddingDeposit biddingDeposit) {
		this.biddingDeposit = biddingDeposit;
	}

}
