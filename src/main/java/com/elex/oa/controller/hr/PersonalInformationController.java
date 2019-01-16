package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.costinformation.CostInformationAddInfo;
import com.elex.oa.entity.hr_entity.manageinformation.ManageInformationAddInfo;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformationExport;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.AppProperties;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:人事档案里的人事信息的查询
 * @Date:Created in  9:47 2018\4\8 0008
 * @Modify By:
 */
@Controller
@CrossOrigin
public class PersonalInformationController {

    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IDeptService iDeptService;
    @Autowired
    IPostService iPostService;
    @Autowired
    IBaseInformationService iBaseInformationService;
    @Autowired
    IManageInformationService iManageInformationService;
    @Autowired
    ICostInformationService iCostInformationService;
    @Autowired
    IOtherInformationService iOtherInformationService;
    @Autowired
    IChangeInformationService iChangeInformationService;
    @Autowired
    IPerandpostrsService iPerandpostrsService;
    @Autowired
    IHRsetService ihRsetService;//HRset字段
    @Autowired
    IGzrzService iGzrzService;//工作日志
    @Autowired
    IContractInformationService iContractInformationService;
    @Autowired
    HrUtils hrUtils;
    @Autowired
    AppProperties appProperties;

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @RequestMapping("/PersonalInformationController/queryPersonalInformations")
    @ResponseBody
    public Map<String,Object> getInitPage(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            PersonalInformation personalInformation
    ) throws ParseException {
        Map<String, Object> resp = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageNum", page);
        paramMap.put("pageSize", rows);
        paramMap.put("entity", personalInformation);
        PageInfo<PersonalInformation> list = iPersonalInformationService.queryPIs(paramMap);
        resp.put("pageData",list);
        Map<String, Object> paramsForFirstpage = iPersonalInformationService.getParamsForFirstpage();
        resp.put("selections",paramsForFirstpage);
       return resp;
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息的查询
     * @Date: 9:49 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformations")
    @ResponseBody
    public PageInfo<PersonalInformation> queryPersonalInformation(@RequestParam("page") Integer page,
                                                                  @RequestParam("rows") Integer rows,
                                                                  PersonalInformation personalInformation
    ) throws ParseException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageNum", page);
        paramMap.put("pageSize", rows);
        paramMap.put("entity", personalInformation);
        PageInfo<PersonalInformation> list = iPersonalInformationService.queryPIs(paramMap);
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationById")
    @ResponseBody
    public PersonalInformation queryPersonalInformationById(
            @RequestParam("personalInformationId") int personalInformationId
    ) {
        return iPersonalInformationService.queryPersonalInformationById(personalInformationId);
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）(IOS端)
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationByUserid")
    @ResponseBody
    public ArrayList<HashMap> queryPersonalInformationByUserid(
            @RequestParam("userid") int userid
    ) {
        return iPersonalInformationService.queryByUseridForIOS(userid);
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationByTruename")
    @ResponseBody
    public PersonalInformation queryPersonalInformationByTruename(
            @RequestParam("truename") String truename
    ) {
        return iPersonalInformationService.queryPersonalInformationByTruename(truename);
    }

    /**
     * @Author:ShiYun;
     * @Description:添加用户人事信息（基本信息）
     * @Date: 18:07 2018\4\10 0010
     */
    @RequestMapping(value = "/addBaseInformation", consumes = "multipart/form-data")
    @ResponseBody
    public Object addBaseInformation(
            PersonalInformation personalInformation,
            @RequestParam("lysqdid") String lysqdid,
            HttpServletRequest request
    ) {
        Map<String, Object> respMap = iPersonalInformationService.addBaseInformation(personalInformation, request, lysqdid);
        return null!=respMap.get("userid")?RespUtil.successResp("200", "添加成功！", respMap.get("userid")):RespUtil.successResp("500","添加失败！",JSON.toJSONString(respMap));
    }

    @RequestMapping("/addManageInformation")
    @ResponseBody
    public Object addManageInformation(
            ManageInformationAddInfo manageInformationAddInfo
    ){
        Map<String, Object> map = iPersonalInformationService.addManageInformation(manageInformationAddInfo);
        return null==map?RespUtil.successResp("500","添加失败！",null):RespUtil.successResp("200","添加成功！",null);
    }

    @RequestMapping("/addCostInformation")
    @ResponseBody
    public Object addCostInformation(
            CostInformationAddInfo costInformationAddInfo
    ){
        return iPersonalInformationService.addCostInformation(costInformationAddInfo);
    }

    /**
     * @Author:ShiYun;
     * @Description:添加人事信息的其它信息
     * @Date: 18:42 2018\4\11 0011
     */
    @RequestMapping("/addOtherInformation")
    @ResponseBody
    public Object addOtherInformation(
            PersonalInformation personalInformation
    ) {
        Map<String, Object> map = iPersonalInformationService.addOtherInformation(personalInformation);
        return null!=map?RespUtil.successResp("200", "添加其他信息成功！", map):RespUtil.successResp("500","信息添加失败！",null);
    }

    /**
     * @Author:ShiYun;
     * @Description:修改人事信息的基本信息
     * @Date: 13:59 2018\4\12 0012
     */
    @RequestMapping("/updateBaseInformation")
    @ResponseBody
    public Object updateBaseInformation(
            PersonalInformation personalInformation,
            @RequestParam("byyxvalue") String byyxvalue,
            @RequestParam("sxzyvalue") String sxzyvalue,
            @RequestParam("zyzsnamevalue") String zyzsnamevalue,
            @RequestParam("parentcompanyvalue") String parentcompanyvalue,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) throws ParseException {
        //获取照片地址
        personalInformation.setUserphoto(hrUtils.getSignalFileAddress(request,"userphoto2","/hr/image/"));
        personalInformation.setIdphoto1(hrUtils.getSignalFileAddress(request,"idphoto11","/hr/image/"));
        personalInformation.setIdphoto2(hrUtils.getSignalFileAddress(request,"idphoto22","/hr/image/"));
        //判断是否需要录入并添加相应的HR设置信息
        if(StringUtils.isNotBlank(byyxvalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"byyx",personalInformation.getByyx());
        if(StringUtils.isNotBlank(sxzyvalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"syxz",personalInformation.getByyx());
        if(StringUtils.isNotBlank(zyzsnamevalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"zyzsname",personalInformation.getByyx());
        if(StringUtils.isNotBlank(parentcompanyvalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"parentcompany",personalInformation.getByyx());
        Map<String, Object> map = iPersonalInformationService.updateBaseInformation(personalInformation, transactorusername);
        return RespUtil.successResp("200", "信息提交成功！", map.get("postids"));
    }

    /**
     * @Author:ShiYun;
     * @Description:修改人事信息的管理信息
     * @Date: 15:59 2018\4\12 0012
     */
    @RequestMapping("/updateManageInformation")
    @ResponseBody
    public Object updateManageInformation(
            PersonalInformation personalInformation,
            @RequestParam("transactorusername") String transactorusername
    ) {
        Map<String, Object> map = iPersonalInformationService.updateManageInformation(personalInformation, transactorusername);
        return null!=map?RespUtil.successResp("200", "提交信息成功！", map):RespUtil.successResp("500","修改失败(没有需要修改的信息)！",null);
    }

    /**
     * @Author:ShiYun;
     * @Description:修改成本信息
     * @Date: 16:28 2018\4\12 0012
     */
    @RequestMapping("/updateCostInformation")
    @ResponseBody
    public String updateCostInformation(
            PersonalInformation personalInformation,
            @RequestParam("transactorusername") String transactorusername,
            @RequestParam("khhvalue") String khhvalue,
            @RequestParam("sbjndvalue") String sbjndvalue
    ) {
        //判断是否需要录入并添加相应的HR设置信息
        if(StringUtils.isNotBlank(khhvalue) && khhvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"khh",personalInformation.getKhh());
        if(StringUtils.isNotBlank(sbjndvalue) && sbjndvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"sbjnd",personalInformation.getSbjnd());
        Boolean aBoolean = iPersonalInformationService.updateCostInformation(personalInformation, transactorusername);
        return aBoolean?"提交信息成功！":"提交失败！";
    }

    /**
     * @Author:ShiYun;
     * @Description:修改其他信息
     * @Date: 16:46 2018\4\12 0012
     */
    @RequestMapping("/updateOtherInformation")
    @ResponseBody
    public Object updateOtherInformation(
            PersonalInformation personalInformation,
            @RequestParam("transactorusername") String transactorusername
    ) {
        Map<String, Object> map = iPersonalInformationService.updateOtherInformation(personalInformation, transactorusername);
        return map!=null?RespUtil.successResp("200", "提交成功！", map):RespUtil.successResp("500","提交失败！",null);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据部门id查询人事信息
     * @Date: 16:40 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByDepid")
    @ResponseBody
    public List<Map<String,Object>> queryPersonalInformationsByDepid(
            @RequestParam("depid") Integer depid
    ) {
        return iPersonalInformationService.queryPersonalInformationsByDepid(depid);
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有员工不分页(导出用)
     * @Date: 17:23 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByNull")
    @ResponseBody
    public Object queryPersonalInformationsByNull() {
        List<PersonalInformationExport> respList = iPersonalInformationService.queryPersonalInformationsByNull();
        return null!=respList?RespUtil.successResp("200","请求成功！",respList):RespUtil.successResp("500","请求失败！",null);
    }

    /**
     * @Author:ShiYun;
     * @Description:数据的导入
     * @Date: 15:09 2018\5\7 0007
     */
    @RequestMapping("/importPersonalInformations")
    @ResponseBody
    public Object importPersonalInformations(
            @RequestParam("file") MultipartFile multipartFile
    ) {
        Map<String, String> map = iPersonalInformationService.importPersonalInformations(multipartFile);
        return map.size()==0?RespUtil.successResp("200", "请求成功", null):RespUtil.successResp("500","导入部分失败！",JSON.toJSONString(map));
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息的删除（无用的信息，直接在数据库中删除）
     * @Date: 13:56 2018\5\10 0010
     */
    @RequestMapping("/deleteInformationsByIds")
    @ResponseBody
    public Object deleteInformationsByIds(
            @RequestParam("personalInformationIds") List<Integer> personalInformationIds
    ) {
        List<String> usernames = new ArrayList<>();
        if (personalInformationIds.size() <= 0) {
            return RespUtil.successResp("500", "删除失败！", null);
        }
        for (int i = 0; i < personalInformationIds.size(); i++) {
            PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationIds.get(i));
            if (personalInformation == null) {
                return RespUtil.successResp("500", "删除失败！", null);
            }
            if (iUserService.getById(personalInformation.getUserid()) != null) {
                usernames.add(iUserService.getById(personalInformation.getUserid()).getUsername());
            } else {
                return RespUtil.successResp("500", "删除失败！", null);
            }


            //1.先删除用户表（直接删除）
            iUserService.removeOne(personalInformation.getUserid());
            //2.再删除相应的基本信息表（直接删除）
            if (personalInformation.getBaseinformationid() != null) {
                iBaseInformationService.removeOne(personalInformation.getBaseinformationid());
            }
            //3.再删除相应的管理信息表（直接删除）
            if (personalInformation.getManageinformationid() != null) {
                iManageInformationService.removeOne(personalInformation.getManageinformationid());
            }
            //4.再删除相应的成本信息表（直接删除）
            if (personalInformation.getCostinformationid() != null) {
                iCostInformationService.remvoeOne(personalInformation.getCostinformationid());
            }
            //5.再删除相应的人事岗位关系表（直接删除）
            iPerandpostrsService.removeByPerid(personalInformationIds.get(i));
            //6.再删除相应的其他信息表（直接删除）
            if (personalInformation.getOtherinformationid() != null) {
                iOtherInformationService.removeOne(personalInformation.getOtherinformationid());
            }
            //7.再删除相应的合同信息表（直接删除）
            List<ContractInformation> contractInformationList = iContractInformationService.queryByUserid(personalInformation.getUserid());
            for (ContractInformation c : contractInformationList
            ) {
                iContractInformationService.removeOne(c.getId());
            }
            //8.再删除相应的人事变更表（直接删除）
            List<ChangeInformation> changeInformationList = iChangeInformationService.queryByUserid(personalInformation.getUserid());
            for (ChangeInformation c : changeInformationList
            ) {
                iChangeInformationService.removeOne(c.getId());
            }
            //9.再删除人事信息主表（直接删除）
            iPersonalInformationService.removeOne(personalInformation.getId());
            //10.最后再修改部门表
            //注：如果将要删除的员工是某部门的正职、副职、秘书则需要修改该字段
            iDeptService.modifyOne(personalInformation.getUserid());


        }
        return RespUtil.successResp("200", "删除成功！", usernames);
    }

    /**
     * @return
     * @Author: shiyun
     * @Description: 测试用
     * @Date 2018\11\2 0002 12:53
     * @Param
     **/
    @RequestMapping("/deleteAllInformations")
    @ResponseBody
    public Object deleteAllInformations(
    ) {
        //1.先删除用户表（直接删除）
        iUserService.removeAll_admin();
        //2.再删除相应的基本信息表（直接删除）
        iBaseInformationService.removeAll();
        //3.再删除相应的管理信息表（直接删除）
        iManageInformationService.removeAll();
        //4.再删除相应的成本信息表（直接删除）
        iCostInformationService.remvoeAll();
        //5.再删除相应的人事岗位关系表（直接删除）
        iPerandpostrsService.removeAll();
        //6.再删除相应的其他信息表（直接删除）
        iOtherInformationService.removeAll();
        //7.再删除相应的合同信息表（直接删除）
        //8.再删除相应的人事变更表（直接删除）
        //9.再删除人事信息主表（直接删除）
        iPersonalInformationService.removeAll();
        //10.最后再修改部门表
        return RespUtil.successResp("200", "删除成功！", null);
    }

    /**
     * @Author:ShiYun;
     * @Description:所有部门的ID、名称
     * @Date: 14:33 2018\7\9 0009
     */
    @RequestMapping("/queryGXF002")
    @ResponseBody
    public List<Object> queryGXF002() {
        List<Object> list = new ArrayList<>();
        List<Dept> depts = iDeptService.queryAllDepts();
        for (Dept dept : depts) {
            Map<String, Object> deptMap = new HashMap<>();
            deptMap.put("deptId", dept.getId());
            deptMap.put("deptName", dept.getDepname());
            list.add(deptMap);
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:所有岗位的ID、名称
     * @Date: 14:38 2018\7\9 0009
     */
    @RequestMapping("/queryGXF003")
    @ResponseBody
    public List<Object> queryGXF003() {
        ArrayList<Object> list = new ArrayList<>();
        List<Post> posts = iPostService.queryAllPosts();
        for (Post post : posts) {
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("postId", post.getId());
            postMap.put("postName", post.getPostname());
            list.add(postMap);
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据部门ID查询部门信息(部门经理)
     * @Date: 11:34 2018\8\10 0010
     */
    @RequestMapping("/queryGXF004")
    @ResponseBody
    public String queryGXF004(
            @RequestParam("depid") Integer depid
    ) {
        Dept dept = iDeptService.queryOneDepByDepid(depid);
        if (dept != null) {
            if (dept.getPrincipaluserid() != null) {
                if (iUserService.getById(dept.getPrincipaluserid()) != null) {
                    iUserService.getById(dept.getPrincipaluserid()).getTruename();
                } else {
                    return null;
                }
                return iUserService.getById(dept.getPrincipaluserid()).getTruename();
            } else {
                return "此部门没有部门正职！";
            }
        } else {
            return "此部门不存在！";
        }
        /*if(dept!=null){
            return RespUtil.successResp("205","查询信息成功！",dept);
        }else{
            return RespUtil.successResp("503","部门信息不存在！",null);
        }*/
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有的一级公司和二级公司
     * @Date: 10:50 2018\8\21 0021
     */
    @RequestMapping("/queryGXF005")
    @ResponseBody
    public Object queryGXF005() {
        List<Dept> depts = iDeptService.queryAllCompany1and2();
        ArrayList<String> strs = new ArrayList<>();

        if(null == depts || depts.size()==0){
            strs.add("公司类型不存在，请先到人力资源-部门信息-设置部门的职能类型");
            return strs;
        }
        for (Dept d : depts
        ) {
            strs.add(d.getDepname());
        }
        return strs;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据公司名称查询其下属所有部门（不管是级的）
     * @Date: 11:08 2018\8\21 0021
     */
    @RequestMapping("/queryGXF006")
    @ResponseBody
    public Object queryGXF006(
            @RequestParam("depname") String depname
    ) {
        List<Dept> depts = iDeptService.queryByCompanyname(depname);
        List<String> strs = new ArrayList<>();
        for (Dept d : depts
        ) {
            strs.add(d.getDepname());
        }
        return strs;
    }

    /**
     * @Author:ShiYun;
     * @Description:所有部门的ID、名称(公司级的提出)
     * @Date: 14:33 2018\7\9 0009
     */
    @RequestMapping("/queryGXF007")
    @ResponseBody
    public List<Object> queryGXF007() {
        List<Object> list = new ArrayList<>();
        List<Dept> depts = iDeptService.queryAllDepts();
        if(null==depts)return null;
        for (Dept dept : depts) {
            if(null==dept.getDeptypeid())continue;
            if (dept.getDeptypeid() != 1 && dept.getDeptypeid() != 3) {
                Map<String, Object> deptMap = new HashMap<>();
                deptMap.put("deptId", dept.getId());
                deptMap.put("deptName", dept.getDepname());
                list.add(deptMap);
            }
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据人员名称查询所在公司
     * @Date: 14:55 2018\9\8 0008
     */
    @RequestMapping("/queryGXF008")
    @ResponseBody
    public Object queryGXF008(
            @RequestParam("truename") String truename
    ) {
        String companyname = null;
        try {
            companyname = iDeptService.queryCompanynameByUseridOrTruename(null, truename);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("500", e.getMessage(), companyname);
        }
        /*return RespUtil.successResp("200","提交成功！",companyname);*/
        return companyname;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据登录ID查询岗位名称
     * @Date: 13:39 2018\9\25 0025
     */
    @RequestMapping("/queryHLT001")
    @ResponseBody//表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用【也就是AJAX】
    public Object queryHLT001(
            @RequestParam("username") String username
    ) {
        return iPostService.queryPostnameByUsername(username);
    }

    /**
     * @Author:ShiYun;
     * @Description:赵宏钢的接口，根据账号查询信息
     * @Date: 18:30 2018\7\17 0017
     */
    @RequestMapping("/queryZHG001")
    @ResponseBody
    public Object queryZHG001(
            @RequestParam("username") String username
    ) {
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUsername(username);
        return null==personalInformation?RespUtil.successResp("500", "没有查到此用户信息", null):RespUtil.successResp("200", "查询成功！", personalInformation);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据岗位名称查询人员
     * @Date: 15:51 2018\8\21 0021
     */
    @RequestMapping("/queryZHG002")
    @ResponseBody
    public Object queryZHG002(
            @RequestParam("postname") String postname
    ) {
        Post post = iPostService.queryOneByPostname(postname);
        List<PerAndPostRs> perAndPostRsList = iPerandpostrsService.queryPerAndPostRsByPostid(post.getId());
        List<User> list = new ArrayList<>();
        for (PerAndPostRs p : perAndPostRsList
        ) {
            PersonalInformation personalInformation = iPersonalInformationService.queryOneById(p.getPerid());
            User byId = iUserService.getById(personalInformation.getUserid());
            list.add(byId);
        }
        return RespUtil.successResp("205", "请求成功！", list);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据日期查询工作日志（填写）
     * @Date: 9:40 2018\8\3 0003
     */
    @RequestMapping("/queryWriteGzrz")
    @ResponseBody
    public Object queryWriteGzrz(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ) {
        Object o = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(year + "/" + month + "/01 00:00:00");
            o = iGzrzService.queryGzrzByTime(date);
        } catch (ParseException e) {
            logger.error("==================================格式转换出错(1306)！==============================");
            System.out.println("格式转换出错！");
            return RespUtil.successResp("500", "响应失败！", o);
        }
        return RespUtil.successResp("200", "响应成功！", o);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据日期查询工作日志（审查）
     * @Date: 14:52 2018\8\3 0003
     */
    @RequestMapping("/queryApproveGzrz")
    @ResponseBody
    public Object queryApproveGzrz(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ) {
        Object o = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(year + "/" + month + "/01 00:00:00");
            o = iGzrzService.queryGzrzByTime2(date);
        } catch (ParseException e) {
            System.out.println("格式转换出错！");
        }
        return RespUtil.successResp("205", "相应成功！", o);
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有的录用申请单
     * @Date: 15:23 2018\9\28 0028
     */
    @RequestMapping("/queryAllLysqd")
    @ResponseBody
    public List<Lysqd> queryAllLysqd() {
        return iGzrzService.queryLyspd();
    }

    //所有人员的工号、人名、部门（ID、名称）、[岗位(ID、名称)]
    @RequestMapping("/queryUseridTruenameDepidDepnamePerid")
    @ResponseBody
    public Object queryUseridTruenameDepidDepnamePerid(){
        return iPersonalInformationService.queryUseridTruenameDepidDepnamePerid();
    }

    @RequestMapping("/queryIdcodeInfoByAnalyzeIdcode")
    @ResponseBody
    public Object queryIdcodeInfoByAnalyzeIdcode(
            @RequestParam("idcode")String idcode
    ){
        Map<String, Object> respMap = iPersonalInformationService.queryIdcodeInfoByAnalyzeIdcode(idcode);
        return null==respMap?RespUtil.successResp("500","身份证解析失败！",null):RespUtil.successResp("200","身份证解析成功！",respMap);
    }

    @RequestMapping("/queryWorkingageincompanyByEntryDate")
    @ResponseBody
    public Object queryWorkingageincompanyByEntryDate(
            @RequestParam("entrydate")String entrydate
    ){
        Long entryDateMillis = hrUtils.getTimeMillisByDateString(entrydate);
        if(null==entryDateMillis){
            return RespUtil.successResp("500","时间格式不对",null);
        }
        if(entryDateMillis>System.currentTimeMillis()){
            return RespUtil.successResp("500","入职时间不能超过当前时间",null);
        }
        return RespUtil.successResp("200","司龄计算成功！",hrUtils.getWorkingageByFirstworkingtime(entrydate));
    }
}
