package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.common.common_shiyun.Commons;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.service_shiyun.*;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.elex.oa.util.util_shiyun.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    IHRsetContracttypeService ihRsetContracttypeService;

    /**
     *@Author:ShiYun;
     *@Description:合同信息的查询
     *@Date: 16:08 2018\4\9 0009
     */
    @RequestMapping("/queryContractInformations")
    @ResponseBody
    public PageInfo<ContractInformation> queryContractInformations(
            @RequestParam("page") int page,
             @RequestParam("rows") int rows,
             ContractInformation contractInformation
    ) throws ParseException {
        HashMap<String, Object> paraMap = new HashMap<>();
        paraMap.put("pageNum",page);
        paraMap.put("pageSize",rows);
        paraMap.put("entity",contractInformation);

        PageInfo<ContractInformation> contractInformationPageInfo = iContractInformationService.queryByConditions(paraMap);
        List<ContractInformation> list = contractInformationPageInfo.getList();
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
            //获得合同类型
            if (ihRsetContracttypeService.queryById(list.get(i).getContracttypeid())!=null) {
                list.get(i).setContracttype(ihRsetContracttypeService.queryById(list.get(i).getContracttypeid()).getContracttype());
            }
            // 获得合同年限
            list.get(i).setContractage(IDcodeUtil.getContractage(list.get(i).getStartdate(),list.get(i).getEnddate()));
        }
        contractInformationPageInfo.setList(list);

        return contractInformationPageInfo;
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
    @RequestMapping("/queryContractsById")
    @ResponseBody
    public ContractInformation queryContractsById(
            @RequestParam("contractid") Integer contractid
    ){
        ContractInformation contractInformation = iContractInformationService.queryById(contractid);
        return contractInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加合同信息
     *@Date: 14:16 2018\4\20 0020
     */
    @RequestMapping("/addContractInformation")
    @ResponseBody
    public String addContractInformation(
            ContractInformation contractInformation,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) throws IOException {
        //获得办理人ID
        User user = new User();
        user.setUsername(transactorusername);
        contractInformation.setTransactoruserid(iUserService.selectByCondition(user).get(0).getId());
        //获得附件
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartHttpServletRequest.getFiles("attfile");
        if(files.size()!=0){
            String realPath = Commons.realpath + "/hr/file";
            Long l = Calendar.getInstance().getTimeInMillis();
            File file = new File(realPath + "/" + l);
            file.mkdirs();
            String attachment = "/hr/file/" + l + "/" + files.get(0).getOriginalFilename();
            files.get(0).transferTo(new File(realPath + "/" + l,files.get(0).getOriginalFilename()));
            contractInformation.setAttachment(attachment);
        }
        //获得办理日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String transdate = simpleDateFormat.format(new Date());
        contractInformation.setTransdate(transdate);

        //将合同添加到数据库中
        Integer contractInformaionId = iContractInformationService.addOne(contractInformation);
        return "添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:修改合同信息
     *@Date: 9:59 2018\5\29 0029
     */
    @RequestMapping("/updateContractInformation")
    @ResponseBody
    public String updateContractInformation(
            ContractInformation contractInformation,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) throws IOException {
        Boolean b = false;
        ContractInformation c2 = iContractInformationService.queryById(contractInformation.getId());
        //获得办理人ID
        User user = new User();
        user.setUsername(transactorusername);
        if (iUserService.selectByCondition(user).get(0)!=null && iUserService.selectByCondition(user).get(0).getId()!=c2.getTransactoruserid()) {
            contractInformation.setTransactoruserid(iUserService.selectByCondition(user).get(0).getId());
            b = true;
        }
        //获得附件
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartHttpServletRequest.getFiles("attfile");
        if(files.size()!=0){
            String realPath = Commons.realpath + "/hr/file";
            Long l = Calendar.getInstance().getTimeInMillis();
            File file = new File(realPath + "/" + l);
            file.mkdirs();
            String attachment = "/hr/file/" + l + "/" + files.get(0).getOriginalFilename();
            files.get(0).transferTo(new File(realPath + "/" + l,files.get(0).getOriginalFilename()));
            contractInformation.setAttachment(attachment);
            b = true;
        }
        if(!c2.getContractcode().equals(contractInformation.getContractcode()) ||
            !c2.getStartdate().equals(contractInformation.getStartdate()) ||
            !c2.getEnddate().equals(contractInformation.getEnddate()) ||
            c2.getContracttypeid()!=contractInformation.getContracttypeid() ||
            !c2.getRemark().equals(contractInformation.getRemark())     ){
            b = true;
        }
        //获得办理日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String transdate = simpleDateFormat.format(new Date());
        if (b) {
            contractInformation.setTransdate(transdate);
        }

        //将合同提交到数据库中
        if (b) {
            iContractInformationService.modifyOne(contractInformation);
        }
        if (b) {
            return "提交成功！";
        }else {
            return "请修改数据后再提交！";
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有合同信息（不分页）
     *@Date: 16:01 2018\5\25 0025
     */
    @RequestMapping("/queryAllContractInformations")
    @ResponseBody
    public List<ContractInformation> queryAllContractInformations() throws ParseException {
        ContractInformation contractInformation = new ContractInformation();
        List<ContractInformation> contractInformationList = iContractInformationService.queryAll(contractInformation);
        for(ContractInformation contractInformation1:contractInformationList){
            if (iUserService.getById(contractInformation1.getUserid())!=null) {
                contractInformation1.setTruename(iUserService.getById(contractInformation1.getUserid()).getTruename());
            }
            if (iPersonalInformationService.queryOneByUserid(contractInformation1.getUserid())!=null) {
                contractInformation1.setEmployeenumber(iPersonalInformationService.queryOneByUserid(contractInformation1.getUserid()).getEmployeenumber());
            }
            if (ihRsetContracttypeService.queryById(contractInformation1.getContracttypeid())!=null) {
                contractInformation1.setContracttype(ihRsetContracttypeService.queryById(contractInformation1.getContracttypeid()).getContracttype());
            }
            contractInformation1.setContractage(IDcodeUtil.getContractage(contractInformation1.getStartdate(),contractInformation1.getEnddate()));
            if (iUserService.getById(contractInformation1.getTransactoruserid())!=null) {
                contractInformation1.setTransactortruename(iUserService.getById(contractInformation1.getTransactoruserid()).getTruename());
            }
        }
        return contractInformationList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除合同信息
     *@Date: 10:40 2018\5\29 0029
     */
    @RequestMapping("/deleteContractsByIds")
    @ResponseBody
    public String deleteContractsByIds(
            @RequestParam("contractids") List<Integer> contractids
    ){
        for(int i=0;i<contractids.size();i++){
            iContractInformationService.removeOne(contractids.get(i));
        }
        return "删除成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:导入合同信息
     *@Date: 11:04 2018\5\29 0029
     */
    @RequestMapping("/importContractinformations")
    @ResponseBody
    public String importContractinformations(
            @RequestParam("file") MultipartFile multipartFile
    ){
        try {
            ReadContractExcel readContractExcel = new ReadContractExcel();
            List<ContractInformation> excelInfo = readContractExcel.getExcelInfo(multipartFile);
            for(ContractInformation contractInformation:excelInfo){
                if (iUserService.queryByTruename(contractInformation.getTruename())!=null) {
                    contractInformation.setUserid(iUserService.queryByTruename(contractInformation.getTruename()).getId());
                }
                if (ihRsetContracttypeService.queryByContracttype(contractInformation.getContracttype())!=null) {
                    contractInformation.setContracttypeid(ihRsetContracttypeService.queryByContracttype(contractInformation.getContracttype()).getId());
                }
                if (iUserService.queryByTruename(contractInformation.getTransactortruename())!=null) {
                    contractInformation.setTransactoruserid(iUserService.queryByTruename(contractInformation.getTransactortruename()).getId());
                }
                iContractInformationService.addOne(contractInformation);
                /*System.out.println(contractInformation.getContractcode());
                System.out.println(contractInformation.getUserid());
                System.out.println(contractInformation.getStartdate());
                System.out.println(contractInformation.getEnddate());
                System.out.println(contractInformation.getContracttypeid());
                System.out.println(contractInformation.getAttachment());
                System.out.println(contractInformation.getRemark());
                System.out.println(contractInformation.getTransactoruserid());
                System.out.println(contractInformation.getTransdate());*/
            }
        } catch (Exception e) {
            return "导入失败！";
        }
        return "导入成功！";
    }
}
