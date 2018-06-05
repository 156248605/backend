package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetZgxl;
import com.elex.oa.service.service_shiyun.IHRsetZgxlService;
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
 * @Description:最高学历
 * @Date:Created in  18:08 2018\5\11 0011
 * @Modify By:
 */
@Controller
@CrossOrigin
public class HRsetZgxlController {
    @Autowired
    IHRsetZgxlService ihRsetZgxlService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:09 2018\5\11 0011
     */
    @RequestMapping("/addOneZgxl")
    @ResponseBody
    public String addOneZgxl(
            @RequestParam("zgxl") String zgxl
    ){
        HRsetZgxl hRsetZgxl = new HRsetZgxl();
        hRsetZgxl.setZgxl(zgxl);
        Integer zgxlid = ihRsetZgxlService.addOne(hRsetZgxl);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的信息
     *@Date: 18:13 2018\5\11 0011
     */
    @RequestMapping("/queryAllZgxl")
    @ResponseBody
    public List<HRsetZgxl> queryAllZgxl(){
        List<HRsetZgxl> hRsetZgxls = ihRsetZgxlService.queryAll();
        return hRsetZgxls;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo16")
    @ResponseBody
    public PageInfo<HRsetZgxl> queryHRPageInfo16(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetZgxl> hRsetZgxlPageInfo = ihRsetZgxlService.queryByParam(paramMap);
        return hRsetZgxlPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR16")
    @ResponseBody
    public Boolean queryValidateHR16(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetZgxl hRsetZgxl = null;
        try {
            hRsetZgxl = ihRsetZgxlService.queryByZgxl(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetZgxl!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR16")
    @ResponseBody
    public String addHR16(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetZgxl hRsetZgxl = new HRsetZgxl();
        hRsetZgxl.setZgxl(valueHR);
        try {
            ihRsetZgxlService.addOne(hRsetZgxl);
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
    @RequestMapping("/removeHR16")
    @ResponseBody
    public String removeHR16(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetZgxlService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR16")
    @ResponseBody
    public String modifyHR16(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetZgxl hRsetZgxl = new HRsetZgxl();
        hRsetZgxl.setZgxl(valueHR);
        hRsetZgxl.setId(id);
        ihRsetZgxlService.modifyOne(hRsetZgxl);
        return "修改成功！";
    }
}
