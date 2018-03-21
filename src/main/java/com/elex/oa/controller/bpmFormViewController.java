package com.elex.oa.controller;

import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.IBpmFormViewService;
import com.elex.oa.util.IdUtil;
import com.elex.oa.util.ResultUtil;
import com.elex.oa.util.resp.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/bpmFormView")
public class bpmFormViewController {
    private IBpmFormViewService formViewService;
    @Autowired
    public bpmFormViewController(IBpmFormViewService formViewService) {
        this.formViewService = formViewService;
    }



    @PostMapping("/listData")
    public Object bpmFormView(HttpServletRequest request){
        String treeId = request.getParameter("treeId");
        Map<String,String> map = new HashMap<>();
        map.put("treeId",treeId);
        return RespUtil.successResp("200","success",this.formViewService.getByTreeFilterNew(map));
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
