package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.service_shiyun.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
            Dept dept
    ){
        HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryByFuctionaltype(dept.getFunctionaltype());
        dept.setFunctionaltypeid(hRsetFunctionalType.getId());
        Integer depid = iDeptService.addOne(dept);
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
        HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(dept.getFunctionaltypeid());
        dept.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());
        return dept;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改部门信息
     *@Date: 9:55 2018\5\2 0002
     */
    @RequestMapping("/updateOneDepartment")
    @ResponseBody
    public String updateOneDepartment(Dept dept){
        try {
            Boolean b = false;

            // 添加部门日志
            DeptLog deptLog = new DeptLog();
            deptLog.setDeptid(dept.getId());
            deptLog.setChangereason("公司业务需要");// 修改的原因业务暂未开发，默认为固定值
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String changedate = simpleDateFormat.format(new Date());
            deptLog.setChangedate(changedate);
            deptLog.setTransactoruserid(1);//默认为管理员，实际从session中拿

            Dept dept2 = iDeptService.queryOneDepByDepid(dept.getId());
            HRsetFunctionalType hRsetFunctionalType = ihRsetFunctionalTypeService.queryById(dept2.getFunctionaltypeid());
            dept2.setFunctionaltype(hRsetFunctionalType.getFunctionaltype());

            if (!dept.getDepname().equals(dept2.getDepname())){
                b = true;
                deptLog.setChangeinformation("部门名称");
                deptLog.setBeforeinformation(dept2.getDepname());
                deptLog.setAfterinformation(dept.getDepname());
                iDeptLogService.addOne(deptLog);
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
            }if (!dept.getParentdepid().toString().equals(dept2.getParentdepid().toString())){
                b = true;
                deptLog.setChangeinformation("上级部门");
                deptLog.setBeforeinformation(iDeptService.queryOneDepByDepid(dept2.getParentdepid()).getDepname());
                deptLog.setAfterinformation(iDeptService.queryOneDepByDepid(dept.getParentdepid()).getDepname());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getPrincipaluserid().toString().equals(dept2.getPrincipaluserid().toString())){
                b = true;
                deptLog.setChangeinformation("部门正职");
                deptLog.setBeforeinformation(iUserService.getById(dept2.getPrincipaluserid()).getTruename());
                deptLog.setAfterinformation(iUserService.getById(dept.getPrincipaluserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getDeputyuserid().toString().equals(dept2.getDeputyuserid().toString())){
                b = true;
                deptLog.setChangeinformation("部门副职");
                deptLog.setBeforeinformation(iUserService.getById(dept2.getDeputyuserid()).getTruename());
                deptLog.setAfterinformation(iUserService.getById(dept.getDeputyuserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }if (!dept.getSecretaryuserid().toString().equals(dept2.getSecretaryuserid().toString())){
                b = true;
                deptLog.setChangeinformation("部门秘书");
                deptLog.setBeforeinformation(iUserService.getById(dept2.getDeputyuserid()).getTruename());
                deptLog.setAfterinformation(iUserService.getById(dept.getDeputyuserid()).getTruename());
                iDeptLogService.addOne(deptLog);
            }

            if (b) {
                HRsetFunctionalType hRsetFunctionalType1 = ihRsetFunctionalTypeService.queryByFuctionaltype(dept.getFunctionaltype());
                dept.setFunctionaltypeid(hRsetFunctionalType1.getId());
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
            @RequestParam("deptname") String deptname,
            @RequestParam("changeinformation") String changeinformation,
            @RequestParam("changedate") String changedate
    ){
        deptname = deptname.trim();
        changeinformation =  changeinformation.trim();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        DeptLog deptLog = new DeptLog();
        if (iDeptService.queryOneDepByDepname(deptname)!=null) {
            deptLog.setDeptid(iDeptService.queryOneDepByDepname(deptname).getId());
        }
        deptLog.setChangeinformation(changeinformation);
        deptLog.setChangedate(changedate);
        paramMap.put("entity",deptLog);
        PageInfo<DeptLog> deptLogPageInfo = iDeptLogService.queryByConditions(paramMap);
        List<DeptLog> list = deptLogPageInfo.getList();
        if(list.size()!=0){
            for (int i = 0;i< list.size();i++) {
                list.get(i).setDeptname(iDeptService.queryOneDepByDepid(list.get(i).getDeptid()).getDepname());
                list.get(i).setTransactortruename(iUserService.getById(list.get(i).getTransactoruserid()).getTruename());
            }
            deptLogPageInfo.setList(list);
        }
        return deptLogPageInfo;
    }

}
