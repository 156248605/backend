package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.ChangeInformation;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IChangeInformationService;
import com.elex.oa.service.service_shiyun.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:变更信息
 * @Date:Created in  11:44 2018\4\13 0013
 * @Modify By:
 */
@Controller
@CrossOrigin
public class ChangeInformationController {
    @Autowired
    IChangeInformationService iChangeInformationService;
    @Autowired
    IUserService iUserService;

    /**
     *@Author:ShiYun;
     *@Description:变更信息的列表
     *@Date: 11:48 2018\4\13 0013
     */
    @RequestMapping("/queryChangeInformations")
    @ResponseBody
    public PageInfo<ChangeInformation> queryChangeInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            @RequestParam("changedtruename") String changedtruename,
            @RequestParam("changedinformation") String changedinformation,
            @RequestParam("changedate") String changedate
    ){
        ChangeInformation changeInformation = new ChangeInformation();
        User user1 = new User();
        user1.setTruename(changedtruename);
        if (iUserService.selectOne(user1)!=null) {
            changeInformation.setChangeduserid(iUserService.selectOne(user1).getId());
        }
        changeInformation.setChangedinformation(changedinformation);
        changeInformation.setChangedate(changedate);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",changeInformation);
        PageInfo<ChangeInformation> changeInformationPageInfo = iChangeInformationService.queryAll(paramMap);
        List<ChangeInformation> list = changeInformationPageInfo.getList();
        for(Integer i=0;i<list.size();i++){
            // 设置姓名
            User changeduser = iUserService.getById(list.get(i).getChangeduserid());
            list.get(i).setChangedtruename(changeduser.getTruename());
            // 设置办理人姓名
            User transactoruser = iUserService.getById(list.get(i).getTransactoruserid());
            list.get(i).setTransactortruename(transactoruser.getTruename());
        }
        changeInformationPageInfo.setList(list);
        return changeInformationPageInfo;
    }
}
