package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetCustomwelfareleave;
import com.elex.oa.service.hr_service.IHRsetCustomwelfareleaveService;
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
 * @Description:自定义福利假
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetCustomwelfareleaveController {
    @Autowired
    IHRsetCustomwelfareleaveService ihRsetCustomwelfareleaveService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneCustomwelfareleave")
    @ResponseBody
    public String addOneCustomwelfareleave(
            @RequestParam("customwelfareleave") String customwelfareleave
    ){
        HRsetCustomwelfareleave hRsetCustomwelfareleave = new HRsetCustomwelfareleave();
        hRsetCustomwelfareleave.setCustomwelfareleave(customwelfareleave);
        Integer id = ihRsetCustomwelfareleaveService.addOne(hRsetCustomwelfareleave);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllCustomwelfareleave")
    @ResponseBody
    public List<HRsetCustomwelfareleave> queryAllCustomwelfareleave(){
        List<HRsetCustomwelfareleave> hRsetCustomwelfareleaveList = ihRsetCustomwelfareleaveService.queryAll();
        return hRsetCustomwelfareleaveList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo73")
    @ResponseBody
    public PageInfo<HRsetCustomwelfareleave> queryHRPageInfo73(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetCustomwelfareleave> hRsetCustomwelfareleavePageInfo = ihRsetCustomwelfareleaveService.queryByParam(paramMap);
        return hRsetCustomwelfareleavePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR73")
    @ResponseBody
    public Boolean queryValidateHR73(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetCustomwelfareleave hRsetCustomwelfareleave = null;
        try {
            hRsetCustomwelfareleave = ihRsetCustomwelfareleaveService.queryByCustomwelfareleave(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetCustomwelfareleave!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR73")
    @ResponseBody
    public String addHR73(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetCustomwelfareleave hRsetCustomwelfareleave = new HRsetCustomwelfareleave();
        hRsetCustomwelfareleave.setCustomwelfareleave(valueHR);
        try {
            ihRsetCustomwelfareleaveService.addOne(hRsetCustomwelfareleave);
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
    @RequestMapping("/removeHR73")
    @ResponseBody
    public String removeHR73(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetCustomwelfareleaveService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR73")
    @ResponseBody
    public String modifyHR73(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetCustomwelfareleave hRsetCustomwelfareleave = new HRsetCustomwelfareleave();
        hRsetCustomwelfareleave.setCustomwelfareleave(valueHR);
        hRsetCustomwelfareleave.setId(id);
        ihRsetCustomwelfareleaveService.modifyOne(hRsetCustomwelfareleave);
        return "修改成功！";
    }
}
