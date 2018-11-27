package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.service.hr_service.IDeptService;
import com.elex.oa.service.hr_service.IPersonalInformationService;
import com.elex.oa.service.hr_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:用户
 * @Date:Created in  11:57 2018\4\12 0012
 * @Modify By:
 */
@Controller
@CrossOrigin
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IDeptService iDeptService;

    /**
     * @Author: shiyun
     * @Description: 将tb_hr_personalinformation表中employeenumber字段添加到tb_id_user表中
     * @Date  2018\11\26 0026 17:10
     * @Param
     * @return
     **/

    @RequestMapping("/updateEmployeenumber")
    @ResponseBody
    public String updateEmployeenumber(){
        Boolean aBoolean = true;
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryAllByNull();
        for (PersonalInformation per:personalInformations){
            aBoolean = iUserService.modifyUser(new User(per.getUserid(), per.getEmployeenumber()));
        }
        return aBoolean?"Success":"Error";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询一个用户信息
     *@Date: 11:58 2018\4\12 0012
     */
    @RequestMapping("/queryUserByUserId")
    @ResponseBody
    public User queryUserByUserId(
            @RequestParam("userid") Integer userid
    ){
        User user = iUserService.getById(userid);
        return user;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有用户信息
     *@Date: 15:38 2018\4\16 0016
     */
    @RequestMapping("/queryAllUsers")
    @ResponseBody
    public List<User> queryAllUsers(){
        List<User> users = iUserService.selectAll();
        return users;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的在职用户
     *@Date: 10:03 2018\8\21 0021
     */
    @RequestMapping("/queryAllServings")
    @ResponseBody
    public List<PersonalInformation> queryAllServings(){
        List<User> users = iUserService.queryAllServings();
        List<PersonalInformation> personalInformationList = new ArrayList<>();
        for (User user:users
             ) {
            PersonalInformation per = iPersonalInformationService.queryOneByUserid(user.getId());
            if (per!=null) {
                Dept dept;
                if (per.getDepid()!=null) {
                    dept = iDeptService.queryOneDepByDepid(per.getDepid());
                    if (dept!=null) {
                        per.setDepname(dept.getDepname());
                    } else {
                        per.setDepname("此员工没有员工！");
                    }
                }
                per.setTruename(user.getTruename());
                personalInformationList.add(per);
            }else {
                continue;
            }
        }
        return personalInformationList;
    }

}
