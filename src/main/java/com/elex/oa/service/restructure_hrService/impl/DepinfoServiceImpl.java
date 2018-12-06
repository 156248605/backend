package com.elex.oa.service.restructure_hrService.impl;

import com.alibaba.druid.util.StringUtils;
import com.elex.oa.dao.hr.IDeptDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.restructure_hr.IDepinfoDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.service.restructure_hrService.IDepinfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 17:04
 * @Version 1.0
 **/
@Service
public class DepinfoServiceImpl implements IDepinfoService {
    @Resource
    IDeptDao iDeptDao;
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;
    @Resource
    IDepinfoDao iDepinfoDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;

    @Override
    public Boolean changeTable() {
        Boolean valBoolean = true;
        List<Dept> deptList = iDeptDao.selectAllDept();
        for (Dept d:deptList
             ) {
            Depinfo temDepinfo = getDepinfoByDepcode(d.getDepcode());
            if(null!=temDepinfo){
                valBoolean = false;
                continue;
            }
            getDepcodeByAddDepInfo(getNewDepinfoByDept(d));
        }
        return valBoolean;
    }

    private String getDepcodeByAddDepInfo(Depinfo depinfo){
        Depinfo temDepinfo = getDepinfoByDepcode(depinfo.getDepcode());
        if(null!=temDepinfo)return null;
        iDepinfoDao.insert(depinfo);
        return depinfo.getDepcode();
    }

    private Depinfo getDepinfoByDepcode(String depcode){
        if(StringUtils.isEmpty(depcode))return null;
        List<Depinfo> depinfoList = iDepinfoDao.selectByEntity(new Depinfo(depcode));
        if(null == depinfoList || depinfoList.size()==0){
            return null;
        }else if(depinfoList.size()==1){
            return depinfoList.get(0);
        }
        return null;
    }

    private Depinfo getNewDepinfoByDept(Dept d) {
        Depinfo depinfo = new Depinfo();
        depinfo.setDepcode(d.getDepcode());
        depinfo.setCompanyname(d.getCompanyname());
        depinfo.setDepname(d.getDepname());
        depinfo.setFunctionaltypeid(new HrUtilsTemp().getDatacodeByHrsetid(d.getFunctionaltypeid()));
        depinfo.setDeptypeid(new HrUtilsTemp().getDatacodeByHrsetid(d.getDeptypeid()));
        depinfo.setParent_depcode(new HrUtilsTemp().getDepcodeByDepid(d.getParentdepid()));
        depinfo.setPrincipaluserid(getEmployeenumberByUserid(d.getPrincipaluserid()));
        depinfo.setDeputyuserid(getEmployeenumberByUserid(d.getDeputyuserid()));
        depinfo.setSecretaryuserid(getEmployeenumberByUserid(d.getSecretaryuserid()));
        depinfo.setDutydescription(d.getDutydescription());
        depinfo.setDepdescription(d.getDepdescription());
        depinfo.setState(Integer.toString(d.getState()));
        depinfo.setOrdercode(Integer.toString(d.getOrdercode()));
        /*层级手动添加(默认4)*/
        depinfo.setNode_level("4");
        return depinfo;
    }

    private String getEmployeenumberByUserid(Integer userid){
        if(null==userid)return null;
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(userid);
        if(null==personalInformation)return null;
        return personalInformation.getEmployeenumber();
    }

}