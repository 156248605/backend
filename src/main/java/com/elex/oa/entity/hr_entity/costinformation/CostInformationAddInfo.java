package com.elex.oa.entity.hr_entity.costinformation;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\15 0015 11:47
 * @Version 1.0
 **/
public class CostInformationAddInfo {
    private Integer userid;
    private String salary;
    private String khh;
    private String khhvalue;
    private String salaryaccount;
    private String sbjnd;
    private String sbjndvalue;
    private String sbcode;
    private String gjjcode;
    private String ssb;
    private String ssbgscd;
    private String ssbgrcd;
    private String gjj;
    private String gjjgscd;
    private String gjjgrcd;

    public CostInformationAddInfo() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    public String getKhhvalue() {
        return khhvalue;
    }

    public void setKhhvalue(String khhvalue) {
        this.khhvalue = khhvalue;
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

    public String getSbjndvalue() {
        return sbjndvalue;
    }

    public void setSbjndvalue(String sbjndvalue) {
        this.sbjndvalue = sbjndvalue;
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

    @Override
    public String toString() {
        return "CostInformationAddInfo{" +
                "userid=" + userid +
                ", salary='" + salary + '\'' +
                ", khh='" + khh + '\'' +
                ", khhvalue='" + khhvalue + '\'' +
                ", salaryaccount='" + salaryaccount + '\'' +
                ", sbjnd='" + sbjnd + '\'' +
                ", sbjndvalue='" + sbjndvalue + '\'' +
                ", sbcode='" + sbcode + '\'' +
                ", ssb='" + ssb + '\'' +
                ", ssbgscd='" + ssbgscd + '\'' +
                ", ssbgrcd='" + ssbgrcd + '\'' +
                ", gjj='" + gjj + '\'' +
                ", gjjgscd='" + gjjgscd + '\'' +
                ", gjjgrcd='" + gjjgrcd + '\'' +
                '}';
    }
}