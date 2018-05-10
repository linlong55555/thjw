package lbw.yht.adv.domain;

/**
 * 广告位信息
 */

public class AdvAttribute implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;

	private Integer advId;// 主键id
	private AdvType typeCode;// 广告位类型
	private String regionCode;// 地区编码
	private String regionName;// 地区名称
	private String imgName;// 图片名
	private String advPattern;// 广告格式 网页广告位和广告机广告位的格式
	private String advSize;// 广告位尺寸大小
	private String webpageUrl;// 网页广告静态链接
	private String advExplain;// 广告位说明
	private Integer advStatus;// 默认值0 【0:可用、-1：已坏】

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public Integer getAdvId() {
		return advId;
	}

	public void setAdvId(Integer advId) {
		this.advId = advId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAdvPattern() {
		return advPattern;
	}

	public void setAdvPattern(String advPattern) {
		this.advPattern = advPattern;
	}

	public String getAdvSize() {
		return advSize;
	}

	public void setAdvSize(String advSize) {
		this.advSize = advSize;
	}

	public String getWebpageUrl() {
		return webpageUrl;
	}

	public void setWebpageUrl(String webpageUrl) {
		this.webpageUrl = webpageUrl;
	}

	public String getAdvExplain() {
		return advExplain;
	}

	public void setAdvExplain(String advExplain) {
		this.advExplain = advExplain;
	}

	public Integer getAdvStatus() {
		return advStatus;
	}

	public void setAdvStatus(Integer advStatus) {
		this.advStatus = advStatus;
	}

	public AdvAttribute() {
		super();
		typeCode = new AdvType();
	}

	public AdvType getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(AdvType typeCode) {
		this.typeCode = typeCode;
	}

}