package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.hr.IGzrzDao;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformationExchange;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.hr_entity.post.PerAndPostRs;
import com.elex.oa.entity.hr_entity.readexcel.ReadDimissioninformationExcel;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    HrUtils hrUtils;

    private static Logger logger = LoggerFactory.getLogger(DimissionController.class);

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
        iDimissionInformationService.addOne(dimissionInformation);
        //创建返回值
        user = iUserService.getById(dimissionInformation.getDimissionuserid());
        HashMap<String,Object> re = new HashMap<>();
        re.put("username",user.getUsername());
        re.put("isactive",user.getIsactive());
        re.put("truename",user.getTruename());
        re.put("state",user.getState());
        re.put("postids",postids);
        return RespUtil.response("200","添加成功！",re);
    }

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
        return iDimissionInformationService.queryByCondition(paramMap);
    }

    @RequestMapping("/queryAllDimissionInformations")
    @ResponseBody
    public List<PersonalInformationExchange> queryAllDimissionInformations(){
        return iDimissionInformationService.queryAllDimissionInformations();
    }

    @RequestMapping("/modifyDimissionInformationById")
    @ResponseBody
    public Object modifyDimissionInformationById(
           DimissionInformation dimissionInformation
    ){
        return iDimissionInformationService.modifyOne(dimissionInformation);
    }

    @RequestMapping("/queryDimissionInformationById")
    @ResponseBody
    public DimissionInformation queryDimissionInformationById(
            @RequestParam("dimissionid")Integer dimissionid
    ){
        return iDimissionInformationService.queryOneById(dimissionid);
    }

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
            iGzrzDao.updateOsUserWenDeleteDimission(username);
            iGzrzDao.updateOsUserWenDeleteDimission2(username);
        }
        return RespUtil.response("200","提交成功！",null) ;
    }

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
                if(dimissionInformationList==null || dimissionInformationList.isEmpty()){
                    //没有则添加
                    importDimissionAdd(dimissionInformation);
                }else if(dimissionInformationList.size()==1){
                    //有且一个则修改
                    dimissionInformation.setId(dimissionInformationList.get(0).getId());
                    importDimissionModify(dimissionInformation);
                }else if(dimissionInformationList.size()>1){
                    responseMap.put(dimissionInformation.getDimissiontruename(),"此员工在数据库中有多条，请联系管理员及时解决");
                }
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return "数据导入失败！";
        }
        return responseMap.size()==0?"数据导入成功！":(JSON.toJSONString(responseMap));
    }

    private void importDimissionAdd(DimissionInformation dimissionInformation) {
        setDimissionDetail(dimissionInformation);
        iDimissionInformationService.addOne(dimissionInformation);
    }

    private void importDimissionModify(DimissionInformation dimissionInformation){
        setDimissionDetail(dimissionInformation);
        iDimissionInformationService.modifyOne(dimissionInformation);
    }

    private void setDimissionDetail(DimissionInformation dimissionInformation){
        //获得dimissiontypeid
        dimissionInformation.setDimissiontypeid(hrUtils.getHrsetidByDatavalueOrInsert(Commons.HRSET_DIMISSION_TYPE, dimissionInformation.getDimissiontype()));
        //获得dimissiondirectionid
        dimissionInformation.setDimissiondirectionid(hrUtils.getHrsetidByDatavalueOrInsert(Commons.HRSET_DIMISSION_DIRECTION, dimissionInformation.getDimissiondirection()));
        //获得dimissionreasonid
        dimissionInformation.setDimissionreasonid(hrUtils.getHrsetidByDatavalueOrInsert(Commons.HRSET_DIMISSION_REASON, dimissionInformation.getDimissionreason()));
        //获得transactoruserid
        dimissionInformation.setTransactoruserid(hrUtils.getUseridByUsername(dimissionInformation.getTransactortruename()));
    }
}
