package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.DimissionInformation;
import com.elex.oa.service.service_shiyun.IDimissionInformationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Author:ShiYun;
 * @Description:离职信息
 * @Date:Created in  16:27 2018\4\16 0016
 * @Modify By:
 */
@Controller
@CrossOrigin
public class DimissionController {
    @Autowired
    IDimissionInformationService iDimissionInformationService;

    /**
     *@Author:ShiYun;
     *@Description:添加离职信息
     *@Date: 17:00 2018\4\16 0016
     */
    @RequestMapping("/addDimission")
    @ResponseBody
    public String addDimission(DimissionInformation dimissionInformation){
        Integer dimissionInformationId = iDimissionInformationService.addOne(dimissionInformation);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据离职人员的id查询离职信息
     *@Date: 18:01 2018\4\16 0016
     */
    @RequestMapping("/queryDimissionInformationsByDimissionuserid")
    @ResponseBody
    public PageInfo<DimissionInformation> queryDimissionInformationsByDimissionuserid(@RequestParam("dimissionuserid") Integer dimissionuserid,
                                                                                       @RequestParam("page") Integer page,
                                                                                       @RequestParam("rows") Integer rows){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        DimissionInformation dimissionInformation = new DimissionInformation();
        dimissionInformation.setDimissionuserid(dimissionuserid);
        paramMap.put("entity",dimissionInformation);
        PageInfo<DimissionInformation> dimissionInformationPageInfo = iDimissionInformationService.queryByCondition(paramMap);
        return dimissionInformationPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid查询离职信息
     *@Date: 13:35 2018\4\17 0017
     */
    @RequestMapping("/queryDimissionInformationByDimissionid")
    @ResponseBody
    public String queryDimissionInformationByDimissionid(DimissionInformation dimissionInformation){
        iDimissionInformationService.modifyOne(dimissionInformation);
        return "数据处理确认成功！";
    }
}
