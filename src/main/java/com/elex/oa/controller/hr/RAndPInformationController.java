package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.RAndPInformation;
import com.elex.oa.service.hr_service.IPersonalInformationService;
import com.elex.oa.service.hr_service.IRAndPInformationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:奖惩信息（WEB层）
 * @Date:Created in  9:34 2018\4\18 0018
 * @Modify By:
 */
@Controller
@CrossOrigin
public class RAndPInformationController {
    @Autowired
    IRAndPInformationService irAndPInformationService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;

    /**
     *@Author:ShiYun;
     *@Description:添加奖惩信息
     *@Date: 9:35 2018\4\18 0018
     */
    @RequestMapping("/addRAndPInformation")
    @ResponseBody
    public String addRAndPInformation(
            @RequestParam("userids") String userids,
            RAndPInformation rAndPInformation
    ){
        String[] split = userids.split(",");
        rAndPInformation.setTransactoruserid(1);//默认为管理做的
        for(Integer i=0;i<split.length;i++){
            rAndPInformation.setUserid(Integer.parseInt(split[i]));
            irAndPInformationService.addOne(rAndPInformation);
        }
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询奖惩名目(不分页)
     *@Date: 20:52 2018\4\18 0018
     */
    @RequestMapping("/queryRAndPProjects")
    @ResponseBody
    public List<RAndPInformation> queryRAndPProjects(){
        List<RAndPInformation> rAndPInformations = irAndPInformationService.queryAllRAndPProject();
        return rAndPInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:奖惩信息列表(分页)
     *@Date: 10:22 2018\4\19 0019
     */
    @RequestMapping("/queryRAndPInformations")
    @ResponseBody
    public PageInfo<RAndPInformation> queryRAndPInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            RAndPInformation rAndPInformation
    ){
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",rAndPInformation);

        PageInfo<RAndPInformation> rAndPInformationPageHelper = irAndPInformationService.queryByCondition(paramMap);
        return rAndPInformationPageHelper;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询奖惩信息
     *@Date: 16:14 2018\4\19 0019
     */
    @RequestMapping("/queryRAndPInformationById")
    @ResponseBody
    public RAndPInformation queryRAndPInformationById(
            @RequestParam("randpinformationid") Integer randpinformationid
    ){
        RAndPInformation rAndPInformation = irAndPInformationService.queryOneById(randpinformationid);
        return rAndPInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID修改奖惩信息
     *@Date: 18:18 2018\4\19 0019
     */
    @RequestMapping("/updateRAndPInformationById")
    @ResponseBody
    public String updateRAndPInformationById(
            RAndPInformation rAndPInformation
    ){
        irAndPInformationService.modifyOne(rAndPInformation);
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:删除信息
     *@Date: 19:39 2018\4\19 0019
     */
    @RequestMapping("/deleteRAndPInformationByIds")
    @ResponseBody
    public String deleteRAndPInformationByIds(String ids){
        List<Integer> list = JSONArray.parseArray(ids,Integer.class);
        for (int i = 0;i<list.size();i++){
            irAndPInformationService.removeOne(list.get(i));
        }
        return "删除成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据人事信息的ID查询奖惩信息
     *@Date: 9:54 2018\4\20 0020
     */
    @RequestMapping("/queryRAndPInformationByUserid")
    @ResponseBody
    public List<RAndPInformation> queryRAndPInformationByUserid(
            @RequestParam("personalInformationId") Integer personalInformationId
    ){
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        RAndPInformation rAndPInformation = new RAndPInformation();
        rAndPInformation.setUserid(personalInformation.getUserid());
        List<RAndPInformation> list = irAndPInformationService.queryByCondition(rAndPInformation);
        return list;
    }
}
