package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.hr.IGzrzDao;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.readexcel.ReadDimissioninformationExcel;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:离职信息
 * @Date:Created in  16:27 2018\4\16 0016
 * @Modify By:
 */
@Controller
@CrossOrigin
public class DimissionController {
    @Autowired
    IDimissionInformationService iDimissionInformationService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IPerandpostrsService iPerandpostrsService;
    @Autowired
    IPostService iPostService;
    @Autowired
    IHRsetService ihRsetService;
    @Resource
    IGzrzDao iGzrzDao;

    /**
     *@Author:ShiYun;
     *@Description:添加离职信息
     *@Date: 17:00 2018\4\16 0016
     */
    @RequestMapping("/addDimission")
    @ResponseBody
    public Object addDimission(
            DimissionInformation dimissionInformation,
            @RequestParam("transactorusername")String transactorusername
    ){
        //获得办理人的ID
        User user = iUserService.queryByUsername(transactorusername);
        dimissionInformation.setTransactoruserid(user.getId());
        //将岗位信息带过去
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(dimissionInformation.getDimissionuserid());
        List<Integer> postids = null;
        if (personalInformation!=null) {
            List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(personalInformation.getId());
            postids = new ArrayList<>();
            for (PerAndPostRs pp:perAndPostRs
                 ) {
                postids.add(pp.getPostid());
            }
        }
        //添加到数据库中
        Integer dimissionInformationId = iDimissionInformationService.addOne(dimissionInformation);
        //创建返回值
        user = iUserService.getById(dimissionInformation.getDimissionuserid());
        HashMap<String,Object> re = new HashMap<>();
        re.put("username",user.getUsername());
        re.put("isactive",user.getIsactive());
        re.put("truename",user.getTruename());
        re.put("state",user.getState());
        re.put("postids",postids);
        return RespUtil.successResp("200","添加成功！",re);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息(分页)
     *@Date: 18:01 2018\4\16 0016
     */
    @RequestMapping("/queryDimissionInformations")
    @ResponseBody
    public PageInfo<DimissionInformation> queryDimissionInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            DimissionInformation dimissionInformation
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",dimissionInformation);
        PageInfo<DimissionInformation> dimissionInformationPageInfo = iDimissionInformationService.queryByCondition(paramMap);
        return dimissionInformationPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:导出离职信息
     *@Date: 15:05 2018\5\29 0029
     */
    @RequestMapping("/queryAllDimissionInformations")
    @ResponseBody
    public List<DimissionInformation> queryAllDimissionInformations(){
        List<DimissionInformation> dimissionInformations = iDimissionInformationService.queryAll();
        return dimissionInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid确认离职信息
     *@Date: 13:35 2018\4\17 0017
     */
    @RequestMapping("/modifyDimissionInformationById")
    @ResponseBody
    public Object modifyDimissionInformationById(
           DimissionInformation dimissionInformation
    ){
        return iDimissionInformationService.modifyOne(dimissionInformation);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询离职信息
     *@Date: 11:18 2018\5\30 0030
     */
    @RequestMapping("/queryDimissionInformationById")
    @ResponseBody
    public DimissionInformation queryDimissionInformationById(
            @RequestParam("dimissionid")Integer dimissionid
    ){
        DimissionInformation dimissionInformation = iDimissionInformationService.queryOneById(dimissionid);
        /**
         * 其中的
         * 待审批单数量（approvalnumbers）、
         * 待办任务数量（workingnumbers）、
         * 文件占用数量（filenumbers）、
         * 办公用品数量（officesupplynumbers）
         * 需要从流程、文件管理、仓库拿接口，前期测试从数据库拿假数据代替
         * */
        return dimissionInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除离职信息
     *@Date: 13:39 2018\5\30 0030
     */
    @RequestMapping("/removeDimissionInformations")
    @ResponseBody
    public Object removeDimissionInformations(
            @RequestParam("dimissionids")List<Integer> dimissionids
    ){
        //想将离职人员的账号保存
        List<String> usernames = new ArrayList<>();
        for (Integer did:dimissionids
             ) {
            Integer dimissionuserid = iDimissionInformationService.queryOneById(did).getDimissionuserid();
            User user = iUserService.getById(dimissionuserid);
            usernames.add(user.getUsername());
        }
        //将离职人员表的状态更新到user表中
        for(int i=0;i<dimissionids.size();i++){
            iDimissionInformationService.removeOne(dimissionids.get(i));
        }
        //将OS_USER表中的状态改为"DEL_JOB"
        for (String username:usernames
             ) {
            iGzrzDao.updateOS_USERWenDeleteDimission(username);
            iGzrzDao.updateOS_USERWenDeleteDimission2(username);
        }
        return RespUtil.successResp("200","提交成功！",null) ;
    }

    /**
     *@Author:ShiYun;
     *@Description:数据的导入
     *@Date: 11:03 2018\6\1 0001
     */
    @RequestMapping("/importDimissionInformations")
    @ResponseBody
    public String importDimissionInformations(
            @RequestParam("file") MultipartFile multipartFile
    ){
        Map<String,String> responseMap = new HashMap<>();
        try {
            ReadDimissioninformationExcel readDimissioninformationExcel = new ReadDimissioninformationExcel();
            List<DimissionInformation> excelInfo = readDimissioninformationExcel.getExcelInfo(multipartFile);
            for(DimissionInformation dimissionInformation:excelInfo){
                //获得dimissionuserid
                User dimissionuser = iUserService.queryByUsername(dimissionInformation.getDimissiontruename());
                if (dimissionuser!=null) {
                    dimissionInformation.setDimissionuserid(iUserService.queryByTruename(dimissionInformation.getDimissiontruename()).getId());
                }else {
                    responseMap.put(dimissionInformation.getDimissiontruename(),"此员工查不到");
                    continue;
                }
                List<DimissionInformation> dimissionInformationList = iDimissionInformationService.queryByDimission(new DimissionInformation(dimissionuser.getId()));
                if(dimissionInformationList==null || dimissionInformationList.size()==0){
                    //没有则添加
                    importDimission_ADD(dimissionInformation);
                }else if(dimissionInformationList.size()==1){
                    //有且一个则修改
                    dimissionInformation.setId(dimissionInformationList.get(0).getId());
                    importDimission_MODIFY(dimissionInformation);
                }else if(dimissionInformationList.size()>1){
                    responseMap.put(dimissionInformation.getDimissiontruename(),"此员工在数据库中有多条，请联系管理员及时解决");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "数据导入失败！";
        }
        return responseMap.size()==0?"数据导入成功！":(JSON.toJSONString(responseMap));
    }

    private void importDimission_ADD(DimissionInformation dimissionInformation) {
        //获得dimissiontypeid
        if (StringUtils.isNotEmpty(dimissionInformation.getDimissiontype())) {
            Integer dimissiontypeid = null;
            List<HRset> hRsetDimissiontypeList = ihRsetService.queryByConditions(new HRset("dimissiontype", dimissionInformation.getDimissiontype()));
            if (hRsetDimissiontypeList!=null && hRsetDimissiontypeList.size()==1) {
                dimissiontypeid = hRsetDimissiontypeList.get(0).getId();
            }else if(hRsetDimissiontypeList==null || hRsetDimissiontypeList.size()==0) {
                dimissiontypeid = ihRsetService.addOne(new HRset(Commons.HRSET_DIMISSION_TYPE, dimissionInformation.getDimissiontype()));
            }
            dimissionInformation.setDimissiontypeid(dimissiontypeid);
        }
        //获得dimissiondirectionid
        if (StringUtils.isNotEmpty(dimissionInformation.getDimissiondirection())) {
            Integer dimissiondirectionid = null;
            List<HRset> hRsetDimissiondirectionList = ihRsetService.queryByConditions(new HRset("dimissiondirection", dimissionInformation.getDimissiondirection()));
            if (hRsetDimissiondirectionList!=null && hRsetDimissiondirectionList.size()==1) {
                dimissiondirectionid = hRsetDimissiondirectionList.get(0).getId();
            }else if(null == hRsetDimissiondirectionList || hRsetDimissiondirectionList.size()==0){
                dimissiondirectionid = ihRsetService.addOne(new HRset(Commons.HRSET_DIMISSION_DIRECTION,dimissionInformation.getDimissiondirection()));
            }
            dimissionInformation.setDimissiondirectionid(dimissiondirectionid);
        }
        //获得dimissionreasonid
        if (StringUtils.isNotEmpty(dimissionInformation.getDimissionreason())) {
            Integer dimissionreasonid = null;
            List<HRset> hRsetDimissionreasonList = ihRsetService.queryByConditions(new HRset("dimissionreason", dimissionInformation.getDimissionreason()));
            if (hRsetDimissionreasonList!=null && hRsetDimissionreasonList.size()==1) {
                dimissionreasonid = hRsetDimissionreasonList.get(0).getId();
            }else if(null==hRsetDimissionreasonList || hRsetDimissionreasonList.size()==0){
                dimissionreasonid = ihRsetService.addOne(new HRset(Commons.HRSET_DIMISSION_REASON,dimissionInformation.getDimissionreason()));
            }
            dimissionInformation.setDimissionreasonid(dimissionreasonid);
        }
        //获得transactoruserid
        if (iUserService.queryByTruename(dimissionInformation.getTransactortruename())!=null) {
            dimissionInformation.setTransactoruserid(iUserService.queryByTruename(dimissionInformation.getTransactortruename()).getId());
        }
        iDimissionInformationService.addOne(dimissionInformation);
    }

    private void importDimission_MODIFY(DimissionInformation dimissionInformation){
        //获得dimissiontypeid
        if (StringUtils.isNotEmpty(dimissionInformation.getDimissiontype())) {
            Integer dimissiontypeid = null;
            List<HRset> hRsetDimissiontypeList = ihRsetService.queryByConditions(new HRset("dimissiontype", dimissionInformation.getDimissiontype()));
            if (hRsetDimissiontypeList!=null && hRsetDimissiontypeList.size()==1) {
                dimissiontypeid = hRsetDimissiontypeList.get(0).getId();
            }else if(hRsetDimissiontypeList==null || hRsetDimissiontypeList.size()==0) {
                dimissiontypeid = ihRsetService.addOne(new HRset(Commons.HRSET_DIMISSION_TYPE, dimissionInformation.getDimissiontype()));
            }
            dimissionInformation.setDimissiontypeid(dimissiontypeid);
        }
        //获得dimissiondirectionid
        if (StringUtils.isNotEmpty(dimissionInformation.getDimissiondirection())) {
            Integer dimissiondirectionid = null;
            List<HRset> hRsetDimissiondirectionList = ihRsetService.queryByConditions(new HRset("dimissiondirection", dimissionInformation.getDimissiondirection()));
            if (hRsetDimissiondirectionList!=null && hRsetDimissiondirectionList.size()==1) {
                dimissiondirectionid = hRsetDimissiondirectionList.get(0).getId();
            }else if(null == hRsetDimissiondirectionList || hRsetDimissiondirectionList.size()==0){
                dimissiondirectionid = ihRsetService.addOne(new HRset(Commons.HRSET_DIMISSION_DIRECTION,dimissionInformation.getDimissiondirection()));
            }
            dimissionInformation.setDimissiondirectionid(dimissiondirectionid);
        }
        //获得dimissionreasonid
        if (StringUtils.isNotEmpty(dimissionInformation.getDimissionreason())) {
            Integer dimissionreasonid = null;
            List<HRset> hRsetDimissionreasonList = ihRsetService.queryByConditions(new HRset("dimissionreason", dimissionInformation.getDimissionreason()));
            if (hRsetDimissionreasonList!=null && hRsetDimissionreasonList.size()==1) {
                dimissionreasonid = hRsetDimissionreasonList.get(0).getId();
            }else if(null==hRsetDimissionreasonList || hRsetDimissionreasonList.size()==0){
                dimissionreasonid = ihRsetService.addOne(new HRset(Commons.HRSET_DIMISSION_REASON,dimissionInformation.getDimissionreason()));
            }
            dimissionInformation.setDimissionreasonid(dimissionreasonid);
        }
        //获得transactoruserid
        if (iUserService.queryByTruename(dimissionInformation.getTransactortruename())!=null) {
            dimissionInformation.setTransactoruserid(iUserService.queryByTruename(dimissionInformation.getTransactortruename()).getId());
        }
        iDimissionInformationService.modifyOne(dimissionInformation);
    }
}
