package com.elex.oa.entity.hr_entity.readexcel;

import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformationExchange;
import com.elex.oa.util.hr_util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadPersonalinformationExcel extends ReadBaseExcel<PersonalInformationExchange>{
    @Override
    public Object getObject(Sheet sheet, Row row) {
        PersonalInformationExchange personalInformationExchange = new PersonalInformationExchange();
        // 循环Excel的列
        for (int c = 0; c < this.totalCells; c++) {
            Cell cell = row.getCell(c);
            Cell cell2 = sheet.getRow(0).getCell(c);
            String columnname = String.valueOf(cell2.getStringCellValue());
            if (null != cell) {
                if ("工号".equals(columnname.replace(" ",""))) {
                    //tb_id_user表======================================================================================
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    if(str.indexOf("admin")==-1 && str.length()<6){//工号不足补0
                        switch (str.length()){
                            case 1:str = "00000"+str;break;
                            case 2:str = "0000"+str;break;
                            case 3:str = "000"+str;break;
                            case 4:str = "00"+str;break;
                            case 5:str = "0"+str;break;
                        }
                    }
                    personalInformationExchange.setEmployeenumber(str);
                }else if ("登录ID".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setUsername(str);
                }else if ("姓名".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setTruename(str);
                }else if ("是否激活".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setIsactive(str);
                }else if("是否在职".equals(columnname)){
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setIsatwork(str);
                }else if("最后工作时间".equals(columnname)){
                    //tb_hr_dimission表=================================================================================
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setLastworkingdate(str);
                }else if("离职类型".equals(columnname)){
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setDimissiontype(str);
                }else if("离职方向".equals(columnname)){
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setDimissiondirection(str);
                }else if("离职原因".equals(columnname)){
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setDimissionreason(str);
                }else if("离职办理人工号".equals(columnname)){
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setDimission_transactoremployeenumber(str);
                }else if("离职办理日期".equals(columnname)){
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setDimission_transactiondate(str);
                }else if ("部门编号".equals(columnname.replace(" ",""))) {
                    //tb_id_department表================================================================================
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setDepcode(str);
                }else if ("部门名称".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setDepname(str);
                }else if ("岗位名称-岗位编号".equals(columnname.replace(" ",""))) {
                    //tb_id_post表======================================================================================
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setPostlist(str);
                }else if ("免冠照片".equals(columnname.replace(" ",""))) {
                    //tb_id_baseinformation表===========================================================================
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setUserphoto(str);
                }else if ("身份证正面".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setIdphoto1(str);
                }else if ("身份证背面".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setIdphoto2(str);
                }else if ("英文名".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setEnglishname(str);
                }else if ("身份证号码".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setIdcode(str);
                }else if ("性别".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSex(str);
                }else if ("出生日期".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setBirthday(str);
                }else if ("星座".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setConstellation(str);
                }else if ("生肖".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setChinesecs(str);
                }else if ("户籍".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setHousehold_register(str);
                }else if ("民族".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setRace(str);
                }else if ("婚姻".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setMarriage(str);
                }else if ("生育".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setChildren(str);
                }else if ("政治面貌".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setZzmm(str);
                }else if ("最高学历".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setZgxl(str);
                }else if ("毕业院校".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setByyx(str);
                }else if ("所学专业".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setSxzy(str);
                }else if ("培养方式".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setPyfs(str);
                }else if ("第一外语".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setFirstla(str);
                }else if ("其它外语".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setElsela(str);
                }else if ("职称".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setPosttitle(str);
                }else if ("职业证书类型".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setZyzstype(str);
                }else if ("职业证书名称".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setZyzsname(str);
                }else if ("上家雇主".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setParentcompany(str);
                }else if ("首次参加工作时间".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setFirstworkingtime(str);
                }else if ("职级".equals(columnname.replace(" ",""))) {
                    //tb_id_manageinformation表=========================================================================
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setPostrank(str);
                }else if ("员工类型".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setEmployeetype(str);
                }else if ("入职时间".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setEntrydate(str);
                }else if ("转正时间".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformationExchange.setZhuanzhengdate(str);
                }else if ("工资标准".equals(columnname.replace(" ",""))) {
                    //tb_id_costinformation表===========================================================================
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSalarystandard(str);
                }else if ("社保基数".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSsb(str);
                }else if ("社保公司缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSsbgscd(str);
                }else if ("社保个人缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSsbgrcd(str);
                }else if ("公积金基数".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setGjj(str);
                }else if ("公积金公司缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setGjjgscd(str);
                }else if ("公积金个人缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setGjjgrcd(str);
                }else if ("开户行".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setKhh(str);
                }else if ("社保缴纳地".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setSbjnd(str);
                }else if ("工资账号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSalaryaccount(str);
                }else if ("社保账号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setSbcode(str);
                }else if ("公积金账号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setGjjcode(str);
                }else if ("私人邮箱".equals(columnname.replace(" ",""))) {
                    //tb_id_otherinformation表==========================================================================
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setPrivateemail(str);
                }else if ("公司邮箱".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setCompanyemail(str);
                }else if ("紧急联系人".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setEmergencycontract(str);
                }else if ("紧急联系人关系".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setEmergencyrp(str);
                }else if ("紧急联系电话".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setEmergencyphone(str);
                }else if ("住址".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformationExchange.setAddress(str);
                }else if ("移动电话".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setMobilephone(str);
                }else if ("办公电话".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setTelphone(str);
                }else if ("备注".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setRemark(str);
                }else if ("合同编号--合同开始时间--合同结束时间--合同类型--合同附件地址--合同备注--变更人员工号--变更日期".equals(columnname.replace(" ",""))){
                    //tb_hr_contractinformation表=======================================================================
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformationExchange.setContractlist(str);
                }
            }
        }
        return personalInformationExchange;
    }
}
