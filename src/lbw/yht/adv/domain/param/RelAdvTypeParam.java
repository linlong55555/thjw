package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.RelAdvType;

public class RelAdvTypeParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;

	private Pagination pagination; // 分页
	private RelAdvType relAdvType;// 筛选

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	
	

	public RelAdvType getRelAdvType() {
		return relAdvType;
	}

	public void setRelAdvType(RelAdvType relAdvType) {
		this.relAdvType = relAdvType;
	}

	public RelAdvTypeParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		relAdvType = new RelAdvType();
	}

}
