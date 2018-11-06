package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetTeachingstyle;
import com.elex.oa.service.hr_service.IHRsetTeachingstyleService;
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
 * @Description:授课方式
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetTeachingstyleController {
    @Autowired
    IHRsetTeachingstyleService ihRsetTeachingstyleService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneTeachingstyle")
    @ResponseBody
    public String addOneTeachingstyle(
            @RequestParam("teachingstyle") String teachingstyle
    ){
        HRsetTeachingstyle hRsetTeachingstyle = new HRsetTeachingstyle();
        hRsetTeachingstyle.setTeachingstyle(teachingstyle);
        Integer id = ihRsetTeachingstyleService.addOne(hRsetTeachingstyle);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllTeachingstyle")
    @ResponseBody
    public List<HRsetTeachingstyle> queryAllTeachingstyle(){
        List<HRsetTeachingstyle> hRsetTeachingstyleList = ihRsetTeachingstyleService.queryAll();
        return hRsetTeachingstyleList;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo81")
    @ResponseBody
    public PageInfo<HRsetTeachingstyle> queryHRPageInfo81(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetTeachingstyle> hRsetTeachingstylePageInfo = ihRsetTeachingstyleService.queryByParam(paramMap);
        return hRsetTeachingstylePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR81")
    @ResponseBody
    public Boolean queryValidateHR81(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetTeachingstyle hRsetTeachingstyle = null;
        try {
            hRsetTeachingstyle = ihRsetTeachingstyleService.queryByTeachingstyle(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetTeachingstyle!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR81")
    @ResponseBody
    public String addHR81(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetTeachingstyle hRsetTeachingstyle = new HRsetTeachingstyle();
        hRsetTeachingstyle.setTeachingstyle(valueHR);
        try {
            ihRsetTeachingstyleService.addOne(hRsetTeachingstyle);
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
    @RequestMapping("/removeHR81")
    @ResponseBody
    public String removeHR81(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetTeachingstyleService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR81")
    @ResponseBody
    public String modifyHR81(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetTeachingstyle hRsetTeachingstyle = new HRsetTeachingstyle();
        hRsetTeachingstyle.setTeachingstyle(valueHR);
        hRsetTeachingstyle.setId(id);
        ihRsetTeachingstyleService.modifyOne(hRsetTeachingstyle);
        return "修改成功！";
    }
}
