package com.elex.oa.entity.eqpt;

public class Partner {

    // 对象
    private String pnCategory;
    // 单位
    private String company;
    // 联系人姓名
    private String name;
    // 联系人电话
    private String tel;
    // 地址:国家
    private String country;
    // 地址:省
    private String province;
    // 地址:市
    private String city;
    // 地址:区
    private String area;
    // 地址:详细
    private String detail;

    public Partner() {
    }

    public Partner(String pnCategory, String company, String name, String tel, String country, String province, String city, String area, String detail) {
        this.pnCategory = pnCategory;
        this.company = company;
        this.name = name;
        this.tel = tel;
        this.country = country;
        this.province = province;
        this.city = city;
        this.area = area;
        this.detail = detail;
    }

    public String getPnCategory() {
        return pnCategory;
    }

    public void setPnCategory(String pnCategory) {
        this.pnCategory = pnCategory;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "pnCategory='" + pnCategory + '\'' +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
