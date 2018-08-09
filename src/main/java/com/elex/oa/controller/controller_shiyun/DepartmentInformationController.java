package com.elex.oa.controller.controller_shiyun;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.project.ProjectBoardService;
import com.elex.oa.service.service_shiyun.*;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    IHRsetFunctionalTypeService ihRsetFunctionalTypeService;

    @Autowired
    IChangeInformationService iChangeInformationService;

    @Autowired
    private ProjectBoardService projectBoardService;

    @Autowired
    IHRsetDeptypeService ihRsetDeptypeService;

    /**
     *@Author:ShiYun;
     *@Description:根据部门名称获取部门对象
     *@Date: 13:20 2018\3\16 0016
     */
    @RequestMapping("/queryOneDepByDepname")
    @ResponseBody
    public Dept queryOneDepByDepname(@RequestParam("title") String depname){
        Dept dept = new Dept();
        dept = iDeptService.queryOneDepByDepname(depname);
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
            principaluser = iUserService.getById(dept.getPrincipaluserid());
        }else {
            principaluser.setTruename("");
        }
        if(dept.getDeputyuserid()!=null){
            deputyuser = iUserService.getById(dept.getDeputyuserid());
        }else {
            deputyuser.setTruename("");
        }
        if(dept.getSecretaryuserid()!=null){
            secretaryuser = iUserService.getById(dept.getSecretaryuserid());
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

        HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(dept.getFunctionaltypeid());
        if (hRsetFunctionalType!=null) {
            dept.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
        }
        HRsetDeptype hRsetDeptype = ihRsetDeptypeService.queryById(dept.getDeptypeid());
        if(hRsetDeptype!=null){
            dept.setDeptype(hRsetDeptype.getDeptype());
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
            HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(depts.get(i).getFunctionaltypeid());
            if (hRsetFunctionalType!=null) {
                depts.get(i).setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
            }
            HRsetDeptype hRsetDeptype = ihRsetDeptypeService.queryById(depts.get(i).getDeptypeid());
            if(hRsetDeptype!=null){
                depts.get(i).setDeptype(hRsetDeptype.getDeptype());
            }
        }
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
    public String addOneDepartment(
            Dept dept,
            @RequestParam("transactorusername") String transactorusername
    ){
        //先校验部门名称是否存在
        Dept queryOneDepByDepname = iDeptService.queryOneDepByDepname(dept.getDepname());
        if(queryOneDepByDepname!=null){
            return "部门名称已存在，请重新输入部门名称！";
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
        if (ihRsetFunctionalTypeService.queryByFuctionaltype(dept.getFunctionaltype())!=null) {
            dept.setFunctionaltypeid(ihRsetFunctionalTypeService.queryByFuctionaltype(dept.getFunctionaltype()).getId());
        }
        if(ihRsetDeptypeService.queryByDeptype(dept.getDeptype())!=null){
            dept.setDeptypeid(ihRsetDeptypeService.queryByDeptype(dept.getDeptype()).getId());
        }
        Integer depid = iDeptService.addOne(dept);
        //正职、副职、秘书的原部门信息修改、并添加相应的部门信息修改日志
        Integer principaluserid = dept.getPrincipaluserid();
        if(principaluserid!=null){
            //如果在其他部门任正职、副职、秘书的话，需先将原部门信息相应信息修改并添加部门信息修改日志
            PersonalInformation principalPer = iPersonalInformationService.queryOneByUserid(principaluserid);
            Dept dept1 = iDeptService.queryOneDepByDepid(principalPer.getDepid());
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
        Integer deputyuserid = dept.getDeputyuserid();
        if(deputyuserid!=null){
            //如果在其他部门任正职、副职、秘书的话，需先将原部门信息相应信息修改并添加部门信息修改日志
            PersonalInformation deputyPer = iPersonalInformationService.queryOneByUserid(deputyuserid);
            Dept dept1 = iDeptService.queryOneDepByDepid(deputyPer.getDepid());
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
        Integer secretaryuserid = dept.getSecretaryuserid();
        if(secretaryuserid!=null){
            //如果在其他部门任正职、副职、秘书的话，需先将原部门信息相应信息修改并添加部门信息修改日志
            PersonalInformation secretaryPer = iPersonalInformationService.queryOneByUserid(secretaryuserid);
            Dept dept1 = iDeptService.queryOneDepByDepid(secretaryPer.getDepid());
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
        return "提交成功！";
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
        if (ihRsetFunctionalTypeService.queryById(dept.getFunctionaltypeid())!=null) {
            dept.setFunctionaltype(ihRsetFunctionalTypeService.queryById(dept.getFunctionaltypeid()).getFunctionaltype());
        }
        if(ihRsetDeptypeService.queryById(dept.getDeptypeid())!=null){
            dept.setDeptype(ihRsetDeptypeService.queryById(dept.getDeptypeid()).getDeptype());
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
    public String updateOneDepartment(
            Dept dept,
            @RequestParam("transactorusername") String transactorusername
    ) {
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
            if (ihRsetFunctionalTypeService.queryById(dept2.getFunctionaltypeid())!=null) {
                dept2.setFunctionaltype(ihRsetFunctionalTypeService.queryById(dept2.getFunctionaltypeid()).getFunctionaltype());
            }

            if (!dept.getDepname().equals(dept2.getDepname())){
                Dept dept1 = iDeptService.queryOneDepByDepname(dept.getDepname());
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
            }if (dept2.getPrincipaluserid()!=null && !dept2.getPrincipaluserid().toString().equals(dept.getPrincipaluserid().toString())){
                PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(dept.getPrincipaluserid());
                Integer depid = personalInformation.getDepid();
                Dept dept1 = iDeptService.queryOneDepByDepid(depid);//其他部门
                if(depid!=dept2.getId()){
                    //说明此员工非本部门，需要先将该员工的部门信息改为该部门信息,并添加人事信息修改日志,
                    // 如果还在其他部门信息担任正职、副职、秘书等职位，则需要先将相应的部门相应信息修改一下,并添加相应部门的部门信息修改日志
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
                deptLog.setBeforeinformation(iUserService.getById(dept2.getPrincipaluserid()).getTruename());
                deptLog.setAfterinformation(iUserService.getById(dept.getPrincipaluserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }if (dept2.getDeputyuserid()!=null && !dept2.getDeputyuserid().toString().equals(dept.getDeputyuserid().toString())){
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
                deptLog.setBeforeinformation(iUserService.getById(dept2.getDeputyuserid()).getTruename());
                deptLog.setAfterinformation(iUserService.getById(dept.getDeputyuserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }if (dept2.getSecretaryuserid()!=null && !dept2.getSecretaryuserid().toString().equals(dept.getSecretaryuserid().toString())){
                PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(dept.getSecretaryuserid());
                Integer depid = personalInformation.getDepid();
                Dept dept1 = iDeptService.queryOneDepByDepid(depid);//其他部门
                if(depid!=dept2.getId()){
                    //说明此员工非本部门，需要先将该员工的部门信息改为该部门信息,并添加人事信息修改日志,
                    // 如果还在其他部门信息担任正职、副职、秘书等职位，则需要先将相应的部门相应信息修改一下,并添加相应部门的部门信息修改日志
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
                b = true;
                deptLog.setChangeinformation("部门秘书");
                deptLog.setBeforeinformation(iUserService.getById(dept2.getSecretaryuserid()).getTruename());
                deptLog.setAfterinformation(iUserService.getById(dept.getSecretaryuserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getDutydescription().equals(dept2.getDutydescription())){
                b = true;
                deptLog.setChangeinformation("部门职责");
                deptLog.setBeforeinformation(dept2.getDutydescription());
                deptLog.setAfterinformation(dept.getDutydescription());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getDepdescription().equals(dept2.getDepdescription())){
                b = true;
                deptLog.setChangeinformation("部门概述");
                deptLog.setBeforeinformation(dept2.getDepdescription());
                deptLog.setAfterinformation(dept.getDepdescription());
                iDeptLogService.addOne(deptLog);
            }


            if (b) {
                if (ihRsetFunctionalTypeService.queryByFuctionaltype(dept.getFunctionaltype())!=null) {
                    dept.setFunctionaltypeid(ihRsetFunctionalTypeService.queryByFuctionaltype(dept.getFunctionaltype()).getId());
                }
                if (ihRsetDeptypeService.queryByDeptype(dept.getDeptype())!=null) {
                    dept.setDeptypeid(ihRsetDeptypeService.queryByDeptype(dept.getDeptype()).getId());
                }
                iDeptService.modifyOne(dept);
            } else {
                return "没有需要修改的部门信息！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "提交失败！";
        }
        return "提交成功！";
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
                    deptLog.setDeptid(iDeptService.queryOneDepByDepname(deptLog.getDeptname()).getId());
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
            depts = iDeptService.queryByParentId(iDeptService.queryOneDepByDepname(title).getId());
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
            Dept dept = iDeptService.queryOneDepByDepname(t.getTitle());
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
    public Map<String,Object> queryHRManageCard(){
        HashMap<String, Object> hrManageCard = iDeptService.getHRManageCard();
        return hrManageCard;
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
        Dept dept = iDeptService.queryOneDepByDepname(deptname);
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
        Dept dept = iDeptService.queryOneDepByDepname(deptcode);
        if(dept!=null){
            return true;
        }else {
            return false;
        }
    }
}
