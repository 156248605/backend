package com.elex.oa.controller.business;

import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.service.business.IOpportunityService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\10 0010 14:21
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@RequestMapping("/opportunity")
public class OpportunityController {
    @Autowired
    IOpportunityService iOpportunityService;
    @Autowired
    HrUtils hrUtils;

    @RequestMapping(value = "/opportunity_ADD",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object opportunityAdd(
            Opportunity opportunity,
            HttpServletRequest request,
            @RequestParam(name = "attachmentSize", required = false)Integer i,
            @RequestParam("username")String username
    ){
        //获得附件地址
        if (null!=i) {
            List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
            opportunity.setBusinessAttachmentList(businessAttachmentList);
        }
        //自动生成商机编码（主键）
        //商机编号的格式暂定为：ELEX-BIZ-UN-YYYY-MMNNNN
        String opportunityCode = hrUtils.getOpportunityCode(username);
        opportunity.setCode(opportunityCode);
        opportunity.setIn_department(request.getParameter("depName"));
        //调用业务层方法
        return iOpportunityService.transforClueToOpportunity(opportunity, username);
    }

    @RequestMapping(value = "/getPageInfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageInfo<Opportunity> getPageInfo(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            Opportunity opportunity,
            @RequestParam("flag") String flag,
            @RequestParam("state") String state,
            @RequestParam("queryStr") String queryStr
    ){
        opportunity.setState(state);
        if("PRIVATE".equals(flag)){
            opportunity.setSale_employeenumber(hrUtils.getEmployeenumberByUsername(opportunity.getUsername()));
        }
        if (null != queryStr && !"".equals(queryStr)) {
            opportunity.setQueryStr(queryStr);
        }
        return iOpportunityService.getPageInfoByCondition(page,rows,opportunity,flag);
    }

    @RequestMapping(value = "/getDetailOpportunityinfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Opportunity getDetailOpportunityinfo(
            @RequestParam("opportunitycode")String opportunitycode
    ){
        return iOpportunityService.getDetailOpportunityinfo(opportunitycode);
    }

    @RequestMapping(value = "/opportunity_UPDATE",consumes = "multipart/form-data",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object opportunityUpdate(
            Opportunity opportunity,
            HttpServletRequest request,
            @RequestParam(name = "attachmentSize", required = false)Integer i
    ){
        //获得附件地址
        if (null!=i) {
            List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
            opportunity.setBusinessAttachmentList(businessAttachmentList);
        }
        if (request.getParameter("instId") != null) {
            opportunity.setInst_id(request.getParameter("instId"));
        }
        if (request.getParameter("projectNumber") != null) {
            opportunity.setProject_number(request.getParameter("projectNumber"));
        }
        opportunity.setIn_department(request.getParameter("depName"));
        Boolean aBoolean = iOpportunityService.modifyOpportunityInfo(opportunity);
        return aBoolean?RespUtil.response("200","更新成功！",opportunity.getCode()):RespUtil.response("500","更新失败！",null);
    }

    @RequestMapping(value = "/closeOpportunityinfo",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object closeOpportunityinfo(
            @RequestParam("opportunitycode")String opportunitycode,
            @RequestParam("trackcontent")String trackcontent,
            @RequestParam("username")String username
    ){
        return iOpportunityService.closeOpportunityInfo(opportunitycode,trackcontent,username);
    }

    @RequestMapping(value = "/getBusinessInfoByState_OFF",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> getBusinessInfoByStateOff(){
        return iOpportunityService.getBusinessInfoByStateOFF();
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
}    
