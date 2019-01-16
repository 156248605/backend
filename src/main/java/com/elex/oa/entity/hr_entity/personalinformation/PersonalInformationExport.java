package com.elex.oa.entity.hr_entity.personalinformation;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\15 0015 15:24
 * @Version 1.0
 **/
public class PersonalInformationExport {
    private String employeenumber;//员工号
    private String username;//登录ID
    private String truename;//姓名
    private String isactive;//是否激活
    private String isatwork;//是否在职(与数据库中字段名不一样)

    private String lastworkingdate;//最后工作时间
    private String dimissiontype;//离职类型
    private String dimissiondirection;//离职方向
    private String dimissionreason;//离职原因
    private String dimission_transactoremployeenumber;//离职办理人员工号
    private String dimission_transactiondate;//离职办理日期

    private String depcode;//部门编号
    private String depname;//部门名称

    private String postlist;//格式：(之间用英文分号相连)[岗位名称-岗位编号]

    private String userphoto;//免冠照片
    private String idphoto1;//身份证正面
    private String idphoto2;//身份证背面
    private String englishname;//英文名
    private String idcode;//身份证号码
    private String sex;//性别
    private String birthday;//出生日期
    private String constellation;//星座
    private String chinesecs;//生肖
    private String race;//民族
    private String marriage;//婚姻
    private String children;//生育
    private String zzmm;//政治面貌
    private String zgxl;//最高学历
    private String byyx;//毕业院校
    private String sxzy;//所学专业
    private String pyfs;//培养方式
    private String firstla;//第一外语
    private String elsela;//其他外语
    private String posttitle;//职称
    private String zyzstype;//职业证书类型
    private String zyzsname;//职业证书名称
    private String parentcompany;//上家雇主
    private String firstworkingtime;//首次参加工作时间
    private String household_register;//户籍

    private String postrank;//职级
    private String employeetype;//员工类型
    private String entrydate;//入职时间
    private String zhuanzhengdate;//转正时间

    private String salarystandard;//工资标准
    private String ssb;//社保基数
    private String ssbgscd;//社保公司承担比例
    private String ssbgrcd;//社保个人承担比例
    private String gjj;//公积金基数
    private String gjjgscd;//公积金公司承担比例
    private String gjjgrcd;//公积金个人承担比例
    private String khh;//开户行
    private String sbjnd;//社保缴纳地
    private String salaryaccount;//工资账号
    private String sbcode;//社保账号
    private String gjjcode;//公积金账号

    private String privateemail;//私人邮箱
    private String companyemail;//公司邮箱
    private String emergencycontract;//紧急联系人
    private String emergencyrp;//紧急联系人关系
    private String emergencyphone;//紧急联系电话
    private String address;//住址
    private String mobilephone;//移动电话
    private String telphone;//办公电话
    private String remark;//备注

    //格式为：(之间用英文分号相连)[合同编号-合同开始时间-合同结束时间-合同类型-合同附件地址-合同备注-变更人员工号-变更日期]
    private String contractlist;

    public PersonalInformationExport() {
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

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getIsatwork() {
        return isatwork;
    }

    public void setIsatwork(String isatwork) {
        this.isatwork = isatwork;
    }

    public String getLastworkingdate() {
        return lastworkingdate;
    }

    public void setLastworkingdate(String lastworkingdate) {
        this.lastworkingdate = lastworkingdate;
    }

    public String getDimissiontype() {
        return dimissiontype;
    }

    public void setDimissiontype(String dimissiontype) {
        this.dimissiontype = dimissiontype;
    }

    public String getDimissiondirection() {
        return dimissiondirection;
    }

    public void setDimissiondirection(String dimissiondirection) {
        this.dimissiondirection = dimissiondirection;
    }

    public String getDimissionreason() {
        return dimissionreason;
    }

    public void setDimissionreason(String dimissionreason) {
        this.dimissionreason = dimissionreason;
    }

    public String getDimission_transactoremployeenumber() {
        return dimission_transactoremployeenumber;
    }

    public void setDimission_transactoremployeenumber(String dimission_transactoremployeenumber) {
        this.dimission_transactoremployeenumber = dimission_transactoremployeenumber;
    }

    public String getDimission_transactiondate() {
        return dimission_transactiondate;
    }

    public void setDimission_transactiondate(String dimission_transactiondate) {
        this.dimission_transactiondate = dimission_transactiondate;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getPostlist() {
        return postlist;
    }

    public void setPostlist(String postlist) {
        this.postlist = postlist;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getParentcompany() {
        return parentcompany;
    }

    public void setParentcompany(String parentcompany) {
        this.parentcompany = parentcompany;
    }

    public String getFirstworkingtime() {
        return firstworkingtime;
    }

    public void setFirstworkingtime(String firstworkingtime) {
        this.firstworkingtime = firstworkingtime;
    }

    public String getHousehold_register() {
        return household_register;
    }

    public void setHousehold_register(String household_register) {
        this.household_register = household_register;
    }

    public String getPostrank() {
        return postrank;
    }

    public void setPostrank(String postrank) {
        this.postrank = postrank;
    }

    public String getEmployeetype() {
        return employeetype;
    }

    public void setEmployeetype(String employeetype) {
        this.employeetype = employeetype;
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

    public String getSalarystandard() {
        return salarystandard;
    }

    public void setSalarystandard(String salarystandard) {
        this.salarystandard = salarystandard;
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

    public String getSbjnd() {
        return sbjnd;
    }

    public void setSbjnd(String sbjnd) {
        this.sbjnd = sbjnd;
    }

    public String getSalaryaccount() {
        return salaryaccount;
    }

    public void setSalaryaccount(String salaryaccount) {
        this.salaryaccount = salaryaccount;
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

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContractlist() {
        return contractlist;
    }

    public void setContractlist(String contractlist) {
        this.contractlist = contractlist;
    }

    @Override
    public String toString() {
        return "PersonalInformationExport{" +
                "employeenumber='" + employeenumber + '\'' +
                ", username='" + username + '\'' +
                ", truename='" + truename + '\'' +
                ", isactive='" + isactive + '\'' +
                ", isatwork='" + isatwork + '\'' +
                ", lastworkingdate='" + lastworkingdate + '\'' +
                ", dimissiontype='" + dimissiontype + '\'' +
                ", dimissiondirection='" + dimissiondirection + '\'' +
                ", dimissionreason='" + dimissionreason + '\'' +
                ", dimission_transactoremployeenumber='" + dimission_transactoremployeenumber + '\'' +
                ", dimission_transactiondate='" + dimission_transactiondate + '\'' +
                ", depcode='" + depcode + '\'' +
                ", depname='" + depname + '\'' +
                ", postlist='" + postlist + '\'' +
                ", userphoto='" + userphoto + '\'' +
                ", idphoto1='" + idphoto1 + '\'' +
                ", idphoto2='" + idphoto2 + '\'' +
                ", englishname='" + englishname + '\'' +
                ", idcode='" + idcode + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
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
                ", parentcompany='" + parentcompany + '\'' +
                ", firstworkingtime='" + firstworkingtime + '\'' +
                ", household_register='" + household_register + '\'' +
                ", postrank='" + postrank + '\'' +
                ", employeetype='" + employeetype + '\'' +
                ", entrydate='" + entrydate + '\'' +
                ", zhuanzhengdate='" + zhuanzhengdate + '\'' +
                ", salarystandard='" + salarystandard + '\'' +
                ", ssb='" + ssb + '\'' +
                ", ssbgscd='" + ssbgscd + '\'' +
                ", ssbgrcd='" + ssbgrcd + '\'' +
                ", gjj='" + gjj + '\'' +
                ", gjjgscd='" + gjjgscd + '\'' +
                ", gjjgrcd='" + gjjgrcd + '\'' +
                ", khh='" + khh + '\'' +
                ", sbjnd='" + sbjnd + '\'' +
                ", salaryaccount='" + salaryaccount + '\'' +
                ", sbcode='" + sbcode + '\'' +
                ", gjjcode='" + gjjcode + '\'' +
                ", privateemail='" + privateemail + '\'' +
                ", companyemail='" + companyemail + '\'' +
                ", emergencycontract='" + emergencycontract + '\'' +
                ", emergencyrp='" + emergencyrp + '\'' +
                ", emergencyphone='" + emergencyphone + '\'' +
                ", address='" + address + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", telphone='" + telphone + '\'' +
                ", remark='" + remark + '\'' +
                ", contractlist='" + contractlist + '\'' +
                '}';
    }
}