package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.readexcel.ReadContractExcel;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

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
    @Autowired
    IHRsetService ihRsetService;
    @Autowired
    HrUtils hrUtils;

    @RequestMapping("/queryContractInformationByUseridAndCurTime")
    @ResponseBody
    public Object queryContractInformationByUseridAndCurTime(
            @RequestParam("userid")Integer userid
    ){
        return iContractInformationService.queryContractInformationByUseridAndCurTime(userid);
    }

    @RequestMapping("/queryContractInformations")
    @ResponseBody
    public PageInfo<ContractInformation> queryContractInformations(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            ContractInformation contractInformation
    ) throws ParseException {
        HashMap<String, Object> paraMap = new HashMap<>();
        paraMap.put("pageNum", page);
        paraMap.put("pageSize", rows);
        paraMap.put("entity", contractInformation);

        PageInfo<ContractInformation> contractInformationPageInfo = iContractInformationService.queryByConditions(paraMap);
        List<ContractInformation> list = contractInformationPageInfo.getList();
        if (list.size() == 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            // 获得工号
            Integer userid = list.get(i).getUserid();
            PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
            list.get(i).setEmployeenumber(personalInformation.getEmployeenumber());
            // 获得姓名
            User user = iUserService.getById(userid);
            list.get(i).setTruename(user.getTruename());
            //获得合同类型
            HRset hRsetContracttype = ihRsetService.queryById(list.get(i).getContracttypeid());
            if (hRsetContracttype != null) {
                list.get(i).setContracttype(hRsetContracttype.getDatavalue());
            }
            // 获得合同年限
            /*list.get(i).setContractage(IDcodeUtil.getContractage(list.get(i).getStartdate(),list.get(i).getEnddate()));*/
        }
        contractInformationPageInfo.setList(list);

        return contractInformationPageInfo;
    }

    @RequestMapping("/queryContractsByUserid")
    @ResponseBody
    public List<ContractInformation> queryContractsByUserid(
            @RequestParam("personalInformationId") Integer personalInformationId
    ) {
        List<ContractInformation> contractInformationList = null;
        if (iPersonalInformationService.queryOneById(personalInformationId) != null) {
            contractInformationList = iContractInformationService.queryByUserid(iPersonalInformationService.queryOneById(personalInformationId).getUserid());
        } else {
            return null;
        }
        return contractInformationList;
    }

    @RequestMapping("/queryOneContractInformation")
    @ResponseBody
    public List<RenewContractRecord> queryOneContractInformation(@RequestParam("contractId") int contractId) {
        List<RenewContractRecord> renewContractRecords = iRenewContractRecordService.queryRenewContractRecordsByContractId(contractId);
        for (int i = 0; i < renewContractRecords.size(); i++) {
            User user = iUserService.getById(renewContractRecords.get(i).getZhbgruserid());
            if (user != null) {
                renewContractRecords.get(i).setZhbgrtruename(user.getTruename());
            }
        }
        return renewContractRecords;
    }

    @RequestMapping("/queryContractsById")
    @ResponseBody
    public ContractInformation queryContractsById(
            @RequestParam("contractid") Integer contractid
    ) {
        ContractInformation contractInformation = iContractInformationService.queryById(contractid);
        return contractInformation;
    }

    @RequestMapping("/addContractInformation")
    @ResponseBody
    public Object addContractInformation(
            ContractInformation contractInformation,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) {
        //获得办理人ID
        contractInformation.setTransactoruserid(hrUtils.getUseridByUsername(transactorusername));
        //获得附件
        contractInformation.setAttachment(hrUtils.getSignalFileAddress(request,"attfile","/hr/file/"));
        //获得办理日期
        contractInformation.setTransdate(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        //将合同添加到数据库中
        Integer contractInformaionId = iContractInformationService.addOne(contractInformation);
        return null == contractInformaionId ? RespUtil.successResp("500", "添加失败！", null) : RespUtil.successResp("200", "添加成功！", contractInformaionId);
    }

    @RequestMapping("/updateContractInformation")
    @ResponseBody
    public Object updateContractInformation(
            ContractInformation contractInformation,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) {
        //获得办理人ID
        contractInformation.setTransactoruserid(hrUtils.getUseridByUsername(transactorusername));
        //获得附件
        contractInformation.setAttachment(hrUtils.getSignalFileAddress(request,"attfile","/hr/file/"));
        //获得办理日期
        contractInformation.setTransdate(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        //将合同提交到数据库中
        return iContractInformationService.modifyOne(contractInformation);
    }

    @RequestMapping("/queryAllContractInformations")
    @ResponseBody
    public List<ContractInformation> queryAllContractInformations() throws ParseException {
        ContractInformation contractInformation = new ContractInformation();
        List<ContractInformation> contractInformationList = iContractInformationService.queryAll(contractInformation);
        for (ContractInformation contractInformation1 : contractInformationList) {
            if (iUserService.getById(contractInformation1.getUserid()) != null) {
                contractInformation1.setTruename(iUserService.getById(contractInformation1.getUserid()).getTruename());
            }
            if (iPersonalInformationService.queryOneByUserid(contractInformation1.getUserid()) != null) {
                contractInformation1.setEmployeenumber(iPersonalInformationService.queryOneByUserid(contractInformation1.getUserid()).getEmployeenumber());
            }
            HRset hRsetContracttype = ihRsetService.queryById(contractInformation.getContracttypeid());
            if (hRsetContracttype != null) {
                contractInformation1.setContracttype(hRsetContracttype.getDatavalue());
            }
            try {
                contractInformation1.setContractage(hrUtils.getContractage(contractInformation1.getStartdate(), contractInformation1.getEnddate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (iUserService.getById(contractInformation1.getTransactoruserid()) != null) {
                contractInformation1.setTransactortruename(iUserService.getById(contractInformation1.getTransactoruserid()).getTruename());
            }
        }
        return contractInformationList;
    }

    @RequestMapping("/deleteContractsByIds")
    @ResponseBody
    public String deleteContractsByIds(
            @RequestParam("contractids") List<Integer> contractids
    ) {
        for (int i = 0; i < contractids.size(); i++) {
            iContractInformationService.removeOne(contractids.get(i));
        }
        return "删除成功！";
    }

    @RequestMapping("/importContractinformations")
    @ResponseBody
    public String importContractinformations(
            @RequestParam("file") MultipartFile multipartFile
    ) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            ReadContractExcel readContractExcel = new ReadContractExcel();
            List<ContractInformation> excelInfo = readContractExcel.getExcelInfo(multipartFile);
            for (ContractInformation contractInformation : excelInfo) {
                //合同编号为必填项
                if (StringUtils.isEmpty(contractInformation.getContractcode())) continue;
                //姓名为必填项
                if (StringUtils.isEmpty(contractInformation.getTruename())) {
                    responseMap.put("合同编号[" + contractInformation.getContractcode() + "]", "姓名为空");
                    continue;
                }
                //姓名为必填项
                if (null != iUserService.queryByTruename(contractInformation.getTruename())) {
                    contractInformation.setUserid(iUserService.queryByUsername(contractInformation.getTruename()).getId());
                } else {
                    responseMap.put("合同编号[" + contractInformation.getContractcode() + "]，", "所对应的姓名不存在:亲，请先在人力资源-->人事档案-->人事信息中导入人事信息！");
                    continue;
                }
                //根据合同编号查询合同，有则更新，没有则添加
                ContractInformation contractcodeInstance = iContractInformationService.queryByContractcode(contractInformation.getContractcode());
                if (null==contractcodeInstance) {
                    addContractcode(contractInformation);
                } else {
                    contractInformation.setId(contractcodeInstance.getId());
                    updateContractcode(contractInformation);
                }
            }
        } catch (Exception e) {
            return "导入失败！";
        }
        return responseMap.size()==0?"导入成功！":JSON.toJSONString(responseMap);
    }

    private void updateContractcode(ContractInformation contractInformation) throws ParseException {
        List<HRset> hRsetContracttypeList = ihRsetService.queryByConditions(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
        Integer contracttypeid = null;
        if (null != hRsetContracttypeList && hRsetContracttypeList.size() == 1) {
            contracttypeid = hRsetContracttypeList.get(0).getId();
        }else if(null==hRsetContracttypeList || hRsetContracttypeList.size()==0){
            Resp resp = (Resp) ihRsetService.addOne(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
            String rspCode = resp.getHead().getRspCode();
            if("200".equals(rspCode)){
                contracttypeid = (Integer) resp.getBody();
            }
        }
        contractInformation.setContracttypeid(contracttypeid);
        //办理人不存在则添加默认值（管理员-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransactortruename()))
            contractInformation.setTransactortruename("admin");
        User transactor = iUserService.queryByUsername(contractInformation.getTransactortruename());
        if (transactor != null) {
            contractInformation.setTransactoruserid(transactor.getId());
        }
        //办理日期不存在则添加默认值（入职时间-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransdate()))
            contractInformation.setTransdate(contractInformation.getStartdate());
        //合同结束时间不存在则添加默认值（开始时间-->实际情况不能这么搞）
        iContractInformationService.modifyOne(contractInformation);
    }

    private void addContractcode(ContractInformation contractInformation) throws ParseException {
        List<HRset> hRsetContracttypeList = ihRsetService.queryByConditions(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
        Integer contracttypeid = null;
        if (null != hRsetContracttypeList && hRsetContracttypeList.size() == 1) {
            contracttypeid = hRsetContracttypeList.get(0).getId();
        }else if(null==hRsetContracttypeList || hRsetContracttypeList.size()==0){
            Resp resp = (Resp) ihRsetService.addOne(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
            String rspCode = resp.getHead().getRspCode();
            if("200".equals(rspCode)){
                contracttypeid = (Integer) resp.getBody();
            }
        }
        contractInformation.setContracttypeid(contracttypeid);
        //办理人不存在则添加默认值（管理员-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransactortruename()))
            contractInformation.setTransactortruename("admin");
        User transactor = iUserService.queryByUsername(contractInformation.getTransactortruename());
        if (transactor != null) {
            contractInformation.setTransactoruserid(transactor.getId());
        }
        //办理日期不存在则添加默认值（入职时间-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransdate()))
            contractInformation.setTransdate(contractInformation.getStartdate());
        //合同结束时间不存在则添加默认值（开始时间-->实际情况不能这么搞）
        iContractInformationService.addOne(contractInformation);
    }
}
