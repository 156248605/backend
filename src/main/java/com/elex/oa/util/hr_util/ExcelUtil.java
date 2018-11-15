package com.elex.oa.util.hr_util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 读取Excel时用到的一些方法
 * @Author shiyun
 * @Date 2018\11\15 0015 9:57
 * @Version 1.0
 **/
public class ExcelUtil {
    public static String getStringByDateCell(Cell cell) {
        String str = "";
        if (cell.getCellTypeEnum()== CellType.STRING) {
            cell.setCellType(CellType.STRING);
            str = cell.getStringCellValue();
            str = str.replace("年", "/");
            str = str.replace("月", "/");
            str = str.replace("日", "/");
            str = str.replace("-", "/");
            str = getDateString(str);
        } else if(cell.getCellTypeEnum()==CellType.NUMERIC){
            Date zhaunDate = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            str = sdf.format(zhaunDate).toString();
        }
        return str;
    }

    public static String getStringByNumberCell(Cell cell){
        cell.setCellType(CellType.STRING);
        String str = String.valueOf(cell.getStringCellValue()).replace("\t","");
        return str;
    }

    private static String getDateString(String str){
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