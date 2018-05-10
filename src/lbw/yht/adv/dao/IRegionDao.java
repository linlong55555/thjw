package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.Region;

public interface IRegionDao {

	/**
	 * 查询所有地区
	 * 
	 * @return
	 */
	public List<Region> queryRegionAll();

	public List<Region> getRegionByParentCode(String code);

	public List<Region> getParentRegionAll();
	/**
	 * 通过孩子code找父code
	 */
	public String getRegionByChildrenCode(String code);
}
