package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetGjjgrcd;
import com.elex.oa.service.hr_service.IHRsetGjjgrcdService;
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
 * @Description:公积金个人承担
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetGjjgrcdController {
    @Autowired
    IHRsetGjjgrcdService ihRsetGjjgrcdService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneGjjgrcd")
    @ResponseBody
    public String addOneGjjgrcd(
            @RequestParam("gjjgrcd") String gjjgrcd
    ){
        HRsetGjjgrcd hRsetGjjgrcd = new HRsetGjjgrcd();
        hRsetGjjgrcd.setGjjgrcd(gjjgrcd);
        Integer id = ihRsetGjjgrcdService.addOne(hRsetGjjgrcd);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllGjjgrcd")
    @ResponseBody
    public List<HRsetGjjgrcd> queryAllGjjgrcd(){
        List<HRsetGjjgrcd> hRsetGjjgrcds = ihRsetGjjgrcdService.queryAll();
        return hRsetGjjgrcds;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo36")
    @ResponseBody
    public PageInfo<HRsetGjjgrcd> queryHRPageInfo36(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetGjjgrcd> hRsetGjjgrcdPageInfo = ihRsetGjjgrcdService.queryByParam(paramMap);
        return hRsetGjjgrcdPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR36")
    @ResponseBody
    public Boolean queryValidateHR36(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetGjjgrcd hRsetGjjgrcd = null;
        try {
            hRsetGjjgrcd = ihRsetGjjgrcdService.queryByGjjgrcd(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetGjjgrcd!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR36")
    @ResponseBody
    public String addHR36(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetGjjgrcd hRsetGjjgrcd = new HRsetGjjgrcd();
        hRsetGjjgrcd.setGjjgrcd(valueHR);
        try {
            ihRsetGjjgrcdService.addOne(hRsetGjjgrcd);
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
    @RequestMapping("/removeHR36")
    @ResponseBody
    public String removeHR36(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetGjjgrcdService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR36")
    @ResponseBody
    public String modifyHR36(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetGjjgrcd hRsetGjjgrcd = new HRsetGjjgrcd();
        hRsetGjjgrcd.setGjjgrcd(valueHR);
        hRsetGjjgrcd.setId(id);
        ihRsetGjjgrcdService.modifyOne(hRsetGjjgrcd);
        return "修改成功！";
    }
}
