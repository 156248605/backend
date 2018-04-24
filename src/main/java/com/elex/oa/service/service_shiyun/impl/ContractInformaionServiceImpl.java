package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IContractInformationDao;
import com.elex.oa.entity.entity_shiyun.ContractInformation;
import com.elex.oa.service.service_shiyun.IContractInformationService;
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

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询合同信息
     *@Date: 16:55 2018\4\9 0009
     */
    @Override
    public com.elex.oa.util.util_shiyun.PageHelper<ContractInformation> queryByConditions(Map<String, Object> paramMap) {

        ContractInformation entity = (ContractInformation) paramMap.get("entity");
        List<ContractInformation> list = iContractInformationDao.selectByConditions(entity);

        int pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = Integer.parseInt(paramMap.get("pageSize").toString());

        String ed = paramMap.get("ed").toString();
        if(ed!="" && ed!=null){
            List<ContractInformation> list2 = new ArrayList<ContractInformation>();
            Calendar c1 = Calendar.getInstance();
            c1.add(Calendar.DAY_OF_MONTH,+15);// 取现在时间往后15天
            Calendar c2 = Calendar.getInstance();
            c2.add(Calendar.MONTH,+1);// 取现在时间往后1个月
            Calendar c3 = Calendar.getInstance();
            c3.add(Calendar.MONTH,+2);// 取现在时间往后2个月
            Calendar c4 = Calendar.getInstance();
            c4.add(Calendar.MONTH,+3);// 取现在时间往后3个月
            for(int i=0;i<list.size();i++){
                String enddate = list.get(i).getEnddate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date edate = new Date();
                try {
                    edate = simpleDateFormat.parse(enddate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                switch (ed) {
                    case "1" :
                        if(edate.before(c1.getTime()) && edate.after(new Date())){
                            list2.add(list.get(i));
                        }
                    break;
                    case "2" :
                        if(edate.before(c2.getTime()) && edate.after(new Date())){
                            list2.add(list.get(i));
                        }
                    break;
                    case "3" :
                        if(edate.before(c3.getTime()) && edate.after(new Date())){
                            list2.add(list.get(i));
                        }
                    break;
                    case "4" :
                        if(edate.before(c4.getTime()) && edate.after(new Date())){
                            list2.add(list.get(i));
                        }
                    break;
                }
            }
            list = list2;
        }
        com.elex.oa.util.util_shiyun.PageHelper<ContractInformation> contractInformationPageHelper = new com.elex.oa.util.util_shiyun.PageHelper<>(pageNum, pageSize, list);
        return contractInformationPageHelper;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询合同信息
     *@Date: 16:55 2018\4\9 0009
     */
    @Override
    public ContractInformation queryById(Integer id) {
        ContractInformation contractInformation = iContractInformationDao.selectById(id);
        return contractInformation;
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
     *@Description:添加合同信息并返回主键
     *@Date: 14:28 2018\4\20 0020
     */
    @Override
    public Integer addOne(ContractInformation contractInformation) {
        Integer integer = iContractInformationDao.insertOne(contractInformation);
        return contractInformation.getId();
    }
}
