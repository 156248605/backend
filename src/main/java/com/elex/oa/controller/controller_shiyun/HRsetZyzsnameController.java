package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetZyzsname;
import com.elex.oa.service.service_shiyun.IHRsetZyzsnameService;
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
 * @Description:职业证书名称
 * @Date:Created in  15:01 2018\5\12 0012
 * @Modify By:
 */
@Controller
@CrossOrigin
public class HRsetZyzsnameController {
    @Autowired
    IHRsetZyzsnameService ihRsetZyzsnameService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 15:01 2018\5\12 0012
     */
    @RequestMapping("/addOneZyzsname")
    @ResponseBody
    public String addOneZyzsname(
            @RequestParam("zyzsname") String zyzsname
    ){
        HRsetZyzsname hRsetZyzsname = new HRsetZyzsname();
        hRsetZyzsname.setZyzsname(zyzsname);
        Integer zyzsnameid = ihRsetZyzsnameService.addOne(hRsetZyzsname);
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 15:03 2018\5\12 0012
     */
    @RequestMapping("/queryAllZyzsname")
    @ResponseBody
    public List<HRsetZyzsname> queryAllZyzsname(){
        List<HRsetZyzsname> hRsetZyzsnames = ihRsetZyzsnameService.queryAll();
        return hRsetZyzsnames;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo25")
    @ResponseBody
    public PageInfo<HRsetZyzsname> queryHRPageInfo25(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetZyzsname> hRsetZyzsnamePageInfo = ihRsetZyzsnameService.queryByParam(paramMap);
        return hRsetZyzsnamePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR25")
    @ResponseBody
    public Boolean queryValidateHR25(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetZyzsname hRsetZyzsname = null;
        try {
            hRsetZyzsname = ihRsetZyzsnameService.queryByZyzsname(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetZyzsname!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR25")
    @ResponseBody
    public String addHR25(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetZyzsname hRsetZyzsname = new HRsetZyzsname();
        hRsetZyzsname.setZyzsname(valueHR);
        try {
            ihRsetZyzsnameService.addOne(hRsetZyzsname);
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
    @RequestMapping("/removeHR25")
    @ResponseBody
    public String removeHR25(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetZyzsnameService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR25")
    @ResponseBody
    public String modifyHR25(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetZyzsname hRsetZyzsname = new HRsetZyzsname();
        hRsetZyzsname.setZyzsname(valueHR);
        hRsetZyzsname.setId(id);
        ihRsetZyzsnameService.modifyOne(hRsetZyzsname);
        return "修改成功！";
    }
}
