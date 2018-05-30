package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.DimissionInformation;
import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.*;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    IUserService iUserService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IPerandpostrsService iPerandpostrsService;
    @Autowired
    IPostService iPostService;

    /**
     *@Author:ShiYun;
     *@Description:添加离职信息
     *@Date: 17:00 2018\4\16 0016
     */
    @RequestMapping("/addDimission")
    @ResponseBody
    public String addDimission(
            DimissionInformation dimissionInformation,
            @RequestParam("transactorusername")String transactorusername
    ){
        //获得办理人的ID
        User user = new User();
        user.setUsername(transactorusername);
        User user1 = iUserService.selectOne(user);
        dimissionInformation.setTransactoruserid(user1.getId());
        //添加到数据库中
        Integer dimissionInformationId = iDimissionInformationService.addOne(dimissionInformation);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询离职信息(分页)
     *@Date: 18:01 2018\4\16 0016
     */
    @RequestMapping("/queryDimissionInformations")
    @ResponseBody
    public PageInfo<DimissionInformation> queryDimissionInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            DimissionInformation dimissionInformation
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",dimissionInformation);
        PageInfo<DimissionInformation> dimissionInformationPageInfo = iDimissionInformationService.queryByCondition(paramMap);
        return dimissionInformationPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:导出离职信息
     *@Date: 15:05 2018\5\29 0029
     */
    @RequestMapping("/queryAllDimissionInformations")
    @ResponseBody
    public List<DimissionInformation> queryAllDimissionInformations(){
        List<DimissionInformation> dimissionInformations = iDimissionInformationService.queryAll();
        return dimissionInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据dimissionid确认离职信息
     *@Date: 13:35 2018\4\17 0017
     */
    @RequestMapping("/modifyDimissionInformationById")
    @ResponseBody
    public String modifyDimissionInformationById(
           DimissionInformation dimissionInformation
    ){
        Boolean b = false;
        DimissionInformation dimissionInformation1 = iDimissionInformationService.queryOneById(dimissionInformation.getId());
        if(dimissionInformation1.getApprovalstatue()!=dimissionInformation.getApprovalstatue() ||
           dimissionInformation1.getWorkingstatue()!=dimissionInformation.getWorkingstatue() ||
           dimissionInformation1.getFilestatue()!=dimissionInformation.getFilestatue() ||
           dimissionInformation1.getOfficesupplystatue()!=dimissionInformation.getOfficesupplystatue()){
            b = true;
        }
        if (b) {
            iDimissionInformationService.modifyOne(dimissionInformation);
        } else {
            return "没有修改项！";
        }
        return "确认成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询离职信息
     *@Date: 11:18 2018\5\30 0030
     */
    @RequestMapping("/queryDimissionInformationById")
    @ResponseBody
    public DimissionInformation queryDimissionInformationById(
            @RequestParam("dimissionid")Integer dimissionid
    ){
        DimissionInformation dimissionInformation = iDimissionInformationService.queryOneById(dimissionid);
        /**
         * 其中的
         * 待审批单数量（approvalnumbers）、
         * 待办任务数量（workingnumbers）、
         * 文件占用数量（filenumbers）、
         * 办公用品数量（officesupplynumbers）
         * 需要从流程、文件管理、仓库拿接口，前期测试从数据库拿假数据代替
         * */
        return dimissionInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除离职信息
     *@Date: 13:39 2018\5\30 0030
     */
    @RequestMapping("/removeDimissionInformations")
    @ResponseBody
    public String removeDimissionInformations(
            @RequestParam("dimissionids")List<Integer> dimissionids
    ){
        for(int i=0;i<dimissionids.size();i++){
            iDimissionInformationService.removeOne(dimissionids.get(i));
        }
        return "提交成功！";
    }
}
