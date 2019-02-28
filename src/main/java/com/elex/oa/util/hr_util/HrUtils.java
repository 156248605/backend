package com.elex.oa.util.hr_util;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.dao.business.IOpportunityDao;
import com.elex.oa.dao.business.ISequenceDao;
import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.dao.hr.IDeptDao;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IUserDao;
import com.elex.oa.dao.restructure_hr.IDepinfoDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.dao.restructure_hr.IPersonalinfoDao;
import com.elex.oa.dao.restructure_hr.IPostinfoDao;
import com.elex.oa.entity.business.Sequence;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.entity.hr_entity.department.Dept;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.entity.restructure_hrentity.Personalinfo;
import com.elex.oa.entity.restructure_hrentity.Postinfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\29 0029 19:23
 * @Version 1.0
 **/
@Component
public class HrUtils {
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
    IPostinfoDao iPostinfoDao;
    @Resource
    IPersonalinfoDao iPersonalinfoDao;
    @Resource
    IDcodeUtil iDcodeUtil;
    @Resource
    IClueDao iClueDao;
    @Resource
    IOpportunityDao iOpportunityDao;
    @Autowired
    AppProperties appProperties;
    @Resource
    ISequenceDao iSequenceDao;

    //根据tb_hr_set表中id查询tb_data_dictionary表中datacode字段（仅仅迁移数据用，已过时）
    public String getDatacodeByHrsetid(Integer hrsetid) {
        if(null==hrsetid)return null;
        HRset hRset = ihRsetDao.selectById(hrsetid);
        if(null==hRset)return null;
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(hRset.getDatatype(), hRset.getDatavalue()));
        if(null==hrdatadictionaryList || hrdatadictionaryList.size()==0){
            //没有则添加
            /*String datacode = hRset.getDatatype() + "_" + System.currentTimeMillis();*/
            String datacode = new StringBuilder(hRset.getDatatype()).append("-").append(System.currentTimeMillis()).toString();
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

    //根据账号ID获得姓名（已过时）
    public String getTruenameByUserid(Integer userid){
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

    //根据格式为："yyyy/MM/dd HH:mm:ss"的日期字符串获得毫秒值
    public Long getTimeMillisByDateString(String date){
        if(StringUtils.isBlank(date))return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return calendar.getTimeInMillis();
    }

    //根据数据字典的编号获得相应的值
    public String getDatavalueByDatacode(String datacode){
        if(StringUtils.isEmpty(datacode))return null;
        List<Hrdatadictionary> hrdatadictionaryList = iHrdatadictionaryDao.selectByEntity(new Hrdatadictionary(datacode));
        if(null==hrdatadictionaryList || hrdatadictionaryList.size()!=1)return null;
        return hrdatadictionaryList.get(0).getDatavalue();
    }

    //根据HRset的id获得相应的值（已过时）
    public String getDatavalueByHrsetid(Integer id){
        if(null==id)return null;
        HRset hRset = ihRsetDao.selectById(id);
        if(null==hRset)return null;
        return hRset.getDatavalue();
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

    //此方法有BUG（部门名称不是唯一标识）
    public String getDepcodeByDepname(String depname){
        if(null==depname)return null;
        if(StringUtils.isEmpty(depname))return null;
        List<Depinfo> depinfoList = iDepinfoDao.select(new Depinfo(null, depname, null, null, null));
        if(null==depinfoList || depinfoList.size()==0)return null;
        return depinfoList.get(0).getDepcode();
    }

    //获取文件地址（单个文件）
    //fielname="df",dirsPath="/org/file/"
    public String getSignalFileAddress(HttpServletRequest request, String filename, String dirsPath) {
        String fileAddress = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> files= multipartRequest.getFiles(filename);
        if(files.size()!=0){
            try {
                String realPath = getInitFilesPosition() + dirsPath;
                Long l = System.currentTimeMillis();
                File file = new File(realPath  + l);
                file.mkdirs();
                fileAddress = dirsPath + l+ "/" + files.get(0).getOriginalFilename();
                files.get(0).transferTo(new File(realPath + l,files.get(0).getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(StringUtils.isEmpty(fileAddress))return null;
        return fileAddress;
    }

    //获取多个文件
    public List<String> getMultiFileAddress(HttpServletRequest request, int i) {
        List<String> fileNameList = new ArrayList<>();
        for(int j=0;j<i;j++){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            List<MultipartFile> attachments = multipartRequest.getFiles("attachment_"+(j+1));
            if(attachments.size()!=0){
                String realPath = getInitFilesPosition();
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/attachments/" + l);
                file.mkdirs();
                String attachment_address = "/attachments/" + l + "/" + attachments.get(0).getOriginalFilename();
                try {
                    attachments.get(0).transferTo(new File(realPath + attachment_address));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileNameList.add(attachment_address);
            }
        }
        return fileNameList;
    }

    //根据父节点的postcode获取子节点的层级码
    public String getNodelevelByParentpostcode(String parent_postcode){
        if(null==parent_postcode || StringUtils.isEmpty(parent_postcode))return null;
        Postinfo parent_postinfo = iPostinfoDao.selectByPrimaryKey(parent_postcode);
        if(null == parent_postinfo)return null;
        String parent_nodelevel = parent_postinfo.getNode_level();
        int i = Integer.parseInt(parent_nodelevel) + 1;
        /*String node_level = i + "";*/
        String node_level = new StringBuilder(i).toString();
        return node_level;
    }

    //根据postcode获取postname
    public String getPostnameByPostcode(String postcode){
        if(null==postcode || StringUtils.isEmpty(postcode))return null;
        Postinfo postinfo = iPostinfoDao.selectByPrimaryKey(postcode);
        if(null==postinfo)return null;
        return postinfo.getPostname();
    }

    //此方法有BUG（岗位名称不是唯一标识）
    public String getPostcodeByPostname(String postname) {
        if(null==postname || StringUtils.isEmpty(postname))return null;
        List<Postinfo> postinfoList = iPostinfoDao.select(new Postinfo(null, postname, null, null, null));
        if(null==postinfoList || postinfoList.size()==0)return null;
        return postinfoList.get(0).getPostcode();
    }

    //根据登录ID获得账号ID
    public Integer getUseridByUsername(String username){
        if(StringUtils.isBlank(username))return null;
        User user = iUserDao.selectByUsername(username);
        if(null==user)return null;
        return user.getId();
    }

    //根据首次参加工作时间获得工龄
    public String getWorkingageByFirstworkingtime(String firstworkingtime){
        if(StringUtils.isBlank(firstworkingtime))return null;
        String workingage = null;
        try {
            workingage = iDcodeUtil.getWorkingage(firstworkingtime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return workingage;
    }

    //根据数据字典值获得相应的HRsetID（没有则返回null）
    public Integer getHrsetidByDatavalue(String datatype,String datavalue){
        if(StringUtils.isBlank(datatype) || StringUtils.isBlank(datavalue))return null;
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(datatype, datavalue));
        if(null==hRsetList || hRsetList.size()==0)return null;
        return hRsetList.get(0).getId();
    }

    //根据数据字典值获得相应的HRsetID（没有则添加）
    public Integer getHrsetidByDatavalueOrInsert(String datatype,String datavalue){
        if(StringUtils.isBlank(datatype) || StringUtils.isBlank(datavalue))return null;
        List<HRset> hRsetList = ihRsetDao.selectByConditions(new HRset(datatype, datavalue));
        if(null==hRsetList || hRsetList.size()==0){
            //没有则添加
            HRset hRset = new HRset();
            hRset.setDatatype(datatype);
            /*hRset.setDatacode(datatype+"_"+System.currentTimeMillis());*/
            hRset.setDatacode(new StringBuilder(datatype).append("-").append(System.currentTimeMillis()).toString());
            hRset.setDatavalue(datavalue);
            ihRsetDao.insertOne(hRset);
            return hRset.getId();
        }
        return hRsetList.get(0).getId();
    }

    //判断是否需要录入并添加相应的HR设置信息
    public void addHrsetByDatavalue(String datacode,String datatype,String datavalue){
        Integer hrsetidTemp = getHrsetidByDatavalue(datatype, datavalue);
        if(null!=hrsetidTemp)return;
        if(StringUtils.isBlank(datacode)){datacode = datatype+"_"+System.currentTimeMillis();}
        ihRsetDao.insertOne(new HRset(datatype,datacode,datavalue));
    }

    //获取后台版本号
    public String getOaBackendVersion(){
        return appProperties.getProperty("versionbackend");
    }

    //获取图片初始位置
    public String getInitFilesPosition(){
        return appProperties.getProperty("attachment.realpath");
    }

    //根据身份证号码获得出生日期
    public String getBirthday(String idcode) {
        return iDcodeUtil.getBirthday(idcode);
    }

    //根据时间获得当月的头尾日期
    public Map getFirstAndLastDate(Date date){
        return iDcodeUtil.getFirstAndLastDate(date);
    }

    //根据日期获得某月的天数
    public Integer getDaysByDate(Date date){
       return iDcodeUtil.getDaysByDate(date);
    }

    //根据日期获得几号
    public Integer getDaycodeByDate(String str){
        return Integer.parseInt(str.split("/|-")[2].substring(0,2));
    }

    //根据年龄获得出生日期
    public HashMap<String, String> getBirthdayByAge(String age) {
        return iDcodeUtil.getBirdayByAge(age);
    }

    //获得年龄
    public String getAge(String birthday){
        return iDcodeUtil.getAge(birthday);
    }

    //获得生肖
    public String getChinesecs(String idcode) {
        return iDcodeUtil.getChinesecs(idcode);
    }

    //获得星座
    public String getConstellation(String idcode) {
        return iDcodeUtil.getConstellation(idcode);
    }

    //获得性别
    public String getSex(String idcode) {
        return iDcodeUtil.getSex(idcode);
    }

    //获得户籍
    public String getProvinceByIdcode(String idcode) {
        return iDcodeUtil.getProvinceByIdcode(idcode);
    }

    //获得合同期限
    public String getContractage(String startdate,String enddate){
        return iDcodeUtil.getContractage(startdate,enddate);
    }

    //将数组拼接成字符串
    public String getArrayToString(List<String> strs, String s) {
        return iDcodeUtil.getArrayToString(strs,s);
    }

    //获得工作年限
    public String getWorkingage(String firstworkingtime) {
        return iDcodeUtil.getWorkingage(firstworkingtime);
    }

    //获得司龄
    public String getCompanyAge(String entrydate) {
        return iDcodeUtil.getCompanyAge(entrydate);
    }

    //获得转正时间
    public String getZhuanzhengdate(String entrydate) {
        return iDcodeUtil.getZhuanzhengdate(entrydate);
    }

    //将字符串分割成字符串数组
    public List<String> getStringToListString(String post_list, String s) {
        return iDcodeUtil.getStringToListString(post_list,s);
    }

    //根据集合获得字符串
    public String getListStringFromString(List<String> list,String s){
        if(null==list || list.size()==0)return null;
        if(StringUtils.isBlank(s))s = "@";
        String resp = "";
        for (String str:list
             ) {
            resp += str+s;
        }
        return resp.substring(0,resp.length()-s.length());
    }

    //根据员工号获得账号ID
    public Integer getUseridByEmployeenumber(String employeenumber) {
        if(StringUtils.isBlank(employeenumber))return null;
        User user = iUserDao.selectByEmployeenumber(employeenumber);
        if(null==user)return null;
        return user.getId();
    }

    //获取线索编号
    //线索编号的格式暂定为：ELEX-CLU-UN-YYYY-MMNNNN
    public String getClueCode(String username) {
        //获得UN编号
        String strUN = getCompanyCodeByUsername(username);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
        String format = sdf.format(date);
        String currentTime = format.substring(0,4)+"-"+format.substring(5,7);
        String clueCodePrefix = "ELEX-CLU-"+strUN+"-"+currentTime;
        String clueCode = clueCodePrefix+getSequenceCode(currentTime,Commons.CLUE_SEQUENCE_NAME);
        return clueCode;
    }

    //获得商机编号
    //商机编号的格式暂定为：ELEX-BIZ-UN-YYYY-MMNNNN
    public String getOpportunityCode(String username){
        //获得UN编号
        String strUN = getCompanyCodeByUsername(username);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
        String format = sdf.format(date);
        String currentTime = format.substring(0,4)+"-"+format.substring(5,7);
        String opportunityCodePrefix = "ELEX-BIZ-"+strUN+"-"+currentTime;
        String opportunityCode = opportunityCodePrefix+getSequenceCode(currentTime, Commons.OPPORTUNITY_SEQUENCE_NAME);
        return opportunityCode;
    }

    //字符串数组去重
    public List<String> removeDeplication(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    //获得公司编码99、13、11（根据登录ID）
    public String getCompanyCodeByUsername(String username){
        if(StringUtils.isBlank(username))return null;
        Dept dept = iDeptDao.selectDeptByUsername(username);
        if(null==dept || null==dept.getId())return null;
        return getCompanyCodeByDept(dept);
    }

    //获得公司编码99、13、11（根据部门对象）
    private String getCompanyCodeByDept(Dept dept){
        String depcode = dept.getDepcode();
        if(depcode.indexOf("ELEX")!=-1)return depcode.substring(4,depcode.length());
        Dept parentdept = iDeptDao.selectDeptByDepid(dept.getParentdepid());
        if(null==parentdept || null==parentdept.getId())return null;//防止出现死循环
        return getCompanyCodeByDept(parentdept);
    }

    //根据YYYY-MM获得索引号
    public String getSequenceCode(String currentTime,String sequenceName){
        List<Sequence> sequenceList = iSequenceDao.select(new Sequence(sequenceName));
        if (null == sequenceList || sequenceList.size() == 0) {
            //没有则创建，且返还"0001"
            iSequenceDao.insert(new Sequence("sequence_" + System.currentTimeMillis(), sequenceName, 1, currentTime));
            return "00001";
        } else {
            //有则需要判断是否为同一个月份
            Sequence sequence = sequenceList.get(0);
            if (sequence.getCurrent_time().equals(currentTime)) {
                //同一个月份则加一并返回
                Integer numberCode = sequence.getSequence_value() + 1;
                sequence.setSequence_value(numberCode);
                iSequenceDao.updateByPrimaryKey(sequence);
                int numberCodeLength = String.valueOf(numberCode).length();
                if (numberCodeLength == 1) return "000" + String.valueOf(numberCode);
                if (numberCodeLength == 2) return "00" + String.valueOf(numberCode);
                if (numberCodeLength == 3) return "0" + String.valueOf(numberCode);
                if (numberCodeLength >= 4) return String.valueOf(numberCode);
            } else {
                //不是同一个月份则返回"00001"
                sequence.setSequence_value(1);
                sequence.setCurrent_time(currentTime);
                iSequenceDao.updateByPrimaryKeySelective(sequence);
                return "0001";
            }
        }
        return "NNNN";
    }

    //根据登录ID查询所在的部门正职登录ID
    public String getPrincipalUsernameByUsername(String username){
        User principalUser = getPrincipalUserByUsername(username);
        if(null==principalUser)return null;
        return principalUser.getUsername();
    }

    //根据登录ID查询所在的部门正职
    public User getPrincipalUserByUsername(String username){
        if(StringUtils.isBlank(username))return null;
        User user = iDeptDao.selectPrincipalUserByUsername(username);
        if(null==user)return null;
        return user;
    }

    //根据登录ID获得员工号
    public String getEmployeenumberByUsername(String username) {
        User user = iUserDao.selectUserByUsername(username);
        if(null==user)return null;
        return user.getEmployeenumber();
    }
}
