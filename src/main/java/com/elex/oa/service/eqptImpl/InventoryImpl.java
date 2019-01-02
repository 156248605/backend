package com.elex.oa.service.eqptImpl;

import com.alibaba.fastjson.JSON;
import com.elex.oa.dao.eqptDao.InventoryMapper;
import com.elex.oa.entity.Page;

import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.InventoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class InventoryImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    // 显示所有信息
    @Override
    public PageInfo<Repository> showAllInfo(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> listInv = inventoryMapper.find();
        return new PageInfo<>(listInv);
    }

    // 查询部分信息
    @Override
    public PageInfo<Repository> searchInfo(Page page, HttpServletRequest request) {
        String invId = request.getParameter("invId");
        String invIdC = request.getParameter("invIdC");
        String invTime = request.getParameter("invTime");
        String invTimeC = request.getParameter("invTimeC");
        String materialId = request.getParameter("materialId");
        String materialIdC = request.getParameter("materialIdC");
        String materialName = request.getParameter("materialName");
        String materialNameC = request.getParameter("materialNameC");
        String position = request.getParameter("position");
        String positionC = request.getParameter("positionC");
        String reptId = request.getParameter("rept");
        String reptIdC = request.getParameter("reptC");
        String price = request.getParameter("price");
        String priceC = request.getParameter("priceC");
        String numInv = request.getParameter("numInv");
        String numInvC = request.getParameter("numInvC");
        String pal = request.getParameter("pal");
        String palC = request.getParameter("palC");
        String remark = request.getParameter("remark");
        String num = request.getParameter("num");
        String numC = request.getParameter("numC");
        if ( invTime == null && invId == null && position == null && materialId == null && num == null && materialName == null && price == null && numInv == null && pal == null && remark == null){
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            List<Repository> listR = inventoryMapper.find();
            return new PageInfo<>(listR);
        } else {
            PageHelper.startPage(page.getCurrentPage(),page.getRows());
            Repository repository = new Repository();
            repository.setInvId(invId);
            repository.setInvIdC(invIdC);
            repository.setInvTime(invTime);
            repository.setInvTimeC(invTimeC);
            repository.setNum(num);
            repository.setNumC(numC);
            repository.setNumInv(numInv);
            repository.setNumInvC(numInvC);
            repository.setReptId(reptId);
            repository.setReptIdC(reptIdC);
            repository.setMaterialName(materialName);
            repository.setMatNameC(materialNameC);
            repository.setPosition(position);
            repository.setPositionC(positionC);
            repository.setMaterialId(materialId);
            repository.setMatIdC(materialIdC);
            repository.setPrice(price);
            repository.setPriceC(priceC);
            repository.setPal(pal);
            repository.setPalC(palC);
            List<Repository> listR = inventoryMapper.search(repository);
            return new PageInfo<>(listR);
        }
    }


    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };

    private static final String CN_FULL = "整";

    private static final String CN_NEGATIVE = "负";

    private static final int MONEY_PRECISION = 2;

    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
                if (numUnit > 0) {
                    if ((numIndex == 9) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                    }
                    if ((numIndex == 13) && (zeroSize >= 3)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                    }
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    getZero = false;
                    zeroSize = 0;
                } else {
                    ++zeroSize;
                    if (!(getZero)) {
                        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                    }
                    if (numIndex == 2) {
                        if (number > 0) {
                            sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                        }
                    } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                    getZero = true;
                }
                // 让number每次都去掉最后一个数
                number = number / 10;
                ++numIndex;
            }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }





    // 流程结束同步
    @Override
    public List<HashMap<String, Object>> insertInv(String instId){
        String INST_ID_ = String.valueOf(Long.valueOf(instId) + 1);
        List<HashMap<String, Object>> list1 = inventoryMapper.invInfo(INST_ID_);
        if (list1.size() > 0) {
            String wdbh = list1.get(0).get("ID_").toString();// 两张表同样部分
            String PAL = list1.get(0).get("F_JEYKXX").toString();// 盈亏金额小写
            BigDecimal bigDecimal = new BigDecimal(PAL);
            String PALCAL = number2CNMontrayUnit(bigDecimal);// 盈亏金额大写
            String invId = list1.get(0).get("F_PDBH").toString();
            List<HashMap<String, Object>> list = inventoryMapper.invDetail(wdbh);
            for (int i = 0; i < list.size(); i++){
                Repository repository = new Repository();
                repository.setInvId(invId);// 1.确定盘点单号
                String invTime = list.get(i).get("UPDATE_TIME_").toString();// 2.获取盘点时间(需要修改)
                String materialId = list.get(i).get("F_WPBM").toString();// 3.获取物料Id(需要修改)
                String materialName = list.get(i).get("F_WPMC").toString();// 4.获取物料名称(需要修改)
                String spec = list.get(i).get("F_GGXH").toString();// 5.获取规格型号(需要修改)
                String category = list.get(i).get("F_WPLB").toString();// 6.获取物料类别(需要修改)
                String price = list.get(i).get("F_DJ").toString();// 7.获取物料单价(需要修改)
                String reptId = list.get(i).get("F_CK").toString();// 8.获取盘点仓库(需要修改)
                String postId = list.get(i).get("F_KW").toString();// 9.获取盘点库位(需要修改)
                String num = list.get(i).get("F_KCSL").toString();// 10.获取账上数量(需要修改)
                String numInv = list.get(i).get("F_PDSL").toString();// 11.获取盘点(实际)数量(需要修改)
                String remark = list.get(i).get("F_BZ").toString();// 12.获取备注(需要修改)
                String palPer = list.get(i).get("F_PDYK").toString();// 13.获取物品盈亏(需要修改)
                String instid = list.get(i).get("REF_ID_").toString();// 14.INSTID
                // 这两个值在主表
                repository.setInvTime(invTime);
                repository.setMaterialId(materialId);
                repository.setMaterialName(materialName);
                repository.setCategory(category);
                repository.setSpec(spec);
                repository.setPrice(price);
                repository.setReptId(reptId);
                repository.setPostId(postId);
                repository.setNum(num);
                repository.setNumInv(numInv);
                repository.setUnit(String.valueOf(parseInt(numInv) - parseInt(num)));
                repository.setRemark(remark);
                repository.setPalPer(palPer);
                repository.setPal(PAL);
                repository.setPalCal(PALCAL);
                repository.setInstid(instid);
                inventoryMapper.changeNum(repository);// 修改数据(detail)
                inventoryMapper.changeNumMat(repository);// 修改数据(material)
                inventoryMapper.insert(repository);
            }
        }
        return null;
    }


    // 插入数据
    @Override
    public void insertInfo(HttpServletRequest request) throws ParseException {
        String INVLIST = request.getParameter("invList");
        List<HashMap> listINV =JSON.parseArray(INVLIST, HashMap.class);
        for (int i = 0; i < listINV.size(); i++){
            Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            String price = listINV.get(i).get("theMatPrice").toString();
            if (price.contains(".")){
                if (Double.parseDouble(price)%1 == 0){
                    price = price.substring(0,price.indexOf("."));
                }
            }
            repository.setPrice(price);
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setUnit(String.valueOf(parseInt(listINV.get(i).get("theMatNumInv").toString()) - parseInt(listINV.get(i).get("theMatNum").toString())));
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            String palPer = listINV.get(i).get("theMatPal").toString();
            if (palPer.contains(".")){
                palPer = palPer.substring(0,palPer.indexOf("."));
            }
            repository.setPalPer(palPer);
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setRemark(listINV.get(i).get("theMatRemark").toString());
            inventoryMapper.insert(repository);
            inventoryMapper.changeNum(repository);// 修改数据(detail)
            inventoryMapper.changeNumMat(repository);// 修改数据(material)
        }
    }

    // 暂存数据
    @Override
    public void saveInfo(HttpServletRequest request) throws ParseException{
        String INVLIST = request.getParameter("invList");
        List<HashMap> listINV =JSON.parseArray(INVLIST, HashMap.class);
        for (int i = 0; i < listINV.size(); i++){
            Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            repository.setPrice(listINV.get(i).get("theMatPrice").toString());
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setRemark("");
            inventoryMapper.insertDraft(repository);
        }
    }

    // 删除数据
    @Override
    public void deleteInfo(HttpServletRequest request){
        String onlyIdInv = request.getParameter("onlyIdInv");
        String invId = request.getParameter("invId");
        Repository repository = new Repository();
        repository.setOnlyIdInv( parseInt(onlyIdInv) );
        repository.setInvId(invId);
        inventoryMapper.delete(repository);
    }

    // 修改草稿
    @Override
    public void changeInfo(HttpServletRequest request) throws ParseException{
        String INVLIST = request.getParameter("invList");
        List<HashMap> listINV =JSON.parseArray(INVLIST, HashMap.class);
        for (int i = 0; i < listINV.size(); i++){
            Repository repository = new Repository();
            repository.setInvId(request.getParameter("invId"));
            String date = request.getParameter("invTime");
            date = date.replace("Z", " UTC");// 注意是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");// 注意格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = sdf.format(d);
            repository.setInvTime(sDate);
            repository.setMaterialId(listINV.get(i).get("theMatId").toString());
            repository.setMaterialName(listINV.get(i).get("theMatName").toString());
            repository.setReptId(listINV.get(i).get("theMatRept").toString());
            repository.setPosition(listINV.get(i).get("theMatPost").toString());
            repository.setPrice(listINV.get(i).get("theMatPrice").toString());
            repository.setNum(listINV.get(i).get("theMatNum").toString());
            repository.setNumInv(listINV.get(i).get("theMatNumInv").toString());
            repository.setSpec(listINV.get(i).get("theMatSpec").toString());
            repository.setCategory(listINV.get(i).get("theMatCate").toString());
            repository.setPal(request.getParameter("pal"));
            repository.setPalCal(request.getParameter("palCal"));
            repository.setRemark(listINV.get(i).get("theMatRemark").toString());
            inventoryMapper.changeInvD(repository);
        }
    }

    // 确定盘点单号
    @Override
    public List showInvId(HttpServletRequest request){
        String dateToId = request.getParameter("date");
        Repository repository = new Repository();
        repository.setInvId(dateToId);
        List showInvId = inventoryMapper.showINVID(repository);
        List showInvIdD = inventoryMapper.showINVID(repository);
        String result1 = "0";
        String result2 = "0";
        if (showInvId.size() > 0){
            result1 = showInvId.get(showInvId.size()-1).toString();
        }
        if (showInvIdD.size() > 0) {
            result2 = showInvIdD.get(showInvIdD.size()-1).toString();
        }
        if (parseInt(result2) > parseInt(result1)) {
            return showInvIdD;
        } else {
            return showInvId;
        }
    }

    /*@Override
    public List<Material> chooseMat(HttpServletRequest request) {
        String id = request.getParameter("pdbh");
        Material material = new Material();
        material.setId(id);
        List<Material> listMIR = inventoryMapper.chooseMat(material);
        return listMIR;
    }*/

    @Override
    public List<Material> matinrept(HttpServletRequest request) {
        String reptId = request.getParameter("reptId");
        String id = request.getParameter("id");
        List<HashMap> ID =JSON.parseArray(id, HashMap.class);
        String matId = "";
        for (int i = 0; i < ID.size(); i++){
            matId += ID.get(i).get("id").toString() + ",";
        }
        Material material = new Material();
        material.setReptId(reptId);
        material.setId(matId.substring(0,matId.length() - 1));
        List<Material> listMIR = inventoryMapper.matinrept(material);
        return listMIR;
    }

    @Override
    public List<Repository> ReptList() {
        List<Repository> reptlist = inventoryMapper.reptlist();
        return reptlist;
    }

    @Override
    public String checkDraft(HttpServletRequest request){
        String id = request.getParameter("id");
        List<HashMap> ID =JSON.parseArray(id, HashMap.class);
        Repository repository = new Repository();
        repository.setInvId(ID.get(0).get("invId").toString());
        List<Repository> result = inventoryMapper.checkDraft(repository);
        if (result.size() > 0){
            return "1";
        }else {
            return "0";
        }
    }

    @Override
    public void deleteDraft(HttpServletRequest request){
        String id = request.getParameter("id");
        /*List<HashMap> ID =JSON.parseArray(id, HashMap.class);*/
        Repository repository = new Repository();
        repository.setInvId(id);
        inventoryMapper.deleteDraft(repository);
    }

    @Override
    public List<Repository> openDraft(HttpServletRequest request) {
        String id = request.getParameter("id");
        List<HashMap> ID =JSON.parseArray(id, HashMap.class);
        Repository repository = new Repository();
        repository.setInvId(ID.get(0).get("invId").toString());
        List<Repository> list = inventoryMapper.openDraft(repository);
        return list;
    }

    @Override
    public List getInvId() {
        List list = inventoryMapper.getInvId();
        return list;
    }
}
