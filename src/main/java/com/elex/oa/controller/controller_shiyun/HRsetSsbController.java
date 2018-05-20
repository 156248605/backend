package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetByyx;
import com.elex.oa.entity.entity_shiyun.HRsetSsb;
import com.elex.oa.service.service_shiyun.IHRsetByyxService;
import com.elex.oa.service.service_shiyun.IHRsetSsbService;
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
 * @Description:社保基数
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetSsbController {
    @Autowired
    IHRsetSsbService ihRsetSsbService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneSsb")
    @ResponseBody
    public String addOneSsb(
            @RequestParam("ssb") String ssb
    ){
        HRsetSsb hRsetSsb = new HRsetSsb();
        hRsetSsb.setSsb(ssb);
        Integer id = ihRsetSsbService.addOne(hRsetSsb);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllSsb")
    @ResponseBody
    public List<HRsetSsb> queryAllSsb(){
        List<HRsetSsb> hRsetSsbs = ihRsetSsbService.queryAll();
        return hRsetSsbs;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo31")
    @ResponseBody
    public PageInfo<HRsetSsb> queryHRPageInfo31(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetSsb> hRsetSsbPageInfo = ihRsetSsbService.queryByParam(paramMap);
        return hRsetSsbPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR31")
    @ResponseBody
    public Boolean queryValidateHR31(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetSsb hRsetSsb = null;
        try {
            hRsetSsb = ihRsetSsbService.queryBySsb(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetSsb!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR31")
    @ResponseBody
    public String addHR31(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetSsb hRsetSsb = new HRsetSsb();
        hRsetSsb.setSsb(valueHR);
        try {
            ihRsetSsbService.addOne(hRsetSsb);
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
    @RequestMapping("/removeHR31")
    @ResponseBody
    public String removeHR31(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetSsbService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR31")
    @ResponseBody
    public String modifyHR31(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetSsb hRsetSsb = new HRsetSsb();
        hRsetSsb.setSsb(valueHR);
        hRsetSsb.setId(id);
        ihRsetSsbService.modifyOne(hRsetSsb);
        return "修改成功！";
    }
}
