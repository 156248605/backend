package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetRace;
import com.elex.oa.service.hr_service.IHRsetRaceService;
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
 * @Description:
 * @Date:Created in  16:52 2018\5\9 0009
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetRaceController {
    @Autowired
    IHRsetRaceService iRaceService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 16:52 2018\5\9 0009
     */
    @RequestMapping("/addOneRace")
    @ResponseBody
    public String addOneRace(
            HRsetRace race
    ){
        Integer raceid = iRaceService.addOne(race);
        return "添加信息成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 16:54 2018\5\9 0009
     */
    @RequestMapping("/queryAllRaces")
    @ResponseBody
    public List<HRsetRace> queryAllRaces(){
        List<HRsetRace> races = iRaceService.queryAll();
        return races;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo13")
    @ResponseBody
    public PageInfo<HRsetRace> queryHRPageInfo13(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetRace> hRsetRacePageInfo = iRaceService.queryByParam(paramMap);
        return hRsetRacePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR13")
    @ResponseBody
    public Boolean queryValidateHR13(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetRace hRsetRace = null;
        try {
            hRsetRace = iRaceService.queryByRace(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetRace!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR13")
    @ResponseBody
    public String addHR13(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetRace hRsetRace = new HRsetRace();
        hRsetRace.setRace(valueHR);
        try {
            iRaceService.addOne(hRsetRace);
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
    @RequestMapping("/removeHR13")
    @ResponseBody
    public String removeHR13(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            iRaceService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR13")
    @ResponseBody
    public String modifyHR13(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetRace hRsetRace = new HRsetRace();
        hRsetRace.setRace(valueHR);
        hRsetRace.setId(id);
        iRaceService.modifyOne(hRsetRace);
        return "修改成功！";
    }
}
