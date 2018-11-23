package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.hr_util.IDcodeUtil;
import com.elex.oa.util.hr_util.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     *@Author:ShiYun;
     *@Description:根据部门名称获取部门对象(此方法已经过时)
     *@Date: 13:20 2018\3\16 0016
     */
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
            e.printStackTrace();
        }

        return dept;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门编号获取部门对象
     *@Date: 19:37 2018\8\11 0011
     */
    @RequestMapping("/queryOneDepByDepcode")
    @ResponseBody
    public Dept queryOneDepByDepcode(
            @RequestParam("code")String code
    ){
        Dept dept = null;
        try {
            dept = new Dept();
            dept = iDeptService.queryOneByDepcode(code);
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
            e.printStackTrace();
        }

        return dept;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门
     *@Date: 11:00 2018\4\11 0011
     */
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

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门（去除下级部门和自身）
     *@Date: 17:49 2018\8\30 0030
     */
    @RequestMapping("/queryDepartmentsRemoveChilren")
    @ResponseBody
    public List<Dept> queryDepartmentsRemoveChilren(
            @RequestParam("depid")Integer depid
    ){
        List<Dept> depts = iDeptService.queryDepartmentsRemoveChilren(depid);
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:将部门树列出来
     *@Date: 10:43 2018\4\16 0016
     */
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
        DeptTree deptTree1 = getDeptTree(deptTree, dept.getId());
        return deptTree1;
    }

    public DeptTree getDeptTree(DeptTree deptTree,Integer parentid){
        List<Dept> depts = iDeptService.queryByParentId(parentid);
        if (depts.size()!=0) {
            List<DeptTree> children = new ArrayList<DeptTree>();
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

    /**
     *@Author:ShiYun;
     *@Description:添加部门信息
     *@Date: 10:50 2018\4\23 0023
     */
    @RequestMapping("/addOneDepartment")
    @ResponseBody
    public Object addOneDepartment(
            Dept dept,
            @RequestParam("transactorusername") String transactorusername
    ){
        //先校验部门名称是否存在
        Dept queryOneByDepcode = iDeptService.queryOneByDepcode(dept.getDepcode());
        if(queryOneByDepcode!=null){
            return RespUtil.successResp("500","部门编号已存在，请重新输入部门编号！",null) ;
        }

        /**1.添加新部门
         * 2.正职、副职、秘书的原部门信息修改、并添加相应的部门信息修改日志
         * 3.人事信息的修改、并添加相应的人事信息修改日志
         * */
        //查询办理人
        User user = new User();
        user.setUsername(transactorusername);
        User user1 = iUserService.selectOne(user);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        //先将部门信息添加到数据库中
        List<HRset> hRsetFunctionaltypeList = ihRsetService.queryByConditions(new HRset("functionaltype", dept.getFunctionaltype()));
        if (hRsetFunctionaltypeList!=null && hRsetFunctionaltypeList.size()==1) {
            dept.setFunctionaltypeid(hRsetFunctionaltypeList.get(0).getId());
        }
        List<HRset> hRsetDeptypeList = ihRsetService.queryByConditions(new HRset("deptype", dept.getDeptype()));
        if(hRsetDeptypeList!=null && hRsetDeptypeList.size()==1){
            dept.setDeptypeid(hRsetDeptypeList.get(0).getId());
        }

        //添加公司名称
        //首先根据"ELEX"模糊查询出所有的公司
        //根据公司编号的后两位数字判断是哪个公司
        //最后添加公司名称
        List<Dept> elex = iDeptService.queryDeptsByDepcode("ELEX");
        Boolean validateCP = true;
        for (Dept d:elex
             ) {
            int l = d.getDepcode().length();
            if(dept.getDepcode().substring(0,2).equals(d.getDepcode().substring(l-2,l))){
                dept.setCompanyname(d.getDepname());
                validateCP = false;
                break;
            }
        }
        if(validateCP){
            dept.setCompanyname(dept.getDepname());
        }
        Integer depid = iDeptService.addOne(dept);
        dept.setId(depid);

        //正职、副职、秘书的原部门信息修改、并添加相应的部门信息修改日志
        Integer principaluserid = dept.getPrincipaluserid();
        if(principaluserid!=null){
            //如果在其他部门任正职、副职、秘书的话，需先将原部门信息相应信息修改并添加部门信息修改日志
            PersonalInformation principalPer = iPersonalInformationService.queryOneByUserid(principaluserid);
            Dept dept1 = null;
            if (null!=principalPer) {
                dept1 = iDeptService.queryOneDepByDepid(principalPer.getDepid());
            }
            if (dept1!=null) {
                String perBeforeinformation = dept1.getDepname();
                if(principaluserid==dept1.getPrincipaluserid()||principaluserid==dept1.getDeputyuserid()||principaluserid==dept1.getSecretaryuserid()){
                    /*修改部门信息*/
                    Dept dept2 = new Dept();
                    dept2.setId(dept1.getId());
                    String str = "";
                    //如果任正职
                    if(principaluserid==dept1.getPrincipaluserid()){
                        dept2.setPrincipaluserid(principaluserid);
                        str = "正职";
                    }
                    //如果任副职
                    if(principaluserid==dept1.getDeputyuserid()){
                        dept2.setDeputyuserid(principaluserid);
                        str = "副职";
                    }
                    //如果任秘书
                    if(principaluserid==dept1.getSecretaryuserid()){
                        dept2.setSecretaryuserid(principaluserid);
                        str = "秘书";
                    }
                    iDeptService.modifyByDeptidAndOtherinformation(dept2);

                    /*添加相应部门的部门信息修改日志*/
                    DeptLog deptLog1 = new DeptLog();
                    deptLog1.setChangeinformation(str);
                    deptLog1.setDeptid(depid);
                    deptLog1.setBeforeinformation(iUserService.getById(principaluserid).getTruename());
                    deptLog1.setAfterinformation(null);
                    deptLog1.setChangereason("系统需要");
                    deptLog1.setChangedate(changedate);
                    deptLog1.setTransactoruserid(user1.getId());
                    iDeptLogService.addOne(deptLog1);
                }

                //人事信息的修改
                principalPer.setDepid(depid);
                iPersonalInformationService.modifyOne(principalPer);
                //添加人事信息的日志
                ChangeInformation changeInformation = new ChangeInformation();
                changeInformation.setChangeinformation("部门");//变更项目
                changeInformation.setChangeduserid(principaluserid);//变更姓名
                changeInformation.setBeforeinformation(perBeforeinformation);//变更前内容
                changeInformation.setAfterinformation(dept.getDepname());//变更后内容
                changeInformation.setChangereason("系统需要");//变更原因
                changeInformation.setChangedate(changedate);//变更日期
                changeInformation.setTransactoruserid(user1.getId());//变更人
                iChangeInformationService.addOne(changeInformation);
            }
        }
        Integer deputyuserid = dept.getDeputyuserid();
        if(deputyuserid!=null){
            //如果在其他部门任正职、副职、秘书的话，需先将原部门信息相应信息修改并添加部门信息修改日志
            PersonalInformation deputyPer = iPersonalInformationService.queryOneByUserid(deputyuserid);
            Dept dept1 = iDeptService.queryOneDepByDepid(deputyPer.getDepid());
            if (dept1!=null) {
                String perBeforeinformation = dept1.getDepname();
                if(deputyuserid==dept1.getPrincipaluserid()||deputyuserid==dept1.getDeputyuserid()||deputyuserid==dept1.getSecretaryuserid()){
                    /*修改部门信息*/
                    Dept dept2 = new Dept();
                    dept2.setId(dept1.getId());
                    String str = "";
                    //如果任正职
                    if(deputyuserid==dept1.getPrincipaluserid()){
                        dept2.setPrincipaluserid(deputyuserid);
                        str = "正职";
                    }
                    //如果任副职
                    if(deputyuserid==dept1.getDeputyuserid()){
                        dept2.setDeputyuserid(deputyuserid);
                        str = "副职";
                    }
                    //如果任秘书
                    if(deputyuserid==dept1.getSecretaryuserid()){
                        dept2.setSecretaryuserid(deputyuserid);
                        str = "秘书";
                    }
                    iDeptService.modifyByDeptidAndOtherinformation(dept2);

                    /*添加相应部门的部门信息修改日志*/
                    DeptLog deptLog1 = new DeptLog();
                    deptLog1.setChangeinformation(str);
                    deptLog1.setDeptid(depid);
                    deptLog1.setBeforeinformation(iUserService.getById(deputyuserid).getTruename());
                    deptLog1.setAfterinformation(null);
                    deptLog1.setChangereason("系统需要");
                    deptLog1.setChangedate(changedate);
                    deptLog1.setTransactoruserid(user1.getId());
                    iDeptLogService.addOne(deptLog1);
                }

                //人事信息的修改
                deputyPer.setDepid(depid);
                iPersonalInformationService.modifyOne(deputyPer);
                //添加人事信息的日志
                ChangeInformation changeInformation = new ChangeInformation();
                changeInformation.setChangeinformation("部门");//变更项目
                changeInformation.setChangeduserid(deputyuserid);//变更姓名
                changeInformation.setBeforeinformation(perBeforeinformation);//变更前内容
                changeInformation.setAfterinformation(dept.getDepname());//变更后内容
                changeInformation.setChangereason("系统需要");//变更原因
                changeInformation.setChangedate(changedate);//变更日期
                changeInformation.setTransactoruserid(user1.getId());//变更人
                iChangeInformationService.addOne(changeInformation);
            }
        }
        Integer secretaryuserid = dept.getSecretaryuserid();
        if(secretaryuserid!=null){
            //如果在其他部门任正职、副职、秘书的话，需先将原部门信息相应信息修改并添加部门信息修改日志
            PersonalInformation secretaryPer = iPersonalInformationService.queryOneByUserid(secretaryuserid);
            Dept dept1 = iDeptService.queryOneDepByDepid(secretaryPer.getDepid());
            if (dept1!=null) {
                String perBeforeinformation = dept1.getDepname();
                if(secretaryuserid==dept1.getPrincipaluserid()||secretaryuserid==dept1.getDeputyuserid()||secretaryuserid==dept1.getSecretaryuserid()){
                    /*修改部门信息*/
                    Dept dept2 = new Dept();
                    dept2.setId(dept1.getId());
                    String str = "";
                    //如果任正职
                    if(secretaryuserid==dept1.getPrincipaluserid()){
                        dept2.setPrincipaluserid(secretaryuserid);
                        str = "正职";
                    }
                    //如果任副职
                    if(secretaryuserid==dept1.getDeputyuserid()){
                        dept2.setDeputyuserid(secretaryuserid);
                        str = "副职";
                    }
                    //如果任秘书
                    if(secretaryuserid==dept1.getSecretaryuserid()){
                        dept2.setSecretaryuserid(secretaryuserid);
                        str = "秘书";
                    }
                    iDeptService.modifyByDeptidAndOtherinformation(dept2);

                    /*添加相应部门的部门信息修改日志*/
                    DeptLog deptLog1 = new DeptLog();
                    deptLog1.setChangeinformation(str);
                    deptLog1.setDeptid(depid);
                    deptLog1.setBeforeinformation(iUserService.getById(secretaryuserid).getTruename());
                    deptLog1.setAfterinformation(null);
                    deptLog1.setChangereason("系统需要");
                    deptLog1.setChangedate(changedate);
                    deptLog1.setTransactoruserid(user1.getId());
                    iDeptLogService.addOne(deptLog1);
                }

                //人事信息的修改
                secretaryPer.setDepid(depid);
                iPersonalInformationService.modifyOne(secretaryPer);
                //添加人事信息的日志
                ChangeInformation changeInformation = new ChangeInformation();
                changeInformation.setChangeinformation("部门");//变更项目
                changeInformation.setChangeduserid(secretaryuserid);//变更姓名
                changeInformation.setBeforeinformation(perBeforeinformation);//变更前内容
                changeInformation.setAfterinformation(dept.getDepname());//变更后内容
                changeInformation.setChangereason("系统需要");//变更原因
                changeInformation.setChangedate(changedate);//变更日期
                changeInformation.setTransactoruserid(user1.getId());//变更人
                iChangeInformationService.addOne(changeInformation);
            }
        }
        return RespUtil.successResp("200","提交成功！",dept) ;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID查询部门信息
     *@Date: 9:30 2018\5\2 0002
     */
    @RequestMapping("/queryOneByDepid")
    @ResponseBody
    public Dept queryOneByDepid(@RequestParam("depid") Integer id){
        Dept dept = iDeptService.queryOneDepByDepid(id);
        HRset hRsetFunctionaltype = ihRsetService.queryById(dept.getFunctionaltypeid());
        if (hRsetFunctionaltype!=null) {
            dept.setFunctionaltype(hRsetFunctionaltype.getDatavalue());
        }
        HRset hRsetDeptype = ihRsetService.queryById(dept.getDeptypeid());
        if(hRsetDeptype!=null){
            dept.setDeptype(hRsetDeptype.getDatavalue());
        }
        return dept;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改部门信息
     *@Date: 9:55 2018\5\2 0002
     */
    @RequestMapping("/updateOneDepartment")
    @ResponseBody
    public Object updateOneDepartment(
            Dept dept,
            @RequestParam("transactorusername") String transactorusername
    ) {
        //先校验部门名称是否存在
        Dept queryOneByDepcode = iDeptService.queryOneByDepcode(dept.getDepcode());
        iDeptService.queryOneDepByDepid(dept.getId()).getDepcode();
        if(!iDeptService.queryOneDepByDepid(dept.getId()).getDepcode().equals(dept.getDepcode()) && queryOneByDepcode!=null){
            return RespUtil.successResp("500","部门编号已存在，请重新输入部门名称！",null) ;
        }
        try {
            Boolean b = false;

            // 添加部门日志
            DeptLog deptLog = new DeptLog();
            deptLog.setDeptid(dept.getId());
            deptLog.setChangereason("公司业务需要");// 修改的原因业务暂未开发，默认为固定值
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String changedate = simpleDateFormat.format(new Date());
            deptLog.setChangedate(changedate);
            User user = new User();
            user.setUsername(transactorusername);
            deptLog.setTransactoruserid(iUserService.selectByCondition(user).get(0).getId());//默认为管理员，实际从session中拿

            Dept dept2 = iDeptService.queryOneDepByDepid(dept.getId());
            HRset hRsetFunctionaltype = ihRsetService.queryById(dept2.getFunctionaltypeid());
            if (hRsetFunctionaltype!=null) {
                dept2.setFunctionaltype(hRsetFunctionaltype.getDatavalue());
            }

            if (!dept.getDepname().equals(dept2.getDepname())){
                Dept dept1 = iDeptService.queryOneDepByDepname(dept.getDepname()).get(0);
                if (dept1==null) {
                    b = true;
                    deptLog.setChangeinformation("部门名称");
                    deptLog.setBeforeinformation(dept2.getDepname());
                    deptLog.setAfterinformation(dept.getDepname());
                    iDeptLogService.addOne(deptLog);
                }
            }if (!dept.getDepcode().equals(dept2.getDepcode())){
                b = true;
                deptLog.setChangeinformation("部门编号");
                deptLog.setBeforeinformation(dept2.getDepcode());
                deptLog.setAfterinformation(dept.getDepcode());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getFunctionaltype().equals(dept2.getFunctionaltype())){
                b = true;
                deptLog.setChangeinformation("职能类型");
                deptLog.setBeforeinformation(dept2.getFunctionaltype());
                deptLog.setAfterinformation(dept.getFunctionaltype());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getDeptype().equals(dept2.getDeptype())){
                b = true;
                deptLog.setChangeinformation("部门类型");
                deptLog.setBeforeinformation(dept2.getDeptype());
                deptLog.setAfterinformation(dept.getDeptype());
                iDeptLogService.addOne(deptLog);
            }if (dept.getParentdepid()!=null && !dept.getParentdepid().toString().equals(dept2.getParentdepid().toString())){
                b = true;
                deptLog.setChangeinformation("上级部门");
                deptLog.setBeforeinformation(iDeptService.queryOneDepByDepid(dept2.getParentdepid()).getDepname());
                deptLog.setAfterinformation(iDeptService.queryOneDepByDepid(dept.getParentdepid()).getDepname());
                iDeptLogService.addOne(deptLog);
            }if (dept2.getPrincipaluserid()!=null && dept.getPrincipaluserid()!=null &&  !dept2.getPrincipaluserid().toString().equals(dept.getPrincipaluserid().toString())){
                PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(dept.getPrincipaluserid());
                Integer depid = personalInformation.getDepid();
                Dept dept1 = iDeptService.queryOneDepByDepid(depid);//其他部门
                if(depid!=dept2.getId()){
                    //说明此员工非本部门，需要先将该员工的部门信息改为该部门信息,并添加人事信息修改日志,
                    // 如果还在其他部门信息担任正职、副职、秘书等职位，则需要先将相应的部门相应信息修改一下,并添加相应部门的部门信息修改日志
                    if (dept1!=null) {
                        if(dept.getPrincipaluserid()==dept1.getPrincipaluserid() || dept.getPrincipaluserid()==dept1.getDeputyuserid() || dept.getPrincipaluserid()==dept1.getSecretaryuserid()){
                            String str = "";
                            /*修改相应的部门信息*/
                            Dept dept3 = new Dept();//需要修改的部门
                            dept3.setId(depid);
                            //如果是正职
                            if (dept.getPrincipaluserid()==dept1.getPrincipaluserid()) {
                                dept3.setPrincipaluserid(dept.getPrincipaluserid());
                                str = "部门正职";
                            }
                            //如果是副职
                            if (dept.getPrincipaluserid()==dept1.getDeputyuserid()) {
                                dept3.setDeputyuserid(dept.getPrincipaluserid());
                                str = "部门副职";
                            }
                            //如果是秘书
                            if (dept.getPrincipaluserid()==dept1.getSecretaryuserid()) {
                                dept3.setSecretaryuserid(dept.getPrincipaluserid());
                                str = "部门秘书";
                            }
                            iDeptService.modifyByDeptidAndOtherinformation(dept3);
                            /*添加相应部门的部门信息修改日志*/
                            DeptLog deptLog1 = new DeptLog();
                            deptLog1.setChangeinformation(str);
                            deptLog1.setDeptid(depid);
                            deptLog1.setBeforeinformation(iUserService.getById(dept.getPrincipaluserid()).getTruename());
                            deptLog1.setAfterinformation(null);
                            deptLog1.setChangereason("系统需要");
                            deptLog1.setChangedate(changedate);
                            deptLog1.setTransactoruserid(deptLog.getTransactoruserid());
                            iDeptLogService.addOne(deptLog1);
                        }
                    }
                    //根据ID修改员工信息,此时该员工是该部门的普通员工
                    PersonalInformation per = new PersonalInformation();
                    per.setId(personalInformation.getId());
                    per.setDepid(dept2.getId());
                    iPersonalInformationService.modifyOne(per);
                    //添加人事信息修改日志，此时该员工是无部门员工
                    ChangeInformation changeInformation = new ChangeInformation();
                    changeInformation.setChangeinformation("部门");//变更项目
                    changeInformation.setChangeduserid(dept.getPrincipaluserid());//变更姓名
                    if (iDeptService.queryOneDepByDepid(depid)!=null) {
                        changeInformation.setBeforeinformation(iDeptService.queryOneDepByDepid(depid).getDepname());//变更前内容
                    } else {
                        changeInformation.setBeforeinformation("此部门已经不存在！");//变更前内容
                    }
                    changeInformation.setAfterinformation(dept.getDepname());//变更后内容
                    changeInformation.setChangereason("系统需要");//变更原因
                    changeInformation.setChangedate(changedate);//变更日期
                    changeInformation.setTransactoruserid(deptLog.getTransactoruserid());//变更人
                    iChangeInformationService.addOne(changeInformation);
                }else {
                    //说明是本部门内部职位的变动，需要先将其原职位取消掉，再添加新职位
                    if(dept.getPrincipaluserid()==dept1.getDeputyuserid() || dept.getPrincipaluserid()==dept1.getSecretaryuserid()){
                        String str = "";
                        /*修改相应的部门信息*/
                        Dept dept3 = new Dept();//需要修改的部门
                        dept3.setId(depid);
                        //如果是副职
                        if (dept.getPrincipaluserid()==dept1.getDeputyuserid()) {
                            dept3.setDeputyuserid(dept.getPrincipaluserid());
                            str = "部门副职";
                        }
                        //如果是秘书
                        if (dept.getPrincipaluserid()==dept1.getSecretaryuserid()) {
                            dept3.setSecretaryuserid(dept.getPrincipaluserid());
                            str = "部门秘书";
                        }
                        iDeptService.modifyByDeptidAndOtherinformation(dept3);
                        /*添加相应部门的部门信息修改日志*/
                        DeptLog deptLog1 = new DeptLog();
                        deptLog1.setChangeinformation(str);
                        deptLog1.setDeptid(depid);
                        deptLog1.setBeforeinformation(iUserService.getById(dept.getPrincipaluserid()).getTruename());
                        deptLog1.setAfterinformation(null);
                        deptLog1.setChangereason("系统需要");
                        deptLog1.setChangedate(changedate);
                        deptLog1.setTransactoruserid(deptLog.getTransactoruserid());
                        iDeptLogService.addOne(deptLog1);
                    }
                }
                b = true;
                deptLog.setChangeinformation("部门正职");
                User principaluser = iUserService.getById(dept2.getPrincipaluserid());
                if (principaluser!=null) {
                    deptLog.setBeforeinformation(iUserService.getById(dept2.getPrincipaluserid()).getTruename());
                } else {
                    deptLog.setBeforeinformation("原部门正职已经不存在！");
                }
                deptLog.setAfterinformation(iUserService.getById(dept.getPrincipaluserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }if (dept2.getDeputyuserid()!=null && dept.getDeputyuserid()!=null &&  !dept2.getDeputyuserid().toString().equals(dept.getDeputyuserid().toString())){
                PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(dept.getDeputyuserid());
                Integer depid = personalInformation.getDepid();
                Dept dept1 = iDeptService.queryOneDepByDepid(depid);//其他部门
                if(depid!=dept2.getId()){
                    //说明此员工非本部门，需要先将该员工的部门信息改为该部门信息,并添加人事信息修改日志,
                    // 如果还在其他部门信息担任正职、副职、秘书等职位，则需要先将相应的部门相应信息修改一下,并添加相应部门的部门信息修改日志
                    if(dept.getDeputyuserid()==dept1.getPrincipaluserid() || dept.getDeputyuserid()==dept1.getDeputyuserid() || dept.getDeputyuserid()==dept1.getSecretaryuserid()){
                        String str = "";
                        /*修改相应的部门信息*/
                        Dept dept3 = new Dept();//需要修改的部门
                        dept3.setId(depid);
                        //如果是正职
                        if (dept.getDeputyuserid()==dept1.getPrincipaluserid()) {
                            dept3.setPrincipaluserid(dept.getDeputyuserid());
                            str = "部门正职";
                        }
                        //如果是副职
                        if (dept.getDeputyuserid()==dept1.getDeputyuserid()) {
                            dept3.setDeputyuserid(dept.getDeputyuserid());
                            str = "部门副职";
                        }
                        //如果是秘书
                        if (dept.getDeputyuserid()==dept1.getSecretaryuserid()) {
                            dept3.setSecretaryuserid(dept.getDeputyuserid());
                            str = "部门秘书";
                        }
                        iDeptService.modifyByDeptidAndOtherinformation(dept3);
                        /*添加相应部门的部门信息修改日志*/
                        DeptLog deptLog1 = new DeptLog();
                        deptLog1.setChangeinformation(str);
                        deptLog1.setDeptid(depid);
                        deptLog1.setBeforeinformation(iUserService.getById(dept.getDeputyuserid()).getTruename());
                        deptLog1.setAfterinformation(null);
                        deptLog1.setChangereason("系统需要");
                        deptLog1.setChangedate(changedate);
                        deptLog1.setTransactoruserid(deptLog.getTransactoruserid());
                        iDeptLogService.addOne(deptLog1);
                    }
                    //根据ID修改员工信息,此时该员工是该部门的普通员工
                    PersonalInformation per = new PersonalInformation();
                    per.setId(personalInformation.getId());
                    per.setDepid(dept2.getId());
                    iPersonalInformationService.modifyOne(per);
                    //添加人事信息修改日志，此时该员工是无部门员工
                    ChangeInformation changeInformation = new ChangeInformation();
                    changeInformation.setChangeinformation("部门");//变更项目
                    changeInformation.setChangeduserid(dept.getPrincipaluserid());//变更姓名
                    changeInformation.setBeforeinformation(iDeptService.queryOneDepByDepid(depid).getDepname());//变更前内容
                    changeInformation.setAfterinformation(dept.getDepname());//变更后内容
                    changeInformation.setChangereason("系统需要");//变更原因
                    changeInformation.setChangedate(changedate);//变更日期
                    changeInformation.setTransactoruserid(deptLog.getTransactoruserid());//变更人
                    iChangeInformationService.addOne(changeInformation);
                }else {
                    if(dept.getDeputyuserid()==dept1.getPrincipaluserid() || dept.getDeputyuserid()==dept1.getSecretaryuserid()){
                        String str = "";
                        /*修改相应的部门信息*/
                        Dept dept3 = new Dept();//需要修改的部门
                        dept3.setId(depid);
                        //如果是正职
                        if (dept.getDeputyuserid()==dept1.getPrincipaluserid()) {
                            dept3.setPrincipaluserid(dept.getDeputyuserid());
                            str = "部门正职";
                        }
                        //如果是秘书
                        if (dept.getDeputyuserid()==dept1.getSecretaryuserid()) {
                            dept3.setSecretaryuserid(dept.getDeputyuserid());
                            str = "部门秘书";
                        }
                        iDeptService.modifyByDeptidAndOtherinformation(dept3);
                        /*添加相应部门的部门信息修改日志*/
                        DeptLog deptLog1 = new DeptLog();
                        deptLog1.setChangeinformation(str);
                        deptLog1.setDeptid(depid);
                        deptLog1.setBeforeinformation(iUserService.getById(dept.getDeputyuserid()).getTruename());
                        deptLog1.setAfterinformation(null);
                        deptLog1.setChangereason("系统需要");
                        deptLog1.setChangedate(changedate);
                        deptLog1.setTransactoruserid(deptLog.getTransactoruserid());
                        iDeptLogService.addOne(deptLog1);
                    }
                }
                b = true;
                deptLog.setChangeinformation("部门副职");
                User deputyuser = iUserService.getById(dept2.getDeputyuserid());
                if (deputyuser!=null) {
                    deptLog.setBeforeinformation(iUserService.getById(dept2.getDeputyuserid()).getTruename());
                } else {
                    deptLog.setBeforeinformation("原副职信息已经不存在！");
                }
                deptLog.setAfterinformation(iUserService.getById(dept.getDeputyuserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }
            if (dept2.getSecretaryuserid()!=null && dept.getSecretaryuserid()!=null && !dept2.getSecretaryuserid().toString().equals(dept.getSecretaryuserid().toString())){
                PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(dept.getSecretaryuserid());
                Integer depid = personalInformation.getDepid();
                Dept dept1 = iDeptService.queryOneDepByDepid(depid);//其他部门
                if(depid!=dept2.getId()){
                    //说明此员工非本部门，需要先将该员工的部门信息改为该部门信息,并添加人事信息修改日志,
                    // 如果还在其他部门信息担任正职、副职、秘书等职位，则需要先将相应的部门相应信息修改一下,并添加相应部门的部门信息修改日志
                    if (dept1!=null) {
                        if(dept.getSecretaryuserid()==dept1.getPrincipaluserid() || dept.getSecretaryuserid()==dept1.getDeputyuserid() || dept.getSecretaryuserid()==dept1.getSecretaryuserid()){
                            String str = "";
                            /*修改相应的部门信息*/
                            Dept dept3 = new Dept();//需要修改的部门
                            dept3.setId(depid);
                            //如果是正职
                            if (dept.getSecretaryuserid()==dept1.getPrincipaluserid()) {
                                dept3.setPrincipaluserid(dept.getSecretaryuserid());
                                str = "部门正职";
                            }
                            //如果是副职
                            if (dept.getSecretaryuserid()==dept1.getDeputyuserid()) {
                                dept3.setDeputyuserid(dept.getSecretaryuserid());
                                str = "部门副职";
                            }
                            //如果是秘书
                            if (dept.getSecretaryuserid()==dept1.getSecretaryuserid()) {
                                dept3.setSecretaryuserid(dept.getSecretaryuserid());
                                str = "部门秘书";
                            }
                            iDeptService.modifyByDeptidAndOtherinformation(dept3);
                            /*添加相应部门的部门信息修改日志*/
                            DeptLog deptLog1 = new DeptLog();
                            deptLog1.setChangeinformation(str);
                            deptLog1.setDeptid(depid);
                            deptLog1.setBeforeinformation(iUserService.getById(dept.getSecretaryuserid()).getTruename());
                            deptLog1.setAfterinformation(null);
                            deptLog1.setChangereason("系统需要");
                            deptLog1.setChangedate(changedate);
                            deptLog1.setTransactoruserid(deptLog.getTransactoruserid());
                            iDeptLogService.addOne(deptLog1);
                        }
                    }
                    //根据ID修改员工信息,此时该员工是该部门的普通员工
                    PersonalInformation per = new PersonalInformation();
                    per.setId(personalInformation.getId());
                    per.setDepid(dept2.getId());
                    iPersonalInformationService.modifyOne(per);
                    //添加人事信息修改日志，此时该员工是无部门员工
                    ChangeInformation changeInformation = new ChangeInformation();
                    changeInformation.setChangeinformation("部门");//变更项目
                    changeInformation.setChangeduserid(dept.getPrincipaluserid());//变更姓名
                    if (iDeptService.queryOneDepByDepid(depid)!=null) {
                        changeInformation.setBeforeinformation(iDeptService.queryOneDepByDepid(depid).getDepname());//变更前内容
                    } else {
                        changeInformation.setBeforeinformation("此部门已经不存在！");//变更前内容
                    }
                    changeInformation.setAfterinformation(dept.getDepname());//变更后内容
                    changeInformation.setChangereason("系统需要");//变更原因
                    changeInformation.setChangedate(changedate);//变更日期
                    changeInformation.setTransactoruserid(deptLog.getTransactoruserid());//变更人
                    iChangeInformationService.addOne(changeInformation);
                }else {
                    if (dept1!=null) {
                        if(dept.getSecretaryuserid()==dept1.getPrincipaluserid() || dept.getSecretaryuserid()==dept1.getDeputyuserid()){
                            String str = "";
                            /*修改相应的部门信息*/
                            Dept dept3 = new Dept();//需要修改的部门
                            dept3.setId(depid);
                            //如果是正职
                            if (dept.getSecretaryuserid()==dept1.getPrincipaluserid()) {
                                dept3.setPrincipaluserid(dept.getSecretaryuserid());
                                str = "部门正职";
                            }
                            //如果是副职
                            if (dept.getSecretaryuserid()==dept1.getDeputyuserid()) {
                                dept3.setDeputyuserid(dept.getSecretaryuserid());
                                str = "部门副职";
                            }
                            iDeptService.modifyByDeptidAndOtherinformation(dept3);
                            /*添加相应部门的部门信息修改日志*/
                            DeptLog deptLog1 = new DeptLog();
                            deptLog1.setChangeinformation(str);
                            deptLog1.setDeptid(depid);
                            deptLog1.setBeforeinformation(iUserService.getById(dept.getSecretaryuserid()).getTruename());
                            deptLog1.setAfterinformation(null);
                            deptLog1.setChangereason("系统需要");
                            deptLog1.setChangedate(changedate);
                            deptLog1.setTransactoruserid(deptLog.getTransactoruserid());
                            iDeptLogService.addOne(deptLog1);
                        }
                    }
                }
                b = true;
                deptLog.setChangeinformation("部门秘书");
                User secretaryuser = iUserService.getById(dept2.getSecretaryuserid());
                if (secretaryuser!=null) {
                    deptLog.setBeforeinformation(iUserService.getById(dept2.getSecretaryuserid()).getTruename());
                } else {
                    deptLog.setBeforeinformation("原秘书信息已经不存在！");
                }
                deptLog.setAfterinformation(iUserService.getById(dept.getSecretaryuserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }
            if (!dept.getDutydescription().equals(dept2.getDutydescription())){
                b = true;
                deptLog.setChangeinformation("部门职责");
                deptLog.setBeforeinformation(dept2.getDutydescription());
                deptLog.setAfterinformation(dept.getDutydescription());
                iDeptLogService.addOne(deptLog);
            }
            if (!dept.getDepdescription().equals(dept2.getDepdescription())){
                b = true;
                deptLog.setChangeinformation("部门概述");
                deptLog.setBeforeinformation(dept2.getDepdescription());
                deptLog.setAfterinformation(dept.getDepdescription());
                iDeptLogService.addOne(deptLog);
            }

            if (b) {
                List<HRset> hRsetFunctionaltypeList = ihRsetService.queryByConditions(new HRset("functionaltype", dept.getFunctionaltype()));
                if (hRsetFunctionaltypeList!=null && hRsetFunctionaltypeList.size()==1) {
                    dept.setFunctionaltypeid(hRsetFunctionaltypeList.get(0).getId());
                }
                List<HRset> hRsetDeptypeList = ihRsetService.queryByConditions(new HRset("deptype", dept.getDeptype()));
                if (hRsetDeptypeList!=null && hRsetDeptypeList.size()==1) {
                    dept.setDeptypeid(hRsetDeptypeList.get(0).getId());
                }

                //添加公司名称
                //首先根据"ELEX"模糊查询出所有的公司
                //根据公司编号的后两位数字判断是哪个公司
                //最后添加公司名称
                List<Dept> elex = iDeptService.queryDeptsByDepcode("ELEX");
                Boolean validateCP = true;
                for (Dept d:elex
                        ) {
                    int l = d.getDepcode().length();
                    if(dept.getDepcode().substring(0,2).equals(d.getDepcode().substring(l-2,l))){
                        dept.setCompanyname(d.getDepname());
                        validateCP = false;
                        break;
                    }
                }
                if(validateCP){
                    dept.setCompanyname(dept.getDepname());
                }
                iDeptService.modifyOne(dept);
            } else {
                return RespUtil.successResp("500","没有需要修改的部门信息！",null) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("400","提交失败！",null) ;
        }
        return RespUtil.successResp("200","提交成功！",dept) ;
    }

    /**
     *@Author:ShiYun;
     *@Description:删除部门信息
     *@Date: 13:39 2018\5\2 0002
     */
    @RequestMapping("/deleteDeptsById")
    @ResponseBody
    public String deleteDeptsById(@RequestParam("id") Integer id){
        try {
            List<Integer> depids = new ArrayList<Integer>();
            depids.add(id);
            depids = getDeptids(id,depids);
            if (depids.size()!=0) {
                for (int i=0;i<depids.size();i++){
                    iDeptService.removeOne(depids.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
        return "删除成功！";
    }
    // 通过递归获得需要删除的所有部门ID
    public List<Integer> getDeptids(Integer parentdepid,List<Integer> list){
        List<Dept> depts = iDeptService.queryByParentId(parentdepid);
        if(depts.size()!=0){
            for (Integer i = 0;i< depts.size();i++){
                list.add(depts.get(i).getId());
                list = getDeptids(depts.get(i).getId(), list);
            }
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询部门日志信息
     *@Date: 17:13 2018\5\2 0002
     */
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
        if(list.size()!=0){
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

    /**
     *@Author:ShiYun;
     *@Description:查询部门日志（不分页）
     *@Date: 9:18 2018\5\24 0024
     */
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

    /**
     *@Author:ShiYun;
     *@Description:删除部门日志信息
     *@Date: 10:50 2018\5\24 0024
     */
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

    /**
     *@Author:ShiYun;
     *@Description:数据的导入(日志)
     *@Date: 15:09 2018\5\7 0007
     */
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
                if (users.size()!=0) {
                    deptLog.setTransactoruserid(users.get(0).getId());
                }
                iDeptLogService.addOne(deptLog);
            }
        } catch (Exception e) {
            return "数据导入失败！";
        }
        return "数据导入成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:部门排序--》根据父节点查询所有的同级数据
     *@Date: 11:15 2018\6\15 0015
     */
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
            return null;
        }
        for(int i = 0;i<depts.size();i++){
            TitleAndCode titleAndCode = new TitleAndCode();
            titleAndCode.setTitle(depts.get(i).getDepname());
            titleAndCode.setCode(depts.get(i).getOrdercode());
            list.add(titleAndCode);
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:对数据进行排序
     *@Date: 14:52 2018\6\15 0015
     */
    @RequestMapping("/submitSortdata")
    @ResponseBody
    public DeptTree submitSortdata(
            @RequestParam("sortdata") String sortdata,
            @RequestParam("title") String title,
            @RequestParam("deptTree") String deptTree
    ){
        List<TitleAndCode> list = null;
        try {
            list = JSONObject.parseArray(sortdata, TitleAndCode.class);
        } catch (Exception e) {
            return null;
        }
        //在数据库中将顺序码保存一下
        for (TitleAndCode t:list
             ) {
            Dept dept = iDeptService.queryOneDepByDepname(t.getTitle()).get(0);
            Dept dept1 = new Dept();
            dept1.setId(dept.getId());
            dept1.setOrdercode(t.getCode());
            iDeptService.modifyOne(dept1);
        }
        //先将树形结构数据查出来
        List<Dept> depts = iDeptService.queryByParentId(null);
        DeptTree deptTree1 = JSONObject.parseObject(deptTree,new TypeReference<DeptTree>(){});
        //先排序
        if(title!=null && !"".equals(title) && list.size()>=2){
            list.sort(new Comparator<TitleAndCode>() {
                @Override
                public int compare(TitleAndCode o1, TitleAndCode o2) {
                    return o1.getCode().compareTo(o2.getCode());
                }
            });
        }
        //再换节点
        //首先将父节点是定点的情况去除
        if(deptTree1.getTitle().equals(title)){
            List<DeptTree> old = deptTree1.getChildren();
            List<DeptTree> renew = new ArrayList<>();
            for (TitleAndCode titleAndCode:list
                    ) {
                for (DeptTree dtt:old
                        ) {
                    if(dtt.getTitle().equals(titleAndCode.getTitle())){
                        renew.add(dtt);
                    }
                }
            }
            deptTree1.setChildren(renew);
            return deptTree1;
        }
        DeptTree newDepttree = getNewDepttree(deptTree1, title, list);
        return newDepttree;
    }

    /**
     *@Author:ShiYun;
     *@Description:同级排序
     *@Date: 18:41 2018\6\15 0015
     */
    public DeptTree getNewDepttree(DeptTree deptTree,String title,List<TitleAndCode> list){
        for(int i = 0;i<deptTree.getChildren().size();i++){
            if(deptTree.getChildren().get(i).getTitle().equals(title)){
                List<DeptTree> old = deptTree.getChildren().get(i).getChildren();
                List<DeptTree> renew = new ArrayList<>();
                for (TitleAndCode titleAndCode:list
                     ) {
                    for (DeptTree dtt:old
                            ) {
                        if(dtt.getTitle().equals(titleAndCode.getTitle())){
                            renew.add(dtt);
                        }
                    }
                }
                deptTree.getChildren().get(i).setChildren(renew);
                return deptTree;
            }else {
                if(deptTree.getChildren().get(i).getChildren().size()>0){
                    DeptTree parentDepttree = getNewDepttree(deptTree.getChildren().get(i), title,list);
                    if(parentDepttree!=null){
                        deptTree.getChildren().get(i).setChildren(parentDepttree.getChildren());
                        return deptTree;
                    }
                }
            }
        }
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:人事的管理看板
     *@Date: 14:16 2018\6\28 0028
     */
    @RequestMapping("/queryHRManageCard")
    @ResponseBody
    public Object queryHRManageCard(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname
    ){
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname="江苏博智软件科技股份有限公司";break;
            case "南京总部":companyname="江苏博智软件科技南京总部";break;
            case "上海臻相":companyname="上海臻相软件科技有限公司";break;
            case "贵州中科":companyname="贵州中科博智科技有限公司";break;
        }

        Object hrManageCard = iDeptService.getHRManageCard(companyname,sdate,edate);
        return hrManageCard;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得总人数(edate时间点的在职总人数)
     *@Date: 15:53 2018\8\15 0015
     */
    @RequestMapping("/queryHRManageCard2")
    @ResponseBody
    public Object queryHRManageCard2(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page
    ) throws ParseException {
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname="江苏博智软件科技股份有限公司";break;
            case "南京总部":companyname="江苏博智软件科技南京总部";break;
            case "上海臻相":companyname="上海臻相软件科技有限公司";break;
            case "贵州中科":companyname="贵州中科博智科技有限公司";break;
        }

        Object hrManageCard = iDeptService.getHRManageCard2(companyname,rows,page,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = this.getOnePersonalinformation(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得入职总人数(edate时间点的入职总人数)
     *@Date: 15:55 2018\8\15 0015
     */
    @RequestMapping("/queryHRManageCard3")
    @ResponseBody
    public Object queryHRManageCard3(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page
    ) throws ParseException {
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname="江苏博智软件科技股份有限公司";break;
            case "南京总部":companyname="江苏博智软件科技南京总部";break;
            case "上海臻相":companyname="上海臻相软件科技有限公司";break;
            case "贵州中科":companyname="贵州中科博智科技有限公司";break;
        }

        Object hrManageCard = iDeptService.getHRManageCard3(companyname,rows,page,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = this.getOnePersonalinformation(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得离职总人数(edate时间点的离职总人数)
     *@Date: 15:56 2018\8\15 0015
     */
    @RequestMapping("/queryHRManageCard4")
    @ResponseBody
    public Object queryHRManageCard4(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("companyname")String companyname,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page
    ) throws ParseException {
        //为了和高晓飞统一参数名称，其实这是不科学的，后期公司名称修改或添加新公司只需要将这段代码注释掉
        switch (companyname){
            case "全部":companyname="江苏博智软件科技股份有限公司";break;
            case "南京总部":companyname="江苏博智软件科技南京总部";break;
            case "上海臻相":companyname="上海臻相软件科技有限公司";break;
            case "贵州中科":companyname="贵州中科博智科技有限公司";break;
        }

        Object hrManageCard = iDeptService.getHRManageCard4(companyname,rows,page,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = this.getOnePersonalinformation(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 15:57 2018\8\15 0015
     */
    @RequestMapping("/queryHRManageCard5")
    @ResponseBody
    public Object queryHRManageCard5(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page,
            @RequestParam("depid")Integer depid
    ) throws ParseException {
        Object hrManageCard = iDeptService.getHRManageCard5(rows,page,depid,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = this.getOnePersonalinformation(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 15:58 2018\8\15 0015
     */
    @RequestMapping("/queryHRManageCard6")
    @ResponseBody
    public Object queryHRManageCard6(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page,
            @RequestParam("depid")Integer depid
    ) throws ParseException {
        Object hrManageCard = iDeptService.getHRManageCard6(rows,page,depid,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = this.getOnePersonalinformation(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 15:59 2018\8\15 0015
     */
    @RequestMapping("/queryHRManageCard7")
    @ResponseBody
    public Object queryHRManageCard7(
            @RequestParam("sdate")String sdate,
            @RequestParam("edate")String edate,
            @RequestParam("rows")Integer rows,
            @RequestParam("page")Integer page,
            @RequestParam("depid")Integer depid
    ) throws ParseException {
        Object hrManageCard = iDeptService.getHRManageCard7(rows,page,depid,sdate,edate);
        Resp resp = (Resp) hrManageCard;
        if(resp.getBody()!=null){
            PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp.getBody();
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:pageHelper.getList()
                    ) {
                per = this.getOnePersonalinformation(per.getId());
                personalInformationList.add(per);
            }
            pageHelper.setList(personalInformationList);
            resp.setBody(pageHelper);
        }else {
            return resp;
        }
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:IOS的通讯录数据
     *@Date: 10:52 2018\6\29 0029
     */
    @RequestMapping("/queryIOSData")
    @ResponseBody
    public HRManageCard queryIOSData(
            Integer deptid
    ){
        HRManageCard paramMap1 = iDeptService.getParamMap1(deptid);
        return paramMap1;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加部门的时候校验部门名称
     *@Date: 10:08 2018\7\16 0016
     */
    @RequestMapping("/validateDeptnameForAddDept")
    @ResponseBody
    public Boolean validateDeptnameForAddDept(
            @RequestParam("deptname") String deptname
    ){
        Dept dept = iDeptService.queryOneDepByDepname(deptname).get(0);
        if(dept!=null){
            return true;
        }else{
            return  false;
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:添加部门的时候校验部门编号
     *@Date: 10:12 2018\7\16 0016
     */
    @RequestMapping("/validateDeptcodeForAddDept")
    @ResponseBody
    public Boolean validateDeptcodeForAddDept(
            @RequestParam("deptcode") String deptcode
    ){
        Dept dept = iDeptService.queryOneByDepcode(deptcode);
        if(dept!=null){
            return true;
        }else {
            return false;
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:根据perid查询信息
     *@Date: 17:36 2018\5\17 0017
     */
    public  PersonalInformation getOnePersonalinformation(Integer personalInformationId) throws ParseException {
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById2(personalInformationId);
        if(personalInformation==null){
            return null;
        }
        //1.获得User信息
        User user = iUserService.getById(personalInformation.getUserid());
        if (user!=null) {
            personalInformation.setIsactive(user.getIsactive());
            personalInformation.setUsername(user.getUsername());
            personalInformation.setTruename(user.getTruename());
        }
        //2.获得基本信息
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation.getBaseinformationid());
        if (baseInformation!=null) {
            personalInformation.setUserphoto(baseInformation.getUserphoto());
            personalInformation.setIdphoto1(baseInformation.getIdphoto1());
            personalInformation.setIdphoto2(baseInformation.getIdphoto2());
            personalInformation.setEnglishname(baseInformation.getEnglishname());
            personalInformation.setIdcode(baseInformation.getIdcode());
            personalInformation.setBirthday(baseInformation.getBirthday());
            try {
                personalInformation.setAge(IDcodeUtil.getAge(baseInformation.getBirthday()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            personalInformation.setConstellation(baseInformation.getConstellation());
            personalInformation.setChinesecs(baseInformation.getChinesecs());
            if (ihRsetService.queryById(baseInformation.getRaceid())!=null) {
                personalInformation.setRace(ihRsetService.queryById(baseInformation.getRaceid()).getDatavalue());
            }
            personalInformation.setMarriage(baseInformation.getMarriage());
            if (ihRsetService.queryById(baseInformation.getChildrenid())!=null) {
                personalInformation.setChildren(ihRsetService.queryById(baseInformation.getChildrenid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZzmmid())!=null) {
                personalInformation.setZzmm(ihRsetService.queryById(baseInformation.getZzmmid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZgxlid())!=null) {
                personalInformation.setZgxl(ihRsetService.queryById(baseInformation.getZgxlid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getByyxid())!=null) {
                personalInformation.setByyx(ihRsetService.queryById(baseInformation.getByyxid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getSxzyid())!=null) {
                personalInformation.setSxzy(ihRsetService.queryById(baseInformation.getSxzyid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getPyfsid())!=null) {
                personalInformation.setPyfs(ihRsetService.queryById(baseInformation.getPyfsid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getFirstlaid())!=null) {
                personalInformation.setFirstla(ihRsetService.queryById(baseInformation.getFirstlaid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getElselaid())!=null) {
                personalInformation.setElsela(ihRsetService.queryById(baseInformation.getElselaid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getPosttitleid())!=null) {
                personalInformation.setPosttitle(ihRsetService.queryById(baseInformation.getPosttitleid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZyzstypeid())!=null) {
                personalInformation.setZyzstype(ihRsetService.queryById(baseInformation.getZyzstypeid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZyzsnameid())!=null) {
                personalInformation.setZyzsname(ihRsetService.queryById(baseInformation.getZyzsnameid()).getDatavalue());
            }
            personalInformation.setFirstworkingtime(baseInformation.getFirstworkingtime());
            if (baseInformation.getFirstworkingtime()!=null && !"".equals(baseInformation.getFirstworkingtime())) {
                try {
                    personalInformation.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ihRsetService.queryById(baseInformation.getParentcompanyid())!=null) {
                personalInformation.setParentcompany(ihRsetService.queryById(baseInformation.getParentcompanyid()).getDatavalue());
            }
        }
        //3.获得管理信息
        ManageInformation manageInformation = iManageInformationService.queryOneById(personalInformation.getManageinformationid());
        if (manageInformation!=null) {
            if (iDeptService.queryOneDepByDepid(personalInformation.getDepid())!=null) {
                personalInformation.setCompany(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getCompanyname());
                personalInformation.setDepname(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getDepname());
                if (iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid())!=null) {
                    personalInformation.setPrincipaltruename(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getTruename());
                    if (iPersonalInformationService.queryOneByUserid(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getId())!=null) {
                        personalInformation.setPrincipalemployeenumber(iPersonalInformationService.queryOneByUserid(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getId()).getEmployeenumber());
                    }
                }
            }
            List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(personalInformationId);
            List<String> strs = new ArrayList<>();
            List<Integer> postids = new ArrayList<>();
            if (perAndPostRs.size()!=0) {
                for(PerAndPostRs perAndPost:perAndPostRs){
                    strs.add(iPostService.queryOneByPostid(perAndPost.getPostid()).getPostname());
                    postids.add(perAndPost.getPostid());
                }
            }
            personalInformation.setPostnames(IDcodeUtil.getArrayToString(strs,";"));
            personalInformation.setPostids(postids);
            if (ihRsetService.queryById(manageInformation.getRankid())!=null) {
                personalInformation.setZj(ihRsetService.queryById(manageInformation.getRankid()).getDatavalue());
            }
            personalInformation.setEntrydate(manageInformation.getEntrydate());
            try {
                personalInformation.setSn(IDcodeUtil.getCompanyAge(manageInformation.getEntrydate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            personalInformation.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
            if (ihRsetService.queryById(manageInformation.getEmployeetypeid())!=null) {
                personalInformation.setEmployeetype(ihRsetService.queryById(manageInformation.getEmployeetypeid()).getDatavalue());
            }
        }
        //获得成本信息
        CostInformation costInformation = iCostInformationService.queryOneById(personalInformation.getCostinformationid());
        if (costInformation!=null) {
            if (ihRsetService.queryById(costInformation.getSalarystandardid())!=null) {
                personalInformation.setSalary(ihRsetService.queryById(costInformation.getSalarystandardid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getSsbid())!=null) {
                personalInformation.setSsb(ihRsetService.queryById(costInformation.getSsbid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getSsbgscdid())!=null) {
                personalInformation.setSsbgscd(ihRsetService.queryById(costInformation.getSsbgscdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getSsbgrcdid())!=null) {
                personalInformation.setSsbgrcd(ihRsetService.queryById(costInformation.getSsbgrcdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getGjjid())!=null) {
                personalInformation.setGjj(ihRsetService.queryById(costInformation.getGjjid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getGjjgscdid())!=null) {
                personalInformation.setGjjgscd(ihRsetService.queryById(costInformation.getGjjgscdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getGjjgrcdid())!=null) {
                personalInformation.setGjjgrcd(ihRsetService.queryById(costInformation.getGjjgrcdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getKhhid())!=null) {
                personalInformation.setKhh(ihRsetService.queryById(costInformation.getKhhid()).getDatavalue());
            }
            personalInformation.setSalaryaccount(costInformation.getSalaryaccount());
            if (ihRsetService.queryById(costInformation.getSbjndid())!=null) {
                personalInformation.setSbjnd(ihRsetService.queryById(costInformation.getSbjndid()).getDatavalue());
            }
            personalInformation.setSbcode(costInformation.getSbcode());
            personalInformation.setGjjcode(costInformation.getGjjcode());
        }
        //获得其他信息
        OtherInformation otherInformation = iOtherInformationService.queryOneById(personalInformation.getOtherinformationid());
        if (otherInformation!=null) {
            if (ihRsetService.queryById(personalInformation.getTelphoneid())!=null) {
                personalInformation.setTelphone(ihRsetService.queryById(personalInformation.getTelphoneid()).getDatavalue());
            }
            personalInformation.setPrivateemail(otherInformation.getPrivateemail());
            personalInformation.setCompanyemail(otherInformation.getCompanyemail());
            personalInformation.setEmergencycontract(otherInformation.getEmergencycontract());
            if (ihRsetService.queryById(otherInformation.getEmergencyrpid())!=null) {
                personalInformation.setEmergencyrp(ihRsetService.queryById(otherInformation.getEmergencyrpid()).getDatavalue());
            }
            personalInformation.setEmergencyphone(otherInformation.getEmergencyphone());
            personalInformation.setAddress(otherInformation.getAddress());
            personalInformation.setRemark(otherInformation.getRemark());
        }
        return personalInformation;
    }
}
