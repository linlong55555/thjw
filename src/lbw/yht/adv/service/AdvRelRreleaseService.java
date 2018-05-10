package lbw.yht.adv.service;

import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IAdvRelRreleaseDao;
import lbw.yht.adv.domain.AdvRelRrelease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvRelRreleaseService {

	@Autowired
	private IAdvRelRreleaseDao advRelRreleaseDao;

	/**
	 * 插入一条发布-广告位中间记录
	 * 
	 * @param advRelRrelease
	 */
	public void save(AdvRelRrelease advRelRrelease) {
		advRelRreleaseDao.save(advRelRrelease);
	}

	/**
	 * 通过发布id删除发布-广告位中间记录
	 * 
	 * @param releaseId
	 */
	public void deleteByReleaseId(String releaseId) {
		advRelRreleaseDao.deleteByReleaseId(releaseId);
	}

	/**
	 * 查询最热广告位
	 * 
	 * @param releaseName
	 * @return
	 */
	public List<String> querySellAdvattribute(Map<String, Object> map) {
		return advRelRreleaseDao.querySellAdvattribute(map);
	}
	
}
