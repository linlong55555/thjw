package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.RelAdvLocation;

public class RelAdvLocationParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;

	private Pagination pagination; // 分页
	private RelAdvLocation relAdvLocation;// 筛选

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	
	public RelAdvLocation getRelAdvLocation() {
		return relAdvLocation;
	}

	public void setRelAdvLocation(RelAdvLocation relAdvLocation) {
		this.relAdvLocation = relAdvLocation;
	}

	public RelAdvLocationParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		relAdvLocation = new RelAdvLocation();
	}

}
