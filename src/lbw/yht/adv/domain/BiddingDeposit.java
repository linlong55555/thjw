package lbw.yht.adv.domain;

/**
 *
 */

public class BiddingDeposit implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer depositId;// 押金记录id
	private Integer memberId;// 旺乐用户id
	private String memberName;// 旺乐用户名称
	private String memberNo;// 旺乐卡号
	private String glideNumber;// 流水帐号
	private String depositPrice;// 押金金额(元)
	private Integer isReturn;// 是否归还  0：否,  1:是  2:违约

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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

	public String getGlideNumber() {
		return glideNumber;
	}

	public void setGlideNumber(String glideNumber) {
		this.glideNumber = glideNumber;
	}

	public String getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(String depositPrice) {
		this.depositPrice = depositPrice;
	}

	public Integer getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}

}