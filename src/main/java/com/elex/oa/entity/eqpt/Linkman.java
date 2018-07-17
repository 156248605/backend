package com.elex.oa.entity.eqpt;

public class Linkman {
    // 联系人编号
    private String linkId;
    // 联系人职务
    private String job;
    // 联系人姓名
    private String name;
    // 联系人电话
    private String tel;
    // 联系人邮箱
    private String email;
    // 联系人QQ
    private String qqNum;
    // 联系人微信
    private String wechatNum;
    // 联系地址
    private String address;
    // 唯一识别Id
    private int onlyIdL;
    // 查询条件
    // 查询条件
    private String linkIdC;
    private String nameC;
    private String telC;
    private String jobC;
    private String emailC;
    private String qqNumC;
    private String wechatNumC;
    private String addressC;

    public Linkman() {
    }

    public Linkman(String linkId, String job, String name, String tel, String email, String qqNum, String wechatNum, String address, int onlyIdL, String linkIdC, String nameC, String telC, String jobC, String emailC, String qqNumC, String wechatNumC, String addressC) {
        this.linkId = linkId;
        this.job = job;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.qqNum = qqNum;
        this.wechatNum = wechatNum;
        this.address = address;
        this.onlyIdL = onlyIdL;
        this.linkIdC = linkIdC;
        this.nameC = nameC;
        this.telC = telC;
        this.jobC = jobC;
        this.emailC = emailC;
        this.qqNumC = qqNumC;
        this.wechatNumC = wechatNumC;
        this.addressC = addressC;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOnlyIdL() {
        return onlyIdL;
    }

    public void setOnlyIdL(int onlyIdL) {
        this.onlyIdL = onlyIdL;
    }

    public String getLinkIdC() {
        return linkIdC;
    }

    public void setLinkIdC(String linkIdC) {
        this.linkIdC = linkIdC;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getTelC() {
        return telC;
    }

    public void setTelC(String telC) {
        this.telC = telC;
    }

    public String getJobC() {
        return jobC;
    }

    public void setJobC(String jobC) {
        this.jobC = jobC;
    }

    public String getEmailC() {
        return emailC;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public String getQqNumC() {
        return qqNumC;
    }

    public void setQqNumC(String qqNumC) {
        this.qqNumC = qqNumC;
    }

    public String getWechatNumC() {
        return wechatNumC;
    }

    public void setWechatNumC(String wechatNumC) {
        this.wechatNumC = wechatNumC;
    }

    public String getAddressC() {
        return addressC;
    }

    public void setAddressC(String addressC) {
        this.addressC = addressC;
    }

    @Override
    public String toString() {
        return "Linkman{" +
                "linkId='" + linkId + '\'' +
                ", job='" + job + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", qqNum='" + qqNum + '\'' +
                ", wechatNum='" + wechatNum + '\'' +
                ", address='" + address + '\'' +
                ", onlyIdL=" + onlyIdL +
                ", linkIdC='" + linkIdC + '\'' +
                ", nameC='" + nameC + '\'' +
                ", telC='" + telC + '\'' +
                ", jobC='" + jobC + '\'' +
                ", emailC='" + emailC + '\'' +
                ", qqNumC='" + qqNumC + '\'' +
                ", wechatNumC='" + wechatNumC + '\'' +
                ", addressC='" + addressC + '\'' +
                '}';
    }
}
