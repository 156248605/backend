package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetSxzy;
import com.elex.oa.service.hr_service.IHRsetSxzyService;
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
 * @Description:所学专业
 * @Date:Created in  10:03 2018\5\12 0012
 * @Modify By:
 */
@Controller
@CrossOrigin
public class HRsetSxzyController {
    @Autowired
    IHRsetSxzyService ihRsetSxzyService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:04 2018\5\12 0012
     */
    @RequestMapping("/addOneSxzy")
    @ResponseBody
    public String addOneSxzy(
            @RequestParam("sxzy") String sxzy
    ){
        HRsetSxzy hRsetSxzy = new HRsetSxzy();
        hRsetSxzy.setSxzy(sxzy);
        Integer sxzyid = ihRsetSxzyService.addOne(hRsetSxzy);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:08 2018\5\12 0012
     */
    @RequestMapping("/queryAllSxzy")
    @ResponseBody
    public List<HRsetSxzy> queryAllSxzy(){
        List<HRsetSxzy> hRsetSxzies = ihRsetSxzyService.queryAll();
        return hRsetSxzies;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo18")
    @ResponseBody
    public PageInfo<HRsetSxzy> queryHRPageInfo18(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetSxzy> hRsetSxzyPageInfo = ihRsetSxzyService.queryByParam(paramMap);
        return hRsetSxzyPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR18")
    @ResponseBody
    public Boolean queryValidateHR18(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetSxzy hRsetSxzy = null;
        try {
            hRsetSxzy = ihRsetSxzyService.queryBySxzy(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetSxzy!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR18")
    @ResponseBody
    public String addHR18(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetSxzy hRsetSxzy = new HRsetSxzy();
        hRsetSxzy.setSxzy(valueHR);
        try {
            ihRsetSxzyService.addOne(hRsetSxzy);
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
    @RequestMapping("/removeHR18")
    @ResponseBody
    public String removeHR18(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetSxzyService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR18")
    @ResponseBody
    public String modifyHR18(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetSxzy hRsetSxzy = new HRsetSxzy();
        hRsetSxzy.setSxzy(valueHR);
        hRsetSxzy.setId(id);
        ihRsetSxzyService.modifyOne(hRsetSxzy);
        return "修改成功！";
    }
}
