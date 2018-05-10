package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IRelAdvTypeDao;
import lbw.yht.adv.domain.RelAdvType;
import lbw.yht.adv.domain.param.RelAdvTypeParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelAdvTypeService {

	@Autowired
	private IRelAdvTypeDao relAdvTypeDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(RelAdvTypeParam relAdvTypeParam){
		return relAdvTypeDao.queryCount(relAdvTypeParam);
	}
	
	
	
	/**
	 * 分页查询
	 * 
	 * @param RelAdvTypeParam
	 * @return
	 */
	public List<RelAdvType> queryAllpageList(RelAdvTypeParam relAdvTypeParam){
		return relAdvTypeDao.queryAllpageList(relAdvTypeParam);
	}
	
		
}
