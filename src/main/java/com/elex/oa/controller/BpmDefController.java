package com.elex.oa.controller;

import com.elex.oa.entity.BpmDef;
import com.elex.oa.service.IBpmDefService;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bpmDef")
public class BpmDefController {
    @Autowired
    private IBpmDefService bpmDefService;

    @PostMapping("/listData")
    public Object bpmFormView(HttpServletRequest request){
        String treeId = request.getParameter("treeId");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String subject = request.getParameter("subject");
        String key = request.getParameter("key");
        String status = request.getParameter("status");
        if("0".equals(status)){
            status = "INIT";
        }else if("1".equals(status)){
            status = "DEPLOYED";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tenantId","1");//租户ID
        map.put("treeId",treeId);
        map.put("page",page);
        map.put("rows",rows);
        map.put("subject",subject);
        map.put("key",key);
        map.put("status",status);
        PageInfo<BpmDef> pageInfo = this.bpmDefService.query(map);
        return RespUtil.successResp("200","success",pageInfo);
   }
}
