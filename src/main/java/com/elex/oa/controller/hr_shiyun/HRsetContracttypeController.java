package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetContracttype;
import com.elex.oa.service.hr_service.IHRsetContracttypeService;
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
 * @Description:合同类型
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetContracttypeController {
    @Autowired
    IHRsetContracttypeService ihRsetContracttypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneContracttype")
    @ResponseBody
    public String addOneContracttype(
            @RequestParam("contracttype") String contracttype
    ){
        HRsetContracttype hRsetContracttype = new HRsetContracttype();
        hRsetContracttype.setContracttype(contracttype);
        Integer id = ihRsetContracttypeService.addOne(hRsetContracttype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllContracttype")
    @ResponseBody
    public List<HRsetContracttype> queryAllContracttype(){
        List<HRsetContracttype> hRsetContracttypes = ihRsetContracttypeService.queryAll();
        return hRsetContracttypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo28")
    @ResponseBody
    public PageInfo<HRsetContracttype> queryHRPageInfo28(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetContracttype> hRsetContracttypePageInfo = ihRsetContracttypeService.queryByParam(paramMap);
        return hRsetContracttypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR28")
    @ResponseBody
    public Boolean queryValidateHR28(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetContracttype hRsetContracttype = null;
        try {
            hRsetContracttype = ihRsetContracttypeService.queryByContracttype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetContracttype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR28")
    @ResponseBody
    public String addHR28(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetContracttype hRsetContracttype = new HRsetContracttype();
        hRsetContracttype.setContracttype(valueHR);
        try {
            ihRsetContracttypeService.addOne(hRsetContracttype);
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
    @RequestMapping("/removeHR28")
    @ResponseBody
    public String removeHR28(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetContracttypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR28")
    @ResponseBody
    public String modifyHR28(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetContracttype hRsetContracttype = new HRsetContracttype();
        hRsetContracttype.setContracttype(valueHR);
        hRsetContracttype.setId(id);
        ihRsetContracttypeService.modifyOne(hRsetContracttype);
        return "修改成功！";
    }
}
