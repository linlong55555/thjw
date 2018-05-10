package lbw.yht.adv.service;

import java.util.List;

import lbw.yht.adv.dao.IAdvReleaseDao;
import lbw.yht.adv.domain.AdvRelease;
import lbw.yht.adv.domain.param.AdvReleaseParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvReleaseService {

	@Autowired
	private IAdvReleaseDao advReleaseDao;

	/**
	 * 分页查找总数
	 * 
	 * @return
	 */
	public int queryCount(AdvReleaseParam advReleaseParam) {
		return advReleaseDao.queryCount(advReleaseParam);
	}

	/**
	 * 分页查询
	 * 
	 * @param advReleaseParam
	 * @return
	 */
	public List<AdvRelease> queryAllpageList(AdvReleaseParam advReleaseParam) {
		return advReleaseDao.queryAllpageList(advReleaseParam);
	}

	/**
	 * 插入一条发布记录
	 * 
	 * @param advRelease
	 */
	public void save(AdvRelease advRelease) {
		advReleaseDao.save(advRelease);
	}

	/**
	 * 通过发布id删除发布记录
	 * 
	 * @param releaseId
	 */
	public void deleteByReleaseId(String releaseId) {
		advReleaseDao.deleteByReleaseId(releaseId);
	}

	/**
	 * 通过主键id找一条记录
	 * 
	 * @param releaseId
	 * @return
	 */
	public AdvRelease findByReleaseId(String releaseId) {
		return advReleaseDao.findByReleaseId(releaseId);
	}

	/**
	 * 通过发布名找一条记录
	 * 
	 * @param releaseId
	 * @return
	 */
	public List<AdvRelease> findByReleaseName(String releaseName) {
		return advReleaseDao.findByReleaseName(releaseName);
	}

	/**
	 * 修改一条记录
	 * 
	 * @param advRelease
	 */
	public void modify(AdvRelease advRelease) {
		advReleaseDao.modify(advRelease);
	}
}
