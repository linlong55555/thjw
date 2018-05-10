package lbw.yht.adv.domain;

public class AdvRelRrelease implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	private String advReleaseId;// 发布广告中间主键
	private String advId;// 广告位主键
	private String releaseId;// 发布主键

	public String getAdvReleaseId() {
		return advReleaseId;
	}

	public void setAdvReleaseId(String advReleaseId) {
		this.advReleaseId = advReleaseId;
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

}