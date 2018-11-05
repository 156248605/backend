package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetReward;
import com.elex.oa.service.hr_service.IHRsetRewardService;
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
 * @Description:奖励
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetRewardController {
    @Autowired
    IHRsetRewardService ihRsetRewardService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneReward")
    @ResponseBody
    public String addOneReward(
            @RequestParam("reward") String reward
    ){
        HRsetReward hRsetReward = new HRsetReward();
        hRsetReward.setReward(reward);
        Integer id = ihRsetRewardService.addOne(hRsetReward);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllReward")
    @ResponseBody
    public List<HRsetReward> queryAllReward(){
        List<HRsetReward> hRsetRewardList = ihRsetRewardService.queryAll();
        return hRsetRewardList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo61")
    @ResponseBody
    public PageInfo<HRsetReward> queryHRPageInfo61(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetReward> hRsetRewardPageInfo = ihRsetRewardService.queryByParam(paramMap);
        return hRsetRewardPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR61")
    @ResponseBody
    public Boolean queryValidateHR61(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetReward hRsetReward = null;
        try {
            hRsetReward = ihRsetRewardService.queryByReward(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetReward!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR61")
    @ResponseBody
    public String addHR61(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetReward hRsetReward = new HRsetReward();
        hRsetReward.setReward(valueHR);
        try {
            ihRsetRewardService.addOne(hRsetReward);
        } catch (Exception e) {
            return "添加失败！";
        }
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的删除
     *@Date: 16:36 2018\5\19 0019
     */
    @RequestMapping("/removeHR61")
    @ResponseBody
    public String removeHR61(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetRewardService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR61")
    @ResponseBody
    public String modifyHR61(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetReward hRsetReward = new HRsetReward();
        hRsetReward.setReward(valueHR);
        hRsetReward.setId(id);
        ihRsetRewardService.modifyOne(hRsetReward);
        return "修改成功！";
    }
}
