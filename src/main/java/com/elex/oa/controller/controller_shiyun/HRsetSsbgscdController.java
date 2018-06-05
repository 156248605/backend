package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetSsbgscd;
import com.elex.oa.service.service_shiyun.IHRsetSsbgscdService;
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
 * @Description:社保公司缴费比例
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetSsbgscdController {
    @Autowired
    IHRsetSsbgscdService ihRsetSsbgscdService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneSsbgscd")
    @ResponseBody
    public String addOneSsbgscd(
            @RequestParam("ssbgscd") String ssbgscd
    ){
        HRsetSsbgscd hRsetSsbgscd = new HRsetSsbgscd();
        hRsetSsbgscd.setSsbgscd(ssbgscd);
        Integer id = ihRsetSsbgscdService.addOne(hRsetSsbgscd);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllSsbgscd")
    @ResponseBody
    public List<HRsetSsbgscd> queryAllSsbgscd(){
        List<HRsetSsbgscd> hRsetSsbgscds = ihRsetSsbgscdService.queryAll();
        return hRsetSsbgscds;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo32")
    @ResponseBody
    public PageInfo<HRsetSsbgscd> queryHRPageInfo32(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetSsbgscd> hRsetSsbgscdPageInfo = ihRsetSsbgscdService.queryByParam(paramMap);
        return hRsetSsbgscdPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR32")
    @ResponseBody
    public Boolean queryValidateHR32(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetSsbgscd hRsetSsbgscd = null;
        try {
            hRsetSsbgscd = ihRsetSsbgscdService.queryBySsbgscd(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetSsbgscd!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR32")
    @ResponseBody
    public String addHR32(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetSsbgscd hRsetSsbgscd = new HRsetSsbgscd();
        hRsetSsbgscd.setSsbgscd(valueHR);
        try {
            ihRsetSsbgscdService.addOne(hRsetSsbgscd);
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
    @RequestMapping("/removeHR32")
    @ResponseBody
    public String removeHR32(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetSsbgscdService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR32")
    @ResponseBody
    public String modifyHR32(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetSsbgscd hRsetSsbgscd = new HRsetSsbgscd();
        hRsetSsbgscd.setSsbgscd(valueHR);
        hRsetSsbgscd.setId(id);
        ihRsetSsbgscdService.modifyOne(hRsetSsbgscd);
        return "修改成功！";
    }
}
