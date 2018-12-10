package com.elex.oa.util.hr_util;

import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.dao.hr.IDeptDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.hr_entity.User;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
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

    public String getDepcodeByDepid(Integer depid) {
        if(null==depid)return null;
        Dept dept = iDeptDao.selectDeptByDepid(depid);
        if(null==dept)return null;
        return dept.getDepcode();
    }

    public String getEmployeenumberByUserid(Integer userid){
        if(null==userid)return null;
        User user = iUserDao.selectById(userid);
        if(null==user)return null;
        return user.getEmployeenumber();
    }

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

    public String getTrackcontentByTrackid(String trackid){
        if(StringUtils.isEmpty(trackid))return null;
        TrackInfo trackInfo = iTrackInfoDao.selectByPrimaryKey(trackid);
        if(null==trackInfo)return null;
        return trackInfo.getTrack_content();
    }

    public String getDateStringByTimeMillis(Long l){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(l);
        String dateString = sdf.format(date);
        return dateString;
    }
}    