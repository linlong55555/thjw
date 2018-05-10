package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IAdvLocationDao;
import lbw.yht.adv.domain.AdvLocation;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.param.AdvLocationParam;
import lbw.yht.adv.domain.param.AdvTypeParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvLocationService {

	@Autowired
	private IAdvLocationDao advLocationDao;

	/**
	 * 查询所有类型
	 * 
	 * @return
	 */
	public List<AdvLocation> queryAdvLocationAll() {
		return advLocationDao.queryAdvLocationAll();
	}
	
	
	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvLocationParam advLocationParam) {
		return advLocationDao.queryCount(advLocationParam);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param advVideo
	 * @return
	 */
	public List<AdvLocation> queryAllpageList(AdvLocationParam advLocationParam) {
		return advLocationDao.queryAllpageList(advLocationParam);
	}
	
	
	/**
	 * 添加广告类型
	 * 
	 * @param advAttribute
	 */
	public void save(AdvLocation advLocation) {
		advLocationDao.save(advLocation);
	}
	
	/**
	 * 通过主键查询详细信息
	 * 
	 * @param advId
	 * @return
	 */
	public AdvLocation findOneByadvId(String advId){
		return advLocationDao.findOneByadvId(advId);
	}
	
	
	/**
	 *修改广告类型
	 * 
	 * @param advVideoParam
	 * @return
	 */
	public void modify(AdvLocation advLocation){
		
		 advLocationDao.modify(advLocation);
	}
	
	/**
	 * 修改广告类型
	 * 
	 * @param advType
	 */
	public void updateAdvStatus(AdvLocation advLocation){
		
		advLocationDao.updateAdvStatus(advLocation);
	}
	
	/**
	 * 删除广告位
	 * 
	 * @param advTypes
	 */
	public void delete(String advLocations){
		
		advLocationDao.delete(advLocations);
	}

}
