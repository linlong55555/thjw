package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IRelAdvLocationDao;
import lbw.yht.adv.domain.RelAdvLocation;
import lbw.yht.adv.domain.param.RelAdvLocationParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelAdvLocationService {

	@Autowired
	private IRelAdvLocationDao relAdvLocationDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(RelAdvLocationParam relAdvLocationParam){
		return relAdvLocationDao.queryCount(relAdvLocationParam);
	}
	
	
	
	/**
	 * 分页查询
	 * 
	 * @param RelAdvLocationParam
	 * @return
	 */
	public List<RelAdvLocation> queryAllpageList(RelAdvLocationParam relAdvLocationParam){
		return relAdvLocationDao.queryAllpageList(relAdvLocationParam);
	}
	
		
}
