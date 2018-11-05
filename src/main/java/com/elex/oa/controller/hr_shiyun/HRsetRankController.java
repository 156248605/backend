package com.elex.oa.controller.hr_shiyun;

import com.elex.oa.entity.hr_entity.HRsetRank;
import com.elex.oa.service.hr_service.IHRsetRankService;
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
 * @Description:职级
 * @Date:Created in  18:42 2018\5\11 0011
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetRankController {
    @Autowired
    IHRsetRankService ihRsetRankService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 18:43 2018\5\11 0011
     */
    @RequestMapping("/addOneRank")
    @ResponseBody
    public String addOneRank(
            @RequestParam("rank") String rank
    ){
        HRsetRank hRsetRank = new HRsetRank();
        hRsetRank.setRank(rank);
        Integer byyxid = ihRsetRankService.addOne(hRsetRank);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 18:45 2018\5\11 0011
     */
    @RequestMapping("/queryAllRank")
    @ResponseBody
    public List<HRsetRank> queryAllRank(){
        List<HRsetRank> hRsetRanks = ihRsetRankService.queryAll();
        return hRsetRanks;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo26")
    @ResponseBody
    public PageInfo<HRsetRank> queryHRPageInfo26(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetRank> hRsetRankPageInfo = ihRsetRankService.queryByParam(paramMap);
        return hRsetRankPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR26")
    @ResponseBody
    public Boolean queryValidateHR26(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetRank hRsetRank = null;
        try {
            hRsetRank = ihRsetRankService.queryByRank(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetRank!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR26")
    @ResponseBody
    public String addHR26(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetRank hRsetRank = new HRsetRank();
        hRsetRank.setRank(valueHR);
        try {
            ihRsetRankService.addOne(hRsetRank);
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
    @RequestMapping("/removeHR26")
    @ResponseBody
    public String removeHR26(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetRankService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR26")
    @ResponseBody
    public String modifyHR26(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetRank hRsetRank = new HRsetRank();
        hRsetRank.setRank(valueHR);
        hRsetRank.setId(id);
        ihRsetRankService.modifyOne(hRsetRank);
        return "修改成功！";
    }
}
