package com.elex.oa.entity.eqpt;

public class Material {

    // 批次号
    private String bn;
    // 序列号
    private String sn;
    // 库位
    private String position;
    // 物料类别
    private String category;
    // 物料名称
    private String name;
    // 物料编码
    private String id;
    // 物料日期
    private String date;
    // 物料品牌
    private String brand;
    // 物料规格
    private String spec;
    // 物料材质
    private String material;
    // 物料存储上限
    private String maxlimit;
    // 物料存储下限
    private String minlimit;
    // 备注
    private String remark;
    // 业务伙伴
    private String partner;

    public Material() {
    }

    public Material(String bn, String sn, String position, String category, String name, String id, String date, String brand, String spec, String material, String maxlimit, String minlimit, String remark, String partner) {
        this.bn = bn;
        this.sn = sn;
        this.position = position;
        this.category = category;
        this.name = name;
        this.id = id;
        this.date = date;
        this.brand = brand;
        this.spec = spec;
        this.material = material;
        this.maxlimit = maxlimit;
        this.minlimit = minlimit;
        this.remark = remark;
        this.partner = partner;
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaxlimit() {
        return maxlimit;
    }

    public void setMaxlimit(String maxlimit) {
        this.maxlimit = maxlimit;
    }

    public String getMinlimit() {
        return minlimit;
    }

    public void setMinlimit(String minlimit) {
        this.minlimit = minlimit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Override
    public String toString() {
        return "Material{" +
                "bn='" + bn + '\'' +
                ", sn='" + sn + '\'' +
                ", position='" + position + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", brand='" + brand + '\'' +
                ", spec='" + spec + '\'' +
                ", material='" + material + '\'' +
                ", maxlimit='" + maxlimit + '\'' +
                ", minlimit='" + minlimit + '\'' +
                ", remark='" + remark + '\'' +
                ", partner='" + partner + '\'' +
                '}';
    }
}
