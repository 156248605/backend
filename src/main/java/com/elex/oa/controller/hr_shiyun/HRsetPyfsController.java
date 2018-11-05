package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetPyfs;
import com.elex.oa.service.hr_service.IHRsetPyfsService;
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
 * @Description:培养方式
 * @Date:Created in  10:24 2018\5\12 0012
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetPyfsController {
    @Autowired
    IHRsetPyfsService ihRsetPyfsService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:25 2018\5\12 0012
     */
    @RequestMapping("/addOnePyfs")
    @ResponseBody
    public String addOnePyfs(
            @RequestParam("pyfs") String pyfs
    ){
        HRsetPyfs hRsetPyfs = new HRsetPyfs();
        hRsetPyfs.setPyfs(pyfs);
        Integer pyfsid = ihRsetPyfsService.addOne(hRsetPyfs);
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:28 2018\5\12 0012
     */
    @RequestMapping("/queryAllPyfs")
    @ResponseBody
    public List<HRsetPyfs> queryAllPyfs(){
        List<HRsetPyfs> hRsetPyfs = ihRsetPyfsService.queryAll();
        return hRsetPyfs;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo19")
    @ResponseBody
    public PageInfo<HRsetPyfs> queryHRPageInfo19(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetPyfs> hRsetPyfsPageInfo = ihRsetPyfsService.queryByParam(paramMap);
        return hRsetPyfsPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR19")
    @ResponseBody
    public Boolean queryValidateHR19(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetPyfs hRsetPyfs = null;
        try {
            hRsetPyfs = ihRsetPyfsService.queryByPyfs(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetPyfs!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR19")
    @ResponseBody
    public String addHR19(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetPyfs hRsetPyfs = new HRsetPyfs();
        hRsetPyfs.setPyfs(valueHR);
        try {
            ihRsetPyfsService.addOne(hRsetPyfs);
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
    @RequestMapping("/removeHR19")
    @ResponseBody
    public String removeHR19(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetPyfsService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR19")
    @ResponseBody
    public String modifyHR19(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetPyfs hRsetPyfs = new HRsetPyfs();
        hRsetPyfs.setPyfs(valueHR);
        hRsetPyfs.setId(id);
        ihRsetPyfsService.modifyOne(hRsetPyfs);
        return "修改成功！";
    }
}
