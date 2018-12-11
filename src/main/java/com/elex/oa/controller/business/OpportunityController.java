package com.elex.oa.controller.business;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.service.business.IOpportunityService;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    @RequestMapping("/opportunity_ADD")
    @ResponseBody
    public Object opportunity_ADD(
            Opportunity opportunity,
            HttpServletRequest request,
            @RequestParam("attachmentSize")int i
    ){
        //获得附件地址
        List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
        opportunity.setBusinessAttachmentList(businessAttachmentList);
        //自动生成商机编码（主键）
        String opportunityCode = "opportunity_"+System.currentTimeMillis();
        opportunity.setCode(opportunityCode);
        //调用业务层方法
        Boolean aBoolean = iOpportunityService.transforClueToOpportunity(opportunity);
        return aBoolean? RespUtil.successResp("200","添加成功！",null):RespUtil.successResp("500","添加失败！",null);
    }

    @RequestMapping("/getPageInfo")
    @ResponseBody
    public PageInfo<Opportunity> getPageInfo(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            Opportunity opportunity
    ){
        return iOpportunityService.getPageInfoByCondition(page,rows,opportunity);
    }

    @RequestMapping("/getDetailOpportunityinfo")
    @ResponseBody
    public Opportunity getDetailOpportunityinfo(
            @RequestParam("opportunitycode")String opportunitycode
    ){
        Opportunity detailOpportunityinfo = iOpportunityService.getDetailOpportunityinfo(opportunitycode);
        return detailOpportunityinfo;
    }

    @RequestMapping(value = "/opportunity_UPDATE",consumes = "multipart/form-data")
    @ResponseBody
    public Object opportunity_UPDATE(
            Opportunity opportunity,
            HttpServletRequest request,
            @RequestParam("attachmentSize")int i
    ){
        //获得附件地址
        List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
        opportunity.setBusinessAttachmentList(businessAttachmentList);
        Boolean aBoolean = iOpportunityService.modifyOpportunityInfo(opportunity);
        return aBoolean?RespUtil.successResp("200","更新成功！",opportunity.getCode()):RespUtil.successResp("500","更新失败！",null);
    }

    @RequestMapping("/closeOpportunityinfo")
    @ResponseBody
    public Object closeOpportunityinfo(
            @RequestParam("opportunitycode")String opportunitycode
    ){
        Boolean aBoolean = iOpportunityService.closeOpportunityInfo(opportunitycode);
        return aBoolean?RespUtil.successResp("200","关闭成功！",null):RespUtil.successResp("500","关闭失败！",null);
    }

    private List<BusinessAttachment> getBusinessAttachmentList(MultipartHttpServletRequest request, int i) {
        List<BusinessAttachment> businessAttachmentList = new ArrayList<>();
        for(int j=0;j<i;j++){
            MultipartHttpServletRequest multipartRequest = request;
            List<MultipartFile> attachments = multipartRequest.getFiles("attachment_"+(j+1));
            if(attachments.size()!=0){
                String realPath = Commons.realpath;
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/business/attachments/" + l);
                file.mkdirs();
                BusinessAttachment businessAttachment = new BusinessAttachment();
                String attachment_address = "/business/attachments/" + l + "/" + attachments.get(0).getOriginalFilename();
                try {
                    attachments.get(0).transferTo(new File(realPath + attachment_address));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                businessAttachment.setAttachment_address(attachment_address);
                businessAttachmentList.add(businessAttachment);
            }
        }
        return businessAttachmentList;
    }
}    