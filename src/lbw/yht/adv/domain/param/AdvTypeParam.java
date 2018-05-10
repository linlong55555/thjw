package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvType;

public class AdvTypeParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	
	private String typeName;// 广告类型名称
	
	private Pagination pagination;  //分页
	private AdvType advType;// 广告类型

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public AdvType getAdvType() {
		return advType;
	}

	public void setAdvType(AdvType advType) {
		this.advType = advType;
	}

	public AdvTypeParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advType = new AdvType();
	}

}
