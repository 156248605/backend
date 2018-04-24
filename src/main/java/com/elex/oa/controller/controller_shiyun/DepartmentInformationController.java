package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.entity_shiyun.DeptTree;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IDeptService;
import com.elex.oa.service.service_shiyun.IPersonalInformationService;
import com.elex.oa.service.service_shiyun.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门名称查询部门信息
     *@Date: 15:28 2018\4\12 0012
     */
    @RequestMapping("/queryDeptByDepname")
    @ResponseBody
    public Dept queryDeptByDepname(
            @RequestParam("depname") String depname
    ){
        Dept dept = iDeptService.queryOneDepByDepname(depname);
        return dept;
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
    public String addOneDepartment(Dept dept){
        Integer depid = iDeptService.addOne(dept);
        return "提交成功！";
    }
}
