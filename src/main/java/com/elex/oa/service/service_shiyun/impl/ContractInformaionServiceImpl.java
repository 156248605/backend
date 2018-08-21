package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IContractInformationDao;
import com.elex.oa.dao.dao_shiyun.IHRsetContracttypeDao;
import com.elex.oa.dao.dao_shiyun.IPersonalInformationDao;
import com.elex.oa.dao.dao_shiyun.IUserDao;
import com.elex.oa.entity.entity_shiyun.ContractInformation;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IContractInformationService;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:合同信息（业务层）
 * @Date:Created in  16:53 2018\4\9 0009
 * @Modify By:
 */
@Service
public class ContractInformaionServiceImpl implements IContractInformationService {

    @Autowired
    IContractInformationDao iContractInformationDao;
    @Autowired
    IUserDao iUserDao;
    @Autowired
    IHRsetContracttypeDao ihRsetContracttypeDao;
    @Autowired
    IPersonalInformationDao iPersonalInformationDao;

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询合同信息
     *@Date: 16:55 2018\4\9 0009
     */
    @Override
    public PageInfo<ContractInformation> queryByConditions(Map<String, Object> paramMap) {

        ContractInformation contractInformation = (ContractInformation) paramMap.get("entity");
        List<User> users = iUserDao.selectByConditions(contractInformation);
        if(users.size()!=0){
            ArrayList<Integer> userids = new ArrayList<>();
            for(User user:users){
                userids.add(user.getId());
            }
            if (userids.size()!=0) {
                contractInformation.setUserids(userids);
            }
        }
        contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getContractage()));
        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);

        List<ContractInformation> contractInformationList = iContractInformationDao.selectByConditions(contractInformation);

        return new PageInfo<ContractInformation>(contractInformationList);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询合同信息
     *@Date: 16:55 2018\4\9 0009
     */
    @Override
    public ContractInformation queryById(Integer id) {
        ContractInformation contractInformation = iContractInformationDao.selectById(id);
        //获得姓名
        if (contractInformation!=null && contractInformation.getUserid()!=null && iUserDao.selectById(contractInformation.getUserid())!=null) {
            contractInformation.setTruename(iUserDao.selectById(contractInformation.getUserid()).getTruename());
        }
        //获得工号
        Integer uid = contractInformation.getUserid();
        System.out.println(uid);
        PersonalInformation per = iPersonalInformationDao.selectByUserid(uid);
        System.out.println(per);
        System.out.println(per.getEmployeenumber());
        contractInformation.setEmployeenumber(iPersonalInformationDao.selectByUserid(contractInformation.getUserid()).getEmployeenumber());
        //获得合同类型
        if (ihRsetContracttypeDao.selectById(contractInformation.getContracttypeid())!=null) {
            contractInformation.setContracttype(ihRsetContracttypeDao.selectById(contractInformation.getContracttypeid()).getContracttype());
        }
        //获得办理人姓名
        contractInformation.setTransactortruename(iUserDao.selectById(contractInformation.getTransactoruserid()).getTruename());
        //获得合同期限
        try {
            contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
        } catch (ParseException e) {
            System.out.println("获得合同期限失败！");
        }
        //获得续签合同集合
        List<ContractInformation> contractInformationList = iContractInformationDao.selectByUserid(contractInformation.getUserid());
        for(ContractInformation contractInformation1:contractInformationList){
            contractInformation1.setTransactortruename(iUserDao.selectById(contractInformation.getTransactoruserid()).getTruename());
        }
        contractInformation.setHistoryContract(contractInformationList);
        return contractInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据userid查询合同信息
     *@Date: 16:12 2018\5\30 0030
     */
    @Override
    public List<ContractInformation> queryByUserid(Integer userid) {
        List<ContractInformation> contractInformationList = iContractInformationDao.selectByUserid(userid);
        for(ContractInformation contractInformation:contractInformationList){
            //获得姓名
            if (contractInformation!=null && contractInformation.getUserid()!=null && iUserDao.selectById(contractInformation.getUserid())!=null) {
                contractInformation.setTruename(iUserDao.selectById(contractInformation.getUserid()).getTruename());
            }
            //获得工号
            contractInformation.setEmployeenumber(iPersonalInformationDao.selectByUserid(contractInformation.getUserid()).getEmployeenumber());
            //获得合同类型
            contractInformation.setContracttype(ihRsetContracttypeDao.selectById(contractInformation.getContracttypeid()).getContracttype());
            //获得办理人姓名
            contractInformation.setTransactortruename(iUserDao.selectById(contractInformation.getTransactoruserid()).getTruename());
            //获得合同期限
            try {
                contractInformation.setContractage(IDcodeUtil.getContractage(contractInformation.getStartdate(),contractInformation.getEnddate()));
            } catch (ParseException e) {
                System.out.println("获得合同期限失败！");
            }
        }
        return contractInformationList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件（不分页）查询合同信息
     *@Date: 17:37 2018\4\12 0012
     */
    @Override
    public List<ContractInformation> queryByEntity(ContractInformation contractInformation) {
        List<ContractInformation> contractInformations = iContractInformationDao.selectByConditions(contractInformation);
        return contractInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有信息
     *@Date: 17:51 2018\5\25 0025
     */
    public List<ContractInformation> queryAll(ContractInformation contractInformation){
        List<ContractInformation> contractInformationList = iContractInformationDao.selectAll(contractInformation);
        for (ContractInformation con:contractInformationList
             ) {
            Integer state = iUserDao.selectById(con.getUserid()).getState();
            con.setState(state.toString());
        }
        return contractInformationList;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加合同信息并返回主键
     *@Date: 14:28 2018\4\20 0020
     */
    @Override
    public Integer addOne(ContractInformation contractInformation) throws ParseException {
        String contractage = IDcodeUtil.getContractage(contractInformation.getStartdate(), contractInformation.getEnddate());
        if (contractage!=null && !contractage.equals("")) {
            contractInformation.setContractage(contractage);
        }else {
            contractInformation.setContractage("0");
        }
        Integer integer = iContractInformationDao.insertOne(contractInformation);
        return contractInformation.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除合同信息
     *@Date: 17:50 2018\5\25 0025
     */
    @Override
    public void removeOne(Integer id) {
        iContractInformationDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:修改合同信息
     *@Date: 10:14 2018\5\29 0029
     */
    @Override
    public void modifyOne(ContractInformation contractInformation) {
        iContractInformationDao.updateOne(contractInformation);
    }
}
