package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetPostlevel;
import com.elex.oa.service.hr_service.IHRsetPostlevelService;
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
 * @Description:HR设置（岗位级别信息）
 * @Date:Created in  18:15 2018\5\10 0010
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetPostlevelController {
    @Autowired
    IHRsetPostlevelService iPostlevelService;

    /**
     *@Author:ShiYun;
     *@Description:添加岗位级别信息
     *@Date: 18:17 2018\5\10 0010
     */
    @RequestMapping("/addOnePostlevel")
    @ResponseBody
    public String addOnePostlevel(
            @RequestParam("postlevel") String level
    ){
        HRsetPostlevel postlevel = new HRsetPostlevel();
        postlevel.setPostlevel(level);
        Integer postlevelid = iPostlevelService.addOne(postlevel);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的岗位级别信息
     *@Date: 18:19 2018\5\10 0010
     */
    @RequestMapping("/queryAllPostlevels")
    @ResponseBody
    public List<HRsetPostlevel> queryAllPostlevels(){
        List<HRsetPostlevel> postlevels = iPostlevelService.queryAll();
        return postlevels;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo12")
    @ResponseBody
    public PageInfo<HRsetPostlevel> queryHRPageInfo37(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetPostlevel> hRsetPostlevelPageInfo = iPostlevelService.queryByParam(paramMap);
        return hRsetPostlevelPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR12")
    @ResponseBody
    public Boolean queryValidateHR12(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetPostlevel hRsetPostlevel = null;
        try {
            hRsetPostlevel = iPostlevelService.queryByPostlevel(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetPostlevel!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR12")
    @ResponseBody
    public String addHR12(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetPostlevel hRsetPostlevel = new HRsetPostlevel();
        hRsetPostlevel.setPostlevel(valueHR);
        try {
            iPostlevelService.addOne(hRsetPostlevel);
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
    @RequestMapping("/removeHR12")
    @ResponseBody
    public String removeHR12(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            iPostlevelService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR12")
    @ResponseBody
    public String modifyHR12(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetPostlevel hRsetPostlevel = new HRsetPostlevel();
        hRsetPostlevel.setPostlevel(valueHR);
        hRsetPostlevel.setId(id);
        iPostlevelService.modifyOne(hRsetPostlevel);
        return "修改成功！";
    }
}
