package com.elex.oa.entity.hr_entity;

import com.elex.oa.util.hr_util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadPersonalinformationExcel extends ReadBaseExcel<PersonalInformation>{
    @Override
    public Object getObject(Sheet sheet, Row row) {
        PersonalInformation personalInformation = new PersonalInformation();
        // 循环Excel的列
        for (int c = 0; c < this.totalCells; c++) {
            Cell cell = row.getCell(c);
            Cell cell2 = sheet.getRow(0).getCell(c);
            String columnname = String.valueOf(cell2.getStringCellValue());
            if (null != cell) {
                if ("账号激活状态".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    Integer isactive = (str == null ? 1 : Integer.parseInt(str));
                    personalInformation.setIsactive(isactive);
                }else if ("登录ID".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setUsername(str);
                }else if ("姓名".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setTruename(str);
                }else if ("免冠照片".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setUserphoto(str);
                }else if ("身份证扫描件正面".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setIdphoto1(str);
                }else if ("身份证扫描件背面".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setIdphoto2(str);
                }else if ("工号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    if(str.length()<6){//工号不足补0
                        switch (str.length()){
                            case 1:str = "00000"+str;break;
                            case 2:str = "0000"+str;break;
                            case 3:str = "000"+str;break;
                            case 4:str = "00"+str;break;
                            case 5:str = "0"+str;break;
                        }
                    }
                    personalInformation.setEmployeenumber(str);
                }else if ("英文名".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setEnglishname(str);
                }else if ("身份证号码".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setIdcode(str);
                }else if ("民族".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setRace(str);
                }else if ("婚姻".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setMarriage(str);
                }else if ("生育".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setChildren(str);
                }else if ("政治面貌".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setZzmm(str);
                }else if ("最高学历".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setZgxl(str);
                }else if ("毕业院校".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setByyx(str);
                }else if ("所学专业".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setSxzy(str);
                }else if ("培养方式".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setPyfs(str);
                }else if ("第一外语".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setFirstla(str);
                }else if ("其它外语".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setElsela(str);
                }else if ("职称".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setPosttitle(str);
                }else if ("职业证书类型".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setZyzstype(str);
                }else if ("职业证书名称".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setZyzsname(str);
                }else if ("首次参加工作时间".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformation.setFirstworkingtime(str);
                }else if ("上家雇主".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setParentcompany(str);
                }else if ("部门编号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setDepcode(str);
                }else if ("部门".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setDepname(str);
                }else if ("岗位".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setPostnames(str);
                }else if ("职级".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setZj(str);
                }else if ("入职时间".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformation.setEntrydate(str);
                }else if ("转正时间".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    personalInformation.setZhuanzhengdate(str);
                }else if ("员工类型".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setEmployeetype(str);
                }else if ("薪资标准".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setSalary(str);
                }else if ("社保基数".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setSsb(str);
                }else if ("社保公司缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setSsbgscd(str);
                }else if ("社保个人缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setSsbgrcd(str);
                }else if ("公积金基数".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setGjj(str);
                }else if ("公积金公司缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setGjjgscd(str);
                }else if ("公积金个人缴费比例".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setGjjgrcd(str);
                }else if ("开户行".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setKhh(str);
                }else if ("工资账号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setSalaryaccount(str);
                }else if ("社保缴纳地".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setSbjnd(str);
                }else if ("社保账号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setSbcode(str);
                }else if ("公积金账号".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setGjjcode(str);
                }else if ("移动电话".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setMobilephone(str);
                }else if ("办公电话".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setTelphone(str);
                }else if ("私人邮件".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setPrivateemail(str);
                }else if ("公司邮件".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setCompanyemail(str);
                }else if ("应急联系人".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setEmergencycontract(str);
                }else if ("应急联系人关系".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setEmergencyrp(str);
                }else if ("应急联系电话".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setEmergencyphone(str);
                }else if ("应急联系地址".equals(columnname.replace(" ",""))) {
                    String str = String.valueOf(cell.getStringCellValue());
                    personalInformation.setAddress(str);
                }else if ("备注".equals(columnname.replace(" ",""))) {
                    String str = ExcelUtil.getStringByNumberCell(cell);
                    personalInformation.setRemark(str);
                }
            }
        }
        return personalInformation;
    }
}
