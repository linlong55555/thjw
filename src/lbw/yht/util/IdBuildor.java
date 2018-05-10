
/** 
 * Project Name:adv_web 
 * File Name:IdBuildor.java 
 * Package Name:lbw.yht.util 
 * @author @lbw001.com 
 * Date:2015年5月21日下午2:40:27 
 * Copyright (c) 2015, wangbo@lbw001.com All Rights Reserved. 
 */   
  
package lbw.yht.util;  

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
 
/** 
 * ClassName: IdBuildor <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2015年5月21日 下午2:40:27 <br/> 
 * 
 * @author @lbw001.com 
 * @version  
 * @since JDK 1.6 
 */
public class IdBuildor {

  public static String buildFromDateAndSeq(String maxId){
    String rtn = null;
    String date = DateFormatUtils.format(new Date(), "yyyyMMdd");
    if(Utils.Str.isEmpty(maxId)){
      rtn = date + get8Code(1);
    }else{
      String numberStr = maxId.substring(maxId.length() - 8, maxId.length());
      int number = Integer.parseInt(numberStr);
      rtn = date + get8Code(++number);
    }
    return rtn;
  }
  
  /**
   * 获取8位长度以0开头的字符串
   * */
  private static String get8Code(int number){
    String rtn = null;
    rtn = "0000000" + number;
    rtn = rtn.substring(rtn.length() - 8, rtn.length());
    return rtn;
  }
  
  public static void main(String[] args) {
    System.out.println(IdBuildor.buildFromDateAndSeq("2015052100000011"));
  }
}
 