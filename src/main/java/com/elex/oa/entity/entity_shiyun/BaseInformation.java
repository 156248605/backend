package com.elex.oa.entity.entity_shiyun;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:基本信息
 * @Date:Created in  17:37 2018\3\20 0020
 * @Modify By:
 */
@Table(name = "tb_id_baseinformation")
public class BaseInformation implements Serializable{
    @Id
    private Integer id;
    private String truename;
    private String bloodtype;
    private String employeenumber;
    private String marriage;
    private String englishname;
    private String race;
    private String idcode;
    private String hktype;
    private String birthday;
    private String zzmm;
    private Integer age;
    private String bysj;
    private String sex;
    private String hkszd;
    private String constellation;
    private String zgxl;
    private String nationality;
    private String byyx;
    private String sxzy;
    private String pyfs;
    private String firstla;
    private String secondla;
    private String thirdla;
    private String elsela;
    private String zyzstype;
    private String zyzs;
    private String zyzscp;
    private String firstworkingtime;
    private Integer workingage;
    private String sbdd;
    private String parentcompany;

    public BaseInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getIdcode() {
        return idcode;
    }

    public void setIdcode(String idcode) {
        this.idcode = idcode;
    }

    public String getHktype() {
        return hktype;
    }

    public void setHktype(String hktype) {
        this.hktype = hktype;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBysj() {
        return bysj;
    }

    public void setBysj(String bysj) {
        this.bysj = bysj;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHkszd() {
        return hkszd;
    }

    public void setHkszd(String hkszd) {
        this.hkszd = hkszd;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getZgxl() {
        return zgxl;
    }

    public void setZgxl(String zgxl) {
        this.zgxl = zgxl;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getSecondla() {
        return secondla;
    }

    public void setSecondla(String secondla) {
        this.secondla = secondla;
    }

    public String getThirdla() {
        return thirdla;
    }

    public void setThirdla(String thirdla) {
        this.thirdla = thirdla;
    }

    public String getElsela() {
        return elsela;
    }

    public void setElsela(String elsela) {
        this.elsela = elsela;
    }

    public String getZyzstype() {
        return zyzstype;
    }

    public void setZyzstype(String zyzstype) {
        this.zyzstype = zyzstype;
    }

    public String getZyzs() {
        return zyzs;
    }

    public void setZyzs(String zyzs) {
        this.zyzs = zyzs;
    }

    public String getZyzscp() {
        return zyzscp;
    }

    public void setZyzscp(String zyzscp) {
        this.zyzscp = zyzscp;
    }

    public String getFirstworkingtime() {
        return firstworkingtime;
    }

    public void setFirstworkingtime(String firstworkingtime) {
        this.firstworkingtime = firstworkingtime;
    }

    public Integer getWorkingage() {
        return workingage;
    }

    public void setWorkingage(Integer workingage) {
        this.workingage = workingage;
    }

    public String getSbdd() {
        return sbdd;
    }

    public void setSbdd(String sbdd) {
        this.sbdd = sbdd;
    }

    public String getParentcompany() {
        return parentcompany;
    }

    public void setParentcompany(String parentcompany) {
        this.parentcompany = parentcompany;
    }
}
