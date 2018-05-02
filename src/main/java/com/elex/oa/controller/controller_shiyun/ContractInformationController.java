package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.ContractInformation;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.entity.entity_shiyun.RenewContractRecord;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IContractInformationService;
import com.elex.oa.service.service_shiyun.IPersonalInformationService;
import com.elex.oa.service.service_shiyun.IRenewContractRecordService;
import com.elex.oa.service.service_shiyun.IUserService;
import com.elex.oa.util.util_shiyun.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:合同信息
 * @Date:Created in  16:07 2018\4\9 0009
 * @Modify By:
 */
@Controller
@CrossOrigin
public class ContractInformationController {

    @Autowired
    IContractInformationService iContractInformationService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IRenewContractRecordService iRenewContractRecordService;

    /**
     *@Author:ShiYun;
     *@Description:合同信息的查询
     *@Date: 16:08 2018\4\9 0009
     */
    @RequestMapping("/queryContractInformations")
    @ResponseBody
    public PageHelper<ContractInformation> queryContractInformations(@RequestParam("page") int page,
                                                                     @RequestParam("rows") int rows,
                                                                     @RequestParam("truename") String truename,
                                                                     @RequestParam("contractcode") String contractcode,
                                                                     @RequestParam("ed") String ed){
        HashMap<String, Object> paraMap = new HashMap<>();
        paraMap.put("pageNum",page);
        paraMap.put("pageSize",rows);
        paraMap.put("ed",ed);

        ContractInformation contractInformation = new ContractInformation();
        if(truename!=null && truename!=""){
            User user = new User();
            user.setTruename(truename);
            user = iUserService.selectOne(user);
            if (user!=null) {
                contractInformation.setUserid(user.getId());
            } else {
                return null;
            }
        }
        if(contractcode!=null && contractcode!=""){
            contractInformation.setContractcode(contractcode);
        }
        paraMap.put("entity",contractInformation);

        PageHelper<ContractInformation> contractInformationPageHelper = iContractInformationService.queryByConditions(paraMap);
        List<ContractInformation> list = contractInformationPageHelper.getList();
        if(list.size()==0){
            return null;
        }
        for(int i=0;i<list.size();i++){
            // 获得工号
            Integer userid = list.get(i).getUserid();
            PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
            list.get(i).setEmployeenumber(personalInformation.getEmployeenumber());
            // 获得姓名
            User user = iUserService.getById(userid);
            list.get(i).setTruename(user.getTruename());
            // 获得合同年限
            String startdate = list.get(i).getStartdate();
            String enddate = list.get(i).getEnddate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date sdate = new Date();
            Date edate = new Date();
            try {
                sdate = simpleDateFormat.parse(startdate);
                edate = simpleDateFormat.parse(enddate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(sdate);
            c2.setTime(edate);
            int sYear = c1.get(c1.YEAR);
            int sMonth = c1.get(c1.MONTH);
            int eYear = c2.get(c2.YEAR);
            int eMonth = c2.get(c2.MONTH);
            String contractlife = "";
            int age = eYear - sYear;
            int mm = eMonth- sMonth;
            if(mm<0){
                mm = 12 + mm;
                age = age - 1;
            }
            if(age!=0){
                contractlife = contractlife + age + "年";
            }
            if(mm!=0){
                contractlife = contractlife + mm + "个月";
            }
            list.get(i).setContractlife(contractlife);
        }
        contractInformationPageHelper.setList(list);

        return contractInformationPageHelper;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询合同续签记录
     *@Date: 13:29 2018\4\10 0010
     */
    @RequestMapping("/queryOneContractInformation")
    @ResponseBody
    public List<RenewContractRecord> queryOneContractInformation(@RequestParam("contractId") int contractId){
        List<RenewContractRecord> renewContractRecords = iRenewContractRecordService.queryRenewContractRecordsByContractId(contractId);
        for(int i=0;i<renewContractRecords.size();i++){
            User user = iUserService.getById(renewContractRecords.get(i).getZhbgruserid());
            if (user!=null) {
                renewContractRecords.get(i).setZhbgrtruename(user.getTruename());
            }
        }
        return renewContractRecords;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据userID查询合同信息
     *@Date: 17:21 2018\4\12 0012
     */
    @RequestMapping("/queryContractsByUserid")
    @ResponseBody
    public List<ContractInformation> queryContractsByUserid(
            @RequestParam("personalInformationId") Integer personalInformationId
    ){
        ContractInformation contractInformation = new ContractInformation();
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        contractInformation.setUserid(personalInformation.getUserid());
        List<ContractInformation> list = iContractInformationService.queryByEntity(contractInformation);
        if(list.size()==0){
            return null;
        }
        for(int i=0;i<list.size();i++){
            // 获得合同年限
            String startdate = list.get(i).getStartdate();
            String enddate = list.get(i).getEnddate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date sdate = new Date();
            Date edate = new Date();
            try {
                sdate = simpleDateFormat.parse(startdate);
                edate = simpleDateFormat.parse(enddate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(sdate);
            c2.setTime(edate);
            int sYear = c1.get(c1.YEAR);
            int sMonth = c1.get(c1.MONTH);
            int eYear = c2.get(c2.YEAR);
            int eMonth = c2.get(c2.MONTH);
            String contractlife = "";
            int age = eYear - sYear;
            int mm = eMonth- sMonth;
            if(mm<0){
                mm = 12 + mm;
                age = age - 1;
            }
            if(age!=0){
                contractlife = contractlife + age + "年";
            }
            if(mm!=0){
                contractlife = contractlife + mm + "个月";
            }
            list.get(i).setContractlife(contractlife);
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加合同信息
     *@Date: 14:16 2018\4\20 0020
     */
    @RequestMapping("/addContractInformation")
    @ResponseBody
    public String addContractInformation(
            ContractInformation contractInformation
    ){
        Integer contractInformaionId = iContractInformationService.addOne(contractInformation);
        return "添加成功！";
    }
}
