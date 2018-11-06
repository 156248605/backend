package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.IBaseInformationDao;
import com.elex.oa.dao.hr.IPerandpostrsDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.entity.hr_entity.BaseInformation;
import com.elex.oa.entity.hr_entity.PerAndPostRs;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.service.hr_service.IPersonalInformationService;
import com.elex.oa.util.hr_util.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    IBaseInformationDao iBaseInformationDao;
    @Resource
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
        if(personalInformation.getAges()!=null && personalInformation.getAges().size()!=0){
            Map<String,String> ageMap = new HashMap<>();
            for (String age:personalInformation.getAges()
                 ) {
                HashMap<String, String> birdayByAge = IDcodeUtil.getBirdayByAge(age);
                ageMap.put(birdayByAge.get("sbir"),birdayByAge.get("ebir"));
            }
            personalInformation.setAgeMap(ageMap);
        }//年龄数组转换成出生日期
        if (personalInformation.getWorkingage()!=null && !"".equals(personalInformation.getWorkingage())) {
            HashMap<String, String> fwtByWorkingage = IDcodeUtil.getFwtByWorkingage(personalInformation.getWorkingage());
            personalInformation.setSfwt(fwtByWorkingage.get("sfwt"));
            personalInformation.setEfwt(fwtByWorkingage.get("efwt"));
        }//工龄转换成首次工作时间
        if(personalInformation.getWorkingages()!=null && personalInformation.getWorkingages().size()!=0){
            Map<String,String> workingageMap = new HashMap<>();
            for (String workingage:personalInformation.getWorkingages()
                 ) {
                HashMap<String, String> fwtByWorkingage = IDcodeUtil.getBirdayByAge(workingage);
                workingageMap.put(fwtByWorkingage.get("sbir"),fwtByWorkingage.get("ebir"));
            }
            personalInformation.setWorkingageMap(workingageMap);
        }//工龄数组转换成首次工作时间
        if (personalInformation!=null) {
            List<Integer> baseinformationids = new ArrayList<>();
            List<BaseInformation> baseInformations = iBaseInformationDao.selectByConditions(personalInformation);//先进行基本信息的查询
            for(int i=0;i<baseInformations.size();i++){
                baseinformationids.add(baseInformations.get(i).getId());
            }
            if (baseinformationids.size()!=0) {
                personalInformation.setBaseinformationids(baseinformationids);
            }else {
                return null;
            }//再进行基本信息的id集合提取

            if (personalInformation.getPostname()!=null && !"".equals(personalInformation.getPostname()) || personalInformation.getPostnameList().size()!=0) {
                if("不包含".equals(personalInformation.getPostnamevalue())){
                    List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectByConditions2(personalInformation);
                    //这里查出来的是包含的部分，到时候只需not in 一下这部分即可
                    ArrayList<Integer> integers = new ArrayList<>();
                    for(PerAndPostRs pp:perAndPostRs){
                        integers.add(pp.getPerid());
                    }
                    if (integers.size()!=0) {
                        personalInformation.setPpids(integers);
                    }
                }

                List<Integer> perids = new ArrayList<>();
                List<PerAndPostRs> perAndPostRsList = iPerandpostrsDao.selectByConditions(personalInformation);
                for(int i = 0;i<perAndPostRsList.size();i++){
                    perids.add(perAndPostRsList.get(i).getPerid());
                }
                if (perids.size()!=0) {
                    personalInformation.setPerids(perids);
                }
            }//将符合岗位条件的perid集合提取出来
        }
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        com.github.pagehelper.PageHelper.startPage(pageNum,pageSize);
        System.out.println(personalInformation.getUsername()+"===============================================>");
        List<PersonalInformation> list = iPersonalInformationDao.selectByConditions(personalInformation);
        System.out.println(list.size()+"========================================================>");
        return new PageInfo<PersonalInformation>(list);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息（不包括离职的）
     *@Date: 18:48 2018\4\10 0010
     */
    @Override
    public PersonalInformation queryOneById(Integer id) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectById(id);
        return personalInformation;
    }
    //包括离职的
    @Override
    public PersonalInformation queryOneById2(Integer id) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectById2(id);
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
    }

    /**
     *@Author:ShiYun;
     *@Description:根据员工号查询员工
     *@Date: 10:02 2018\8\9 0009
     */
    @Override
    public List<PersonalInformation> queryByEmployeenumber(String employeenumber) {
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByEmployeenumber(employeenumber);
        return personalInformationList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除人事主体信息
     *@Date: 16:10 2018\8\20 0020
     */
    @Override
    public void removeOne(Integer perid) {
        iPersonalInformationDao.deleteById(perid);
    }

    @Override
    public void removeAll() {
        iPersonalInformationDao.deleteAll();
    }

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
