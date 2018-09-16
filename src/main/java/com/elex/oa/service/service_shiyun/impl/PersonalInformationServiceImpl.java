package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IBaseInformationDao;
import com.elex.oa.dao.dao_shiyun.IPerandpostrsDao;
import com.elex.oa.dao.dao_shiyun.IPersonalInformationDao;
import com.elex.oa.entity.entity_shiyun.BaseInformation;
import com.elex.oa.entity.entity_shiyun.PerAndPostRs;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.service.service_shiyun.IPersonalInformationService;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:人事信息
 * @Date:Created in  10:00 2018\4\8 0008
 * @Modify By:
 */
@Service
public class PersonalInformationServiceImpl implements IPersonalInformationService {

    @Autowired
    IPersonalInformationDao iPersonalInformationDao;
    @Autowired
    IBaseInformationDao iBaseInformationDao;
    @Autowired
    IPerandpostrsDao iPerandpostrsDao;

    /**
     *@Author:ShiYun;
     *@Description:查询人事信息
     *@Date: 10:09 2018\4\8 0008
     */
    @Override
    public PageInfo<PersonalInformation> queryPIs(Map<String, Object> paramMap) throws ParseException {


        PersonalInformation personalInformation = (PersonalInformation) paramMap.get("entity");
        if (personalInformation.getAge()!=null && !"".equals(personalInformation.getAge())) {
            HashMap<String, String> birdayByAge = IDcodeUtil.getBirdayByAge(personalInformation.getAge());
            personalInformation.setSbir(birdayByAge.get("sbir"));
            personalInformation.setEbir(birdayByAge.get("ebir"));
        }//年龄转换成出生日期
        if (personalInformation.getWorkingage()!=null && !"".equals(personalInformation.getWorkingage())) {
            HashMap<String, String> fwtByWorkingage = IDcodeUtil.getFwtByWorkingage(personalInformation.getWorkingage());
            personalInformation.setSfwt(fwtByWorkingage.get("sfwt"));
            personalInformation.setEfwt(fwtByWorkingage.get("efwt"));
            System.out.println(personalInformation.getSfwt() + ":" + personalInformation.getEfwt());
        }//工龄转换层首次工作时间
        if (personalInformation!=null) {
            List<Integer> baseinformationids = new ArrayList<>();
            List<BaseInformation> baseInformations = iBaseInformationDao.selectByConditions(personalInformation);
            for(int i=0;i<baseInformations.size();i++){
                baseinformationids.add(baseInformations.get(i).getId());
            }
            if (baseinformationids.size()!=0) {
                personalInformation.setBaseinformationids(baseinformationids);
            }else {
                return null;
            }

            if (personalInformation.getPostname()!=null && !"".equals(personalInformation.getPostname())) {
                if("不包含".equals(personalInformation.getPostnamevalue())){
                    List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectByConditions2(personalInformation);
                    ArrayList<Integer> integers = new ArrayList<>();
                    for(PerAndPostRs pp:perAndPostRs){
                        integers.add(pp.getPerid());
                    }
                    personalInformation.setPpids(integers);
                }
                if (personalInformation.getPpids()!=null) {
                    System.out.println(personalInformation.getPpids().size());
                }else {
                    return null;
                }
                List<Integer> perids = new ArrayList<>();
                List<PerAndPostRs> perAndPostRsList = iPerandpostrsDao.selectByConditions(personalInformation);
                for(int i = 0;i<perAndPostRsList.size();i++){
                    perids.add(perAndPostRsList.get(i).getPerid());
                }
                if (perids.size()!=0) {
                    personalInformation.setPerids(perids);
                }
            }
        }

        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        com.github.pagehelper.PageHelper.startPage(pageNum,pageSize);

        List<PersonalInformation> list = iPersonalInformationDao.selectByConditions(personalInformation);
        return new PageInfo<PersonalInformation>(list);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息
     *@Date: 18:48 2018\4\10 0010
     */
    @Override
    public PersonalInformation queryOneById(Integer id) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectById(id);
        return personalInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据用户ID查询人事信息
     *@Date: 18:49 2018\4\10 0010
     */
    @Override
    public PersonalInformation queryOneByUserid(Integer userid) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(userid);
        return personalInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息
     *@Date: 18:49 2018\4\10 0010
     */
    @Override
    public Integer saveOne(PersonalInformation personalInformation) {
        Integer personalInformationId = iPersonalInformationDao.insertOne(personalInformation);
        return personalInformation.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息
     *@Date: 16:31 2018\4\11 0011
     */
    @Override
    public void modifyOne(PersonalInformation personalInformation) {
        iPersonalInformationDao.updateOne(personalInformation);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息（不分页）
     *@Date: 16:30 2018\4\13 0013
     */
    @Override
    public List<PersonalInformation> queryPIs(PersonalInformation personalInformation) {
        List<PersonalInformation> personalInformations = iPersonalInformationDao.selectByConditions(personalInformation);
        return personalInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID查询人事信息
     *@Date: 16:22 2018\5\28 0028
     */
    public List<PersonalInformation> queryByDepid(Integer depid){
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid(depid);
        return personalInformationList;
    };

    /**
     *@Author:ShiYun;
     *@Description:查询所有人事信息（不分页）
     *@Date: 17:28 2018\4\18 0018
     */
    @Override
    public List<PersonalInformation> queryAllByNull() {
        List<PersonalInformation> personalInformations = iPersonalInformationDao.selectAll();
        return personalInformations;
    }
}