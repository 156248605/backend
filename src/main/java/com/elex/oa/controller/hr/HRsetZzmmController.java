package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetZzmm;
import com.elex.oa.service.hr_service.IHRsetZzmmService;
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
 * @Description:HR设置（政治面貌）
 * @Date:Created in  17:49 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetZzmmController {
    @Autowired
    IHRsetZzmmService ihRsetZzmmService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 17:50 2018\5\11 0011
     */
    @RequestMapping("/addOneZzmm")
    @ResponseBody
    public String addOneZzmm(
            @RequestParam("zzmm") String zzmm
    ){
        HRsetZzmm hRsetZzmm = new HRsetZzmm();
        hRsetZzmm.setZzmm(zzmm);
        Integer zzmmid = ihRsetZzmmService.addOne(hRsetZzmm);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的政治面貌信息
     *@Date: 17:52 2018\5\11 0011
     */
    @RequestMapping("/queryAllZzmm")
    @ResponseBody
    public List<HRsetZzmm> queryAllZzmm(){
        List<HRsetZzmm> hRsetZzmms = ihRsetZzmmService.queryAll();
        return hRsetZzmms;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo15")
    @ResponseBody
    public PageInfo<HRsetZzmm> queryHRPageInfo15(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetZzmm> hRsetZzmmPageInfo = ihRsetZzmmService.queryByParam(paramMap);
        return hRsetZzmmPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR15")
    @ResponseBody
    public Boolean queryValidateHR15(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetZzmm hRsetZzmm = null;
        try {
            hRsetZzmm = ihRsetZzmmService.queryByZzmm(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetZzmm!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR15")
    @ResponseBody
    public String addHR15(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetZzmm hRsetZzmm = new HRsetZzmm();
        hRsetZzmm.setZzmm(valueHR);
        try {
            ihRsetZzmmService.addOne(hRsetZzmm);
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
    @RequestMapping("/removeHR15")
    @ResponseBody
    public String removeHR15(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetZzmmService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR15")
    @ResponseBody
    public String modifyHR15(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetZzmm hRsetZzmm = new HRsetZzmm();
        hRsetZzmm.setZzmm(valueHR);
        hRsetZzmm.setId(id);
        ihRsetZzmmService.modifyOne(hRsetZzmm);
        return "修改成功！";
    }
}
