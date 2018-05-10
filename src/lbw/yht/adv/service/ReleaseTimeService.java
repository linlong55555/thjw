package lbw.yht.adv.service;

import lbw.yht.adv.dao.IReleaseTimeDao;
import lbw.yht.adv.domain.ReleaseTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseTimeService {

	@Autowired
	private IReleaseTimeDao releaseTimeDao;

	/**
	 * 插入一条播放时间段
	 * 
	 * @param releaseTime
	 */
	public void save(ReleaseTime releaseTime) {
		releaseTimeDao.save(releaseTime);
	}

	/**
	 * 通过发布id删除播放时间段
	 * 
	 * @param releaseId
	 */
	public void deleteByReleaseId(String releaseId) {
		releaseTimeDao.deleteByReleaseId(releaseId);
	}

}
