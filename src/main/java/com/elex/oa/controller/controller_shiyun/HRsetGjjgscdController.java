package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetGjjgscd;
import com.elex.oa.service.service_shiyun.IHRsetGjjgscdService;
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
 * @Description:公积金公司缴费比例
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetGjjgscdController {
    @Autowired
    IHRsetGjjgscdService ihRsetGjjgscdService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneGjjgscd")
    @ResponseBody
    public String addOneGjjgscd(
            @RequestParam("gjjgscd") String gjjgscd
    ){
        HRsetGjjgscd hRsetGjjgscd = new HRsetGjjgscd();
        hRsetGjjgscd.setGjjgscd(gjjgscd);
        Integer id = ihRsetGjjgscdService.addOne(hRsetGjjgscd);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllGjjgscd")
    @ResponseBody
    public List<HRsetGjjgscd> queryAllGjjgscd(){
        List<HRsetGjjgscd> hRsetGjjgscds = ihRsetGjjgscdService.queryAll();
        return hRsetGjjgscds;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo35")
    @ResponseBody
    public PageInfo<HRsetGjjgscd> queryHRPageInfo35(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetGjjgscd> hRsetGjjgscdPageInfo = ihRsetGjjgscdService.queryByParam(paramMap);
        return hRsetGjjgscdPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR35")
    @ResponseBody
    public Boolean queryValidateHR35(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetGjjgscd hRsetGjjgscd = null;
        try {
            hRsetGjjgscd = ihRsetGjjgscdService.queryByGjjgscd(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetGjjgscd!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR35")
    @ResponseBody
    public String addHR35(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetGjjgscd hRsetGjjgscd = new HRsetGjjgscd();
        hRsetGjjgscd.setGjjgscd(valueHR);
        try {
            ihRsetGjjgscdService.addOne(hRsetGjjgscd);
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
    @RequestMapping("/removeHR35")
    @ResponseBody
    public String removeHR35(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetGjjgscdService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR35")
    @ResponseBody
    public String modifyHR35(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetGjjgscd hRsetGjjgscd = new HRsetGjjgscd();
        hRsetGjjgscd.setGjjgscd(valueHR);
        hRsetGjjgscd.setId(id);
        ihRsetGjjgscdService.modifyOne(hRsetGjjgscd);
        return "修改成功！";
    }
}
