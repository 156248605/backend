package com.elex.oa.controller.reportobstacles;

import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.reportObstacles.ObstaclesInfo;
import com.elex.oa.entity.reportObstacles.ObstaclesQueryInfo;
import com.elex.oa.service.reportObstacles.IObstaclesInfoService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\4 0004 11:44
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@RequestMapping("/reportObstacles")
public class ObstaclesController {
    @Autowired
    IObstaclesInfoService iObstaclesInfoService;
    @Autowired
    HrUtils hrUtils;


    @RequestMapping(value = "/addObstaclesInfo",consumes = "multipart/form-data")
    @ResponseBody
    public Object addObstaclesInfo(
            ObstaclesInfo obstaclesInfo,
            HttpServletRequest request,
            @RequestParam(name = "attachmentSize", required = false)Integer i
    ){
        //自动生成ID
        String obstaclesInfoId = "obs_"+System.currentTimeMillis();
        obstaclesInfo.setId(obstaclesInfoId);
        //保存附件
        List<String> multiFileAddress = null;
        if (null!=i) {
            multiFileAddress = hrUtils.getMultiFileAddress(request, i);
            List<BusinessAttachment> businessAttachmentList = new ArrayList<>();
            for (String attachmentAddress:multiFileAddress
                 ) {
                BusinessAttachment businessAttachment = new BusinessAttachment();
                businessAttachment.setDependence_code(obstaclesInfoId);
                businessAttachment.setAttachment_address(attachmentAddress);
                businessAttachmentList.add(businessAttachment);
            }
            obstaclesInfo.setAttachmentList(businessAttachmentList);
        }
        Boolean aBoolean = iObstaclesInfoService.addObstaclesInfo(obstaclesInfo);
        return aBoolean? RespUtil.response("200","添加成功！",null):RespUtil.response("500","添加失败！",null);
    }

    @RequestMapping("/queryOaBackendVersion")
    @ResponseBody
    public Object queryOaBackendVersion(){
        return hrUtils.getOaBackendVersion();
    }

    @RequestMapping("/queryAllObstaclesInfo")
    @ResponseBody
    public Object queryAllObstaclesInfo(){
        return iObstaclesInfoService.queryAllObstaclesInfo();
    }

    @RequestMapping("/queryObstaclesByConditions")
    @ResponseBody
    public PageInfo<ObstaclesInfo> queryObstaclesByConditions(
            @RequestParam("pageNum")Integer pageNum,
            @RequestParam("pageSize")Integer pageSize,
            ObstaclesQueryInfo obstaclesQueryInfo
    ){
        return iObstaclesInfoService.queryObstaclesByConditions(pageNum,pageSize,obstaclesQueryInfo);
    }

    @RequestMapping("/changeObstaclesState")
    @ResponseBody
    public Object changeObstaclesState(
            @RequestParam("id") String id,
            @RequestParam("flag") String flag,
            @RequestParam(value = "location_description" ,required = false)String locationDescription,
            @RequestParam(value = "process_description" ,required = false)String processDescription
    ){
        return iObstaclesInfoService.changeObstaclesState(id,flag,locationDescription,processDescription);
    }
}