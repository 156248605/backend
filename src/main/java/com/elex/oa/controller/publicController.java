package com.elex.oa.controller;

import com.elex.oa.util.Pinyin;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/14 16:03
*/
@RestController
@CrossOrigin
@RequestMapping("/pub/")
public class publicController {
    @RequestMapping({"getPinyin"})
    public Object getPinyin(HttpServletRequest request){
        String words = request.getParameter("words");
        String isHead = request.getParameter("isHead");
        String isCap = request.getParameter("isCap");
        if(StringUtils.isBlank(isHead)){
            isHead = "false";
        }
        if(StringUtils.isBlank(isCap)){
            isCap = "false";
        }
        String pinyin ;
        if("true".equals(isHead)) {
            pinyin = Pinyin.getPinYinHeadChar(words);
        } else {
            pinyin = Pinyin.getPingYin(words);
        }
        String data;
        if("true".equals(isCap)) {
            data=pinyin.toUpperCase();
        } else {
            data=pinyin;
        }
        Map map = new HashMap();
        map.put("data",data);
        return RespUtil.successResp("200","success",map);
    }



}
