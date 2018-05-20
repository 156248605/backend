package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetEmployeetype;
import com.elex.oa.service.service_shiyun.IHRsetEmployeetypeService;
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
public class HRsetEmployeetypeController {
    @Autowired
    IHRsetEmployeetypeService ihRsetEmployeetypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneEmployeetype")
    @ResponseBody
    public String addOneEmployeetype(
            @RequestParam("employeetype") String employeetype
    ){
        HRsetEmployeetype hRsetEmployeetype = new HRsetEmployeetype();
        hRsetEmployeetype.setEmployeetype(employeetype);
        Integer id = ihRsetEmployeetypeService.addOne(hRsetEmployeetype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllEmployeetype")
    @ResponseBody
    public List<HRsetEmployeetype> queryAllEmployeetype(){
        List<HRsetEmployeetype> hRsetEmployeetypes = ihRsetEmployeetypeService.queryAll();
        return hRsetEmployeetypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo27")
    @ResponseBody
    public PageInfo<HRsetEmployeetype> queryHRPageInfo27(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetEmployeetype> hRsetEmployeetypePageInfo = ihRsetEmployeetypeService.queryByParam(paramMap);
        return hRsetEmployeetypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR27")
    @ResponseBody
    public Boolean queryValidateHR27(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetEmployeetype hRsetEmployeetype = null;
        try {
            hRsetEmployeetype = ihRsetEmployeetypeService.queryByEmployeetype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetEmployeetype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR27")
    @ResponseBody
    public String addHR27(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetEmployeetype hRsetEmployeetype = new HRsetEmployeetype();
        hRsetEmployeetype.setEmployeetype(valueHR);
        try {
            ihRsetEmployeetypeService.addOne(hRsetEmployeetype);
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
    @RequestMapping("/removeHR27")
    @ResponseBody
    public String removeHR27(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetEmployeetypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR27")
    @ResponseBody
    public String modifyHR27(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetEmployeetype hRsetEmployeetype = new HRsetEmployeetype();
        hRsetEmployeetype.setEmployeetype(valueHR);
        hRsetEmployeetype.setId(id);
        ihRsetEmployeetypeService.modifyOne(hRsetEmployeetype);
        return "修改成功！";
    }
}
