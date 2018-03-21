package com.elex.oa.controller;

import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.IBpmFormViewService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/15 11:33
*/
@RestController
@CrossOrigin
@RequestMapping("/bpmFormView")
public class BpmFormViewController {
    @Autowired
    private IBpmFormViewService bpmFormViewService;

    @RequestMapping({"/getBpmFormView"})
    public PageInfo getBpmFormView(HttpServletRequest request){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String name = request.getParameter("name");
        String key = request.getParameter("key");
        String type = request.getParameter("type");
        if("0".equals(type)){
            type = "ONLINE-DESIGN";
        }else if("1".equals(type)){
            type = "URL";
        }
        String status = request.getParameter("status");
        if("0".equals(status)){
            status = "INIT";
        }else if("1".equals(status)){
            status = "DEPLOYED";
        }
        String editNodeId = request.getParameter("editNodeId");
        Map<String,Object> map = new HashMap<>();
        map.put("tenantId","1");//租户ID
        map.put("page",page);
        map.put("rows",rows);
        map.put("name",name);
        map.put("key",key);
        map.put("type",type);
        map.put("status",status);
        map.put("treeId",editNodeId);
        //表单数据
        PageInfo<BpmFormView> pageInfo = bpmFormViewService.getBpmFormView(map);
        return pageInfo;
    }

}
