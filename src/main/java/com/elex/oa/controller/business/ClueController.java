package com.elex.oa.controller.business;

import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.service.business.IClueService;
import com.elex.oa.service.business.IOpportunityService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/clue")
@Controller
@CrossOrigin
public class ClueController {
    @Autowired
    IClueService iClueService;
    @Autowired
    IOpportunityService iOpportunityService;
    @Autowired
    HrUtils hrUtils;

    @RequestMapping(value = "/getPageInfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageInfo<Clue> getPageInfo(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            Clue clue,
            @RequestParam("flag") String flag,
            HttpServletRequest request
    ){
        String queryStr = request.getParameter("queryStr");
        if (null != request.getParameter("state")) {
            clue.setState(request.getParameter("state"));
        }
        if("PRIVATE".equals(flag)){
            clue.setSale_employeenumber(hrUtils.getEmployeenumberByUsername(clue.getUsername()));
        }
        return iClueService.getPageInfoByCondition(page,rows,clue,flag,queryStr);
    }

    @RequestMapping(value = "/clue_ADD",consumes = "multipart/form-data",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object clueAdd(
            Clue clue,
            HttpServletRequest request,
            @RequestParam(name = "attachmentSize", required = false)Integer i,
            @RequestParam("username")String username
    ){
        //获得附件地址
        if (null!=i) {
            List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
            clue.setBusinessAttachmentList(businessAttachmentList);
        }
        //自动生成线索编码（主键）
        //线索编号的格式暂定为：ELEX-CLU-UN-YYYY-MMNNNN
        String clueCode = hrUtils.getClueCode(username);
        clue.setCode(clueCode);
        clue.setIn_department(request.getParameter("depName"));
        //调用业务层方法
        return iClueService.addClueInfo(clue);
    }

    @RequestMapping(value = "/getDetailClueinfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Clue getDetailClueinfo(
            @RequestParam("cluecode")String cluecode
    ){
        return iClueService.getDetailClueinfo(cluecode);
    }

    @RequestMapping(value = "/getDetailOpportunityinfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Opportunity getDetailOpportunityinfo(
            @RequestParam("cluecode")String cluecode
    ){
        return iOpportunityService.getDetailOpportunityinfoByCluecode(cluecode);
    }

    @RequestMapping(value = "/clue_UPDATE",consumes = "multipart/form-data",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object clueUpdate(
            Clue clue,
            HttpServletRequest request,
            @RequestParam(name = "attachmentSize", required = false)Integer i
    ){
        //获得附件地址
        if (null!=i) {
            List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
            clue.setBusinessAttachmentList(businessAttachmentList);
        }
        clue.setIn_department(request.getParameter("depName"));
        Boolean aBoolean = iClueService.modifyClueInfo(clue);
        return aBoolean?RespUtil.response("200","更新成功！",clue.getCode()):RespUtil.response("500","更新失败！",null);
    }

    @RequestMapping(value = "/closeClueinfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object closeClueinfo(
            @RequestParam("cluecode")String cluecode,
            @RequestParam("trackcontent")String trackcontent,
            @RequestParam("username")String username
    ){
        return iClueService.closeClueInfo(cluecode,trackcontent,username);
    }

    private List<BusinessAttachment> getBusinessAttachmentList(MultipartHttpServletRequest request, int i) {
        List<BusinessAttachment> businessAttachmentList = new ArrayList<>();
        List<String> multiFileAddress = hrUtils.getMultiFileAddress(request, i);
        if(null==multiFileAddress)return Collections.emptyList();
        for (String attachment_address:multiFileAddress
             ) {
            BusinessAttachment businessAttachment = new BusinessAttachment();
            businessAttachment.setAttachment_address(attachment_address);
            businessAttachmentList.add(businessAttachment);
        }
        return businessAttachmentList;
    }

    @RequestMapping(value = "/getRelativeEvent",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List getRelativeEvent (HttpServletRequest request) {
        return iClueService.getRelativeEvent(request);
    }
}