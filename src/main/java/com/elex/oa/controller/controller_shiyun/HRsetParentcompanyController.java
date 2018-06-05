package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetParentcompany;
import com.elex.oa.service.service_shiyun.IHRsetParentcompanyService;
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
 * @Description:上家公司
 * @Date:Created in  14:54 2018\5\12 0012
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetParentcompanyController {
    @Autowired
    IHRsetParentcompanyService ihRsetParentcompanyService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 14:55 2018\5\12 0012
     */
    @RequestMapping("/addOneParentcompany")
    @ResponseBody
    public String addOneParentcompanyname(
         @RequestParam("parentcompany") String parentcompany
    ){
        HRsetParentcompany hRsetParentcompany = new HRsetParentcompany();
        hRsetParentcompany.setParentcompanyname(parentcompany);
        Integer parentcompanyid = ihRsetParentcompanyService.addOne(hRsetParentcompany);
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 14:58 2018\5\12 0012
     */
    @RequestMapping("/queryAllParentcompany")
    @ResponseBody
    public List<HRsetParentcompany> queryAllParentcompany(){
        List<HRsetParentcompany> hRsetParentcompanies = ihRsetParentcompanyService.queryAll();
        return hRsetParentcompanies;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo41")
    @ResponseBody
    public PageInfo<HRsetParentcompany> queryHRPageInfo41(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetParentcompany> hRsetParentcompanyPageInfo = ihRsetParentcompanyService.queryByParam(paramMap);
        return hRsetParentcompanyPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR41")
    @ResponseBody
    public Boolean queryValidateHR41(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetParentcompany hRsetParentcompany = null;
        try {
            hRsetParentcompany = ihRsetParentcompanyService.queryByParentcompanyname(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetParentcompany!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR41")
    @ResponseBody
    public String addHR41(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetParentcompany hRsetParentcompany = new HRsetParentcompany();
        hRsetParentcompany.setParentcompanyname(valueHR);
        try {
            ihRsetParentcompanyService.addOne(hRsetParentcompany);
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
    @RequestMapping("/removeHR41")
    @ResponseBody
    public String removeHR41(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetParentcompanyService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR41")
    @ResponseBody
    public String modifyHR41(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetParentcompany hRsetParentcompany = new HRsetParentcompany();
        hRsetParentcompany.setParentcompanyname(valueHR);
        hRsetParentcompany.setId(id);
        ihRsetParentcompanyService.modifyOne(hRsetParentcompany);
        return "修改成功！";
    }
}
