package com.elex.oa.entity.hr_entity;

import com.elex.oa.util.hr_util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadDimissioninformationExcel extends ReadBaseExcel<DimissionInformation>{
    @Override
    public Object getObject(Sheet sheet, Row row) {
        DimissionInformation dimissionInformation  = new DimissionInformation();
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
                if ("姓名".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    dimissionInformation.setDimissiontruename(str);
                }else if ("最后工作日期".equals(columnname.trim())) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    dimissionInformation.setLastworkingdate(str);
                }else if ("离职类型".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    dimissionInformation.setDimissiontype(str);
                }else if ("离职原因".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    dimissionInformation.setDimissionreason(str);
                }else if ("办理人".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    dimissionInformation.setTransactortruename(str);
                }else if ("办理日期".equals(columnname.trim())) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    dimissionInformation.setTransactiondate(str);
                }else if ("离职去向".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    dimissionInformation.setDimissiondirection(str);
                }
            }
        }
        return dimissionInformation;
    }
}
