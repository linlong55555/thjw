package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IRegionDao;
import lbw.yht.adv.domain.Region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

	@Autowired
	private IRegionDao regionDao;

	/**
	 * 查询所有地区
	 * 
	 * @return
	 */
	public List<Region> getRegionAll() {
		return regionDao.queryRegionAll();
	}

	public List<Region> getRegionByParentCode(String code) {
		return regionDao.getRegionByParentCode(code);
	}

	/**
	 * 通过孩子code找父code
	 */
	public String getRegionByChildrenCode(String code) {
		return regionDao.getRegionByChildrenCode(code);
	}

	public List<Region> getParentRegionAll() {
		return regionDao.getParentRegionAll();
	}

}
