package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.HRsetFunctionalType;
import com.elex.oa.service.service_shiyun.IHRsetFunctionalTypeService;
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
 * @Description:HR设置（职能类型信息）
 * @Date:Created in  17:06 2018\5\10 0010
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetFunctionalTypeController {
    @Autowired
    IHRsetFunctionalTypeService iDepFunctionalTypeService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条职能类型
     *@Date: 17:07 2018\5\10 0010
     */
    @RequestMapping("/addOneFunctionaltype")
    @ResponseBody
    public String addOneFunctionaltype(
            @RequestParam("functionaltype") String ft
    ){
        HRsetFunctionalType depFunctionalType = new HRsetFunctionalType();
        depFunctionalType.setFunctionaltype(ft);
        Integer functionaltypeid = iDepFunctionalTypeService.addOne(depFunctionalType);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的职能类型
     *@Date: 17:11 2018\5\10 0010
     */
    @RequestMapping("/queryAllFunctionaltypes")
    @ResponseBody
    public List<HRsetFunctionalType> queryAllFunctionaltypes(){
        List<HRsetFunctionalType> depFunctionalTypes = iDepFunctionalTypeService.queryAll();
        return depFunctionalTypes;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo11")
    @ResponseBody
    public PageInfo<HRsetFunctionalType> queryHRPageInfo11(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetFunctionalType> hRsetFunctionalTypePageInfo = iDepFunctionalTypeService.queryByParam(paramMap);
        return hRsetFunctionalTypePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR11")
    @ResponseBody
    public Boolean queryValidateHR11(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetFunctionalType hRsetFunctionalType = null;
        try {
            hRsetFunctionalType = iDepFunctionalTypeService.queryByFuctionaltype(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetFunctionalType!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR11")
    @ResponseBody
    public String addHR11(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetFunctionalType hRsetFunctionalType = new HRsetFunctionalType();
        hRsetFunctionalType.setFunctionaltype(valueHR);
        try {
            iDepFunctionalTypeService.addOne(hRsetFunctionalType);
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
    @RequestMapping("/removeHR11")
    @ResponseBody
    public String removeHR11(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            iDepFunctionalTypeService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR11")
    @ResponseBody
    public String modifyHR11(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetFunctionalType hRsetFunctionalType = new HRsetFunctionalType();
        hRsetFunctionalType.setFunctionaltype(valueHR);
        hRsetFunctionalType.setId(id);
        iDepFunctionalTypeService.modifyOne(hRsetFunctionalType);
        return "修改成功！";
    }
}
