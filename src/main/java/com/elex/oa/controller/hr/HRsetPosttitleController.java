package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRsetPosttitle;
import com.elex.oa.service.hr_service.IHRsetPosttitleService;
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
 * @Description:职称
 * @Date:Created in  11:40 2018\5\12 0012
 * @Modify By:
 */
@CrossOrigin
@Controller
public class HRsetPosttitleController {
    @Autowired
    IHRsetPosttitleService ihRsetPosttitleService;

    /**
     *@Author:ShiYun;
     *@Description:添加一条信息
     *@Date: 11:40 2018\5\12 0012
     */
    @RequestMapping("/addOnePosttitle")
    @ResponseBody
    public String addOnePosttitle(
            @RequestParam("posttitle") String posttitle
    ){
        HRsetPosttitle hRsetPosttitle = new HRsetPosttitle();
        hRsetPosttitle.setPosttitle(posttitle);
        Integer posttitleid = ihRsetPosttitleService.addOne(hRsetPosttitle);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 11:43 2018\5\12 0012
     */
    @RequestMapping("/queryAllPosttitle")
    @ResponseBody
    public List<HRsetPosttitle> queryAllPosttitle(){
        List<HRsetPosttitle> hRsetPosttitles = ihRsetPosttitleService.queryAll();
        return hRsetPosttitles;
    }

    /**
     *@Author:ShiYun;
     *@Description:分页
     *@Date: 11:32 2018\5\19 0019
     */
    @RequestMapping("/queryHRPageInfo23")
    @ResponseBody
    public PageInfo<HRsetPosttitle> queryHRPageInfo23(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PageInfo<HRsetPosttitle> hRsetPosttitlePageInfo = ihRsetPosttitleService.queryByParam(paramMap);
        return hRsetPosttitlePageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的校验
     *@Date: 13:47 2018\5\19 0019
     */
    @RequestMapping("/queryValidateHR23")
    @ResponseBody
    public Boolean queryValidateHR23(
            @RequestParam("valueHR") String valueHR
    ){
        HRsetPosttitle hRsetPosttitle = null;
        try {
            hRsetPosttitle = ihRsetPosttitleService.queryByPosttitle(valueHR);
        } catch (Exception e) {
            return false;
        }
        if(hRsetPosttitle!=null){
            return false;
        }
        return true;
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的添加
     *@Date: 14:50 2018\5\19 0019
     */
    @RequestMapping("/addHR23")
    @ResponseBody
    public String addHR23(
            @RequestParam("valueHR")String valueHR
    ){
        HRsetPosttitle hRsetPosttitle = new HRsetPosttitle();
        hRsetPosttitle.setPosttitle(valueHR);
        try {
            ihRsetPosttitleService.addOne(hRsetPosttitle);
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
    @RequestMapping("/removeHR23")
    @ResponseBody
    public String removeHR23(
            @RequestParam("ids") List<Integer> ids
    ){
        for(Integer i=0;i<ids.size();i++){
            ihRsetPosttitleService.removeOne(ids.get(i));
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:HR的修改
     *@Date: 17:28 2018\5\19 0019
     */
    @RequestMapping("/modifyHR23")
    @ResponseBody
    public String modifyHR23(
            @RequestParam("valueHR")String valueHR,
            @RequestParam("id")Integer id
    ){
        HRsetPosttitle hRsetPosttitle = new HRsetPosttitle();
        hRsetPosttitle.setPosttitle(valueHR);
        hRsetPosttitle.setId(id);
        ihRsetPosttitleService.modifyOne(hRsetPosttitle);
        return "修改成功！";
    }
}
