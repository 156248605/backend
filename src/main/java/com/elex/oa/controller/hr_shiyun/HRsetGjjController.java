package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetGjj;
import com.elex.oa.service.hr_service.IHRsetGjjService;
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
 * @Description:公积金
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetGjjController {
    @Autowired
    IHRsetGjjService ihRsetGjjService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneGjj")
    @ResponseBody
    public String addOneGjj(
            @RequestParam("gjj") String gjj
    ){
        HRsetGjj hRsetGjj = new HRsetGjj();
        hRsetGjj.setGjj(gjj);
        Integer id = ihRsetGjjService.addOne(hRsetGjj);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllGjj")
    @ResponseBody
    public List<HRsetGjj> queryAllGjj(){
        List<HRsetGjj> hRsetGjjs = ihRsetGjjService.queryAll();
        return hRsetGjjs;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo34")
    @ResponseBody
    public PageInfo<HRsetGjj> queryHRPageInfo34(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetGjj> hRsetGjjPageInfo = ihRsetGjjService.queryByParam(paramMap);
        return hRsetGjjPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR34")
    @ResponseBody
    public Boolean queryValidateHR34(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetGjj hRsetGjj = null;
        try {
            hRsetGjj = ihRsetGjjService.queryByGjj(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetGjj!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR34")
    @ResponseBody
    public String addHR34(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetGjj hRsetGjj = new HRsetGjj();
        hRsetGjj.setGjj(valueHR);
        try {
            ihRsetGjjService.addOne(hRsetGjj);
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
    @RequestMapping("/removeHR34")
    @ResponseBody
    public String removeHR34(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetGjjService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR34")
    @ResponseBody
    public String modifyHR34(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetGjj hRsetGjj = new HRsetGjj();
        hRsetGjj.setGjj(valueHR);
        hRsetGjj.setId(id);
        ihRsetGjjService.modifyOne(hRsetGjj);
        return "修改成功！";
    }
}
