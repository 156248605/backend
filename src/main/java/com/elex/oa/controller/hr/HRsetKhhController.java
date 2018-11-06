package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetKhh;
import com.elex.oa.service.hr_service.IHRsetKhhService;
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
 * @Description:开户行
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetKhhController {
    @Autowired
    IHRsetKhhService ihRsetKhhService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneKhh")
    @ResponseBody
    public String addOneKhh(
            @RequestParam("khh") String khh
    ){
        HRsetKhh hRsetKhh = new HRsetKhh();
        hRsetKhh.setKhh(khh);
        Integer id = ihRsetKhhService.addOne(hRsetKhh);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllKhh")
    @ResponseBody
    public List<HRsetKhh> queryAllKhh(){
        List<HRsetKhh> hRsetKhhs = ihRsetKhhService.queryAll();
        return hRsetKhhs;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo37")
    @ResponseBody
    public PageInfo<HRsetKhh> queryHRPageInfo37(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetKhh> hRsetKhhPageInfo = ihRsetKhhService.queryByParam(paramMap);
        return hRsetKhhPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR37")
    @ResponseBody
    public Boolean queryValidateHR37(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetKhh hRsetKhh = null;
        try {
            hRsetKhh = ihRsetKhhService.queryByKhh(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetKhh!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR37")
    @ResponseBody
    public String addHR37(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetKhh hRsetKhh = new HRsetKhh();
        hRsetKhh.setKhh(valueHR);
        try {
            ihRsetKhhService.addOne(hRsetKhh);
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
    @RequestMapping("/removeHR37")
    @ResponseBody
    public String removeHR37(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetKhhService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR37")
    @ResponseBody
    public String modifyHR37(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetKhh hRsetKhh = new HRsetKhh();
        hRsetKhh.setKhh(valueHR);
        hRsetKhh.setId(id);
        ihRsetKhhService.modifyOne(hRsetKhh);
        return "修改成功！";
    }
}
