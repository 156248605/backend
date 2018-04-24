package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
