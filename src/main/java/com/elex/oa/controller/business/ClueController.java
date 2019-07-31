package com.elex.oa.controller.business;

import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.entity.business.BusinessAttachment;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.Opportunity;
import com.elex.oa.service.business.IClueService;
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
    @Resource
    UserUtil userUtil;
    @Resource
    IClueDao iClueDao;

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
        if("PRIVATE".equals(flag)) {
            clue.setSale_employeenumber(userUtil.queryEmployeenumberByUserId(request.getParameter("userId")));
            clue.setUsername(request.getParameter("userId"));
        }else if ("DEP".equals(flag)) {
            clue.setUsername(request.getParameter("userId"));
        }else {
            clue.setUsername("");
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
            clue.setIn_department(iClueDao.queryDeptNameByDeptId(depName));
        } else {
            clue.setIn_department(request.getParameter("depName"));
        }
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
            clue.setIn_department(iClueDao.queryDeptNameByDeptId(depName));
        } else {
            clue.setIn_department(request.getParameter("depName"));
        }
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