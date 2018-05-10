package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvRelease;
import lbw.yht.adv.domain.AdvplaceRelease;

public class AdvplaceReleaseParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	private String beginDateS;// 开始竞价时间开始
	private String endDateS;// 结束竞价时间结束
	private String advplaceState;// 发布状态
	private String typeCode;// 广告位类型
	private Pagination pagination;
	private AdvplaceRelease advplaceRelease;// 发布

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public AdvplaceReleaseParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advplaceRelease = new AdvplaceRelease();
	}

	public String getBeginDateS() {
		return beginDateS;
	}

	public void setBeginDateS(String beginDateS) {
		this.beginDateS = beginDateS;
	}

	public String getEndDateS() {
		return endDateS;
	}

	public void setEndDateS(String endDateS) {
		this.endDateS = endDateS;
	}

	public String getAdvplaceState() {
		return advplaceState;
	}

	public void setAdvplaceState(String advplaceState) {
		this.advplaceState = advplaceState;
	}

	public AdvplaceRelease getAdvplaceRelease() {
		return advplaceRelease;
	}

	public void setAdvplaceRelease(AdvplaceRelease advplaceRelease) {
		this.advplaceRelease = advplaceRelease;
	}

}
