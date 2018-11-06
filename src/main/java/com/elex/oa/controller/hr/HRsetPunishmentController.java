package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetPunishment;
import com.elex.oa.service.hr_service.IHRsetPunishmentService;
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
public class HRsetPunishmentController {
    @Autowired
    IHRsetPunishmentService ihRsetPunishmentService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOnePunishment")
    @ResponseBody
    public String addOnePunishment(
            @RequestParam("punishment") String punishment
    ){
        HRsetPunishment hRsetPunishment = new HRsetPunishment();
        hRsetPunishment.setPunishment(punishment);
        Integer id = ihRsetPunishmentService.addOne(hRsetPunishment);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllPunishment")
    @ResponseBody
    public List<HRsetPunishment> queryAllPunishment(){
        List<HRsetPunishment> hRsetPunishments = ihRsetPunishmentService.queryAll();
        return hRsetPunishments;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo62")
    @ResponseBody
    public PageInfo<HRsetPunishment> queryHRPageInfo62(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetPunishment> hRsetPunishmentPageInfo = ihRsetPunishmentService.queryByParam(paramMap);
        return hRsetPunishmentPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR62")
    @ResponseBody
    public Boolean queryValidateHR62(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetPunishment hRsetPunishment = null;
        try {
            hRsetPunishment = ihRsetPunishmentService.queryByPunishment(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetPunishment!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR62")
    @ResponseBody
    public String addHR62(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetPunishment hRsetPunishment = new HRsetPunishment();
        hRsetPunishment.setPunishment(valueHR);
        try {
            ihRsetPunishmentService.addOne(hRsetPunishment);
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
    @RequestMapping("/removeHR62")
    @ResponseBody
    public String removeHR62(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetPunishmentService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR62")
    @ResponseBody
    public String modifyHR62(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetPunishment hRsetPunishment = new HRsetPunishment();
        hRsetPunishment.setPunishment(valueHR);
        hRsetPunishment.setId(id);
        ihRsetPunishmentService.modifyOne(hRsetPunishment);
        return "修改成功！";
    }
}
