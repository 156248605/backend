package com.elex.oa.entity.hr_entity;

import com.elex.oa.util.hr_util.IDcodeUtil;
import org.apache.commons.lang.StringUtils;

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
    private Integer id;//主键
    private String userphoto;//免冠照片
    private String idphoto1;//身份证正面
    private String idphoto2;//身份证背面
    private String englishname;//英文名
    private String idcode;//身份证号码
    private String sex;//性别
    private String birthday;//出生日期
    private String constellation;//星座
    private String chinesecs;//属相
    private Integer raceid;//民族ID
    private String marriage;//婚姻
    private Integer childrenid;//生育ID
    private Integer zzmmid;//政治面貌ID
    private Integer zgxlid;//最高学历ID
    private Integer byyxid;//毕业院校ID
    private Integer sxzyid;//所学专业ID
    private Integer pyfsid;//培养方式ID
    private Integer firstlaid;//第一外语ID
    private Integer elselaid;//其他外语ID
    private Integer posttitleid;//职称ID
    private Integer zyzstypeid;//职业证书类型ID
    private Integer zyzsnameid;//职业证书名称ID
    private String firstworkingtime;//首次参加工作时间
    private Integer parentcompanyid;//上家雇主ID
    private String hj;//户籍

    public BaseInformation() {
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if(StringUtils.isNotBlank(idcode)){
            try {
                this.setSex(IDcodeUtil.getSex(idcode));
                this.setBirthday(IDcodeUtil.getBirthday(idcode));
                this.setConstellation(IDcodeUtil.getConstellation(idcode));
                this.setChinesecs(IDcodeUtil.getChinesecs(idcode));
                this.setHj(IDcodeUtil.getProvinceByIdcode(idcode));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

    public Integer getRaceid() {
        return raceid;
    }

    public void setRaceid(Integer raceid) {
        this.raceid = raceid;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public Integer getChildrenid() {
        return childrenid;
    }

    public void setChildrenid(Integer childrenid) {
        this.childrenid = childrenid;
    }

    public Integer getZzmmid() {
        return zzmmid;
    }

    public void setZzmmid(Integer zzmmid) {
        this.zzmmid = zzmmid;
    }

    public Integer getZgxlid() {
        return zgxlid;
    }

    public void setZgxlid(Integer zgxlid) {
        this.zgxlid = zgxlid;
    }

    public Integer getByyxid() {
        return byyxid;
    }

    public void setByyxid(Integer byyxid) {
        this.byyxid = byyxid;
    }

    public Integer getSxzyid() {
        return sxzyid;
    }

    public void setSxzyid(Integer sxzyid) {
        this.sxzyid = sxzyid;
    }

    public Integer getPyfsid() {
        return pyfsid;
    }

    public void setPyfsid(Integer pyfsid) {
        this.pyfsid = pyfsid;
    }

    public Integer getFirstlaid() {
        return firstlaid;
    }

    public void setFirstlaid(Integer firstlaid) {
        this.firstlaid = firstlaid;
    }

    public Integer getElselaid() {
        return elselaid;
    }

    public void setElselaid(Integer elselaid) {
        this.elselaid = elselaid;
    }

    public Integer getPosttitleid() {
        return posttitleid;
    }

    public void setPosttitleid(Integer posttitleid) {
        this.posttitleid = posttitleid;
    }

    public Integer getZyzstypeid() {
        return zyzstypeid;
    }

    public void setZyzstypeid(Integer zyzstypeid) {
        this.zyzstypeid = zyzstypeid;
    }

    public Integer getZyzsnameid() {
        return zyzsnameid;
    }

    public void setZyzsnameid(Integer zyzsnameid) {
        this.zyzsnameid = zyzsnameid;
    }

    public String getFirstworkingtime() {
        return firstworkingtime;
    }

    public void setFirstworkingtime(String firstworkingtime) {
        this.firstworkingtime = firstworkingtime;
    }

    public Integer getParentcompanyid() {
        return parentcompanyid;
    }

    public void setParentcompanyid(Integer parentcompanyid) {
        this.parentcompanyid = parentcompanyid;
    }

    @Override
    public String toString() {
        return "BaseInformation{" + "\n" +
                "id=" + id + "\n" +
                ", userphoto='" + userphoto + '\'' + "\n" +
                ", idphoto1='" + idphoto1 + '\'' + "\n" +
                ", idphoto2='" + idphoto2 + '\'' + "\n" +
                ", englishname='" + englishname + '\'' + "\n" +
                ", idcode='" + idcode + '\'' + "\n" +
                ", birthday='" + birthday + '\'' + "\n" +
                ", constellation='" + constellation + '\'' + "\n" +
                ", chinesecs='" + chinesecs + '\'' + "\n" +
                ", raceid=" + raceid + "\n" +
                ", marriage='" + marriage + '\'' + "\n" +
                ", childrenid=" + childrenid + "\n" +
                ", zzmmid=" + zzmmid + "\n" +
                ", zgxlid=" + zgxlid + "\n" +
                ", byyxid=" + byyxid + "\n" +
                ", sxzyid=" + sxzyid + "\n" +
                ", pyfsid=" + pyfsid + "\n" +
                ", firstlaid=" + firstlaid + "\n" +
                ", elselaid=" + elselaid + "\n" +
                ", posttitleid=" + posttitleid + "\n" +
                ", zyzstypeid=" + zyzstypeid + "\n" +
                ", zyzsnameid=" + zyzsnameid + "\n" +
                ", firstworkingtime='" + firstworkingtime + '\'' + "\n" +
                ", parentcompanyid=" + parentcompanyid + "\n" +
                '}';
    }
}
