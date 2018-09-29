package com.elex.oa.entity.entity_shiyun;

import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:录用申请单
 * @Date:Created in  13:49 2018\9\28 0028
 * @Modify By:
 */
public class Lysqd implements Serializable {
    private String id;//ID
    private String f_lygw;//录用岗位ID
    private String f_lybm;//录用部门ID
    private String f_rzrq;//入职日期
    private String f_htqx;//合同期限
    private String f_lyxz;//录用薪资
    private String f_hy_name;//婚姻
    private String f_xl;//学历
    private String f_sy_name;//生育
    private String f_zy;//专业
    private String f_sfzh;//身份证号
    private String f_byxx;//毕业学校
    private String f_hjdz;//户籍地址
    private String f_bysj;//毕业时间
    private String f_jzdz;//居住地址
    private String f_lxdh;//联系电话
    private String f_yjlxr;//已经联系人
    private String f_yjlxfs;//应急联系方式
    private String f_txrq;//填写日期
    private String f_xm;//姓名

    public Lysqd() {
    }

    public String getF_xm() {
        return f_xm;
    }

    public void setF_xm(String f_xm) {
        this.f_xm = f_xm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getF_lygw() {
        return f_lygw;
    }

    public void setF_lygw(String f_lygw) {
        this.f_lygw = f_lygw;
    }

    public String getF_lybm() {
        return f_lybm;
    }

    public void setF_lybm(String f_lybm) {
        this.f_lybm = f_lybm;
    }

    public String getF_rzrq() {
        return f_rzrq;
    }

    public void setF_rzrq(String f_rzrq) {
        this.f_rzrq = f_rzrq;
    }

    public String getF_htqx() {
        return f_htqx;
    }

    public void setF_htqx(String f_htqx) {
        this.f_htqx = f_htqx;
    }

    public String getF_lyxz() {
        return f_lyxz;
    }

    public void setF_lyxz(String f_lyxz) {
        this.f_lyxz = f_lyxz;
    }

    public String getF_hy_name() {
        return f_hy_name;
    }

    public void setF_hy_name(String f_hy_name) {
        this.f_hy_name = f_hy_name;
    }

    public String getF_xl() {
        return f_xl;
    }

    public void setF_xl(String f_xl) {
        this.f_xl = f_xl;
    }

    public String getF_sy_name() {
        return f_sy_name;
    }

    public void setF_sy_name(String f_sy_name) {
        this.f_sy_name = f_sy_name;
    }

    public String getF_zy() {
        return f_zy;
    }

    public void setF_zy(String f_zy) {
        this.f_zy = f_zy;
    }

    public String getF_sfzh() {
        return f_sfzh;
    }

    public void setF_sfzh(String f_sfzh) {
        this.f_sfzh = f_sfzh;
    }

    public String getF_byxx() {
        return f_byxx;
    }

    public void setF_byxx(String f_byxx) {
        this.f_byxx = f_byxx;
    }

    public String getF_hjdz() {
        return f_hjdz;
    }

    public void setF_hjdz(String f_hjdz) {
        this.f_hjdz = f_hjdz;
    }

    public String getF_bysj() {
        return f_bysj;
    }

    public void setF_bysj(String f_bysj) {
        this.f_bysj = f_bysj;
    }

    public String getF_jzdz() {
        return f_jzdz;
    }

    public void setF_jzdz(String f_jzdz) {
        this.f_jzdz = f_jzdz;
    }

    public String getF_lxdh() {
        return f_lxdh;
    }

    public void setF_lxdh(String f_lxdh) {
        this.f_lxdh = f_lxdh;
    }

    public String getF_yjlxr() {
        return f_yjlxr;
    }

    public void setF_yjlxr(String f_yjlxr) {
        this.f_yjlxr = f_yjlxr;
    }

    public String getF_yjlxfs() {
        return f_yjlxfs;
    }

    public void setF_yjlxfs(String f_yjlxfs) {
        this.f_yjlxfs = f_yjlxfs;
    }

    public String getF_txrq() {
        return f_txrq;
    }

    public void setF_txrq(String f_txrq) {
        this.f_txrq = f_txrq;
    }

    @Override
    public String toString() {
        return "Lysqd{" +
                "id='" + id + '\'' +
                ", f_lygw='" + f_lygw + '\'' +
                ", f_lybm='" + f_lybm + '\'' +
                ", f_rzrq='" + f_rzrq + '\'' +
                ", f_htqx='" + f_htqx + '\'' +
                ", f_lyxz='" + f_lyxz + '\'' +
                ", f_hy_name='" + f_hy_name + '\'' +
                ", f_xl='" + f_xl + '\'' +
                ", f_sy_name='" + f_sy_name + '\'' +
                ", f_zy='" + f_zy + '\'' +
                ", f_sfzh='" + f_sfzh + '\'' +
                ", f_byxx='" + f_byxx + '\'' +
                ", f_hjdz='" + f_hjdz + '\'' +
                ", f_bysj='" + f_bysj + '\'' +
                ", f_jzdz='" + f_jzdz + '\'' +
                ", f_lxdh='" + f_lxdh + '\'' +
                ", f_yjlxr='" + f_yjlxr + '\'' +
                ", f_yjlxfs='" + f_yjlxfs + '\'' +
                ", f_txrq='" + f_txrq + '\'' +
                '}';
    }
}
