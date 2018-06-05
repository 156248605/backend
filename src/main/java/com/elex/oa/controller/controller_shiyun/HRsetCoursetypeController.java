package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetCoursetype;
import com.elex.oa.service.service_shiyun.IHRsetCoursetypeService;
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
 * @Description:课程类别
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetCoursetypeController {
    @Autowired
    IHRsetCoursetypeService ihRsetCoursetypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneCoursetype")
    @ResponseBody
    public String addOneCoursetype(
            @RequestParam("coursetype") String coursetype
    ){
        HRsetCoursetype hRsetCoursetype = new HRsetCoursetype();
        hRsetCoursetype.setCoursetype(coursetype);
        Integer id = ihRsetCoursetypeService.addOne(hRsetCoursetype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllCoursetype")
    @ResponseBody
    public List<HRsetCoursetype> queryAllCoursetype(){
        List<HRsetCoursetype> hRsetCoursetypeList = ihRsetCoursetypeService.queryAll();
        return hRsetCoursetypeList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo82")
    @ResponseBody
    public PageInfo<HRsetCoursetype> queryHRPageInfo82(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetCoursetype> hRsetCoursetypePageInfo = ihRsetCoursetypeService.queryByParam(paramMap);
        return hRsetCoursetypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR82")
    @ResponseBody
    public Boolean queryValidateHR82(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetCoursetype hRsetCoursetype = null;
        try {
            hRsetCoursetype = ihRsetCoursetypeService.queryByCoursetype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetCoursetype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR82")
    @ResponseBody
    public String addHR82(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetCoursetype hRsetCoursetype = new HRsetCoursetype();
        hRsetCoursetype.setCoursetype(valueHR);
        try {
            ihRsetCoursetypeService.addOne(hRsetCoursetype);
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
    @RequestMapping("/removeHR82")
    @ResponseBody
    public String removeHR82(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetCoursetypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR82")
    @ResponseBody
    public String modifyHR82(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetCoursetype hRsetCoursetype = new HRsetCoursetype();
        hRsetCoursetype.setCoursetype(valueHR);
        hRsetCoursetype.setId(id);
        ihRsetCoursetypeService.modifyOne(hRsetCoursetype);
        return "修改成功！";
    }
}
