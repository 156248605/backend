package com.elex.oa.controller.business;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.service.business.IClueService;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

    @RequestMapping("/getPageInfo")
    @ResponseBody
    public PageInfo<Clue> getPageInfo(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            Clue clue
    ){
        return iClueService.getPageInfoByCondition(page,rows,clue);
    }

    @RequestMapping(value = "/clue_ADD",consumes = "multipart/form-data")
    @ResponseBody
    public Object clue_ADD(
            Clue clue,
            HttpServletRequest request,
            @RequestParam("attachmentSize")int i
    ){
        //获得附件地址
        List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
        clue.setBusinessAttachmentList(businessAttachmentList);
        //自动生成线索编码（主键）
        String clueCode = "clue_"+System.currentTimeMillis();
        clue.setCode(clueCode);
        //调用业务层方法
        Boolean aBoolean = iClueService.addClueInfo(clue);
        return aBoolean?RespUtil.successResp("200","添加成功！",clueCode):RespUtil.successResp("500","添加失败！",null);
    }

    @RequestMapping("/getDetailClueinfo")
    @ResponseBody
    public Clue getDetailClueinfo(
            @RequestParam("cluecode")String cluecode
    ){
        Clue detailClueinfo = iClueService.getDetailClueinfo(cluecode);
        return detailClueinfo;
    }

    @RequestMapping(value = "/clue_UPDATE",consumes = "multipart/form-data")
    @ResponseBody
    public Object clue_UPDATE(
            Clue clue,
            HttpServletRequest request,
            @RequestParam("attachmentSize")int i
    ){
        //获得附件地址
        List<BusinessAttachment> businessAttachmentList = getBusinessAttachmentList((MultipartHttpServletRequest) request, i);
        clue.setBusinessAttachmentList(businessAttachmentList);
        Boolean aBoolean = iClueService.modifyClueInfo(clue);
        return aBoolean?RespUtil.successResp("200","更新成功！",clue.getCode()):RespUtil.successResp("500","更新失败！",null);
    }

    @RequestMapping("/closeClueinfo")
    @ResponseBody
    public Object closeClueinfo(
            @RequestParam("cluecode")String cluecode
    ){
        Boolean aBoolean = iClueService.closeClueInfo(cluecode);
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