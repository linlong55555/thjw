package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvAttribute;

public class AdvAttributeParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	private String typeCode;// 类型code
	private String code;// 地区code
	private String advStatus;// 广告位状态
	private Pagination pagination;
	private AdvAttribute advAttribute;// 广告位

	public String getAdvStatus() {
		return advStatus;
	}

	public void setAdvStatus(String advStatus) {
		this.advStatus = advStatus;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public AdvAttribute getAdvAttribute() {
		return advAttribute;
	}

	public void setAdvAttribute(AdvAttribute advAttribute) {
		this.advAttribute = advAttribute;
	}

	public AdvAttributeParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advAttribute = new AdvAttribute();
	}

}
