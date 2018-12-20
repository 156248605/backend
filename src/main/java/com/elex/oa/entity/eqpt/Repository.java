package com.elex.oa.entity.eqpt;

public class Repository {

    // 仓库类型
    private String reptCategory;
    // 移出类型
    private String outReptC;
    // 移入类型
    private String inReptC;
    // 移库类型
    private String shiftReptC;
    // 盘点
    private String invId;
    // 盘点时间
    private String invTime;
    // 库位
    private String position;
    // 移出库位
    private String outPosition;
    // 移入库位
    private String inPosition;
    // 仓库编号
    private String reptId;
    // 移出库编号
    private String outRept;
    // 移入库编号
    private String inRept;
    // 移出库库位
    private String outPost;
    // 移入库库位
    private String inPost;
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
    // 入库通知
    private String inInfo;
    // 出库通知
    private String outInfo;
    // 移库通知
    private String shiftInfo;
    // 库存数量
    private String num;
    // 盘点数量
    private String numInv;
    // 物品单价
    private String price;
    // 规格型号
    private String spec;
    // 单位
    private String unit;
    // 物料种类
    private String category;
    // 检验单号
    private String check;
    // 盘点盈亏(每个物料)
    private String palPer;
    // 盘盈盘亏
    private String pal;
    // 盘盈盘亏(大写)
    private String palCal;
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
    // 物料名称
    private String materialName;
    // 启用状态
    private String reptState;
    // 库位管理
    private String postManage;
    // 地址
    private String reptAddr;
    // 仓库名称
    private String reptName;
    // 仓库管理员
    private String reptAdmin;
    // 库位编号
    private String postId;
    // 库位名称
    private String postName;
    // 库位类别
    private String postCate;
    // 固定库位物品
    private String fixPostMat;
    // 库位容量
    private String postCap;
    // 备注
    private String remark;
    // 项目相关
    private String projId;
    private String projName;
    // 关联流程INSTID
    private String instid;
    // 四个节点
    private String firstOne;
    private String secondOne;
    private String thirdOne;
    private String fourthOne;
    // 草稿用户
    private String author;
    // 唯一
    private int onlyIdR;
    private int onlyIdP;
    private int onlyIdIn;
    private int onlyIdOut;
    private int onlyIdInv;
    // 对应判断条件
    private String bnC;
    private String snC;
    private String matIdC;
    private String matNameC;
    private String reptIdC;
    private String reptCateC;
    private String positionC;
    private String inNumC;
    private String inIdC;
    private String inTimeC;
    private String outNumC;
    private String outIdC;
    private String outTimeC;
    private String numC;
    private String numInvC;
    private String invIdC;
    private String invTimeC;
    private String palC;
    private String priceC;
    private String reptNameC;
    private String reptAdminC;
    private String reptStateC;
    private String postManageC;
    private String postIdC;
    private String postNameC;
    private String postCateC;
    private String fixPostMatC;
    private String postCapC;
    private String reptAddrC;
    private String shiftIdC;
    private String shiftTimeC;
    private String shiftNumC;
    private String inPostC;
    private String outPostC;
    private String projIdC;
    private String projNameC;

    public Repository() {
    }

    public Repository(String reptCategory, String outReptC, String inReptC, String shiftReptC, String invId, String invTime, String position, String outPosition, String inPosition, String reptId, String outRept, String inRept, String outPost, String inPost, String in, String out, String shift, String inId, String outId, String shiftId, String inTime, String outTime, String shiftTime, String inNum, String outNum, String shiftNum, String inInfo, String outInfo, String shiftInfo, String num, String numInv, String price, String spec, String unit, String category, String check, String palPer, String pal, String palCal, String purchaseIn, String productIn, String depositIn, String purchaseOut, String productOut, String depositOut, String bn, String sn, String materialId, String materialName, String reptState, String postManage, String reptAddr, String reptName, String reptAdmin, String postId, String postName, String postCate, String fixPostMat, String postCap, String remark, String projId, String projName, String instid, String firstOne, String secondOne, String thirdOne, String fourthOne, String author, int onlyIdR, int onlyIdP, int onlyIdIn, int onlyIdOut, int onlyIdInv, String bnC, String snC, String matIdC, String matNameC, String reptIdC, String reptCateC, String positionC, String inNumC, String inIdC, String inTimeC, String outNumC, String outIdC, String outTimeC, String numC, String numInvC, String invIdC, String invTimeC, String palC, String priceC, String reptNameC, String reptAdminC, String reptStateC, String postManageC, String postIdC, String postNameC, String postCateC, String fixPostMatC, String postCapC, String reptAddrC, String shiftIdC, String shiftTimeC, String shiftNumC, String inPostC, String outPostC, String projIdC, String projNameC) {
        this.reptCategory = reptCategory;
        this.outReptC = outReptC;
        this.inReptC = inReptC;
        this.shiftReptC = shiftReptC;
        this.invId = invId;
        this.invTime = invTime;
        this.position = position;
        this.outPosition = outPosition;
        this.inPosition = inPosition;
        this.reptId = reptId;
        this.outRept = outRept;
        this.inRept = inRept;
        this.outPost = outPost;
        this.inPost = inPost;
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
        this.inInfo = inInfo;
        this.outInfo = outInfo;
        this.shiftInfo = shiftInfo;
        this.num = num;
        this.numInv = numInv;
        this.price = price;
        this.spec = spec;
        this.unit = unit;
        this.category = category;
        this.check = check;
        this.palPer = palPer;
        this.pal = pal;
        this.palCal = palCal;
        this.purchaseIn = purchaseIn;
        this.productIn = productIn;
        this.depositIn = depositIn;
        this.purchaseOut = purchaseOut;
        this.productOut = productOut;
        this.depositOut = depositOut;
        this.bn = bn;
        this.sn = sn;
        this.materialId = materialId;
        this.materialName = materialName;
        this.reptState = reptState;
        this.postManage = postManage;
        this.reptAddr = reptAddr;
        this.reptName = reptName;
        this.reptAdmin = reptAdmin;
        this.postId = postId;
        this.postName = postName;
        this.postCate = postCate;
        this.fixPostMat = fixPostMat;
        this.postCap = postCap;
        this.remark = remark;
        this.projId = projId;
        this.projName = projName;
        this.instid = instid;
        this.firstOne = firstOne;
        this.secondOne = secondOne;
        this.thirdOne = thirdOne;
        this.fourthOne = fourthOne;
        this.author = author;
        this.onlyIdR = onlyIdR;
        this.onlyIdP = onlyIdP;
        this.onlyIdIn = onlyIdIn;
        this.onlyIdOut = onlyIdOut;
        this.onlyIdInv = onlyIdInv;
        this.bnC = bnC;
        this.snC = snC;
        this.matIdC = matIdC;
        this.matNameC = matNameC;
        this.reptIdC = reptIdC;
        this.reptCateC = reptCateC;
        this.positionC = positionC;
        this.inNumC = inNumC;
        this.inIdC = inIdC;
        this.inTimeC = inTimeC;
        this.outNumC = outNumC;
        this.outIdC = outIdC;
        this.outTimeC = outTimeC;
        this.numC = numC;
        this.numInvC = numInvC;
        this.invIdC = invIdC;
        this.invTimeC = invTimeC;
        this.palC = palC;
        this.priceC = priceC;
        this.reptNameC = reptNameC;
        this.reptAdminC = reptAdminC;
        this.reptStateC = reptStateC;
        this.postManageC = postManageC;
        this.postIdC = postIdC;
        this.postNameC = postNameC;
        this.postCateC = postCateC;
        this.fixPostMatC = fixPostMatC;
        this.postCapC = postCapC;
        this.reptAddrC = reptAddrC;
        this.shiftIdC = shiftIdC;
        this.shiftTimeC = shiftTimeC;
        this.shiftNumC = shiftNumC;
        this.inPostC = inPostC;
        this.outPostC = outPostC;
        this.projIdC = projIdC;
        this.projNameC = projNameC;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInstid() {
        return instid;
    }

    public void setInstid(String instid) {
        this.instid = instid;
    }

    public String getFirstOne() {
        return firstOne;
    }

    public void setFirstOne(String firstOne) {
        this.firstOne = firstOne;
    }

    public String getSecondOne() {
        return secondOne;
    }

    public void setSecondOne(String secondOne) {
        this.secondOne = secondOne;
    }

    public String getThirdOne() {
        return thirdOne;
    }

    public void setThirdOne(String thirdOne) {
        this.thirdOne = thirdOne;
    }

    public String getFourthOne() {
        return fourthOne;
    }

    public void setFourthOne(String fourthOne) {
        this.fourthOne = fourthOne;
    }

    public String getNumInvC() {
        return numInvC;
    }

    public void setNumInvC(String numInvC) {
        this.numInvC = numInvC;
    }

    public String getInvTimeC() {
        return invTimeC;
    }

    public void setInvTimeC(String invTimeC) {
        this.invTimeC = invTimeC;
    }

    public String getPalC() {
        return palC;
    }

    public void setPalC(String palC) {
        this.palC = palC;
    }

    public String getReptCategory() {
        return reptCategory;
    }

    public void setReptCategory(String reptCategory) {
        this.reptCategory = reptCategory;
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

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    public String getInvTime() {
        return invTime;
    }

    public void setInvTime(String invTime) {
        this.invTime = invTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getReptId() {
        return reptId;
    }

    public void setReptId(String reptId) {
        this.reptId = reptId;
    }

    public String getOutRept() {
        return outRept;
    }

    public void setOutRept(String outRept) {
        this.outRept = outRept;
    }

    public String getInRept() {
        return inRept;
    }

    public void setInRept(String inRept) {
        this.inRept = inRept;
    }

    public String getOutPost() {
        return outPost;
    }

    public void setOutPost(String outPost) {
        this.outPost = outPost;
    }

    public String getInPost() {
        return inPost;
    }

    public void setInPost(String inPost) {
        this.inPost = inPost;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
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

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
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

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
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

    public String getShiftNum() {
        return shiftNum;
    }

    public void setShiftNum(String shiftNum) {
        this.shiftNum = shiftNum;
    }

    public String getInInfo() {
        return inInfo;
    }

    public void setInInfo(String inInfo) {
        this.inInfo = inInfo;
    }

    public String getOutInfo() {
        return outInfo;
    }

    public void setOutInfo(String outInfo) {
        this.outInfo = outInfo;
    }

    public String getShiftInfo() {
        return shiftInfo;
    }

    public void setShiftInfo(String shiftInfo) {
        this.shiftInfo = shiftInfo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumInv() {
        return numInv;
    }

    public void setNumInv(String numInv) {
        this.numInv = numInv;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    public String getPalCal() {
        return palCal;
    }

    public void setPalCal(String palCal) {
        this.palCal = palCal;
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

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getReptState() {
        return reptState;
    }

    public void setReptState(String reptState) {
        this.reptState = reptState;
    }

    public String getPostManage() {
        return postManage;
    }

    public void setPostManage(String postManage) {
        this.postManage = postManage;
    }

    public String getReptAddr() {
        return reptAddr;
    }

    public void setReptAddr(String reptAddr) {
        this.reptAddr = reptAddr;
    }

    public String getReptName() {
        return reptName;
    }

    public void setReptName(String reptName) {
        this.reptName = reptName;
    }

    public String getReptAdmin() {
        return reptAdmin;
    }

    public void setReptAdmin(String reptAdmin) {
        this.reptAdmin = reptAdmin;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostCate() {
        return postCate;
    }

    public void setPostCate(String postCate) {
        this.postCate = postCate;
    }

    public String getFixPostMat() {
        return fixPostMat;
    }

    public void setFixPostMat(String fixPostMat) {
        this.fixPostMat = fixPostMat;
    }

    public String getPostCap() {
        return postCap;
    }

    public void setPostCap(String postCap) {
        this.postCap = postCap;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOnlyIdR() {
        return onlyIdR;
    }

    public void setOnlyIdR(int onlyIdR) {
        this.onlyIdR = onlyIdR;
    }

    public int getOnlyIdIn() {
        return onlyIdIn;
    }

    public void setOnlyIdIn(int onlyIdIn) {
        this.onlyIdIn = onlyIdIn;
    }

    public int getOnlyIdOut() {
        return onlyIdOut;
    }

    public void setOnlyIdOut(int onlyIdOut) {
        this.onlyIdOut = onlyIdOut;
    }

    public int getOnlyIdInv() {
        return onlyIdInv;
    }

    public void setOnlyIdInv(int onlyIdInv) {
        this.onlyIdInv = onlyIdInv;
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

    public String getMatIdC() {
        return matIdC;
    }

    public void setMatIdC(String matIdC) {
        this.matIdC = matIdC;
    }

    public String getMatNameC() {
        return matNameC;
    }

    public void setMatNameC(String matNameC) {
        this.matNameC = matNameC;
    }

    public String getReptIdC() {
        return reptIdC;
    }

    public void setReptIdC(String reptIdC) {
        this.reptIdC = reptIdC;
    }

    public String getReptCateC() {
        return reptCateC;
    }

    public void setReptCateC(String reptCateC) {
        this.reptCateC = reptCateC;
    }

    public String getPositionC() {
        return positionC;
    }

    public void setPositionC(String positionC) {
        this.positionC = positionC;
    }

    public String getInNumC() {
        return inNumC;
    }

    public void setInNumC(String inNumC) {
        this.inNumC = inNumC;
    }

    public String getInIdC() {
        return inIdC;
    }

    public void setInIdC(String inIdC) {
        this.inIdC = inIdC;
    }

    public String getInTimeC() {
        return inTimeC;
    }

    public void setInTimeC(String inTimeC) {
        this.inTimeC = inTimeC;
    }

    public String getOutNumC() {
        return outNumC;
    }

    public void setOutNumC(String outNumC) {
        this.outNumC = outNumC;
    }

    public String getOutIdC() {
        return outIdC;
    }

    public void setOutIdC(String outIdC) {
        this.outIdC = outIdC;
    }

    public String getOutTimeC() {
        return outTimeC;
    }

    public void setOutTimeC(String outTimeC) {
        this.outTimeC = outTimeC;
    }

    public String getNumC() {
        return numC;
    }

    public void setNumC(String numC) {
        this.numC = numC;
    }

    public String getInvIdC() {
        return invIdC;
    }

    public void setInvIdC(String invIdC) {
        this.invIdC = invIdC;
    }

    public String getPriceC() {
        return priceC;
    }

    public void setPriceC(String priceC) {
        this.priceC = priceC;
    }

    public String getShiftReptC() {
        return shiftReptC;
    }

    public void setShiftReptC(String shiftReptC) {
        this.shiftReptC = shiftReptC;
    }

    public String getReptNameC() {
        return reptNameC;
    }

    public void setReptNameC(String reptNameC) {
        this.reptNameC = reptNameC;
    }

    public String getReptAdminC() {
        return reptAdminC;
    }

    public void setReptAdminC(String reptAdminC) {
        this.reptAdminC = reptAdminC;
    }

    public String getReptStateC() {
        return reptStateC;
    }

    public void setReptStateC(String reptStateC) {
        this.reptStateC = reptStateC;
    }

    public String getPostManageC() {
        return postManageC;
    }

    public void setPostManageC(String postManageC) {
        this.postManageC = postManageC;
    }

    public String getPostIdC() {
        return postIdC;
    }

    public void setPostIdC(String postIdC) {
        this.postIdC = postIdC;
    }

    public String getPostNameC() {
        return postNameC;
    }

    public void setPostNameC(String postNameC) {
        this.postNameC = postNameC;
    }

    public String getPostCateC() {
        return postCateC;
    }

    public void setPostCateC(String postCateC) {
        this.postCateC = postCateC;
    }

    public String getFixPostMatC() {
        return fixPostMatC;
    }

    public void setFixPostMatC(String fixPostMatC) {
        this.fixPostMatC = fixPostMatC;
    }

    public String getPostCapC() {
        return postCapC;
    }

    public void setPostCapC(String postCapC) {
        this.postCapC = postCapC;
    }

    public String getReptAddrC() {
        return reptAddrC;
    }

    public void setReptAddrC(String reptAddrC) {
        this.reptAddrC = reptAddrC;
    }

    public int getOnlyIdP() {
        return onlyIdP;
    }

    public void setOnlyIdP(int onlyIdP) {
        this.onlyIdP = onlyIdP;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjIdC() {
        return projIdC;
    }

    public void setProjIdC(String projIdC) {
        this.projIdC = projIdC;
    }

    public String getProjNameC() {
        return projNameC;
    }

    public void setProjNameC(String projNameC) {
        this.projNameC = projNameC;
    }

    public String getShiftIdC() {
        return shiftIdC;
    }

    public void setShiftIdC(String shiftIdC) {
        this.shiftIdC = shiftIdC;
    }

    public String getShiftTimeC() {
        return shiftTimeC;
    }

    public void setShiftTimeC(String shiftTimeC) {
        this.shiftTimeC = shiftTimeC;
    }

    public String getShiftNumC() {
        return shiftNumC;
    }

    public void setShiftNumC(String shiftNumC) {
        this.shiftNumC = shiftNumC;
    }

    public String getInPostC() {
        return inPostC;
    }

    public void setInPostC(String inPostC) {
        this.inPostC = inPostC;
    }

    public String getOutPostC() {
        return outPostC;
    }

    public void setOutPostC(String outPostC) {
        this.outPostC = outPostC;
    }

    public String getPalPer() {
        return palPer;
    }

    public void setPalPer(String palPer) {
        this.palPer = palPer;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "reptCategory='" + reptCategory + '\'' +
                ", outReptC='" + outReptC + '\'' +
                ", inReptC='" + inReptC + '\'' +
                ", shiftReptC='" + shiftReptC + '\'' +
                ", invId='" + invId + '\'' +
                ", invTime='" + invTime + '\'' +
                ", position='" + position + '\'' +
                ", outPosition='" + outPosition + '\'' +
                ", inPosition='" + inPosition + '\'' +
                ", reptId='" + reptId + '\'' +
                ", outRept='" + outRept + '\'' +
                ", inRept='" + inRept + '\'' +
                ", outPost='" + outPost + '\'' +
                ", inPost='" + inPost + '\'' +
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
                ", inInfo='" + inInfo + '\'' +
                ", outInfo='" + outInfo + '\'' +
                ", shiftInfo='" + shiftInfo + '\'' +
                ", num='" + num + '\'' +
                ", numInv='" + numInv + '\'' +
                ", price='" + price + '\'' +
                ", spec='" + spec + '\'' +
                ", unit='" + unit + '\'' +
                ", category='" + category + '\'' +
                ", check='" + check + '\'' +
                ", palPer='" + palPer + '\'' +
                ", pal='" + pal + '\'' +
                ", palCal='" + palCal + '\'' +
                ", purchaseIn='" + purchaseIn + '\'' +
                ", productIn='" + productIn + '\'' +
                ", depositIn='" + depositIn + '\'' +
                ", purchaseOut='" + purchaseOut + '\'' +
                ", productOut='" + productOut + '\'' +
                ", depositOut='" + depositOut + '\'' +
                ", bn='" + bn + '\'' +
                ", sn='" + sn + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", reptState='" + reptState + '\'' +
                ", postManage='" + postManage + '\'' +
                ", reptAddr='" + reptAddr + '\'' +
                ", reptName='" + reptName + '\'' +
                ", reptAdmin='" + reptAdmin + '\'' +
                ", postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", postCate='" + postCate + '\'' +
                ", fixPostMat='" + fixPostMat + '\'' +
                ", postCap='" + postCap + '\'' +
                ", remark='" + remark + '\'' +
                ", projId='" + projId + '\'' +
                ", projName='" + projName + '\'' +
                ", instid='" + instid + '\'' +
                ", firstOne='" + firstOne + '\'' +
                ", secondOne='" + secondOne + '\'' +
                ", thirdOne='" + thirdOne + '\'' +
                ", fourthOne='" + fourthOne + '\'' +
                ", author='" + author + '\'' +
                ", onlyIdR=" + onlyIdR +
                ", onlyIdP=" + onlyIdP +
                ", onlyIdIn=" + onlyIdIn +
                ", onlyIdOut=" + onlyIdOut +
                ", onlyIdInv=" + onlyIdInv +
                ", bnC='" + bnC + '\'' +
                ", snC='" + snC + '\'' +
                ", matIdC='" + matIdC + '\'' +
                ", matNameC='" + matNameC + '\'' +
                ", reptIdC='" + reptIdC + '\'' +
                ", reptCateC='" + reptCateC + '\'' +
                ", positionC='" + positionC + '\'' +
                ", inNumC='" + inNumC + '\'' +
                ", inIdC='" + inIdC + '\'' +
                ", inTimeC='" + inTimeC + '\'' +
                ", outNumC='" + outNumC + '\'' +
                ", outIdC='" + outIdC + '\'' +
                ", outTimeC='" + outTimeC + '\'' +
                ", numC='" + numC + '\'' +
                ", numInvC='" + numInvC + '\'' +
                ", invIdC='" + invIdC + '\'' +
                ", invTimeC='" + invTimeC + '\'' +
                ", palC='" + palC + '\'' +
                ", priceC='" + priceC + '\'' +
                ", reptNameC='" + reptNameC + '\'' +
                ", reptAdminC='" + reptAdminC + '\'' +
                ", reptStateC='" + reptStateC + '\'' +
                ", postManageC='" + postManageC + '\'' +
                ", postIdC='" + postIdC + '\'' +
                ", postNameC='" + postNameC + '\'' +
                ", postCateC='" + postCateC + '\'' +
                ", fixPostMatC='" + fixPostMatC + '\'' +
                ", postCapC='" + postCapC + '\'' +
                ", reptAddrC='" + reptAddrC + '\'' +
                ", shiftIdC='" + shiftIdC + '\'' +
                ", shiftTimeC='" + shiftTimeC + '\'' +
                ", shiftNumC='" + shiftNumC + '\'' +
                ", inPostC='" + inPostC + '\'' +
                ", outPostC='" + outPostC + '\'' +
                ", projIdC='" + projIdC + '\'' +
                ", projNameC='" + projNameC + '\'' +
                '}';
    }
}
