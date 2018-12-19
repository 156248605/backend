package com.elex.oa.util.hr_util;

import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.dao.hr.IDeptDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.restructure_hr.IDepinfoDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.dao.restructure_hr.IPersonalinfoDao;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.entity.restructure_hrentity.Personalinfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\29 0029 19:23
 * @Version 1.0
 **/
@Service
public class HrUtilsTemp {
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;
    @Resource
    IDeptDao iDeptDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    ITrackInfoDao iTrackInfoDao;
    @Resource
    IDepinfoDao iDepinfoDao;
    @Resource
    IPersonalinfoDao iPersonalinfoDao;

    //根据tb_hr_set表中id查询tb_data_dictionary表中datacode字段（仅仅迁移数据用，已过时）
    public String getDatacodeByHrsetid(Integer hrsetid) {
        if(null==hrsetid)return null;
        HRset hRset = ihRsetDao.selectById(hrsetid);
        if(null==hRset)return null;
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(hRset.getDatatype(), hRset.getDatavalue()));
        if(null==hrdatadictionaryList || hrdatadictionaryList.size()==0){
            //没有则添加
            String datacode = hRset.getDatatype() + "_" + System.currentTimeMillis();
            iHrdatadictionaryDao.insert(new Hrdatadictionary(datacode,hRset.getDatatype(),hRset.getDatavalue()));
            return datacode;
        }else if(hrdatadictionaryList.size()==1){
            //有一个则直接拿取
            return hrdatadictionaryList.get(0).getDatacode();
        }else {
            //有多个则说明有重复数据，需要删除多余的数据
            for(int i = 1;i<hrdatadictionaryList.size();i++){
                iHrdatadictionaryDao.deleteByDatacode(hrdatadictionaryList.get(i).getDatacode());
            }
            String datacode = hrdatadictionaryList.get(0).getDatacode();
            return datacode;
        }
    }

    //根据tb_id_department表中id查询depcode
    public String getDepcodeByDepid(Integer depid) {
        if(null==depid)return null;
        Dept dept = iDeptDao.selectDeptByDepid(depid);
        if(null==dept)return null;
        return dept.getDepcode();
    }

    //根据tb_id_user表查询员工号（已过时）
    public String getEmployeenumberByUserid(Integer userid){
        if(null==userid)return null;
        User user = iUserDao.selectById(userid);
        if(null==user)return null;
        return user.getEmployeenumber();
    }

    //根据员工号查询员工姓名（tb_id_personalinformatin中查询userid,然后在tb_id_user中根据userid即id查询员工姓名）（已过时）
    public String getTruenameByEmployeenumber(String employeenumber){
        if(StringUtils.isEmpty(employeenumber))return null;
        PersonalInformation personalInformation = iPersonalInformationDao.selectByEmployeenumber(employeenumber);
        if(null==personalInformation)return null;
        Integer userid = personalInformation.getUserid();
        if(null==userid)return null;
        User user = iUserDao.selectById(userid);
        if(null==user)return null;
        return user.getTruename();
    }

    //根据跟踪线索id查询跟踪内容
    public String getTrackcontentByTrackid(String trackid){
        if(StringUtils.isEmpty(trackid))return null;
        TrackInfo trackInfo = iTrackInfoDao.selectByPrimaryKey(trackid);
        if(null==trackInfo)return null;
        return trackInfo.getTrack_content();
    }

    //根据毫秒值获得格式为："yyyy/MM/dd HH:mm:ss"的日期字符串
    public String getDateStringByTimeMillis(Long l){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(l);
        String dateString = sdf.format(date);
        return dateString;
    }

    //根据数据字典的编号获得相应的值
    public String getDatavalueByDatacode(String datacode){
        if(StringUtils.isEmpty(datacode))return null;
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(datacode));
        if(null==hrdatadictionaryList || hrdatadictionaryList.size()!=1)return null;
        return hrdatadictionaryList.get(0).getDatavalue();
    }

    //根据部门编号获得部门信息（粗略的信息）
    public Depinfo getDepinfoByDepcode(String depcode){
        if(org.apache.commons.lang3.StringUtils.isEmpty(depcode))return null;
        List<Depinfo> depinfoList = iDepinfoDao.selectByEntity(new Depinfo(depcode));
        if(null == depinfoList || depinfoList.size()==0){
            return null;
        }else if(depinfoList.size()==1){
            Depinfo depinfo = depinfoList.get(0);
            return depinfo;
        }
        return null;
    }

    //根据部门（粗略的信息）获得详细的部门信息
    public Depinfo getDepinfoDetailByDepinfo(Depinfo depinfo){
        if(null==depinfo)return null;
        Boolean isExist = false;
        //获取职能类型
        isExist = null!=depinfo.getFunctionaltypeid() && StringUtils.isNotEmpty(depinfo.getFunctionaltypeid());
        if (isExist) {
            depinfo.setFunctionaltype(getDatavalueByDatacode(depinfo.getFunctionaltypeid()));
        }
        //获取部门类型
        depinfo.setDeptype(getDatavalueByDatacode(depinfo.getDeptypeid()));
        //获取上级部门
        depinfo.setParentdep(iDepinfoDao.selectByDepcode(depinfo.getParent_depcode()));
        //获取部门正职
        depinfo.setPrincipaluser(iPersonalinfoDao.selectPersonalinfoByEmployeenumber(depinfo.getPrincipaluserid()));
        //获取部门副职
        depinfo.setDeputyuser(iPersonalinfoDao.selectPersonalinfoByEmployeenumber(depinfo.getDeputyuserid()));
        //获取部门秘书
        depinfo.setSecretaryuser(iPersonalinfoDao.selectPersonalinfoByEmployeenumber(depinfo.getSecretaryuserid()));
        return depinfo;
    }

    //根据部门编号查询部门名称
    public String getDepnameByDepcode(String depcode){
        Depinfo depinfo = getDepinfoByDepcode(depcode);
        if(null==depinfo)return null;
        depinfo = getDepinfoDetailByDepinfo(depinfo);
        return depinfo.getDepname();
    }

    //根据员工号查询员工姓名（tb_id_persoal_info一张表）
    public String getTruenameByEmployeenumberInnewtable(String employeenumber){
        Personalinfo personalinfo = iPersonalinfoDao.selectByPrimaryKey(employeenumber);
        return personalinfo.getTruename();
    }
}    