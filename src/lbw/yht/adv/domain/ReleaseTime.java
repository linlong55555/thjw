package lbw.yht.adv.domain;

/**
 * Region entity. @author MyEclipse Persistence Tools
 */

public class ReleaseTime implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private Integer timeId;// 播放时间段id
	private String releaseId;// 播放时间id
	private String startTime;// 开始时间段
	private String endTime;// 结束时间段
	private String totalNumber;// 播放次数

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
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

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

}