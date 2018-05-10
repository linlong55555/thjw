package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IAdvFiltrateDao;
import lbw.yht.adv.domain.AdvFiltrate;
import lbw.yht.adv.domain.param.AdvFiltrateParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvFiltrateService {

	@Autowired
	private IAdvFiltrateDao advFiltrateDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvFiltrateParam advFiltrateParam){
		return advFiltrateDao.queryCount(advFiltrateParam);
	}
	
	
	/**
	 * 分页查询
	 * 
	 * @param advVideoParam
	 * @return
	 */
	public List<AdvFiltrate> queryAllpageList(AdvFiltrateParam advFiltrateParam){
		return advFiltrateDao.queryAllpageList(advFiltrateParam);
	}
	
		
}
