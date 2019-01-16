package com.elex.oa.entity.hr_entity.personalinformation;

import com.elex.oa.entity.hr_entity.Post;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:人事档案的人事信息类
 * @Date:Created in  17:24 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_personalinformation")
public class  PersonalInformation implements Serializable{
    @Id
    private Integer id;//主键(人事总体表1)
    private Integer userid;//用户ID
    private String employeenumber="";//员工号
    private List<String> employeenumbers;//
    private String employeenumbervalue="";//判断条件
    private String sex="";//性别
    private Integer depid;//部门ID
    private String telphone="";//办公电话
    private List<String> telphones;//
    private Integer telphoneid;//办公电话ID
    private String telphonevalue="";//判断条件
    private String mobilephone="";//移动电话
    private List<String> mobilephones;//
    private String mobilephonevalue="";//判断条件
    private Integer baseinformationid;//基本信息ID
    private Integer manageinformationid;//管理信息ID
    private Integer costinformationid;//成本信息ID
    private Integer otherinformationid;//其他信息ID
    private String company="";//公司（源自部门信息）（只显示）
    private String depname="";//部门（源自部门信息）（只显示）
    private String depcode="";//部门编号（源自部门信息）（只显示）
    private List<String> depnames;//
    private String depnamevalue="";//判断条件
    private String principaltruename="";//主管姓名（源于人事信息）（只显示）
    private String principalemployeenumber="";//主管工号（源于人事信息）（只显示）

    private List<Integer> postids;//岗位IDs（人事和岗位的关系表2）
    private List<Post> postList;//多岗位
    private String postnames="";//多岗位（源自人事和岗位的关系信息）（只显示）
    private String postname="";//判断条件
    private List<String> postnameList;//
    private String postnamevalue="";//判断条件
    private List<Integer> perids;//条件查询用
    private List<Integer> ppids;//辅助查询条件

    private Integer isactive;//是否激活（用户表3）
    private String username="";//登录ID
    private List<String> usernames;//
    private String usernamevalue="";//判断条件
    private String truename="";//用户姓名
    private List<String> truenames;//
    private String truenamevalue="";//判断条件
    private Integer state;//用户状态

    private String userphoto="";//免冠照片（基础信息表4）
    private String idphoto1="";//身份证正面
    private String idphoto2="";//身份证背面
    private String englishname="";//英文名
    private List<String> englishnames;//
    private String englishnamevalue="";//判断条件
    private String idcode="";//身份证号码
    private List<String> idcodes;//
    private String idcodevalue="";//判断条件
    private String birthday="";//出生日期（源于身份证号码）
    private String birthdayvalue1;//判断条件
    private String birthdayvalue2;//判断条件
    private String age="";//年龄（源于|身份证号码）（只显示）
    private List<String> ages;//
    private String agevalue="";//判断条件
    private Map<String,String> ageMap;//
    private String sbir="";//将年龄转换成时间
    private String ebir="";//将年龄转换成时间2
    private String constellation="";//星座（源于身份证号码）
    private List<String> constellations;//
    private String constellationvalue="";//判断条件
    private String chinesecs="";//属相（源于身份证号码）
    private List<String> chinesecses;//
    private String chinesecsvalue="";//判断条件
    private String race="";//民族
    private List<String> races;//
    private String racevalue="";//判断条件
    private String marriage="";//婚姻状况
    private String children="";//生育
    private List<String> childrens;//
    private String zzmm="";//政治面貌
    private List<String> zzmms;//
    private String zgxl="";//最高学历
    private List<String> zgxls;//
    private String byyx="";//毕业院校
    private List<String> byyxs;//
    private String byyxvalue="";//判断条件
    private String sxzy="";//所学专业
    private List<String> sxzys;//
    private String sxzyvalue="";//判断条件
    private String pyfs="";//培养方式
    private List<String> pyfses;//
    private String firstla="";//第一外语
    private List<String> firstlas;//
    private String elsela="";//其它外语
    private List<String> elselas;//
    private String posttitle="";//职称
    private List<String> posttitles;//
    private String zyzstype="";//职业证书类型
    private List<String> zyzstypes;//
    private String zyzstypevalue="";//判断条件
    private String zyzsname="";//职业证书名称
    private List<String> zyzsnames;//
    private String zyzsnamevalue="";//判断条件
    private String firstworkingtime="";//首次工作时间
    private String firstworkingtimevalue1="";//判断条件
    private String firstworkingtimevalue2="";//判断条件
    private String workingage="";//工龄（只显示）
    private List<String> workingages;//
    private String workingagevalue="";//判断条件
    private Map<String,String> workingageMap;//
    private String sfwt="";//将工龄转换成时间
    private String efwt="";//将工龄转换成时间2
    private String parentcompany="";//上家雇主
    private List<String> parentcompanys;//
    private String parentcompanyvalue="";//判断条件
    private List<Integer> baseinformationids;//条件查询用
    private String hj="";//户籍

    private String postlevel="";//岗级（管理信息表5）
    private String postrank="";//职级（管理信息表5）
    private String entrydate="";//入职日期
    private String sn="";//司龄（只显示）
    private String zhuanzhengdate="";//转正日期（默认入职日期后的两个月，可以修改）
    private String employeetype="";//员工类型

    private String salary="";//薪资标准（成本信息表6）
    private String ssb="";//社保基数
    private String ssbgscd="";//社保公司缴费比例
    private String ssbgrcd="";//社保个人缴费比例
    private String gjj="";//公积金基数
    private String gjjgscd="";//公积金公司缴费比例
    private String gjjgrcd="";//公积金个人缴费比例
    private String khh="";//开户行
    private String salaryaccount="";//工资账号
    private String sbjnd="";//社保缴纳地
    private String sbcode="";//社保账号
    private String gjjcode="";//公积金账号

    private String privateemail="";//私人邮件（其他信息表7）
    private String companyemail="";//公司邮件
    private String emergencycontract="";//紧急联系人
    private String emergencyrp="";//紧急联系人关系
    private String emergencyphone="";//紧急联系电话
    private String address="";//应急联系地址
    private String remark="";//备注

    public PersonalInformation() {
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPostrank() {
        return postrank;
    }

    public void setPostrank(String postrank) {
        this.postrank = postrank;
    }

    public Map<String, String> getAgeMap() {
        return ageMap;
    }

    public void setAgeMap(Map<String, String> ageMap) {
        this.ageMap = ageMap;
    }

    public Map<String, String> getWorkingageMap() {
        return workingageMap;
    }

    public void setWorkingageMap(Map<String, String> workingageMap) {
        this.workingageMap = workingageMap;
    }

    public List<String> getEmployeenumbers() {
        return employeenumbers;
    }

    public void setEmployeenumbers(List<String> employeenumbers) {
        this.employeenumbers = employeenumbers;
    }

    public List<String> getTelphones() {
        return telphones;
    }

    public void setTelphones(List<String> telphones) {
        this.telphones = telphones;
    }

    public List<String> getMobilephones() {
        return mobilephones;
    }

    public void setMobilephones(List<String> mobilephones) {
        this.mobilephones = mobilephones;
    }

    public List<String> getDepnames() {
        return depnames;
    }

    public void setDepnames(List<String> depnames) {
        this.depnames = depnames;
    }

    public List<String> getPostnameList() {
        return postnameList;
    }

    public void setPostnameList(List<String> postnameList) {
        this.postnameList = postnameList;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<String> getTruenames() {
        return truenames;
    }

    public void setTruenames(List<String> truenames) {
        this.truenames = truenames;
    }

    public List<String> getEnglishnames() {
        return englishnames;
    }

    public void setEnglishnames(List<String> englishnames) {
        this.englishnames = englishnames;
    }

    public List<String> getIdcodes() {
        return idcodes;
    }

    public void setIdcodes(List<String> idcodes) {
        this.idcodes = idcodes;
    }

    public List<String> getAges() {
        return ages;
    }

    public void setAges(List<String> ages) {
        this.ages = ages;
    }

    public List<String> getConstellations() {
        return constellations;
    }

    public void setConstellations(List<String> constellations) {
        this.constellations = constellations;
    }

    public List<String> getChinesecses() {
        return chinesecses;
    }

    public void setChinesecses(List<String> chinesecses) {
        this.chinesecses = chinesecses;
    }

    public List<String> getRaces() {
        return races;
    }

    public void setRaces(List<String> races) {
        this.races = races;
    }

    public List<String> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<String> childrens) {
        this.childrens = childrens;
    }

    public List<String> getZzmms() {
        return zzmms;
    }

    public void setZzmms(List<String> zzmms) {
        this.zzmms = zzmms;
    }

    public List<String> getZgxls() {
        return zgxls;
    }

    public void setZgxls(List<String> zgxls) {
        this.zgxls = zgxls;
    }

    public List<String> getByyxs() {
        return byyxs;
    }

    public void setByyxs(List<String> byyxs) {
        this.byyxs = byyxs;
    }

    public List<String> getSxzys() {
        return sxzys;
    }

    public void setSxzys(List<String> sxzys) {
        this.sxzys = sxzys;
    }

    public List<String> getPyfses() {
        return pyfses;
    }

    public void setPyfses(List<String> pyfses) {
        this.pyfses = pyfses;
    }

    public List<String> getFirstlas() {
        return firstlas;
    }

    public void setFirstlas(List<String> firstlas) {
        this.firstlas = firstlas;
    }

    public List<String> getElselas() {
        return elselas;
    }

    public void setElselas(List<String> elselas) {
        this.elselas = elselas;
    }

    public List<String> getPosttitles() {
        return posttitles;
    }

    public void setPosttitles(List<String> posttitles) {
        this.posttitles = posttitles;
    }

    public List<String> getZyzstypes() {
        return zyzstypes;
    }

    public void setZyzstypes(List<String> zyzstypes) {
        this.zyzstypes = zyzstypes;
    }

    public List<String> getZyzsnames() {
        return zyzsnames;
    }

    public void setZyzsnames(List<String> zyzsnames) {
        this.zyzsnames = zyzsnames;
    }

    public List<String> getWorkingages() {
        return workingages;
    }

    public void setWorkingages(List<String> workingages) {
        this.workingages = workingages;
    }

    public List<String> getParentcompanys() {
        return parentcompanys;
    }

    public void setParentcompanys(List<String> parentcompanys) {
        this.parentcompanys = parentcompanys;
    }

    public List<Integer> getPpids() {
        return ppids;
    }

    public void setPpids(List<Integer> ppids) {
        this.ppids = ppids;
    }

    public String getSbir() {
        return sbir;
    }

    public void setSbir(String sbir) {
        this.sbir = sbir;
    }

    public String getEbir() {
        return ebir;
    }

    public void setEbir(String ebir) {
        this.ebir = ebir;
    }

    public List<Integer> getPerids() {
        return perids;
    }

    public void setPerids(List<Integer> perids) {
        this.perids = perids;
    }

    public List<Integer> getBaseinformationids() {
        return baseinformationids;
    }

    public void setBaseinformationids(List<Integer> baseinformationids) {
        this.baseinformationids = baseinformationids;
    }

    public String getSfwt() {
        return sfwt;
    }

    public void setSfwt(String sfwt) {
        this.sfwt = sfwt;
    }

    public String getEfwt() {
        return efwt;
    }

    public void setEfwt(String efwt) {
        this.efwt = efwt;
    }

    public String getDepnamevalue() {
        return depnamevalue;
    }

    public void setDepnamevalue(String depnamevalue) {
        this.depnamevalue = depnamevalue;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPostnamevalue() {
        return postnamevalue;
    }

    public void setPostnamevalue(String postnamevalue) {
        this.postnamevalue = postnamevalue;
    }

    public String getUsernamevalue() {
        return usernamevalue;
    }

    public void setUsernamevalue(String usernamevalue) {
        this.usernamevalue = usernamevalue;
    }

    public String getTruenamevalue() {
        return truenamevalue;
    }

    public void setTruenamevalue(String truenamevalue) {
        this.truenamevalue = truenamevalue;
    }

    public String getEnglishnamevalue() {
        return englishnamevalue;
    }

    public void setEnglishnamevalue(String englishnamevalue) {
        this.englishnamevalue = englishnamevalue;
    }

    public String getIdcodevalue() {
        return idcodevalue;
    }

    public void setIdcodevalue(String idcodevalue) {
        this.idcodevalue = idcodevalue;
    }

    public String getAgevalue() {
        return agevalue;
    }

    public void setAgevalue(String agevalue) {
        this.agevalue = agevalue;
    }

    public String getConstellationvalue() {
        return constellationvalue;
    }

    public void setConstellationvalue(String constellationvalue) {
        this.constellationvalue = constellationvalue;
    }

    public String getChinesecsvalue() {
        return chinesecsvalue;
    }

    public void setChinesecsvalue(String chinesecsvalue) {
        this.chinesecsvalue = chinesecsvalue;
    }

    public String getRacevalue() {
        return racevalue;
    }

    public void setRacevalue(String racevalue) {
        this.racevalue = racevalue;
    }

    public String getByyxvalue() {
        return byyxvalue;
    }

    public void setByyxvalue(String byyxvalue) {
        this.byyxvalue = byyxvalue;
    }

    public String getSxzyvalue() {
        return sxzyvalue;
    }

    public void setSxzyvalue(String sxzyvalue) {
        this.sxzyvalue = sxzyvalue;
    }

    public String getZyzstypevalue() {
        return zyzstypevalue;
    }

    public void setZyzstypevalue(String zyzstypevalue) {
        this.zyzstypevalue = zyzstypevalue;
    }

    public String getZyzsnamevalue() {
        return zyzsnamevalue;
    }

    public void setZyzsnamevalue(String zyzsnamevalue) {
        this.zyzsnamevalue = zyzsnamevalue;
    }

    public String getFirstworkingtimevalue1() {
        return firstworkingtimevalue1;
    }

    public void setFirstworkingtimevalue1(String firstworkingtimevalue1) {
        this.firstworkingtimevalue1 = firstworkingtimevalue1;
    }

    public String getFirstworkingtimevalue2() {
        return firstworkingtimevalue2;
    }

    public void setFirstworkingtimevalue2(String firstworkingtimevalue2) {
        this.firstworkingtimevalue2 = firstworkingtimevalue2;
    }

    public String getWorkingagevalue() {
        return workingagevalue;
    }

    public void setWorkingagevalue(String workingagevalue) {
        this.workingagevalue = workingagevalue;
    }

    public String getParentcompanyvalue() {
        return parentcompanyvalue;
    }

    public void setParentcompanyvalue(String parentcompanyvalue) {
        this.parentcompanyvalue = parentcompanyvalue;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getWorkingage() {
        return workingage;
    }

    public void setWorkingage(String workingage) {
        this.workingage = workingage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getEmployeenumbervalue() {
        return employeenumbervalue;
    }

    public void setEmployeenumbervalue(String employeenumbervalue) {
        this.employeenumbervalue = employeenumbervalue;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Integer getTelphoneid() {
        return telphoneid;
    }

    public void setTelphoneid(Integer telphoneid) {
        this.telphoneid = telphoneid;
    }

    public String getTelphonevalue() {
        return telphonevalue;
    }

    public void setTelphonevalue(String telphonevalue) {
        this.telphonevalue = telphonevalue;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getMobilephonevalue() {
        return mobilephonevalue;
    }

    public void setMobilephonevalue(String mobilephonevalue) {
        this.mobilephonevalue = mobilephonevalue;
    }

    public Integer getBaseinformationid() {
        return baseinformationid;
    }

    public void setBaseinformationid(Integer baseinformationid) {
        this.baseinformationid = baseinformationid;
    }

    public Integer getManageinformationid() {
        return manageinformationid;
    }

    public void setManageinformationid(Integer manageinformationid) {
        this.manageinformationid = manageinformationid;
    }

    public Integer getCostinformationid() {
        return costinformationid;
    }

    public void setCostinformationid(Integer costinformationid) {
        this.costinformationid = costinformationid;
    }

    public Integer getOtherinformationid() {
        return otherinformationid;
    }

    public void setOtherinformationid(Integer otherinformationid) {
        this.otherinformationid = otherinformationid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getPrincipaltruename() {
        return principaltruename;
    }

    public void setPrincipaltruename(String principaltruename) {
        this.principaltruename = principaltruename;
    }

    public String getPrincipalemployeenumber() {
        return principalemployeenumber;
    }

    public void setPrincipalemployeenumber(String principalemployeenumber) {
        this.principalemployeenumber = principalemployeenumber;
    }

    public List<Integer> getPostids() {
        return postids;
    }

    public void setPostids(List<Integer> postids) {
        this.postids = postids;
    }

    public String getPostnames() {
        return postnames;
    }

    public void setPostnames(String postnames) {
        this.postnames = postnames;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getIdphoto1() {
        return idphoto1;
    }

    public void setIdphoto1(String idphoto1) {
        this.idphoto1 = idphoto1;
    }

    public String getIdphoto2() {
        return idphoto2;
    }

    public void setIdphoto2(String idphoto2) {
        this.idphoto2 = idphoto2;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayvalue1() {
        return birthdayvalue1;
    }

    public void setBirthdayvalue1(String birthdayvalue1) {
        this.birthdayvalue1 = birthdayvalue1;
    }

    public String getBirthdayvalue2() {
        return birthdayvalue2;
    }

    public void setBirthdayvalue2(String birthdayvalue2) {
        this.birthdayvalue2 = birthdayvalue2;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getChinesecs() {
        return chinesecs;
    }

    public void setChinesecs(String chinesecs) {
        this.chinesecs = chinesecs;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getZgxl() {
        return zgxl;
    }

    public void setZgxl(String zgxl) {
        this.zgxl = zgxl;
    }

    public String getByyx() {
        return byyx;
    }

    public void setByyx(String byyx) {
        this.byyx = byyx;
    }

    public String getSxzy() {
        return sxzy;
    }

    public void setSxzy(String sxzy) {
        this.sxzy = sxzy;
    }

    public String getPyfs() {
        return pyfs;
    }

    public void setPyfs(String pyfs) {
        this.pyfs = pyfs;
    }

    public String getFirstla() {
        return firstla;
    }

    public void setFirstla(String firstla) {
        this.firstla = firstla;
    }

    public String getElsela() {
        return elsela;
    }

    public void setElsela(String elsela) {
        this.elsela = elsela;
    }

    public String getPosttitle() {
        return posttitle;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public String getZyzstype() {
        return zyzstype;
    }

    public void setZyzstype(String zyzstype) {
        this.zyzstype = zyzstype;
    }

    public String getZyzsname() {
        return zyzsname;
    }

    public void setZyzsname(String zyzsname) {
        this.zyzsname = zyzsname;
    }

    public String getFirstworkingtime() {
        return firstworkingtime;
    }

    public void setFirstworkingtime(String firstworkingtime) {
        this.firstworkingtime = firstworkingtime;
    }

    public String getParentcompany() {
        return parentcompany;
    }

    public void setParentcompany(String parentcompany) {
        this.parentcompany = parentcompany;
    }

    public String getPostlevel() {
        return postlevel;
    }

    public void setPostlevel(String postlevel) {
        this.postlevel = postlevel;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getZhuanzhengdate() {
        return zhuanzhengdate;
    }

    public void setZhuanzhengdate(String zhuanzhengdate) {
        this.zhuanzhengdate = zhuanzhengdate;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSsb() {
        return ssb;
    }

    public void setSsb(String ssb) {
        this.ssb = ssb;
    }

    public String getSsbgscd() {
        return ssbgscd;
    }

    public void setSsbgscd(String ssbgscd) {
        this.ssbgscd = ssbgscd;
    }

    public String getSsbgrcd() {
        return ssbgrcd;
    }

    public void setSsbgrcd(String ssbgrcd) {
        this.ssbgrcd = ssbgrcd;
    }

    public String getGjj() {
        return gjj;
    }

    public void setGjj(String gjj) {
        this.gjj = gjj;
    }

    public String getGjjgscd() {
        return gjjgscd;
    }

    public void setGjjgscd(String gjjgscd) {
        this.gjjgscd = gjjgscd;
    }

    public String getGjjgrcd() {
        return gjjgrcd;
    }

    public void setGjjgrcd(String gjjgrcd) {
        this.gjjgrcd = gjjgrcd;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    public String getSalaryaccount() {
        return salaryaccount;
    }

    public void setSalaryaccount(String salaryaccount) {
        this.salaryaccount = salaryaccount;
    }

    public String getSbjnd() {
        return sbjnd;
    }

    public void setSbjnd(String sbjnd) {
        this.sbjnd = sbjnd;
    }

    public String getSbcode() {
        return sbcode;
    }

    public void setSbcode(String sbcode) {
        this.sbcode = sbcode;
    }

    public String getGjjcode() {
        return gjjcode;
    }

    public void setGjjcode(String gjjcode) {
        this.gjjcode = gjjcode;
    }

    public String getPrivateemail() {
        return privateemail;
    }

    public void setPrivateemail(String privateemail) {
        this.privateemail = privateemail;
    }

    public String getCompanyemail() {
        return companyemail;
    }

    public void setCompanyemail(String companyemail) {
        this.companyemail = companyemail;
    }

    public String getEmergencycontract() {
        return emergencycontract;
    }

    public void setEmergencycontract(String emergencycontract) {
        this.emergencycontract = emergencycontract;
    }

    public String getEmergencyrp() {
        return emergencyrp;
    }

    public void setEmergencyrp(String emergencyrp) {
        this.emergencyrp = emergencyrp;
    }

    public String getEmergencyphone() {
        return emergencyphone;
    }

    public void setEmergencyphone(String emergencyphone) {
        this.emergencyphone = emergencyphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" + "\n" +
                "id=" + id + "\n" +
                ", userid=" + userid + "\n" +
                ", employeenumber='" + employeenumber + '\'' + "\n" +
                ", employeenumbervalue='" + employeenumbervalue + '\'' + "\n" +
                ", sex='" + sex + '\'' + "\n" +
                ", depid=" + depid +
                ", telphone='" + telphone + '\'' + "\n" +
                ", telphoneid=" + telphoneid + "\n" +
                ", telphonevalue='" + telphonevalue + '\'' + "\n" +
                ", mobilephone='" + mobilephone + '\'' + "\n" +
                ", mobilephonevalue='" + mobilephonevalue + '\'' + "\n" +
                ", baseinformationid=" + baseinformationid + "\n" +
                ", manageinformationid=" + manageinformationid + "\n" +
                ", costinformationid=" + costinformationid + "\n" +
                ", otherinformationid=" + otherinformationid + "\n" +
                ", company='" + company + '\'' + "\n" +
                ", depname='" + depname + '\'' + "\n" +
                ", depnamevalue='" + depnamevalue + '\'' + "\n" +
                ", principaltruename='" + principaltruename + '\'' + "\n" +
                ", principalemployeenumber='" + principalemployeenumber + '\'' + "\n" +
                ", postids=" + postids + "\n" +
                ", postnames='" + postnames + '\'' + "\n" +
                ", postname='" + postname + '\'' + "\n" +
                ", postnamevalue='" + postnamevalue + '\'' + "\n" +
                ", isactive=" + isactive + "\n" +
                ", username='" + username + '\'' + "\n" +
                ", usernamevalue='" + usernamevalue + '\'' + "\n" +
                ", truename='" + truename + '\'' + "\n" +
                ", truenamevalue='" + truenamevalue + '\'' + "\n" +
                ", userphoto='" + userphoto + '\'' + "\n" +
                ", idphoto1='" + idphoto1 + '\'' + "\n" +
                ", idphoto2='" + idphoto2 + '\'' + "\n" +
                ", englishname='" + englishname + '\'' + "\n" +
                ", englishnamevalue='" + englishnamevalue + '\'' + "\n" +
                ", idcode='" + idcode + '\'' + "\n" +
                ", idcodevalue='" + idcodevalue + '\'' + "\n" +
                ", birthday='" + birthday + '\'' + "\n" +
                ", birthdayvalue1='" + birthdayvalue1 + '\'' + "\n" +
                ", birthdayvalue2='" + birthdayvalue2 + '\'' + "\n" +
                ", age='" + age + '\'' + "\n" +
                ", agevalue='" + agevalue + '\'' + "\n" +
                ", constellation='" + constellation + '\'' + "\n" +
                ", constellationvalue='" + constellationvalue + '\'' + "\n" +
                ", chinesecs='" + chinesecs + '\'' + "\n" +
                ", chinesecsvalue='" + chinesecsvalue + '\'' + "\n" +
                ", race='" + race + '\'' + "\n" +
                ", racevalue='" + racevalue + '\'' + "\n" +
                ", marriage='" + marriage + '\'' + "\n" +
                ", children='" + children + '\'' + "\n" +
                ", zzmm='" + zzmm + '\'' + "\n" +
                ", zgxl='" + zgxl + '\'' + "\n" +
                ", byyx='" + byyx + '\'' + "\n" +
                ", byyxvalue='" + byyxvalue + '\'' + "\n" +
                ", sxzy='" + sxzy + '\'' + "\n" +
                ", sxzyvalue='" + sxzyvalue + '\'' + "\n" +
                ", pyfs='" + pyfs + '\'' + "\n" +
                ", firstla='" + firstla + '\'' + "\n" +
                ", elsela='" + elsela + '\'' + "\n" +
                ", posttitle='" + posttitle + '\'' + "\n" +
                ", zyzstype='" + zyzstype + '\'' + "\n" +
                ", zyzstypevalue='" + zyzstypevalue + '\'' + "\n" +
                ", zyzsname='" + zyzsname + '\'' + "\n" +
                ", zyzsnamevalue='" + zyzsnamevalue + '\'' + "\n" +
                ", firstworkingtime='" + firstworkingtime + '\'' + "\n" +
                ", firstworkingtimevalue1='" + firstworkingtimevalue1 + '\'' + "\n" +
                ", firstworkingtimevalue2='" + firstworkingtimevalue2 + '\'' + "\n" +
                ", workingage='" + workingage + '\'' + "\n" +
                ", workingagevalue='" + workingagevalue + '\'' + "\n" +
                ", parentcompany='" + parentcompany + '\'' + "\n" +
                ", parentcompanyvalue='" + parentcompanyvalue + '\'' + "\n" +
                ", postlevel='" + postlevel + '\'' + "\n" +
                ", entrydate='" + entrydate + '\'' + "\n" +
                ", sn='" + sn + '\'' + "\n" +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' + "\n" +
                ", employeetype='" + employeetype + '\'' + "\n" +
                ", salary='" + salary + '\'' + "\n" +
                ", ssb='" + ssb + '\'' + "\n" +
                ", ssbgscd='" + ssbgscd + '\'' + "\n" +
                ", ssbgrcd='" + ssbgrcd + '\'' + "\n" +
                ", gjj='" + gjj + '\'' + "\n" +
                ", gjjgscd='" + gjjgscd + '\'' + "\n" +
                ", gjjgrcd='" + gjjgrcd + '\'' + "\n" +
                ", khh='" + khh + '\'' + "\n" +
                ", salaryaccount='" + salaryaccount + '\'' + "\n" +
                ", sbjnd='" + sbjnd + '\'' + "\n" +
                ", sbcode='" + sbcode + '\'' + "\n" +
                ", gjjcode='" + gjjcode + '\'' + "\n" +
                ", privateemail='" + privateemail + '\'' + "\n" +
                ", companyemail='" + companyemail + '\'' + "\n" +
                ", emergencycontract='" + emergencycontract + '\'' + "\n" +
                ", emergencyrp='" + emergencyrp + '\'' + "\n" +
                ", emergencyphone='" + emergencyphone + '\'' + "\n" +
                ", address='" + address + '\'' + "\n" +
                ", remark='" + remark + '\'' + "\n" +
                '}';
    }

}
