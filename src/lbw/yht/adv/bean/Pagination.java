package lbw.yht.adv.bean;

import java.io.Serializable;

public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 页数
	 */
	private int page;

	/**
	 * 每页行数
	 */
	private int rows;

	/**
	 * 排序列字段名
	 */
	private String sort;

	/**
	 * 排序方式，可以是 'asc' 或者 'desc'，默认值是 'asc'
	 */
	private String order;

	public Pagination() {

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 计算从第几行开始
	 */
	public void initPageIndex() {
		this.page = (page - 1) * rows;
	}

}
