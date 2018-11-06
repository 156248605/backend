package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetFla;
import com.elex.oa.service.hr_service.IHRsetFlaService;
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
 * @Description:外语
 * @Date:Created in  10:46 2018\5\12 0012
 * @Modify By:
 */
@Controller
@CrossOrigin
public class HRsetFlaController {
    @Autowired
    IHRsetFlaService ihRsetFlaService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 10:46 2018\5\12 0012
     */
    @RequestMapping("/addOneFla")
    @ResponseBody
    public String adddOneFla(
            @RequestParam("fla") String fla
    ){
        HRsetFla hRsetFla = new HRsetFla();
        hRsetFla.setFla(fla);
        ihRsetFlaService.addOne(hRsetFla);
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 10:49 2018\5\12 0012
     */
    @RequestMapping("/queryAllFla")
    @ResponseBody
    public List<HRsetFla> queryAllFla(){
        List<HRsetFla> hRsetFlas = ihRsetFlaService.queryAll();
        return hRsetFlas;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo21")
    @ResponseBody
    public PageInfo<HRsetFla> queryHRPageInfo21(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetFla> hRsetFlaPageInfo = ihRsetFlaService.queryByParam(paramMap);
        return hRsetFlaPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR21")
    @ResponseBody
    public Boolean queryValidateHR21(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetFla hRsetFla = null;
        try {
            hRsetFla = ihRsetFlaService.queryByFla(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetFla!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR21")
    @ResponseBody
    public String addHR21(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetFla hRsetFla = new HRsetFla();
        hRsetFla.setFla(valueHR);
        try {
            ihRsetFlaService.addOne(hRsetFla);
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
    @RequestMapping("/removeHR21")
    @ResponseBody
    public String removeHR21(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetFlaService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR21")
    @ResponseBody
    public String modifyHR21(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetFla hRsetFla = new HRsetFla();
        hRsetFla.setFla(valueHR);
        hRsetFla.setId(id);
        ihRsetFlaService.modifyOne(hRsetFla);
        return "修改成功！";
    }
}
