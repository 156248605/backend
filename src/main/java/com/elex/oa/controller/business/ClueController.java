package com.elex.oa.controller.business;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.service.business.IClueService;
import com.elex.oa.service.business.IOpportunityService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:23
 * @Version 1.0
 **/
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

    @RequestMapping("/getPageInfo")
    @ResponseBody
    public PageInfo<Clue> getPageInfo(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            Clue clue,
            @RequestParam("flag") String flag
    ){
        if("PRIVATE".equals(flag)){
            clue.setSale_employeenumber(hrUtils.getEmployeenumberByUsername(clue.getUsername()));
        }
        return iClueService.getPageInfoByCondition(page,rows,clue,flag);
    }

    @RequestMapping(value = "/clue_ADD",consumes = "multipart/form-data")
    @ResponseBody
    public Object clue_ADD(
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
        //调用业务层方法
        return iClueService.addClueInfo(clue);
    }

    @RequestMapping("/getDetailClueinfo")
    @ResponseBody
    public Clue getDetailClueinfo(
            @RequestParam("cluecode")String cluecode
    ){
        Clue detailClueinfo = iClueService.getDetailClueinfo(cluecode);
        return detailClueinfo;
    }

    @RequestMapping("/getDetailOpportunityinfo")
    @ResponseBody
    public Opportunity getDetailOpportunityinfo(
            @RequestParam("cluecode")String cluecode
    ){
        Opportunity detailOpportunityinfo = iOpportunityService.getDetailOpportunityinfoByCluecode(cluecode);
        return detailOpportunityinfo;
    }

    @RequestMapping(value = "/clue_UPDATE",consumes = "multipart/form-data")
    @ResponseBody
    public Object clue_UPDATE(
            Clue clue,
            HttpServletRequest request,
            @RequestParam(name = "attachmentSize", required = false)Integer i
    ){
        //获得附件地址
        if (null!=i) {
            List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
            clue.setBusinessAttachmentList(businessAttachmentList);
        }
        Boolean aBoolean = iClueService.modifyClueInfo(clue);
        return aBoolean?RespUtil.response("200","更新成功！",clue.getCode()):RespUtil.response("500","更新失败！",null);
    }

    @RequestMapping("/closeClueinfo")
    @ResponseBody
    public Object closeClueinfo(
            @RequestParam("cluecode")String cluecode
    ){
        Boolean aBoolean = iClueService.closeClueInfo(cluecode);
        return aBoolean?RespUtil.response("200","关闭成功！",null):RespUtil.response("500","关闭失败！",null);
    }

    private List<BusinessAttachment> getBusinessAttachmentList(MultipartHttpServletRequest request, int i) {
        List<BusinessAttachment> businessAttachmentList = new ArrayList<>();
        List<String> multiFileAddress = hrUtils.getMultiFileAddress(request, i);
        if(null==multiFileAddress)return null;
        for (String attachment_address:multiFileAddress
             ) {
            BusinessAttachment businessAttachment = new BusinessAttachment();
            businessAttachment.setAttachment_address(attachment_address);
            businessAttachmentList.add(businessAttachment);
        }
        return businessAttachmentList;
    }
}