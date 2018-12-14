package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\29 0029 16:50
 * @Version 1.0
 **/
@Table(name = "tb_id_personal_info")
public class Personalinfo implements Serializable {
    private static final long serialVersionUID = 7301487008224606934L;
    @Id
    private String employeenumber;//员工号（主键）
    private String username;//账号
    private String truename;//姓名
    private String password;//密码
    private String isactive;//是否激活
    private String state;//在职状态
    private String idcode;//身份证号码
    private String birthday;//出生日期
    private String sex;//性别
    private String chinesecs;//属相
    private String constellation;//星座
    private String household_register;//户籍
    private String depcode;//部门编号（外键）
    private String telphoneid;//办公电话（外键）（数据字典）
    private String mobilephone;//移动电话
    private String userphoto;//免冠照片
    private String idphoto1;//身份证正面（头像）
    private String idphoto2;//身份证反面（国徽）
    private String englishname;//英文名
    private String raceid;//民族（外键）（数据字典）
    private String marriage;//婚姻状况
    private String zzmmid;//政治面貌（外键）（数据字典）
    private String childrenid;//生育（外键）（数据字典）
    private String byyxid;//毕业院校（外键）（数据字典）
    private String zgxlid;//最高学历（外键）（数据字典）
    private String sxzyid;//所学专业（外键）（数据字典）
    private String pyfsid;//培养方式（外键）（数据字典）
    private String firstlaid;//第一外语（外键）（数据字典）
    private String elselaid;//其他外语（外键）（数据字典）
    private String posttitleid;//职称（外键）（数据字典）
    private String zyzstypeid;//职业证书类型（外键）（数据字典）
    private String zyzsnameid;//职业证书名称（外键）（数据字典）
    private String parentcompanyid;//上家雇主（外键）（数据字典）
    private String firstworkingtime;//首次参加工作时间
    private String employeetypeid;//员工类型（外键）（数据字典）
    private String entrydate;//入职时间
    private String zhuanzhengdate;//转正时间
    private String salarystandardid;//工资标准（外键）（数据字典）
    private String ssbid;//社保基数（外键）（数据字典）
    private String ssbgscdid;//社保公司承担（外键）（数据字典）
    private String ssbgrcdid;//社保个人承担（外键）（数据字典）
    private String gjjid;//公积金基数（外键）（数据字典）
    private String gjjgscdid;//公积金公司承担（外键）（数据字典）
    private String gjjgrcdid;//公积金个人承担（外键）（数据字典）
    private String khhid;//开户行（外键）（数据字典）
    private String salaryaccount;//社保账号
    private String sbjndid;//社保缴纳地（外键）（数据字典）
    private String sbcode;//社保账号
    private String gjjcode;//公积金账号
    private String privateemail;//个人邮箱
    private String companyemail;//公司邮箱
    private String emergencycontact;//紧急联系人
    private String emergencyrpid;//紧急联系人关系（外键）（数据字典）
    private String emergencyphone;//紧急联系电话
    private String address;//住址
    private String remark;//备注
    private String lastworkingdate;//最后工作时间
    private String dimissiontypeid;//离职类型（外键）（数据字典）
    private String dimissiondirectionid;//离职方向（外键）（数据字典）
    private String dimissionreasonid;//离职原因（外键）（数据字典）
    private String dimission_transaction_id;//离职办理人（外键）
    private String dimission_transaction_date;//办理日期

    public Personalinfo() {
    }

    public Personalinfo(String employeenumber, String depcode) {
        this.employeenumber = employeenumber;
        this.depcode = depcode;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getChinesecs() {
        return chinesecs;
    }

    public void setChinesecs(String chinesecs) {
        this.chinesecs = chinesecs;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getHousehold_register() {
        return household_register;
    }

    public void setHousehold_register(String household_register) {
        this.household_register = household_register;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getTelphoneid() {
        return telphoneid;
    }

    public void setTelphoneid(String telphoneid) {
        this.telphoneid = telphoneid;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
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

    public String getRaceid() {
        return raceid;
    }

    public void setRaceid(String raceid) {
        this.raceid = raceid;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getZzmmid() {
        return zzmmid;
    }

    public void setZzmmid(String zzmmid) {
        this.zzmmid = zzmmid;
    }

    public String getChildrenid() {
        return childrenid;
    }

    public void setChildrenid(String childrenid) {
        this.childrenid = childrenid;
    }

    public String getByyxid() {
        return byyxid;
    }

    public void setByyxid(String byyxid) {
        this.byyxid = byyxid;
    }

    public String getZgxlid() {
        return zgxlid;
    }

    public void setZgxlid(String zgxlid) {
        this.zgxlid = zgxlid;
    }

    public String getSxzyid() {
        return sxzyid;
    }

    public void setSxzyid(String sxzyid) {
        this.sxzyid = sxzyid;
    }

    public String getPyfsid() {
        return pyfsid;
    }

    public void setPyfsid(String pyfsid) {
        this.pyfsid = pyfsid;
    }

    public String getFirstlaid() {
        return firstlaid;
    }

    public void setFirstlaid(String firstlaid) {
        this.firstlaid = firstlaid;
    }

    public String getElselaid() {
        return elselaid;
    }

    public void setElselaid(String elselaid) {
        this.elselaid = elselaid;
    }

    public String getPosttitleid() {
        return posttitleid;
    }

    public void setPosttitleid(String posttitleid) {
        this.posttitleid = posttitleid;
    }

    public String getZyzstypeid() {
        return zyzstypeid;
    }

    public void setZyzstypeid(String zyzstypeid) {
        this.zyzstypeid = zyzstypeid;
    }

    public String getZyzsnameid() {
        return zyzsnameid;
    }

    public void setZyzsnameid(String zyzsnameid) {
        this.zyzsnameid = zyzsnameid;
    }

    public String getParentcompanyid() {
        return parentcompanyid;
    }

    public void setParentcompanyid(String parentcompanyid) {
        this.parentcompanyid = parentcompanyid;
    }

    public String getFirstworkingtime() {
        return firstworkingtime;
    }

    public void setFirstworkingtime(String firstworkingtime) {
        this.firstworkingtime = firstworkingtime;
    }

    public String getEmployeetypeid() {
        return employeetypeid;
    }

    public void setEmployeetypeid(String employeetypeid) {
        this.employeetypeid = employeetypeid;
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

    public String getSalarystandardid() {
        return salarystandardid;
    }

    public void setSalarystandardid(String salarystandardid) {
        this.salarystandardid = salarystandardid;
    }

    public String getSsbid() {
        return ssbid;
    }

    public void setSsbid(String ssbid) {
        this.ssbid = ssbid;
    }

    public String getSsbgscdid() {
        return ssbgscdid;
    }

    public void setSsbgscdid(String ssbgscdid) {
        this.ssbgscdid = ssbgscdid;
    }

    public String getSsbgrcdid() {
        return ssbgrcdid;
    }

    public void setSsbgrcdid(String ssbgrcdid) {
        this.ssbgrcdid = ssbgrcdid;
    }

    public String getGjjid() {
        return gjjid;
    }

    public void setGjjid(String gjjid) {
        this.gjjid = gjjid;
    }

    public String getGjjgscdid() {
        return gjjgscdid;
    }

    public void setGjjgscdid(String gjjgscdid) {
        this.gjjgscdid = gjjgscdid;
    }

    public String getGjjgrcdid() {
        return gjjgrcdid;
    }

    public void setGjjgrcdid(String gjjgrcdid) {
        this.gjjgrcdid = gjjgrcdid;
    }

    public String getKhhid() {
        return khhid;
    }

    public void setKhhid(String khhid) {
        this.khhid = khhid;
    }

    public String getSalaryaccount() {
        return salaryaccount;
    }

    public void setSalaryaccount(String salaryaccount) {
        this.salaryaccount = salaryaccount;
    }

    public String getSbjndid() {
        return sbjndid;
    }

    public void setSbjndid(String sbjndid) {
        this.sbjndid = sbjndid;
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

    public String getEmergencycontact() {
        return emergencycontact;
    }

    public void setEmergencycontact(String emergencycontact) {
        this.emergencycontact = emergencycontact;
    }

    public String getEmergencyrpid() {
        return emergencyrpid;
    }

    public void setEmergencyrpid(String emergencyrpid) {
        this.emergencyrpid = emergencyrpid;
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

    public String getLastworkingdate() {
        return lastworkingdate;
    }

    public void setLastworkingdate(String lastworkingdate) {
        this.lastworkingdate = lastworkingdate;
    }

    public String getDimissiontypeid() {
        return dimissiontypeid;
    }

    public void setDimissiontypeid(String dimissiontypeid) {
        this.dimissiontypeid = dimissiontypeid;
    }

    public String getDimissiondirectionid() {
        return dimissiondirectionid;
    }

    public void setDimissiondirectionid(String dimissiondirectionid) {
        this.dimissiondirectionid = dimissiondirectionid;
    }

    public String getDimissionreasonid() {
        return dimissionreasonid;
    }

    public void setDimissionreasonid(String dimissionreasonid) {
        this.dimissionreasonid = dimissionreasonid;
    }

    public String getDimission_transaction_id() {
        return dimission_transaction_id;
    }

    public void setDimission_transaction_id(String dimission_transaction_id) {
        this.dimission_transaction_id = dimission_transaction_id;
    }

    public String getDimission_transaction_date() {
        return dimission_transaction_date;
    }

    public void setDimission_transaction_date(String dimission_transaction_date) {
        this.dimission_transaction_date = dimission_transaction_date;
    }

    @Override
    public String toString() {
        return "Personalinfo{" +
                "employeenumber='" + employeenumber + '\'' +
                ", username='" + username + '\'' +
                ", truename='" + truename + '\'' +
                ", password='" + password + '\'' +
                ", isactive='" + isactive + '\'' +
                ", state='" + state + '\'' +
                ", idcode='" + idcode + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", chinesecs='" + chinesecs + '\'' +
                ", constellation='" + constellation + '\'' +
                ", household_register='" + household_register + '\'' +
                ", depcode='" + depcode + '\'' +
                ", telphoneid='" + telphoneid + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", userphoto='" + userphoto + '\'' +
                ", idphoto1='" + idphoto1 + '\'' +
                ", idphoto2='" + idphoto2 + '\'' +
                ", englishname='" + englishname + '\'' +
                ", raceid='" + raceid + '\'' +
                ", marriage='" + marriage + '\'' +
                ", zzmmid='" + zzmmid + '\'' +
                ", childrenid='" + childrenid + '\'' +
                ", byyxid='" + byyxid + '\'' +
                ", zgxlid='" + zgxlid + '\'' +
                ", sxzyid='" + sxzyid + '\'' +
                ", pyfsid='" + pyfsid + '\'' +
                ", firstlaid='" + firstlaid + '\'' +
                ", elselaid='" + elselaid + '\'' +
                ", posttitleid='" + posttitleid + '\'' +
                ", zyzstypeid='" + zyzstypeid + '\'' +
                ", zyzsnameid='" + zyzsnameid + '\'' +
                ", parentcompanyid='" + parentcompanyid + '\'' +
                ", firstworkingtime='" + firstworkingtime + '\'' +
                ", employeetypeid='" + employeetypeid + '\'' +
                ", entrydate='" + entrydate + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                ", salarystandardid='" + salarystandardid + '\'' +
                ", ssbid='" + ssbid + '\'' +
                ", ssbgscdid='" + ssbgscdid + '\'' +
                ", ssbgrcdid='" + ssbgrcdid + '\'' +
                ", gjjid='" + gjjid + '\'' +
                ", gjjgscdid='" + gjjgscdid + '\'' +
                ", gjjgrcdid='" + gjjgrcdid + '\'' +
                ", khhid='" + khhid + '\'' +
                ", salaryaccount='" + salaryaccount + '\'' +
                ", sbjndid='" + sbjndid + '\'' +
                ", sbcode='" + sbcode + '\'' +
                ", gjjcode='" + gjjcode + '\'' +
                ", privateemail='" + privateemail + '\'' +
                ", companyemail='" + companyemail + '\'' +
                ", emergencycontact='" + emergencycontact + '\'' +
                ", emergencyrpid='" + emergencyrpid + '\'' +
                ", emergencyphone='" + emergencyphone + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", lastworkingdate='" + lastworkingdate + '\'' +
                ", dimissiontypeid='" + dimissiontypeid + '\'' +
                ", dimissiondirectionid='" + dimissiondirectionid + '\'' +
                ", dimissionreasonid='" + dimissionreasonid + '\'' +
                ", dimission_transaction_id='" + dimission_transaction_id + '\'' +
                ", dimission_transaction_date='" + dimission_transaction_date + '\'' +
                '}';
    }
}