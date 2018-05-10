package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvRelease;

public class AdvReleaseParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	private String beginDateS;
	private String beginDateE;
	private String endDateS;
	private String endDateE;
	private Pagination pagination;
	private AdvRelease advRelease;// 发布

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public AdvRelease getAdvRelease() {
		return advRelease;
	}

	public void setAdvRelease(AdvRelease advRelease) {
		this.advRelease = advRelease;
	}

	public AdvReleaseParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advRelease = new AdvRelease();
	}

	public String getBeginDateS() {
		return beginDateS;
	}

	public void setBeginDateS(String beginDateS) {
		this.beginDateS = beginDateS;
	}

	public String getBeginDateE() {
		return beginDateE;
	}

	public void setBeginDateE(String beginDateE) {
		this.beginDateE = beginDateE;
	}

	public String getEndDateS() {
		return endDateS;
	}

	public void setEndDateS(String endDateS) {
		this.endDateS = endDateS;
	}

	public String getEndDateE() {
		return endDateE;
	}

	public void setEndDateE(String endDateE) {
		this.endDateE = endDateE;
	}

}
