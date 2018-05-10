package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvLocation;
import lbw.yht.adv.domain.AdvType;

public class AdvLocationParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;
	
	private String typeName;// 广告类型名称
	
	private Pagination pagination;  //分页
	private AdvLocation advLocation;// 广告位

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


	public AdvLocation getAdvLocation() {
		return advLocation;
	}

	public void setAdvLocation(AdvLocation advLocation) {
		this.advLocation = advLocation;
	}

	public AdvLocationParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advLocation = new AdvLocation();
	}

}
