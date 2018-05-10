
/** 
 * Project Name:adv_web 
 * File Name:AuctionAttributeController.java 
 * Package Name:lbw.yht.adv.controller 
 * @author @lbw001.com 
 * Date:2015年5月21日下午5:58:58 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.adv.controller;  

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lbw.yht.adv.bean.Message;
import lbw.yht.adv.bean.Pagination;
import lbw.yht.adv.domain.AuctionAttribute;
import lbw.yht.adv.service.AuctionAttributeService;
import lbw.yht.util.Utils;
 
/** 
 * ClassName: AuctionAttributeController <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2015年5月21日 下午5:58:58 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
@Controller
@Scope("prototype")
@RequestMapping("auctionAttribute")
public class AuctionAttributeController extends BaseController {

  private final static String PAGE = "pagination";
  
  @Autowired
  private AuctionAttributeService auctionAttrService;
  
  @RequestMapping("init")
  public String init(){
    return "auctionAttribute/index";
  }
  
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
      model.put("total", this.auctionAttrService.queryCount(paramsMap));
      model.put("rows", this.auctionAttrService.queryAuctionAttributeList(paramsMap));
      this.htmlWrite(resp, model);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @RequestMapping("initAdd")
  public String initAdd(){
    
    return "auctionAttribute/add";
  }
  
  @RequestMapping("checkId")
  public String checkId(HttpServletResponse resp, @RequestParam Map<String,String> params){
    int count = this.auctionAttrService.getCountById(params.get("id"));
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("result", (count == 0));
    this.htmlWrite(resp, model);
    return null;
  }
  
  @RequestMapping("add")
  public String add(HttpServletResponse resp, @ModelAttribute AuctionAttribute auctionAttr){
    Map<String, Object> model = new HashMap<String, Object>();
    final String attrId = auctionAttr.getAttrId();
    final String attrName = auctionAttr.getAttrName();
    final String eletype = auctionAttr.getEleType();
    final String defaultVal = auctionAttr.getDefaultValue();
    class Validator {
      public Message validate() {
        if (Utils.Str.isEmpty(attrId)) {
          return new Message(10, "请输入属性编号!", "attrId");
        }
        if (Utils.Str.isEmpty(attrName)) {
          return new Message(10, "请输入属性名称!", "attrName");
        }
        if (Utils.Str.isEmpty(eletype)) {
          return new Message(10, "请输入元素!", "eletype");
        }
        if (Utils.Str.isEmpty(defaultVal)) {
          return new Message(10, "请输入属性默认值!", "defaultVal");
        }
        return new Message(1, null);
      }
    }
    Validator validator = new Validator();
    Message message = validator.validate();
    if (message.getCode() == 1){
      try {
        this.auctionAttrService.saveAuctionAttribute(auctionAttr);
        message = new Message(1, "添加竞拍属性成功!");
      }catch (Exception e) {
        message = new Message(9, "添加竞拍属性失败!");
        e.printStackTrace();
      }
    }
    this.htmlWrite(resp, message);
    return null;
  }
}
 