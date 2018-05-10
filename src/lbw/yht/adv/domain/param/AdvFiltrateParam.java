package lbw.yht.adv.domain.param;

import java.io.Serializable;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvFiltrate;

public class AdvFiltrateParam implements Serializable {
	private static final long serialVersionUID = 5972325246765283445L;

	private Pagination pagination; // 分页
	private AdvFiltrate advFiltrate;// 筛选

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public AdvFiltrate getAdvFiltrate() {
		return advFiltrate;
	}

	public void setAdvFiltrate(AdvFiltrate advFiltrate) {
		this.advFiltrate = advFiltrate;
	}

	public AdvFiltrateParam(Pagination pagination) {
		super();
		this.pagination = pagination;
		advFiltrate = new AdvFiltrate();
	}

}
