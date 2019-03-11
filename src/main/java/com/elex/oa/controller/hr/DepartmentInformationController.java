package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.controller.restructure_hr.DepartmentInfoController;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.department.Dept;
import com.elex.oa.entity.hr_entity.department.DeptLog;
import com.elex.oa.entity.hr_entity.department.DeptTree;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.hr_entity.post.TitleAndCode;
import com.elex.oa.entity.hr_entity.readexcel.ReadDeplogExcel;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.hr_util.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@CrossOrigin
public class DepartmentInformationController {

    @Autowired
    IDeptService iDeptService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IPersonalInformationService iPersonalInformationService;

    @Autowired
    IDeptLogService iDeptLogService;

    @Autowired
    IHRsetService ihRsetService;

    @Autowired
    IChangeInformationService iChangeInformationService;

    @Autowired
    IBaseInformationService iBaseInformationService;

    @Autowired
    IPostService iPostService;

    @Autowired
    IManageInformationService iManageInformationService;

    @Autowired
    ICostInformationService iCostInformationService;

    @Autowired
    IOtherInformationService iOtherInformationService;

    @Autowired
    IPerandpostrsService iPerandpostrsService;
    @Autowired
    HrUtils hrUtils;

    private static Logger logger = LoggerFactory.getLogger(DepartmentInfoController.class);

    @RequestMapping("/queryOneDepByDepname")
    @ResponseBody
    public Dept queryOneDepByDepname(@RequestParam("title") String depname){
        Dept dept = null;
        try {
            dept = new Dept();
            dept = iDeptService.queryOneDepByDepname(depname).get(0);
            Dept parentdep = new Dept();
            User principaluser = new User();
            User deputyuser = new User();
            User secretaryuser = new User();
            if(dept.getParentdepid()!=null){
                parentdep = iDeptService.queryOneDepByDepid(dept.getParentdepid());
            }else{
                parentdep.setDepname("");
            }
            if(dept.getPrincipaluserid()!=null){
                principaluser = iUserService.queryServingUserByUserid(dept.getPrincipaluserid());
            }else {
                principaluser.setTruename("");
            }
            if(dept.getDeputyuserid()!=null){
                deputyuser = iUserService.queryServingUserByUserid(dept.getDeputyuserid());
            }else {
                deputyuser.setTruename("");
            }
            if(dept.getSecretaryuserid()!=null){
                secretaryuser = iUserService.queryServingUserByUserid(dept.getSecretaryuserid());
            }else {
                secretaryuser.setTruename("");
            }
            dept.setParentdep(parentdep);
            dept.setPrincipaluser(principaluser);
            dept.setDeputyuser(deputyuser);
            dept.setSecretaryuser(secretaryuser);

            PersonalInformation personalInformation = new PersonalInformation();
            personalInformation.setDepid(dept.getId());
            List<PersonalInformation> personalInformations = iPersonalInformationService.queryPIs(personalInformation);
            dept.setNumbers(personalInformations.size());

            HRset hRsetFunctionaltype = ihRsetService.queryById(dept.getFunctionaltypeid());
            if (hRsetFunctionaltype!=null) {
                dept.setFunctionaltype(hRsetFunctionaltype.getDatavalue());
            }
            HRset hRsetDeptype = ihRsetService.queryById(dept.getDeptypeid());
            if(hRsetDeptype!=null){
                dept.setDeptype(hRsetDeptype.getDatavalue());
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
        }

        return dept;
    }

    @RequestMapping("/queryOneDepByDepcode")
    @ResponseBody
    public Dept queryOneDepByDepcode(
            @RequestParam("code")String code
    ){
        return iDeptService.queryOneByDepcode(code);
    }

    @RequestMapping("/queryDepartments")
    @ResponseBody
    public List<Dept> queryDepartments(){
        List<Dept> depts = iDeptService.queryAllDepts();
        for(int i=0;i<depts.size();i++){
            HRset hRsetFunctionaltype = ihRsetService.queryById(depts.get(i).getFunctionaltypeid());
            if (hRsetFunctionaltype!=null) {
                depts.get(i).setFunctionaltype(hRsetFunctionaltype.getDatavalue());
            }
            HRset hRsetDeptype = ihRsetService.queryById(depts.get(i).getDeptypeid());
            if(hRsetDeptype!=null){
                depts.get(i).setDeptype(hRsetDeptype.getDatavalue());
            }
        }
        return depts;
    }

    @RequestMapping("/queryDepartmentsRemoveChilren")
    @ResponseBody
    public List<Dept> queryDepartmentsRemoveChilren(
            @RequestParam("depid")Integer depid
    ){
        return iDeptService.queryDepartmentsRemoveChilren(depid);
    }

    @RequestMapping("/listDepts")
    @ResponseBody
    public DeptTree listDepts(){
        List<Dept> depts = iDeptService.queryByParentId(null);
        Dept dept = depts.get(0);
        DeptTree deptTree = new DeptTree();
        deptTree.setTitle(dept.getDepname());
        deptTree.setName(dept.getDepname());
        deptTree.setCode(dept.getDepcode());
        deptTree.setExpand(true);
        return getDeptTree(deptTree, dept.getId());
    }

    public DeptTree getDeptTree(DeptTree deptTree,Integer parentid){
        List<Dept> depts = iDeptService.queryByParentId(parentid);
        if (depts.isEmpty()) {
            List<DeptTree> children = new ArrayList<>();
            for(int i=0;i<depts.size();i++){
                DeptTree deptTree1 = new DeptTree();
                String depname = depts.get(i).getDepname();
                deptTree1.setTitle(depname);
                deptTree1.setName(depname);
                deptTree1.setCode(depts.get(i).getDepcode());
                deptTree1.setExpand(true);
                DeptTree deptTree2 = getDeptTree(deptTree1, depts.get(i).getId());
                children.add(deptTree2);
            }
            deptTree.setChildren(children);
        }else {
            return deptTree;
        }
        return deptTree;
    }

    @RequestMapping("/addOneDepartment")
    @ResponseBody
    public Object addOneDepartment(
            Dept dept,
            @RequestParam("transactorusername") String transactorusername
    ){
        return iDeptService.addOneDepartment(dept,transactorusername);
    }

    @RequestMapping("/queryOneByDepid")
    @ResponseBody
    public Dept queryOneByDepid(@RequestParam("depid") Integer id){
        return iDeptService.queryOneByDepid(id);
    }

    @RequestMapping("/updateOneDepartment")
    @ResponseBody
    public Object updateOneDepartment(
            Dept dept,
            @RequestParam("transactorusername") String transactorusername
    ) {
        Map<String, String> respMap = iDeptService.updateOneDepartment(dept, transactorusername);
        return respMap.size()==0?RespUtil.response("200","修改成功！",null):RespUtil.response("500","修改失败！", JSON.toJSONString(respMap));
    }

    @RequestMapping("/deleteDeptsById")
    @ResponseBody
    public String deleteDeptsById(@RequestParam("id") Integer id){
        try {
            List<Integer> depids = new ArrayList<>();
            depids.add(id);
            depids = getDeptids(id,depids);
            if (!depids.isEmpty()) {
                for (int i=0;i<depids.size();i++){
                    iDeptService.removeOne(depids.get(i));
                }
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return "删除失败！";
        }
        return "删除成功！";
    }
    // 通过递归获得需要删除的所有部门ID
    public List<Integer> getDeptids(Integer parentdepid,List<Integer> list){
        List<Dept> depts = iDeptService.queryByParentId(parentdepid);
        if(!depts.isEmpty()){
            for (Integer i = 0;i< depts.size();i++){
                list.add(depts.get(i).getId());
                list = getDeptids(depts.get(i).getId(), list);
            }
        }
        return list;
    }

    @RequestMapping("/queryDeptLogInformations")
    @ResponseBody
    public PageInfo<DeptLog> queryDeptLogInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            DeptLog deptLog
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",deptLog);
        PageInfo<DeptLog> deptLogPageInfo = iDeptLogService.queryByConditions(paramMap);
        List<DeptLog> list = deptLogPageInfo.getList();
        if(!list.isEmpty()){
            for (int i = 0;i< list.size();i++) {
                if (iDeptService.queryOneDepByDepid(list.get(i).getDeptid())!=null) {
                    list.get(i).setDeptname(iDeptService.queryOneDepByDepid(list.get(i).getDeptid()).getDepname());
                } else {
                    list.get(i).setDeptname("此部门已经不存在");
                }
                if (iUserService.getById(list.get(i).getTransactoruserid())!=null){
                    list.get(i).setTransactortruename(iUserService.getById(list.get(i).getTransactoruserid()).getTruename());
                } else {
                    list.get(i).setTransactortruename("此员工已经不存在");
                }
            }
            deptLogPageInfo.setList(list);
        }
        return deptLogPageInfo;
    }

    @RequestMapping("/queryAllDeptLogInformations")
    @ResponseBody
    public List<DeptLog> queryAllDeptLogInformations(){
        List<DeptLog> deptLogs = iDeptLogService.queryAllDeptLogs();
        for(DeptLog deptLog:deptLogs){
            if (iDeptService.queryOneDepByDepid(deptLog.getDeptid())!=null) {
                deptLog.setDeptname(iDeptService.queryOneDepByDepid(deptLog.getDeptid()).getDepname());
            }
            if (iUserService.getById(deptLog.getTransactoruserid())!=null) {
                deptLog.setTransactortruename(iUserService.getById(deptLog.getTransactoruserid()).getTruename());
            }
        }
        return deptLogs;
    }

    @RequestMapping("/deleteDeplogByIds")
    @ResponseBody
    public String deleteDeplogByIds(
            @RequestParam("deplogids") List<Integer> deplogids
    ){
       for(Integer deplogid:deplogids){
           try {
               iDeptLogService.removeOne(deplogid);
           } catch (Exception e) {
               return "删除失败！";
           }
       }
       return "删除成功！";
    }

    @RequestMapping("/importDeploginformations")
    @ResponseBody
    public String importDeploginformations(
            @RequestParam("file") MultipartFile multipartFile
    ){
        try {
            ReadDeplogExcel readDeplogExcel = new ReadDeplogExcel();
            List<DeptLog> excelInfo = readDeplogExcel.getExcelInfo(multipartFile);
            for(DeptLog deptLog:excelInfo){
                if (iDeptService.queryOneDepByDepname(deptLog.getDeptname())!=null) {
                    deptLog.setDeptid(iDeptService.queryOneDepByDepname(deptLog.getDeptname()).get(0).getId());
                }
                User user = new User();
                user.setTruename(deptLog.getTransactortruename());
                List<User> users = iUserService.selectByCondition(user);
                if (!users.isEmpty()) {
                    deptLog.setTransactoruserid(users.get(0).getId());
                }
                iDeptLogService.addOne(deptLog);
            }
        } catch (Exception e) {
            return "数据导入失败！";
        }
        return "数据导入成功！";
    }

    @RequestMapping("/sortDepinformation")
    @ResponseBody
    public List<TitleAndCode> sortDepinformation(
            @RequestParam("title") String title
    ){
        List<TitleAndCode> list = new ArrayList<>();
        List<Dept> depts;
        if (iDeptService.queryOneDepByDepname(title)!=null) {
            depts = iDeptService.queryByParentId(iDeptService.queryOneDepByDepname(title).get(0).getId());
        } else {
            return Collections.emptyList();
        }
        for(int i = 0;i<depts.size();i++){
            TitleAndCode titleAndCode = new TitleAndCode();
            titleAndCode.setTitle(depts.get(i).getDepname());
            titleAndCode.setCode(depts.get(i).getOrdercode());
            list.add(titleAndCode);
        }
        return list;
    }

    @RequestMapping("/submitSortdata")
    @ResponseBody
    public DeptTree submitSortdata(
            @RequestParam("sortdata") String sortdata,
            @RequestParam("title") String title,
            @RequestParam("deptTree") String deptTree
    ){
        return iDeptService.submitSortdata(sortdata,title,deptTree);
    }

    //人事的管理看板
    @RequestMapping("/queryHRManageCard")
    @ResponseBody
    public Object queryHRManageCard(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname
    ){
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname= Commons.COMPANYNAME_GROUP;break;
            case "南京总部":companyname=Commons.COMPANYNAME_HEADQUARTERS;break;
            case "上海臻相":companyname=Commons.COMPANYNAME_SHANGHAI;break;
            case "贵州中科":companyname=Commons.COMPANYNAME_GUIZHOU;break;
            default:break;
        }
        return iDeptService.getHRManageCard(companyname,sdate,edate);
    }

    //获得总人数(edate时间点的在职总人数)
    @RequestMapping("/queryHRManageCard2")
    @ResponseBody
    public Object queryHRManageCard2(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page
    ){
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname= Commons.COMPANYNAME_GROUP;break;
            case "南京总部":companyname=Commons.COMPANYNAME_HEADQUARTERS;break;
            case "上海臻相":companyname=Commons.COMPANYNAME_SHANGHAI;break;
            case "贵州中科":companyname=Commons.COMPANYNAME_GUIZHOU;break;
            default:break;
        }

        Object hrManageCard = iDeptService.getHRManageCard2(companyname,rows,page,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = hrUtils.getPersonalinformationDetail(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    //获得入职总人数(edate时间点的入职总人数)
    @RequestMapping("/queryHRManageCard3")
    @ResponseBody
    public Object queryHRManageCard3(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page
    ){
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname= Commons.COMPANYNAME_GROUP;break;
            case "南京总部":companyname=Commons.COMPANYNAME_HEADQUARTERS;break;
            case "上海臻相":companyname=Commons.COMPANYNAME_SHANGHAI;break;
            case "贵州中科":companyname=Commons.COMPANYNAME_GUIZHOU;break;
            default:break;
        }

        Object hrManageCard = iDeptService.getHRManageCard3(companyname,rows,page,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = hrUtils.getPersonalinformationDetail(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    //获得离职总人数(edate时间点的离职总人数)
    @RequestMapping("/queryHRManageCard4")
    @ResponseBody
    public Object queryHRManageCard4(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page
    ) {
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname= Commons.COMPANYNAME_GROUP;break;
            case "南京总部":companyname=Commons.COMPANYNAME_HEADQUARTERS;break;
            case "上海臻相":companyname=Commons.COMPANYNAME_SHANGHAI;break;
            case "贵州中科":companyname=Commons.COMPANYNAME_GUIZHOU;break;
            default:break;
        }

        Object hrManageCard = iDeptService.getHRManageCard4(companyname,rows,page,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = hrUtils.getPersonalinformationDetail(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    //获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
    @RequestMapping("/queryHRManageCard5")
    @ResponseBody
    public Object queryHRManageCard5(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page,
            @RequestParam("depid")Integer depid
    ) {
        Object hrManageCard = iDeptService.getHRManageCard5(rows,page,depid,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = hrUtils.getPersonalinformationDetail(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    //获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
    @RequestMapping("/queryHRManageCard6")
    @ResponseBody
    public Object queryHRManageCard6(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page,
            @RequestParam("depid")Integer depid
    ) {
        Object hrManageCard = iDeptService.getHRManageCard6(rows,page,depid,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = hrUtils.getPersonalinformationDetail(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    //获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
    @RequestMapping("/queryHRManageCard7")
    @ResponseBody
    public Object queryHRManageCard7(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page,
            @RequestParam("depid")Integer depid
    ) {
        Object hrManageCard = iDeptService.getHRManageCard7(rows,page,depid,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = hrUtils.getPersonalinformationDetail(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    //IOS的通讯录数据
    @RequestMapping("/queryIOSData")
    @ResponseBody
    public HRManageCard queryIOSData(
            Integer deptid
    ){
        return iDeptService.getParamMap1(deptid);
    }

    //添加部门的时候校验部门名称
    @RequestMapping("/validateDeptnameForAddDept")
    @ResponseBody
    public Boolean validateDeptnameForAddDept(
            @RequestParam("deptname") String deptname
    ){
        Dept dept = iDeptService.queryOneDepByDepname(deptname).get(0);
        return null!=dept;
    }

    //添加部门的时候校验部门编号
    @RequestMapping("/validateDeptcodeForAddDept")
    @ResponseBody
    public Boolean validateDeptcodeForAddDept(
            @RequestParam("deptcode") String deptcode
    ){
        Dept dept = iDeptService.queryOneByDepcode(deptcode);
        return dept!=null;
    }

    @RequestMapping("/queryPostListByDepidButIsNotNull")
    @ResponseBody
    public Map<String,Object> queryPostListByDepidButIsNotNull(
            @RequestParam("depid")Integer depid
    ){
        return iDeptService.queryPostListByDepidButIsNotNull(depid);
    }
}
