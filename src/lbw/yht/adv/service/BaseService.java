package lbw.yht.adv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lbw.yht.adv.dao.IBaseDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
	@Autowired
	private IBaseDao iBaseDAO;

	public int queryCount(String tname) {
		Map map = new HashMap();
		map.put("tname", tname);
		return iBaseDAO.queryCount(map);
	}

	public List comboboxLoad(String id, String text, String tabname) {
		Map map = new HashMap();
		map.put("cid", id);
		map.put("ctext", text);
		map.put("tname", tabname);
		return iBaseDAO.comboboxLoad(map);
	}
}
