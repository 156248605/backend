package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetSbjnd;
import com.elex.oa.service.hr_service.IHRsetSbjndService;
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
 * @Description:社保缴纳地
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetSbjndController {
    @Autowired
    IHRsetSbjndService ihRsetSbjndService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneSbjnd")
    @ResponseBody
    public String addOneSbjnd(
            @RequestParam("sbjnd") String sbjnd
    ){
        HRsetSbjnd hRsetSbjnd = new HRsetSbjnd();
        hRsetSbjnd.setSbjnd(sbjnd);
        Integer id = ihRsetSbjndService.addOne(hRsetSbjnd);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllSbjnd")
    @ResponseBody
    public List<HRsetSbjnd> queryAllSbjnd(){
        List<HRsetSbjnd> hRsetSbjnds = ihRsetSbjndService.queryAll();
        return hRsetSbjnds;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo38")
    @ResponseBody
    public PageInfo<HRsetSbjnd> queryHRPageInfo37(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetSbjnd> hRsetSbjndPageInfo = ihRsetSbjndService.queryByParam(paramMap);
        return hRsetSbjndPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR38")
    @ResponseBody
    public Boolean queryValidateHR38(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetSbjnd hRsetSbjnd = null;
        try {
            hRsetSbjnd = ihRsetSbjndService.queryBySbjnd(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetSbjnd!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR38")
    @ResponseBody
    public String addHR38(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetSbjnd hRsetSbjnd = new HRsetSbjnd();
        hRsetSbjnd.setSbjnd(valueHR);
        try {
            ihRsetSbjndService.addOne(hRsetSbjnd);
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
    @RequestMapping("/removeHR38")
    @ResponseBody
    public String removeHR38(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetSbjndService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR38")
    @ResponseBody
    public String modifyHR38(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetSbjnd hRsetSbjnd = new HRsetSbjnd();
        hRsetSbjnd.setSbjnd(valueHR);
        hRsetSbjnd.setId(id);
        ihRsetSbjndService.modifyOne(hRsetSbjnd);
        return "修改成功！";
    }
}
