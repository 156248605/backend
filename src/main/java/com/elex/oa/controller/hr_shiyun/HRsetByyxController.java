package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetByyx;
import com.elex.oa.service.hr_service.IHRsetByyxService;
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
 * @Description:毕业院校
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetByyxController {
    @Autowired
    IHRsetByyxService ihRsetByyxService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneByyx")
    @ResponseBody
    public String addOneByyx(
            @RequestParam("byyx") String byyx
    ){
        HRsetByyx hRsetByyx = new HRsetByyx();
        hRsetByyx.setByyx(byyx);
        Integer id = ihRsetByyxService.addOne(hRsetByyx);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllByyx")
    @ResponseBody
    public List<HRsetByyx> queryAllByyx(){
        List<HRsetByyx> hRsetByyxes = ihRsetByyxService.queryAll();
        return hRsetByyxes;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo17")
    @ResponseBody
    public PageInfo<HRsetByyx> queryHRPageInfo17(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetByyx> hRsetByyxPageInfo = ihRsetByyxService.queryByParam(paramMap);
        return hRsetByyxPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR17")
    @ResponseBody
    public Boolean queryValidateHR17(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetByyx hRsetByyx = null;
        try {
            hRsetByyx = ihRsetByyxService.queryByByyx(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetByyx!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR17")
    @ResponseBody
    public String addHR17(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetByyx hRsetByyx = new HRsetByyx();
        hRsetByyx.setByyx(valueHR);
        try {
            ihRsetByyxService.addOne(hRsetByyx);
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
    @RequestMapping("/removeHR17")
    @ResponseBody
    public String removeHR17(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetByyxService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR17")
    @ResponseBody
    public String modifyHR17(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetByyx hRsetByyx = new HRsetByyx();
        hRsetByyx.setByyx(valueHR);
        hRsetByyx.setId(id);
        ihRsetByyxService.modifyOne(hRsetByyx);
        return "修改成功！";
    }
}
