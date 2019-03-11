package com.elex.oa.controller.restructure_hr;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.service.restructure_hrService.*;
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
    IContractinfoService iContractinfoService;
    @Autowired
    IDepinfoService iDepinfoService;
    @Autowired
    IPostinfoService iPostinfoService;
    @Autowired
    IPostlevelrelationshipinfoService iPostlevelrelationshipinfoService;
    @Autowired
    IPersonalinfoService iPersonalinfoService;
    @Autowired
    IPostandpersonalrelationshipService iPostandpersonalrelationshipService;

    @RequestMapping("/tb_hr_data_dictionary")
    @ResponseBody
    public String changetableTbHrDataDictionary(){
        Boolean aBoolean = iHrdatadictionaryService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_hr_contract_info")
    @ResponseBody
    public String changetableTbHrContractInfo(){
        Boolean aBoolean = iContractinfoService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_id_dep_info")
    @ResponseBody
    public String changetableTbIdDepInfo(){
        Boolean aBoolean = iDepinfoService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_id_post_info")
    @ResponseBody
    public String changetableTbIdPostInfo(){
        Boolean aBoolean = iPostinfoService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_id_post_info/updateNodelevel")
    @ResponseBody
    public String changetableTbIdPostInfoUpdateNodelevel(){
        Boolean aBoolean = iPostinfoService.updateNodelevel();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_postlevel_relationship_info")
    @ResponseBody
    public String changetableTbPostlevelRelationshipInfo(){
        Boolean aBoolean = iPostlevelrelationshipinfoService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_id_personal_info")
    @ResponseBody
    public String changetableTbIdPersonalInfo(){
        Boolean aBoolean = iPersonalinfoService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }

    @RequestMapping("/tb_postandpersonal_relationship_info")
    @ResponseBody
    public String changetableTbPostandpersonalRelationshipInfo(){
        Boolean aBoolean = iPostandpersonalrelationshipService.changeTable();
        return aBoolean?Commons.RESP_SUCCESS:Commons.RESP_FAIL;
    }


}    