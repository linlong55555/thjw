package lbw.yht.adv.dao;

import java.util.List;

import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.domain.param.AdvTypeParam;

public interface IAdvTypeDao {

	/**
	 * 查询所有类型
	 * 
	 * @return
	 */
	public List<AdvType> queryAdvTypeAll();
	
	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvTypeParam advTypeParam);
	
	
	/**
	 * 分页查询
	 * 
	 * @param advVideoParam
	 * @return
	 */
	public List<AdvType> queryAllpageList(AdvTypeParam advTypeParam);
	
	/**
	 *添加广告类型
	 * 
	 * @param advVideoParam
	 * @return
	 */
	public void save(AdvType advType);

	/**
	 * 通过广告父类编号查询typeCode
	 * 
	 * @param parentTypeCode
	 * @return
	 */
	public String findOneByTypeCode(String parentTypeCode);
	
	/**
	 * 通过主键查询详细信息
	 * 
	 * @param typeCode
	 * @return
	 */
	public AdvType findOneByTypeCodeID(String typeCode);
	
	/**
	 *修改广告类型
	 * 
	 * @param advType
	 * @return
	 */
	public void modify(AdvType advType);
	
	/**
	 * 修改广告类型状态
	 * 
	 * @param advType
	 */
	public void updateAdvStatus(AdvType advType);
	
	/**
	 * 删除广告类型
	 * 
	 * @param advTypes
	 */
	public void delete(String advTypes);
	
}
