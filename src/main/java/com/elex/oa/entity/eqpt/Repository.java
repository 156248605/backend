package com.elex.oa.entity.eqpt;

public class Repository {

    // 仓库类型
    private String reptCategory;
    // 移出库类型
    private String outReptC;
    // 移入库类型
    private String inReptC;
    // 普通库
    private String commonR;
    // 寄存库
    private String depositR;
    // 库存数量
    private String num;
    // 库位
    private String position;
    // 移出库位
    private String outPosition;
    // 移入库位
    private String inPosition;
    // 仓库编号
    private String reptId;
    // 移出库编号
    private String outReptId;
    // 移入库编号
    private String inReptId;
    // 入库
    private String in;
    // 出库
    private String out;
    // 移库
    private String shift;
    // 入库单号
    private String inId;
    // 出库单号
    private String outId;
    // 移库单号
    private String shiftId;
    // 入库时间
    private String inTime;
    // 出库时间
    private String outTime;
    // 移库时间
    private String shiftTime;
    // 入库数量
    private String inNum;
    // 出库数量
    private String outNum;
    // 移库数量
    private String shiftNum;
    // 采购入库
    private String purchaseIn;
    // 生产入库
    private String productIn;
    // 寄存入库
    private String depositIn;
    // 采购出库
    private String purchaseOut;
    // 生产出库
    private String productOut;
    // 寄存出库
    private String depositOut;
    // 批次号
    private String bn;
    // 序列号
    private String sn;
    // 物料编号
    private String materialId;

    public Repository() {
    }

    public Repository(String reptCategory, String outReptC, String inReptC, String commonR, String depositR, String num, String position, String outPosition, String inPosition, String reptId, String outReptId, String inReptId, String in, String out, String shift, String inId, String outId, String shiftId, String inTime, String outTime, String shiftTime, String inNum, String outNum, String shiftNum, String purchaseIn, String productIn, String depositIn, String purchaseOut, String productOut, String depositOut, String bn, String sn, String materialId) {
        this.reptCategory = reptCategory;
        this.outReptC = outReptC;
        this.inReptC = inReptC;
        this.commonR = commonR;
        this.depositR = depositR;
        this.num = num;
        this.position = position;
        this.outPosition = outPosition;
        this.inPosition = inPosition;
        this.reptId = reptId;
        this.outReptId = outReptId;
        this.inReptId = inReptId;
        this.in = in;
        this.out = out;
        this.shift = shift;
        this.inId = inId;
        this.outId = outId;
        this.shiftId = shiftId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.shiftTime = shiftTime;
        this.inNum = inNum;
        this.outNum = outNum;
        this.shiftNum = shiftNum;
        this.purchaseIn = purchaseIn;
        this.productIn = productIn;
        this.depositIn = depositIn;
        this.purchaseOut = purchaseOut;
        this.productOut = productOut;
        this.depositOut = depositOut;
        this.bn = bn;
        this.sn = sn;
        this.materialId = materialId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCommonR() {
        return commonR;
    }

    public void setCommonR(String commonR) {
        this.commonR = commonR;
    }

    public String getDepositR() {
        return depositR;
    }

    public void setDepositR(String depositR) {
        this.depositR = depositR;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPurchaseIn() {
        return purchaseIn;
    }

    public void setPurchaseIn(String purchaseIn) {
        this.purchaseIn = purchaseIn;
    }

    public String getProductIn() {
        return productIn;
    }

    public void setProductIn(String productIn) {
        this.productIn = productIn;
    }

    public String getDepositIn() {
        return depositIn;
    }

    public void setDepositIn(String depositIn) {
        this.depositIn = depositIn;
    }

    public String getPurchaseOut() {
        return purchaseOut;
    }

    public void setPurchaseOut(String purchaseOut) {
        this.purchaseOut = purchaseOut;
    }

    public String getProductOut() {
        return productOut;
    }

    public void setProductOut(String productOut) {
        this.productOut = productOut;
    }

    public String getDepositOut() {
        return depositOut;
    }

    public void setDepositOut(String depositOut) {
        this.depositOut = depositOut;
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

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getReptId() {
        return reptId;
    }

    public void setReptId(String reptId) {
        this.reptId = reptId;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getInId() {
        return inId;
    }

    public void setInId(String inId) {
        this.inId = inId;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getReptCategory() {
        return reptCategory;
    }

    public void setReptCategory(String reptCategory) {
        this.reptCategory = reptCategory;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public String getShiftNum() {
        return shiftNum;
    }

    public void setShiftNum(String shiftNum) {
        this.shiftNum = shiftNum;
    }

    public String getOutReptId() {
        return outReptId;
    }

    public void setOutReptId(String outReptId) {
        this.outReptId = outReptId;
    }

    public String getInReptId() {
        return inReptId;
    }

    public void setInReptId(String inReptId) {
        this.inReptId = inReptId;
    }

    public String getOutReptC() {
        return outReptC;
    }

    public void setOutReptC(String outReptC) {
        this.outReptC = outReptC;
    }

    public String getInReptC() {
        return inReptC;
    }

    public void setInReptC(String inReptC) {
        this.inReptC = inReptC;
    }

    public String getOutPosition() {
        return outPosition;
    }

    public void setOutPosition(String outPosition) {
        this.outPosition = outPosition;
    }

    public String getInPosition() {
        return inPosition;
    }

    public void setInPosition(String inPosition) {
        this.inPosition = inPosition;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "reptCategory='" + reptCategory + '\'' +
                ", outReptC='" + outReptC + '\'' +
                ", inReptC='" + inReptC + '\'' +
                ", commonR='" + commonR + '\'' +
                ", depositR='" + depositR + '\'' +
                ", num='" + num + '\'' +
                ", position='" + position + '\'' +
                ", outPosition='" + outPosition + '\'' +
                ", inPosition='" + inPosition + '\'' +
                ", reptId='" + reptId + '\'' +
                ", outReptId='" + outReptId + '\'' +
                ", inReptId='" + inReptId + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                ", shift='" + shift + '\'' +
                ", inId='" + inId + '\'' +
                ", outId='" + outId + '\'' +
                ", shiftId='" + shiftId + '\'' +
                ", inTime='" + inTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", shiftTime='" + shiftTime + '\'' +
                ", inNum='" + inNum + '\'' +
                ", outNum='" + outNum + '\'' +
                ", shiftNum='" + shiftNum + '\'' +
                ", purchaseIn='" + purchaseIn + '\'' +
                ", productIn='" + productIn + '\'' +
                ", depositIn='" + depositIn + '\'' +
                ", purchaseOut='" + purchaseOut + '\'' +
                ", productOut='" + productOut + '\'' +
                ", depositOut='" + depositOut + '\'' +
                ", bn='" + bn + '\'' +
                ", sn='" + sn + '\'' +
                ", materialId='" + materialId + '\'' +
                '}';
    }

}
