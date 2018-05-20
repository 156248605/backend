package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetZyzstype;
import com.elex.oa.service.service_shiyun.IHRsetZyzstypeService;
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
 * @Description:职业证书类型
 * @Date:Created in  11:45 2018\5\12 0012
 * @Modify By:
 */
@Controller
@CrossOrigin
public class HRsetZyzstypeController {
    @Autowired
    IHRsetZyzstypeService ihRsetZyzstypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 11:46 2018\5\12 0012
     */
    @RequestMapping("/addOneZyzstype")
    @ResponseBody
    public String addOneZyzstype(
            @RequestParam("zyzstype") String zyzstype
    ){
        HRsetZyzstype hRsetZyzstype = new HRsetZyzstype();
        hRsetZyzstype.setZyzstype(zyzstype);
        Integer zyzstypeid = ihRsetZyzstypeService.addOne(hRsetZyzstype);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 11:48 2018\5\12 0012
     */
    @RequestMapping("/queryAllZyzstype")
    @ResponseBody
    public List<HRsetZyzstype> queryAllZyzstype(){
        List<HRsetZyzstype> hRsetZyzstypes = ihRsetZyzstypeService.queryAll();
        return hRsetZyzstypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo24")
    @ResponseBody
    public PageInfo<HRsetZyzstype> queryHRPageInfo24(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetZyzstype> hRsetZyzstypePageInfo = ihRsetZyzstypeService.queryByParam(paramMap);
        return hRsetZyzstypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR24")
    @ResponseBody
    public Boolean queryValidateHR24(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetZyzstype hRsetZyzstype = null;
        try {
            hRsetZyzstype = ihRsetZyzstypeService.queryByZyzstype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetZyzstype!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR24")
    @ResponseBody
    public String addHR24(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetZyzstype hRsetZyzstype = new HRsetZyzstype();
        hRsetZyzstype.setZyzstype(valueHR);
        try {
            ihRsetZyzstypeService.addOne(hRsetZyzstype);
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
    @RequestMapping("/removeHR24")
    @ResponseBody
    public String removeHR24(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetZyzstypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR24")
    @ResponseBody
    public String modifyHR24(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetZyzstype hRsetZyzstype = new HRsetZyzstype();
        hRsetZyzstype.setZyzstype(valueHR);
        hRsetZyzstype.setId(id);
        ihRsetZyzstypeService.modifyOne(hRsetZyzstype);
        return "修改成功！";
    }
}
