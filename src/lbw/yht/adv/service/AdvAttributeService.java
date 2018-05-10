package lbw.yht.adv.service;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IAdvAttributeDao;
import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.param.AdvAttributeParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvAttributeService {

	@Autowired
	private IAdvAttributeDao advAttributeDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvAttributeParam advAttributeParam) {
		return advAttributeDao.queryCount(advAttributeParam);
	}

	/**
	 * 分页查询
	 * 
	 * @param advAttributeParam
	 * @return
	 */
	public List<AdvAttribute> queryAllpageList(AdvAttributeParam advAttributeParam) {
		return advAttributeDao.queryAllpageList(advAttributeParam);
	}

	/**
	 * 通过主键查询详细信息
	 * 
	 * @param advId
	 * @return
	 */
	public AdvAttribute findOneByAdvId(String advId) {
		return advAttributeDao.findOneByAdvId(advId);
	}

	/**
	 * 保存
	 * 
	 * @param advAttribute
	 */
	public void save(AdvAttribute advAttribute) {
		advAttributeDao.save(advAttribute);
	}

	public void modify(AdvAttribute advAttribute) {
		advAttributeDao.modify(advAttribute);
	}

	public void delete(String advIds) {
		advAttributeDao.delete(advIds);
	}

	/**
	 * 修改广告位状态
	 * 
	 * @param map
	 */
	public void updateAdvStatus(AdvAttribute advAttribute) {
		advAttributeDao.updateAdvStatus(advAttribute);
	}
	
	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryAttributeCount(Map<String, Object> map) {
		return advAttributeDao.queryAttributeCount(map);
	}

	/**
	 * 分页查询
	 * 
	 * @param advAttributeParam
	 * @return
	 */
	public List<AdvAttribute> queryAttributeList(Map<String, Object> map) {
		return advAttributeDao.queryAttributeList(map);
	}
	

}
