package com.elex.oa.controller.hr;

import com.elex.oa.service.restructure_hrService.IContractInfoService;
import com.elex.oa.service.restructure_hrService.IHrdatadictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 将旧表的数据更新到新表里面
 * @Author shiyun
 * @Date 2018\11\27 0027 11:44
 * @Version 1.0
 **/
@CrossOrigin
@Controller
@RequestMapping("/changetable")
public class TablesController {
    @Autowired
    IHrdatadictionaryService iHrdatadictionaryService;
    @Autowired
    IContractInfoService iContractInfoService;

    @RequestMapping("/tb_hr_data_dictionary")
    @ResponseBody
    public String changetable_tb_hr_data_dictionary(){
        Boolean aBoolean = iHrdatadictionaryService.changeTable();
        return aBoolean?"更新数据成功":"更新失败";
    }

    @RequestMapping("/tb_hr_contract_info")
    @ResponseBody
    public String changetable_tb_hr_contract_info(){
        Boolean aBoolean = iContractInfoService.changeTable();
        return aBoolean?"更新数据成功":"更新失败";
    }


}    