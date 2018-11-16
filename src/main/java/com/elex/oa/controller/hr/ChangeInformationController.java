package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.entity.hr_entity.ChangeInformation;
import com.elex.oa.entity.hr_entity.ReadChangeinformationExcel;
import com.elex.oa.service.hr_service.IChangeInformationService;
import com.elex.oa.service.hr_service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:变更信息
 * @Date:Created in  11:44 2018\4\13 0013
 * @Modify By:
 */
@Controller
@CrossOrigin
public class ChangeInformationController {
    @Autowired
    IChangeInformationService iChangeInformationService;
    @Autowired
    IUserService iUserService;

    /**
     *@Author:ShiYun;
     *@Description:变更信息的列表
     *@Date: 11:48 2018\4\13 0013
     */
    @RequestMapping("/queryChangeInformations")
    @ResponseBody
    public PageInfo<ChangeInformation> queryChangeInformations(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            ChangeInformation changeInformation
    ){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        paramMap.put("entity",changeInformation);
        PageInfo<ChangeInformation> changeInformationPageInfo = iChangeInformationService.queryAll(paramMap);
        return changeInformationPageInfo;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询信息（不分页）
     *@Date: 10:46 2018\5\25 0025
     */
    @RequestMapping("/queryAllChangeInformations")
    @ResponseBody
    public List<ChangeInformation> queryAllChangeInformations(){
        List<ChangeInformation> changeInformations = iChangeInformationService.queryAll();
        return changeInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:删除入职信息
     *@Date: 10:57 2018\5\25 0025
     */
    @RequestMapping("/deleteChangeinformationByIds")
    @ResponseBody
    public String deleteChangeinformationByIds(
            @RequestParam("changeinformationids") List<Integer> changeinformationids
    ){
        for(Integer changeinformationid:changeinformationids){
            try {
                iChangeInformationService.removeOne(changeinformationid);
            } catch (Exception e) {
                return "删除失败！";
            }
        }
        return "删除成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:数据的导入
     *@Date: 10:59 2018\5\25 0025
     */
    @RequestMapping("/importChangeinformations")
    @ResponseBody
    public String importChangeinformations(
            @RequestParam("file") MultipartFile multipartFile
    ){
        Map<String,String> responseMap = new HashMap<>();
        try {
            ReadChangeinformationExcel readChangeinformationExcel = new ReadChangeinformationExcel();
            List<ChangeInformation> excelInfo = readChangeinformationExcel.getExcelInfo(multipartFile);
            for(ChangeInformation changeInformation:excelInfo){
                if (iUserService.queryByTruename(changeInformation.getChangedtruename())!=null) {
                    changeInformation.setChangeduserid(iUserService.queryByTruename(changeInformation.getChangedtruename()).getId());
                }else {
                    responseMap.put(changeInformation.getChangedtruename(),"该员工查不到，请重新导入");
                    continue;
                }
                if (iUserService.queryByTruename(changeInformation.getTransactortruename())!=null) {
                    changeInformation.setTransactoruserid(iUserService.queryByTruename(changeInformation.getTransactortruename()).getId());
                }else {
                    responseMap.put(changeInformation.getTransactortruename(),"该办理人查不到，请重新导入");
                    continue;
                }
                iChangeInformationService.addOne(changeInformation);
            }
        } catch (Exception e) {
            return "数据导入失败！";
        }
        return responseMap.size()==0?"数据导入成功！":(JSON.toJSONString(responseMap));
    }
}
