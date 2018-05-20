package com.elex.oa.entity.eqpt;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Material {

    // 自增ID
    private int onlyId;
    // 批次号
    @Excel(name = "批次号")
    private String bn;
    // 序列号
    @Excel(name = "序列号")
    private String sn;
    // 库位
    @Excel(name = "库位")
    private String position;
    // 物料类别
    @Excel(name = "物料种类")
    private String category;
    // 物料名称
    @Excel(name = "物料名称")
    private String name;
    // 物料编码
    @Excel(name = "物料编码")
    private String id;
    // 物料日期
    @Excel(name = "物料日期")
    private String date;
    // 物料单价
    @Excel(name = "物料单价")
    private String price;
    // 物料品牌
    @Excel(name = "物料品牌")
    private String brand;
    // 物料规格
    @Excel(name = "物料规格")
    private String spec;
    // 物料材质
    @Excel(name = "物料材质")
    private String material;
    // 物料存储上限
    @Excel(name = "存储数量上限")
    private String maxlimit;
    // 物料存储下限
    @Excel(name = "存储数量下限")
    private String minlimit;
    // 备注
    @Excel(name = "备注")
    private String remark;
    // 业务伙伴
    @Excel(name = "业务伙伴")
    private String partner;
    // 启用状态
    private String materialState;
    // 是否单品管理
    private String singleManage;
    // 非单品管理子件
    private String notSingle;
    // 批次号/序列号管理
    private String BSManage;
    // 固定库位
    private String fixPosition;
    // 对应查询条件
    private String bnC;
    private String snC;
    private String positionC;
    private String categoryC;
    private String nameC;
    private String idC;
    private String dateC;
    private String priceC;
    private String brandC;
    private String specC;
    private String materialC;
    private String maxlimitC;
    private String minlimitC;
    private String remarkC;
    private String partnerC;
    private String materialStateC;
    private String singleManageC;
    private String notSingleC;
    private String BSManageC;
    private String fixPositionC;
    private String sDate;
    private String eDate;

    public Material() {
    }

    public Material(int onlyId, String bn, String sn, String position, String category, String name, String id, String date, String price, String brand, String spec, String material, String maxlimit, String minlimit, String remark, String partner, String materialState, String singleManage, String notSingle, String BSManage, String fixPosition, String bnC, String snC, String positionC, String categoryC, String nameC, String idC, String dateC, String priceC, String brandC, String specC, String materialC, String maxlimitC, String minlimitC, String remarkC, String partnerC, String materialStateC, String singleManageC, String notSingleC, String BSManageC, String fixPositionC, String sDate, String eDate) {
        this.onlyId = onlyId;
        this.bn = bn;
        this.sn = sn;
        this.position = position;
        this.category = category;
        this.name = name;
        this.id = id;
        this.date = date;
        this.price = price;
        this.brand = brand;
        this.spec = spec;
        this.material = material;
        this.maxlimit = maxlimit;
        this.minlimit = minlimit;
        this.remark = remark;
        this.partner = partner;
        this.materialState = materialState;
        this.singleManage = singleManage;
        this.notSingle = notSingle;
        this.BSManage = BSManage;
        this.fixPosition = fixPosition;
        this.bnC = bnC;
        this.snC = snC;
        this.positionC = positionC;
        this.categoryC = categoryC;
        this.nameC = nameC;
        this.idC = idC;
        this.dateC = dateC;
        this.priceC = priceC;
        this.brandC = brandC;
        this.specC = specC;
        this.materialC = materialC;
        this.maxlimitC = maxlimitC;
        this.minlimitC = minlimitC;
        this.remarkC = remarkC;
        this.partnerC = partnerC;
        this.materialStateC = materialStateC;
        this.singleManageC = singleManageC;
        this.notSingleC = notSingleC;
        this.BSManageC = BSManageC;
        this.fixPositionC = fixPositionC;
        this.sDate = sDate;
        this.eDate = eDate;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaterialState() {
        return materialState;
    }

    public void setMaterialState(String materialState) {
        this.materialState = materialState;
    }

    public String getSingleManage() {
        return singleManage;
    }

    public void setSingleManage(String singleManage) {
        this.singleManage = singleManage;
    }

    public String getNotSingle() {
        return notSingle;
    }

    public void setNotSingle(String notSingle) {
        this.notSingle = notSingle;
    }

    public String getBSManage() {
        return BSManage;
    }

    public void setBSManage(String BSManage) {
        this.BSManage = BSManage;
    }

    public String getFixPosition() {
        return fixPosition;
    }

    public void setFixPosition(String fixPosition) {
        this.fixPosition = fixPosition;
    }

    public String getBnC() {
        return bnC;
    }

    public void setBnC(String bnC) {
        this.bnC = bnC;
    }

    public String getSnC() {
        return snC;
    }

    public void setSnC(String snC) {
        this.snC = snC;
    }

    public String getPositionC() {
        return positionC;
    }

    public void setPositionC(String positionC) {
        this.positionC = positionC;
    }

    public String getCategoryC() {
        return categoryC;
    }

    public void setCategoryC(String categoryC) {
        this.categoryC = categoryC;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getIdC() {
        return idC;
    }

    public void setIdC(String idC) {
        this.idC = idC;
    }

    public String getDateC() {
        return dateC;
    }

    public void setDateC(String dateC) {
        this.dateC = dateC;
    }

    public String getPriceC() {
        return priceC;
    }

    public void setPriceC(String priceC) {
        this.priceC = priceC;
    }

    public String getBrandC() {
        return brandC;
    }

    public void setBrandC(String brandC) {
        this.brandC = brandC;
    }

    public String getSpecC() {
        return specC;
    }

    public void setSpecC(String specC) {
        this.specC = specC;
    }

    public String getMaterialC() {
        return materialC;
    }

    public void setMaterialC(String materialC) {
        this.materialC = materialC;
    }

    public String getMaxlimitC() {
        return maxlimitC;
    }

    public void setMaxlimitC(String maxlimitC) {
        this.maxlimitC = maxlimitC;
    }

    public String getMinlimitC() {
        return minlimitC;
    }

    public void setMinlimitC(String minlimitC) {
        this.minlimitC = minlimitC;
    }

    public String getRemarkC() {
        return remarkC;
    }

    public void setRemarkC(String remarkC) {
        this.remarkC = remarkC;
    }

    public String getPartnerC() {
        return partnerC;
    }

    public void setPartnerC(String partnerC) {
        this.partnerC = partnerC;
    }

    public String getMaterialStateC() {
        return materialStateC;
    }

    public void setMaterialStateC(String materialStateC) {
        this.materialStateC = materialStateC;
    }

    public String getSingleManageC() {
        return singleManageC;
    }

    public void setSingleManageC(String singleManageC) {
        this.singleManageC = singleManageC;
    }

    public String getNotSingleC() {
        return notSingleC;
    }

    public void setNotSingleC(String notSingleC) {
        this.notSingleC = notSingleC;
    }

    public String getBSManageC() {
        return BSManageC;
    }

    public void setBSManageC(String BSManageC) {
        this.BSManageC = BSManageC;
    }

    public String getFixPositionC() {
        return fixPositionC;
    }

    public void setFixPositionC(String fixPositionC) {
        this.fixPositionC = fixPositionC;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public int getOnlyId() {
        return onlyId;
    }

    public void setOnlyId(int onlyId) {
        this.onlyId = onlyId;
    }

    @Override
    public String toString() {
        return "Material{" +
                "onlyId=" + onlyId +
                ", bn='" + bn + '\'' +
                ", sn='" + sn + '\'' +
                ", position='" + position + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                ", spec='" + spec + '\'' +
                ", material='" + material + '\'' +
                ", maxlimit='" + maxlimit + '\'' +
                ", minlimit='" + minlimit + '\'' +
                ", remark='" + remark + '\'' +
                ", partner='" + partner + '\'' +
                ", materialState='" + materialState + '\'' +
                ", singleManage='" + singleManage + '\'' +
                ", notSingle='" + notSingle + '\'' +
                ", BSManage='" + BSManage + '\'' +
                ", fixPosition='" + fixPosition + '\'' +
                ", bnC='" + bnC + '\'' +
                ", snC='" + snC + '\'' +
                ", positionC='" + positionC + '\'' +
                ", categoryC='" + categoryC + '\'' +
                ", nameC='" + nameC + '\'' +
                ", idC='" + idC + '\'' +
                ", dateC='" + dateC + '\'' +
                ", priceC='" + priceC + '\'' +
                ", brandC='" + brandC + '\'' +
                ", specC='" + specC + '\'' +
                ", materialC='" + materialC + '\'' +
                ", maxlimitC='" + maxlimitC + '\'' +
                ", minlimitC='" + minlimitC + '\'' +
                ", remarkC='" + remarkC + '\'' +
                ", partnerC='" + partnerC + '\'' +
                ", materialStateC='" + materialStateC + '\'' +
                ", singleManageC='" + singleManageC + '\'' +
                ", notSingleC='" + notSingleC + '\'' +
                ", BSManageC='" + BSManageC + '\'' +
                ", fixPositionC='" + fixPositionC + '\'' +
                ", sDate='" + sDate + '\'' +
                ", eDate='" + eDate + '\'' +
                '}';
    }
}
