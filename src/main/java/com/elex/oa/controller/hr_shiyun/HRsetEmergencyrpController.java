package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetEmergencyrp;
import com.elex.oa.service.hr_service.IHRsetEmergencyrpService;
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
 * @Description:应急联系人关系
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetEmergencyrpController {
    @Autowired
    IHRsetEmergencyrpService ihRsetEmergencyrpService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneEmergencyrp")
    @ResponseBody
    public String addOneEmergencyrp(
            @RequestParam("emergencyrp") String emergencyrp
    ){
        HRsetEmergencyrp hRsetEmergencyrp = new HRsetEmergencyrp();
        hRsetEmergencyrp.setEmergencyrp(emergencyrp);
        Integer id = ihRsetEmergencyrpService.addOne(hRsetEmergencyrp);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllEmergencyrp")
    @ResponseBody
    public List<HRsetEmergencyrp> queryAllByyx(){
        List<HRsetEmergencyrp> hRsetEmergencyrps = ihRsetEmergencyrpService.queryAll();
        return hRsetEmergencyrps;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo22")
    @ResponseBody
    public PageInfo<HRsetEmergencyrp> queryHRPageInfo22(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetEmergencyrp> hRsetEmergencyrpPageInfo = ihRsetEmergencyrpService.queryByParam(paramMap);
        return hRsetEmergencyrpPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR22")
    @ResponseBody
    public Boolean queryValidateHR22(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetEmergencyrp hRsetEmergencyrp = null;
        try {
            hRsetEmergencyrp = ihRsetEmergencyrpService.queryByEmergencyrp(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetEmergencyrp!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR22")
    @ResponseBody
    public String addHR22(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetEmergencyrp hRsetEmergencyrp = new HRsetEmergencyrp();
        hRsetEmergencyrp.setEmergencyrp(valueHR);
        try {
            ihRsetEmergencyrpService.addOne(hRsetEmergencyrp);
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
    @RequestMapping("/removeHR22")
    @ResponseBody
    public String removeHR22(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetEmergencyrpService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR22")
    @ResponseBody
    public String modifyHR22(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetEmergencyrp hRsetEmergencyrp = new HRsetEmergencyrp();
        hRsetEmergencyrp.setEmergencyrp(valueHR);
        hRsetEmergencyrp.setId(id);
        ihRsetEmergencyrpService.modifyOne(hRsetEmergencyrp);
        return "修改成功！";
    }
}
