package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetDeptype;
import com.elex.oa.service.hr_service.IHRsetDeptypeService;
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
 * @Description:部门类型
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetDeptypeController {
    @Autowired
    IHRsetDeptypeService ihRsetDeptypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneDeptype")
    @ResponseBody
    public String addOneDeptype(
            @RequestParam("deptype") String deptype
    ){
        HRsetDeptype hRsetDeptype = new HRsetDeptype();
        hRsetDeptype.setDeptype(deptype);
        Integer id = ihRsetDeptypeService.addOne(hRsetDeptype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllDeptype")
    @ResponseBody
    public List<HRsetDeptype> queryAllDeptype(){
        List<HRsetDeptype> hRsetDeptypes = ihRsetDeptypeService.queryAll();
        return hRsetDeptypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo71")
    @ResponseBody
    public PageInfo<HRsetDeptype> queryHRPageInfo71(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetDeptype> hRsetDeptypePageInfo = ihRsetDeptypeService.queryByParam(paramMap);
        return hRsetDeptypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR71")
    @ResponseBody
    public Boolean queryValidateHR71(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetDeptype hRsetDeptype = null;
        try {
            hRsetDeptype = ihRsetDeptypeService.queryByDeptype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetDeptype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR71")
    @ResponseBody
    public String addHR71(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetDeptype hRsetDeptype = new HRsetDeptype();
        hRsetDeptype.setDeptype(valueHR);
        try {
            ihRsetDeptypeService.addOne(hRsetDeptype);
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
    @RequestMapping("/removeHR71")
    @ResponseBody
    public String removeHR71(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetDeptypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR71")
    @ResponseBody
    public String modifyHR71(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetDeptype hRsetDeptype = new HRsetDeptype();
        hRsetDeptype.setDeptype(valueHR);
        hRsetDeptype.setId(id);
        ihRsetDeptypeService.modifyOne(hRsetDeptype);
        return "修改成功！";
    }
}
