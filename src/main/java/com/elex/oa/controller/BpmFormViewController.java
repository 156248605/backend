package com.elex.oa.controller;

import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.util.IdUtil;
import com.elex.oa.util.ResultUtil;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bpmFormView")
public class BpmFormViewController {
    private IBpmFormViewService formViewService;
    @Autowired
    public BpmFormViewController(IBpmFormViewService formViewService) {
        this.formViewService = formViewService;
    }



    @PostMapping("/listData")
    public Object bpmFormView(HttpServletRequest request){
        String treeId = request.getParameter("treeId");
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
        Map<String,String> map = new HashMap<>();
        map.put("tenantId","1");//租户ID
        map.put("treeId",treeId);
        map.put("page",page);
        map.put("rows",rows);
        map.put("name",name);
        map.put("key",key);
        map.put("type",type);
        map.put("status",status);
        PageInfo<BpmFormView> pageInfo = this.formViewService.getByTreeFilterNew(map);
        return RespUtil.successResp("200","success",pageInfo);
   }

   @GetMapping("/previewById/{viewId}")
   public Object previewById(@PathVariable String viewId){
       return RespUtil.successResp("200","success",this.formViewService.getById(viewId));
   }

   @PostMapping("/save")
    public Object save(BpmFormView bpmFormView,HttpServletRequest request){
      boolean rtn = formViewService.isKeyExist(bpmFormView.getViewId());
      if (rtn){
          return RespUtil.successResp(ResultUtil.SC_HAS_EXIST.getCode(),"表单key已存在",null);
      }
       String msg = null;
       String deploy = request.getParameter("deploy");
       if("true".equals(deploy)) {
           bpmFormView.setStatus("DEPLOYED");
       }
       String deployNew = request.getParameter("deployNew");
       String treeId = request.getParameter("treeId");
       if(!StringUtils.isEmpty(treeId)){
           bpmFormView.setTreeId(treeId);
       }
       if(StringUtils.isEmpty(bpmFormView.getViewId())){
           String id = IdUtil.getId();
           bpmFormView.setViewId(id);
           this.formViewService.create(bpmFormView);
           msg = "业务表单视图成功创建";
       }else if("true".equals(deployNew)){
           msg = "业务表单视图成功创建";
       }else {
           this.formViewService.update(bpmFormView);
           msg = "业务表单视图成功更新!";
       }
        return  RespUtil.successResp(ResultUtil.SC_OK.getCode(),msg,null);
    }
}
