package com.elex.oa.entity.hr_entity;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadContractExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadContractExcel(){}
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
    public List<ContractInformation> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<ContractInformation> contractInformationList = new ArrayList<ContractInformation>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            contractInformationList = createExcel(mFile.getInputStream(), isExcel2003);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contractInformationList;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<ContractInformation> createExcel(InputStream is, boolean isExcel2003) {
        List<ContractInformation> contractInformationList = new ArrayList<ContractInformation>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            contractInformationList = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contractInformationList;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<ContractInformation> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<ContractInformation> contractInformationList = new ArrayList<ContractInformation>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                break;
            }
            ContractInformation contractInformation = new ContractInformation();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                Cell cell2 = sheet.getRow(0).getCell(c);
                String columnname;
                if (cell2!=null) {
                    columnname = String.valueOf(cell2.getStringCellValue());
                } else {
                    continue;
                }
                if (null != cell) {
                    if ("合同编号".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        contractInformation.setContractcode(str);
                    }else if ("姓名".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        contractInformation.setTruename(str);
                    }else if ("合同开始时间".equals(columnname.trim())) {
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            String str = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            contractInformation.setStartdate(str);
                        }
                    }else if ("合同结束时间".equals(columnname.trim())) {
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            String str = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            contractInformation.setEnddate(str);
                        }
                    }else if ("合同类型".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        contractInformation.setContracttype(str);
                    }else if ("附件".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        contractInformation.setAttachment(str);
                    }else if ("备注".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        contractInformation.setRemark(str);
                    }else if ("办理人".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        contractInformation.setTransactortruename(str);
                    }else if ("办理日期".equals(columnname.trim())) {
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            String str = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            contractInformation.setTransdate(str);
                        }
                    }
                }
            }
            // 添加到list
            contractInformationList.add(contractInformation);
        }
        return contractInformationList;
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
}
