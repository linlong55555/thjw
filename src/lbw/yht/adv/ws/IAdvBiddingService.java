package lbw.yht.adv.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.domain.AdvAttribute;
import lbw.yht.adv.domain.AdvBidding;
import lbw.yht.adv.domain.AdvRelBidding;
import lbw.yht.adv.domain.AdvplaceRelease;
import lbw.yht.adv.domain.BiddingTime;
import lbw.yht.adv.service.AdvAttributeService;
import lbw.yht.adv.service.AdvBiddingService;
import lbw.yht.adv.service.AdvRelBiddingService;
import lbw.yht.adv.service.AdvRelRreleaseService;
import lbw.yht.adv.service.AdvplaceReleaseService;
import lbw.yht.adv.service.BiddingDepositService;
import lbw.yht.adv.service.BiddingTimeService;
import lbw.yht.util.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("service/advbidding")
public class IAdvBiddingService {
	@Autowired
	private BiddingTimeService biddingTimeService;// 竞价时间段
	@Autowired
	private AdvBiddingService advBiddingService;// 竞价主表
	@Autowired
	private AdvRelBiddingService advRelBiddingService;// 竞价-广告位
	@Autowired
	private AdvAttributeService advAttributeService;// 广告位
	@Autowired
	private AdvplaceReleaseService advplaceReleaseService;// 广告位发布
	@Autowired
	private AdvRelRreleaseService advRelRreleaseService;// 广告发布中间表
	@Autowired
	private BiddingDepositService biddingDepositService;// 广告押金
	
	/**
	 * 条件查询广告位发布列表信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryAdvplaceatt")
	public Object queryAdvplaceatt(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String typeCode = (String) jsonObject.get("typeCode");// 广告位类型
																													// 海报广告位、广告机广告位、网页广告位
		String regionCode = (String) jsonObject.get("regionCode");// 地区编码

		Message message = new Message(1, null);
		if (message.getCode() == 1) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("typeCode", typeCode);//
				if (!Utils.Str.isEmpty(regionCode)) {
					map.put("regionCode", regionCode);//
				}
				List<AdvAttribute> advAttributeList = advplaceReleaseService
						.queryAttributeList(map);
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("advAttributeList", advAttributeList);// 列表
				map1.put("count", advplaceReleaseService.queryAttributeCount(map));// 总数
				message.setItems(map1);

			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统错误");
			}
		}

		message.setVersion(version);
		return message;
	}

	/**
	 * 广告位发布详情
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("detailAdvplaceatt")
	public Object detailAdvplaceatt(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String advplaceId = (String) jsonObject.get("advplaceId");// 广告位发布id
		Message message = new Message(1, "");
		try {
			message.setItems(advplaceReleaseService.findByAdvplaceId(advplaceId));// 详情
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统错误");
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 根据member_id看用户是否有没有押金
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findBiddingdeposit")
	public Object findBiddingdeposit(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String memberId = (String) jsonObject.get("memberId");// memberId
		Message message = new Message(1, "");
		try {
			message.setItems(biddingDepositService.findOneByMemberId(memberId));//
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统错误");
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 增加一条押金记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addBiddingdeposit")
	public Object addBiddingdeposit(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		final String memberId = (String) jsonObject.get("memberId");// memberId
		final String memberName = (String) jsonObject.get("memberName");// memberName
		final String memberNo = (String) jsonObject.get("memberNo");// memberNo
		final String glideNumber = (String) jsonObject.get("glideNumber");// glideNumber
		final String depositPrice = (String) jsonObject.get("depositPrice");// depositPrice

		class Validator {
			public Message validate() {
				if (Utils.Str.isEmpty(memberId)) {
					return new Message(10, "请输入旺乐用户编号!", "memberId");
				}
				if (Utils.Str.isEmpty(memberName)) {
					return new Message(10, "请输入旺乐用户名!", "memberName");
				}
				if (Utils.Str.isEmpty(memberNo)) {
					return new Message(10, "请输入旺乐卡号!", "memberNo");
				}
				if (Utils.Str.isEmpty(glideNumber)) {
					return new Message(10, "请输入流水号!", "glideNumber");
				}
				if (Utils.Str.isEmpty(depositPrice)) {
					return new Message(10, "请输入押金金额!", "depositPrice");
				} else if (!depositPrice.matches("[1-9]\\d*")) {
					return new Message(10, "请输入正确的押金金额!", "depositPrice");
				}
				return new Message(1, null);
			}
		}

		Message message = new Validator().validate();
		try {
			if (message.getCode() == 1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("memberId", memberId);
				map.put("memberName", memberName);
				map.put("memberNo", memberNo);
				map.put("glideNumber", glideNumber);
				map.put("depositPrice", depositPrice);
				biddingDepositService.addBiddingdeposit(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统错误");
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 根据member_id修改押金为归还
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateBiddingdeposit")
	public Object updateBiddingdeposit(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String memberId = (String) jsonObject.get("memberId");// memberId
		Message message = new Message(1, "");
		try {
			biddingDepositService.updateOneByMemberId(memberId);//
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统错误");
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 查询热门竞拍的广告位列表信息     按照竞拍中间表中的竞拍次数（广告位发布id的次数）
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryhotAdvattribute")
	public Object queryhotAdvattribute(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象
		String number = (String) jsonObject.get("number");// 广告位类型
																											// 海报广告位、广告机广告位、网页广告位
		Message message = new Message(1, null);
		if (message.getCode() == 1) {
			try {
				if (Utils.Str.isEmpty(number)) {
					message = new Message(10, "数量不为空!", "number");
				} else if (!number.matches("[1-9]\\d*")) {
					message = new Message(10, "请输入正确的数量!", "number");
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("number", number);
					List<String> advplaceIdList = advRelBiddingService
							.queryHotAdvattribute(map);
					List<AdvplaceRelease> advplaceReleaseList = new ArrayList<AdvplaceRelease>();
					for (String advplaceId : advplaceIdList) {// 循环获取广告发布信息
						AdvplaceRelease advplaceRelease = advplaceReleaseService
								.findByAdvplaceId(advplaceId);
						advplaceRelease.setBiddingBulletin("");
						advplaceRelease.setBiddingNotice("");
						advplaceRelease.getAdvAttribute().setAdvExplain("");
						advplaceReleaseList.add(advplaceRelease);
					}
					message.setItems(advplaceReleaseList);
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统错误");
			}
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 查询最新竞拍列表信息   
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("querynewAdvbidding")
	public Object querynewAdvbidding(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String numCount = (String) jsonObject.get("numCount");// 广告位类型
																													// 海报广告位、广告机广告位、网页广告位
		Message message = new Message(1, null);
		if (message.getCode() == 1) {
			try {
				if (Utils.Str.isEmpty(numCount)) {
					message = new Message(10, "数量不为空!", "numCount");
				} else if (!numCount.matches("[1-9]\\d*")) {
					message = new Message(10, "请输入正确的数量!", "numCount");
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("numCount", numCount);
					List<AdvBidding> advBiddingList = advBiddingService
							.queryNewAdvbidding(map);
					message.setItems(advBiddingList);
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统错误");
			}
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 查询热销的广告位列表信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("querysellAdvattribute")
	public Object querysellAdvattribute(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String numCount = (String) jsonObject.get("numCount");// 广告位类型
																													// 海报广告位、广告机广告位、网页广告位
		Message message = new Message(1, null);
		if (message.getCode() == 1) {
			try {
				if (Utils.Str.isEmpty(numCount)) {
					message = new Message(10, "数量不为空!", "numCount");
				} else if (!numCount.matches("[1-9]\\d*")) {
					message = new Message(10, "请输入正确的数量!", "numCount");
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("numCount", numCount);
					List<String> advList = advRelRreleaseService
							.querySellAdvattribute(map);
					List<AdvAttribute> advAttributeList = new ArrayList<AdvAttribute>();
					for (String advId : advList) {// 循环获取广告位信息
						AdvAttribute attribute = advAttributeService.findOneByAdvId(advId);
						attribute.setAdvExplain("");// 说明比较多，传输影响效率
						advAttributeList.add(attribute);
					}
					message.setItems(advAttributeList);
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统错误");
			}
		}
		message.setVersion(version);
		return message;
	}
	
	/**
	 * 查询热销的广告位列表信息
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("querysellAdvVideo")
	public Object querysellAdvVideo(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String numCount = (String) jsonObject.get("numCount");// 广告位类型
																													// 海报广告位、广告机广告位、网页广告位
		Message message = new Message(1, null);
		if (message.getCode() == 1) {
			try {
				if (Utils.Str.isEmpty(numCount)) {
					message = new Message(10, "数量不为空!", "numCount");
				} else if (!numCount.matches("[1-9]\\d*")) {
					message = new Message(10, "请输入正确的数量!", "numCount");
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("numCount", numCount);
					List<String> advList = advRelRreleaseService
							.querySellAdvattribute(map);
					List<AdvAttribute> advAttributeList = new ArrayList<AdvAttribute>();
					for (String advId : advList) {// 循环获取广告位信息
						AdvAttribute attribute = advAttributeService.findOneByAdvId(advId);
						attribute.setAdvExplain("");// 说明比较多，传输影响效率
						advAttributeList.add(attribute);
					}
					message.setItems(advAttributeList);
				}

			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统错误");
			}
		}
		message.setVersion(version);
		return message;
	}


	/**
	 * 根据memberId、竞价状态和分页信息查看竞价列表接口
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryBiddingByMember")
	public Object queryBiddingByMember(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String memberId = (String) jsonObject.get("memberId");// 用户id
		String biddingStatus = (String) jsonObject.get("biddingStatus");// 【0:竞价中、1：竞价成功、2
		// : 已发布、-1：竞价失败】
		String page = (String) jsonObject.get("page");// 开始
		String rows = (String) jsonObject.get("rows");// 条数

		Message message = new Message(1, null);
		if (message.getCode() == 1) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("memberId", memberId);//
				if (!Utils.Str.isEmpty(biddingStatus)) {
					map.put("biddingStatus", biddingStatus);//
				}
				map.put("page", page);//
				map.put("rows", rows);//
				List<AdvBidding> advBiddingList = advBiddingService
						.queryBiddingByMember(map);

				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("advBiddingList", advBiddingList);// 列表
				map1.put("count", advBiddingService.queryBidByMemCount(map));// 总数
				message.setItems(map1);

			} catch (Exception e) {
				e.printStackTrace();
				message = new Message(9, "系统错误");
			}
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 竞价详情
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping("detailAdvbidding")
	public Object detailAdvbidding(HttpServletRequest req,
			HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本
		String data = req.getParameter("data");// 值
		JSONObject jsonObject = JSON.parseObject(data);// 转换成JSONObject对象

		String biddingId = (String) jsonObject.get("biddingId");// 广告位id
		Message message = new Message(1, "");
		try {
			message.setItems(advBiddingService.findByBiddingId(biddingId));// 详情
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统错误");
		}
		message.setVersion(version);
		return message;
	}

	/**
	 * 增加一条竞拍记录
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("addAdvbidding")
	public Object addAdvbidding(HttpServletRequest req, HttpServletResponse resp) {
		String version = req.getParameter("version");// 版本

		Message message = new Message(1, "");
		try {
			AdvBidding advBidding = com.alibaba.fastjson.JSON.parseObject(
					req.getParameter("data"), AdvBidding.class);// 获取advBidding
			if (message.getCode() == 1) {
				Integer countInteger = advBiddingService.queryMaxBiddingId();// 查询总数
				if (countInteger == null) {
					countInteger = 0;
				}
				advBidding.setBiddingId(countInteger + 1);
				advBidding.setBiddingStatus(0);// 竞拍中
				advBiddingService.addAdvBidding(advBidding);// 增加一条竞价记录

				List<BiddingTime> biddingTimeList = advBidding.getBiddingTimeList();// 竞价时间段
				if (biddingTimeList != null && biddingTimeList.size() > 0) {
					BiddingTime biddingTime = new BiddingTime();
					biddingTime.setBiddingId(advBidding.getBiddingId());// 竞价id
					for (int i = 0; i < biddingTimeList.size(); i++) {
						biddingTime.setStartTime(biddingTimeList.get(i).getStartTime());// 开始时间
						biddingTime.setEndTime(biddingTimeList.get(i).getEndTime());// 结束时间
						biddingTime.setTotalNumber(biddingTimeList.get(i).getTotalNumber());// 次数
						biddingTimeService.save(biddingTime);// 插入一条竞价时间段记录
					}
				}

				List<AdvplaceRelease> advplaceReleaseList = advBidding
						.getAdvplaceReleaseList();
				if (advplaceReleaseList != null && advplaceReleaseList.size() > 0) {
					AdvRelBidding advRelBidding = new AdvRelBidding();
					advRelBidding.setBiddingId(advBidding.getBiddingId());// 竞价id
					for (int i = 0; i < advplaceReleaseList.size(); i++) {
						advRelBidding.setAdvplaceId(advplaceReleaseList.get(i)
								.getAdvplaceId());// 广告位发布id
						advRelBiddingService.save(advRelBidding);// 插入一条竞价-广告位中间记录
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			message = new Message(9, "系统错误");
		}
		message.setVersion(version);
		return message;
	}

}