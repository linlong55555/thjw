package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IAdvTypeDao;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.param.AdvTypeParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvTypeService {

	@Autowired
	private IAdvTypeDao advTypeDao;

	/**
	 * 查询所有类型
	 * 
	 * @return
	 */
	public List<AdvType> queryAdvTypeAll() {
		return advTypeDao.queryAdvTypeAll();
	}
	
	
	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvTypeParam advTypeParam) {
		return advTypeDao.queryCount(advTypeParam);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param advVideo
	 * @return
	 */
	public List<AdvType> queryAllpageList(AdvTypeParam advTypeParam) {
		return advTypeDao.queryAllpageList(advTypeParam);
	}
	
	
	/**
	 * 添加广告类型
	 * 
	 * @param advAttribute
	 */
	public void save(AdvType advType) {
		advTypeDao.save(advType);
	}
	
	/**
	 * 通过广告父类编号查询typeCode
	 * 
	 * @param parentTypeCode
	 * @return
	 */
	public String findOneByTypeCode(String parentTypeCode) {
		return advTypeDao.findOneByTypeCode(parentTypeCode);
	}
	
	/**
	 * 通过主键查询详细信息
	 * 
	 * @param typeCode
	 * @return
	 */
	public AdvType findOneByTypeCodeID(String typeCode){
		return advTypeDao.findOneByTypeCodeID(typeCode);
	}
	
	
	/**
	 *修改广告类型
	 * 
	 * @param advVideoParam
	 * @return
	 */
	public void modify(AdvType advType){
		
		 advTypeDao.modify(advType);
	}
	
	/**
	 * 修改广告类型状态
	 * 
	 * @param advType
	 */
	public void updateAdvStatus(AdvType advType){
		
		advTypeDao.updateAdvStatus(advType);
	}
	
	/**
	 * 删除广告类型
	 * 
	 * @param advTypes
	 */
	public void delete(String advTypes){
		
		advTypeDao.delete(advTypes);
	}

}
