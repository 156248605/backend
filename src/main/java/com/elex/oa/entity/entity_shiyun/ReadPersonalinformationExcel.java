package com.elex.oa.entity.entity_shiyun;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadPersonalinformationExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadPersonalinformationExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取信息集合
     * @return
     */
    public List<PersonalInformation> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<PersonalInformation> personalInformationList = new ArrayList<PersonalInformation>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            personalInformationList = createExcel(mFile.getInputStream(), isExcel2003);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalInformationList;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<PersonalInformation> createExcel(InputStream is, boolean isExcel2003) {
        List<PersonalInformation> personalInformationList = new ArrayList<PersonalInformation>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            personalInformationList = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personalInformationList;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<PersonalInformation> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<PersonalInformation> personalInformationList = new ArrayList<PersonalInformation>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            PersonalInformation personalInformation = new PersonalInformation();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                Cell cell2 = sheet.getRow(0).getCell(c);
                String columnname = String.valueOf(cell2.getStringCellValue());
                if (null != cell) {
                    if ("账号激活状态".equals(columnname.replace(" ",""))) {
                        Integer isactive = null;
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        isactive = str == null ? null : Integer.parseInt(str);
                        personalInformation.setIsactive(isactive);
                    }else if ("登录ID".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
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
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
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
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue()).replace("\t","");
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
                        String str = "";
                        if (cell.getCellType()==1) {
                            cell.setCellType(CellType.STRING);
                            str = cell.getStringCellValue();
                            str = str.replace("年", "/");
                            str = str.replace("月", "/");
                            str = str.replace("日", "/");
                            str = str.replace("-", "/");
                            str = getDateString(str);
                        } else if(cell.getCellType()==0){
                            Date zhaunDate = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            str = sdf.format(zhaunDate).toString();
                        }
                        personalInformation.setFirstworkingtime(str);
                    }else if ("上家雇主".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setParentcompany(str);
                    }else if ("部门编号".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setDepcode(str);
                    }else if ("部门".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setDepname(str);
                    }else if ("岗位".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setPostnames(str);
                    }else if ("职级".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setZj(str);
                    }else if ("入职时间".equals(columnname.replace(" ",""))) {
                        String str = "";
                        if (cell.getCellType()==1) {
                            cell.setCellType(CellType.STRING);
                            str = cell.getStringCellValue();
                            str = str.replace("年", "/");
                            str = str.replace("月", "/");
                            str = str.replace("日", "/");
                            str = str.replace("-", "/");
                            str = getDateString(str);
                        } else if(cell.getCellType()==0){
                            Date entrydate = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            str = sdf.format(entrydate).toString();
                        }
                        personalInformation.setEntrydate(str);
                    }else if ("转正时间".equals(columnname.replace(" ",""))) {
                        String str = "";
                        if (cell.getCellType()==1) {
                            cell.setCellType(CellType.STRING);
                            str = cell.getStringCellValue();
                            str = str.replace("年", "/");
                            str = str.replace("月", "/");
                            str = str.replace("日", "/");
                            str = str.replace("-", "/");
                            str = getDateString(str);
                        } else if(cell.getCellType()==0){
                            Date zhaunDate = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            str = sdf.format(zhaunDate).toString();
                        }
                        personalInformation.setZhuanzhengdate(str);
                    }else if ("员工类型".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setEmployeetype(str);
                    }else if ("薪资标准".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSalary(str);
                    }else if ("社保基数".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSsb(str);
                    }else if ("社保公司缴费比例".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSsbgscd(str);
                    }else if ("社保个人缴费比例".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSsbgrcd(str);
                    }else if ("公积金基数".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setGjj(str);
                    }else if ("公积金公司缴费比例".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setGjjgscd(str);
                    }else if ("公积金个人缴费比例".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setGjjgrcd(str);
                    }else if ("开户行".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setKhh(str);
                    }else if ("工资账号".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSalaryaccount(str);
                    }else if ("社保缴纳地".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSbjnd(str);
                    }else if ("社保账号".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setSbcode(str);
                    }else if ("公积金账号".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setGjjcode(str);
                    }else if ("移动电话".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setMobilephone(str);
                    }else if ("办公电话".equals(columnname.replace(" ",""))) {
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
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
                        cell.setCellType(CellType.STRING);
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setEmergencyphone(str);
                    }else if ("应急联系地址".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setAddress(str);
                    }else if ("备注".equals(columnname.replace(" ",""))) {
                        String str = String.valueOf(cell.getStringCellValue());
                        personalInformation.setRemark(str);
                    }
                }
            }
            // 添加到list
            personalInformationList.add(personalInformation);
        }
        return personalInformationList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static String getDateString(String str){
        String[] strs = str.split("/");
        if(strs.length==3){
            String year = strs[0];
            String month = strs[1];
            month = month.length()==2?month:"0"+month;
            String day = strs[2];
            day = day.length()==2?day:"0"+day;
            str = year+"/"+month+"/"+day;
        }
        return str;
    }
}
