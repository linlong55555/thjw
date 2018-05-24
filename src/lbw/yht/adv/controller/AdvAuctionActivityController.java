
/** 
 * Project Name:adv_web 
 * File Name:AdvAuctionActivityController.java 
 * Package Name:lbw.yht.adv.controller 
 * @author @lbw001.com 
 * Date:2015年5月21日上午10:48:49 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.controller;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AdvType;
import lbw.yht.adv.service.AdvAuctionActivityService;
import lbw.yht.adv.service.AdvTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
/** 
 * ClassName: AdvAuctionActivityController <br/> 
 * Function: 处理竞拍活动的相关交互请求. <br/> 
 * date: 2015年5月21日 上午10:48:49 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
@Controller
@Scope("prototype")
@RequestMapping("activity")
public class AdvAuctionActivityController extends BaseController {

  private final static String PAGE = "pagination";
  
  @Autowired
  private AdvAuctionActivityService auctionActivityService;
  
  @Autowired
  private AdvTypeService advTypeService;
  
  /**
   * 初始化竞拍活动页面
   * 
   * */
  @RequestMapping("init")
  public ModelAndView init(){    
    return new ModelAndView("advAuctionActivity/index");
  }
  
  /**
   * 处理查询
   * 
   * */
  @RequestMapping("list")
  public String list(HttpServletRequest req, HttpServletResponse resp, 
      @RequestParam Map<String,String> params){
    try {
      //获取分页相关参数
      Pagination page = this.initPage(req);
      //处理查询参数并添加分页参数
      Map<String, Object> paramsMap = new HashMap<String, Object>();
      paramsMap.putAll(params);
      paramsMap.put(PAGE, page);
      //返回查询结果
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("total", this.auctionActivityService.queryCount(paramsMap));
      model.put("rows", this.auctionActivityService.queryAuctionActivity(paramsMap));
      this.htmlWrite(resp, model);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @RequestMapping("initAdd")
  public ModelAndView initAdd(){
    List<AdvType> advTypes = this.advTypeService.queryAdvTypeAll();
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("advTypes", advTypes);//新增了一个项目
    return new ModelAndView("advAuctionActivity/add", model);
  }
  
  @RequestMapping("getAuctionAttrs")
  public String getAuctionAttrs(){
    
    return null;
  }
}
 