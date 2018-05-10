package lbw.yht.adv.domain;

/**
 * 竞价时间段表
 */

public class BiddingTime implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer biddingTimeId;// 竞价时间段主键
	private Integer biddingId;// 竞价id
	private String startTime;// 播放开始时间段
	private String endTime;// 播放结束时间段
	private Integer totalNumber;// 播放次数

	public Integer getBiddingTimeId() {
		return biddingTimeId;
	}

	public void setBiddingTimeId(Integer biddingTimeId) {
		this.biddingTimeId = biddingTimeId;
	}

	public Integer getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(Integer biddingId) {
		this.biddingId = biddingId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

}