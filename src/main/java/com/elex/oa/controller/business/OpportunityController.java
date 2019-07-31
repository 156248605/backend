package com.elex.oa.controller.business;

import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.service.business.IOpportunityService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.user.UserUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
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
    @Resource
    UserUtil userUtil;
    @Resource
    IClueDao iClueDao;

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
        List<HashMap<String, Object>> depList = iClueDao.queryDeptByUserId(iClueDao.queryUserIdByEmployeeNumber(request.getParameter("sale_employeenumber")).get(0).get("USER_ID_").toString());
        String depName = "";
        if (depList.size() != 0){
            depName = depList.get(0).get("PATH_").toString();
            if (depName.length() - depName.replaceAll("\\.","").length() == 2 || depName.length() - depName.replaceAll("\\.","").length() == 3) {
                for (int j = 0; j < depName.length() - depName.replaceAll("\\.","").length(); j++) {
                    depName = depName.substring(depName.indexOf(".") + 1);
                }
                depName = depName.substring(0,depName.indexOf("."));
            } else {
                for (int j = 0; j < 2; j++) {
                    depName = depName.substring(depName.indexOf(".") + 1);
                }
                depName = depName.substring(0,depName.indexOf("."));
            }
        }
        if (request.getParameter("depName") == null || request.getParameter("depName").length() == 0 || request.getParameter("depName").equals("未选择")) {
            opportunity.setIn_department(iClueDao.queryDeptNameByDeptId(depName));
        } else {
            opportunity.setIn_department(request.getParameter("depName"));
        }
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
            HttpServletRequest request
    ){
        String queryStr = request.getParameter("queryStr");
        if (null != request.getParameter("state")) {
            opportunity.setState(request.getParameter("state"));
        }
        if("PRIVATE".equals(flag)){
            opportunity.setSale_employeenumber(userUtil.queryEmployeenumberByUserId(request.getParameter("userId")));
            opportunity.setUsername(request.getParameter("userId"));
        }else if ("DEP".equals(flag)) {
            opportunity.setUsername(request.getParameter("userId"));
        }else {
            opportunity.setUsername("");
        }
        return iOpportunityService.getPageInfoByCondition(page,rows,opportunity,flag,queryStr);
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
        List<HashMap<String, Object>> depList = iClueDao.queryDeptByUserId(iClueDao.queryUserIdByEmployeeNumber(request.getParameter("sale_employeenumber")).get(0).get("USER_ID_").toString());
        String depName = "";
        if (depList.size() != 0){
            depName = depList.get(0).get("PATH_").toString();
            if (depName.length() - depName.replaceAll("\\.","").length() == 2 || depName.length() - depName.replaceAll("\\.","").length() == 3) {
                for (int j = 0; j < depName.length() - depName.replaceAll("\\.","").length(); j++) {
                    depName = depName.substring(depName.indexOf(".") + 1);
                }
                depName = depName.substring(0,depName.indexOf("."));
            } else {
                for (int j = 0; j < 2; j++) {
                    depName = depName.substring(depName.indexOf(".") + 1);
                }
                depName = depName.substring(0,depName.indexOf("."));
            }
        }
        if (request.getParameter("depName") == null || request.getParameter("depName").length() == 0 || request.getParameter("depName").equals("未选择")) {
            opportunity.setIn_department(iClueDao.queryDeptNameByDeptId(depName));
        } else {
            opportunity.setIn_department(request.getParameter("depName"));
        }
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
