package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:人事档案的人事信息类
 * @Date:Created in  17:24 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_personalinformation")
public class PersonalInformation implements Serializable{
    @Id
    private Integer id;//主键(人事总体表1)
    private Integer userid;//用户ID
    private String employeenumber;//员工号
    private String employeenumbervalue;//判断条件
    private String sex;//性别
    private Integer depid;//部门ID
    private String telphone;//办公电话
    private Integer telphoneid;//办公电话ID
    private String telphonevalue;//判断条件
    private String mobilephone;//移动电话
    private String mobilephonevalue;//判断条件
    private Integer baseinformationid;//基本信息ID
    private Integer manageinformationid;//管理信息ID
    private Integer costinformationid;//成本信息ID
    private Integer otherinformationid;//其他信息ID
    private String company;//公司（源自部门信息）（只显示）
    private String depname;//部门（源自部门信息）（只显示）
    private String principaltruename;//主管姓名（源于人事信息）（只显示）
    private String principalemployeenumber;//主管工号（源于人事信息）（只显示）

    private List<Integer> postids;//岗位IDs（人事和岗位的关系表2）
    private String postnames;//多岗位（源自人事和岗位的关系信息）（只显示）

    private Integer isactive;//是否激活（用户表3）
    private String username;//登录ID
    private String truename;//用户姓名

    private String userphoto;//免冠照片（基础信息表4）
    private String idphoto1;//身份证正面
    private String idphoto2;//身份证背面
    private String englishname;//英文名
    private String idcode;//身份证号码
    private String birthday;//出生日期（源于身份证号码）
    private String birthdayvalue1;//判断条件
    private String birthdayvalue2;//判断条件
    private String age;//年龄（源于身份证号码）（只显示）
    private String constellation;//星座（源于身份证号码）
    private String chinesecs;//属相（源于身份证号码）
    private String race;//民族
    private String marriage;//婚姻状况
    private String children;//生育
    private String zzmm;//政治面貌
    private String zgxl;//最高学历
    private String byyx;//毕业院校
    private String sxzy;//所学专业
    private String pyfs;//培养方式
    private String firstla;//第一外语
    private String elsela;//其它外语
    private String posttitle;//职称
    private String zyzstype;//职业证书类型
    private String zyzsname;//职业证书名称
    private String firstworkingtime;//首次工作时间
    private String workingage;//工龄（只显示）
    private String parentcompany;//上家雇主


    private String zj;//职级（管理信息表5）
    private String entrydate;//入职日期
    private String sn;//司龄（只显示）
    private String zhuanzhengdate;//转正日期（默认入职日期后的两个月，可以修改）
    private String employeetype;//员工类型

    private String salary;//薪资标准（成本信息表6）
    private String ssb;//社保基数
    private String ssbgscd;//社保公司缴费比例
    private String ssbgrcd;//社保个人缴费比例
    private String gjj;//公积金基数
    private String gjjgscd;//公积金公司缴费比例
    private String gjjgrcd;//公积金个人缴费比例
    private String khh;//开户行
    private String salaryaccount;//工资账号
    private String sbjnd;//社保缴纳地
    private String sbcode;//社保账号
    private String gjjcode;//公积金账号

    private String privateemail;//私人邮件（其他信息表7）
    private String companyemail;//公司邮件
    private String emergencycontract;//紧急联系人
    private String emergencyrp;//紧急联系人关系
    private String emergencyphone;//紧急联系电话
    private String address;//应急联系地址
    private String remark;//备注

    public PersonalInformation() {
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

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
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
        return "PersonalInformation{" +
                "id=" + id +
                ", userid=" + userid +
                ", employeenumber='" + employeenumber + '\'' +
                ", employeenumbervalue='" + employeenumbervalue + '\'' +
                ", sex='" + sex + '\'' +
                ", depid=" + depid +
                ", telphone='" + telphone + '\'' +
                ", telphoneid=" + telphoneid +
                ", telphonevalue='" + telphonevalue + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", mobilephonevalue='" + mobilephonevalue + '\'' +
                ", baseinformationid=" + baseinformationid +
                ", manageinformationid=" + manageinformationid +
                ", costinformationid=" + costinformationid +
                ", otherinformationid=" + otherinformationid +
                ", company='" + company + '\'' +
                ", depname='" + depname + '\'' +
                ", principaltruename='" + principaltruename + '\'' +
                ", principalemployeenumber='" + principalemployeenumber + '\'' +
                ", postids=" + postids +
                ", postnames='" + postnames + '\'' +
                ", isactive=" + isactive +
                ", username='" + username + '\'' +
                ", truename='" + truename + '\'' +
                ", userphoto='" + userphoto + '\'' +
                ", idphoto1='" + idphoto1 + '\'' +
                ", idphoto2='" + idphoto2 + '\'' +
                ", englishname='" + englishname + '\'' +
                ", idcode='" + idcode + '\'' +
                ", birthday='" + birthday + '\'' +
                ", birthdayvalue1='" + birthdayvalue1 + '\'' +
                ", birthdayvalue2='" + birthdayvalue2 + '\'' +
                ", age='" + age + '\'' +
                ", constellation='" + constellation + '\'' +
                ", chinesecs='" + chinesecs + '\'' +
                ", race='" + race + '\'' +
                ", marriage='" + marriage + '\'' +
                ", children='" + children + '\'' +
                ", zzmm='" + zzmm + '\'' +
                ", zgxl='" + zgxl + '\'' +
                ", byyx='" + byyx + '\'' +
                ", sxzy='" + sxzy + '\'' +
                ", pyfs='" + pyfs + '\'' +
                ", firstla='" + firstla + '\'' +
                ", elsela='" + elsela + '\'' +
                ", posttitle='" + posttitle + '\'' +
                ", zyzstype='" + zyzstype + '\'' +
                ", zyzsname='" + zyzsname + '\'' +
                ", firstworkingtime='" + firstworkingtime + '\'' +
                ", parentcompany='" + parentcompany + '\'' +
                ", zj='" + zj + '\'' +
                ", entrydate='" + entrydate + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                ", employeetype='" + employeetype + '\'' +
                ", salary='" + salary + '\'' +
                ", ssb='" + ssb + '\'' +
                ", ssbgscd='" + ssbgscd + '\'' +
                ", ssbgrcd='" + ssbgrcd + '\'' +
                ", gjj='" + gjj + '\'' +
                ", gjjgscd='" + gjjgscd + '\'' +
                ", gjjgrcd='" + gjjgrcd + '\'' +
                ", khh='" + khh + '\'' +
                ", salaryaccount='" + salaryaccount + '\'' +
                ", sbjnd='" + sbjnd + '\'' +
                ", sbcode='" + sbcode + '\'' +
                ", gjjcode='" + gjjcode + '\'' +
                ", privateemail='" + privateemail + '\'' +
                ", companyemail='" + companyemail + '\'' +
                ", emergencycontract='" + emergencycontract + '\'' +
                ", emergencyrp='" + emergencyrp + '\'' +
                ", emergencyphone='" + emergencyphone + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
