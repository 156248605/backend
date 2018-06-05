package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetTelphone;
import com.elex.oa.service.service_shiyun.IHRsetTelphoneService;
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
 * @Description:办公电话
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetTelphoneController {
    @Autowired
    IHRsetTelphoneService ihRsetTelphoneService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneTelphone")
    @ResponseBody
    public String addOneTelphone(
            @RequestParam("telphone") String telphone
    ){
        HRsetTelphone hRsetTelphone = new HRsetTelphone();
        hRsetTelphone.setTelphone(telphone);
        Integer id = ihRsetTelphoneService.addOne(hRsetTelphone);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllTelphone")
    @ResponseBody
    public List<HRsetTelphone> queryAllTelphone(){
        List<HRsetTelphone> hRsetTelphones = ihRsetTelphoneService.queryAll();
        return hRsetTelphones;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo39")
    @ResponseBody
    public PageInfo<HRsetTelphone> queryHRPageInfo39(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetTelphone> hRsetTelphonePageInfo = ihRsetTelphoneService.queryByParam(paramMap);
        return hRsetTelphonePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR39")
    @ResponseBody
    public Boolean queryValidateHR39(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetTelphone hRsetTelphone = null;
        try {
            hRsetTelphone = ihRsetTelphoneService.queryByTelphone(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetTelphone!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR39")
    @ResponseBody
    public String addHR39(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetTelphone hRsetTelphone = new HRsetTelphone();
        hRsetTelphone.setTelphone(valueHR);
        try {
            ihRsetTelphoneService.addOne(hRsetTelphone);
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
    @RequestMapping("/removeHR39")
    @ResponseBody
    public String removeHR39(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetTelphoneService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR39")
    @ResponseBody
    public String modifyHR39(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetTelphone hRsetTelphone = new HRsetTelphone();
        hRsetTelphone.setTelphone(valueHR);
        hRsetTelphone.setId(id);
        ihRsetTelphoneService.modifyOne(hRsetTelphone);
        return "修改成功！";
    }
}
