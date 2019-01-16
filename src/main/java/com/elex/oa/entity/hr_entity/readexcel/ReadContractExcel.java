package com.elex.oa.entity.hr_entity.readexcel;

import com.elex.oa.entity.hr_entity.ContractInformation;
import com.elex.oa.util.hr_util.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadContractExcel extends ReadBaseExcel<ContractInformation>{
    @Override
    public Object getObject(Sheet sheet, Row row) {
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
                    String str = ExcelUtil.getStringByDateCell(cell);
                    contractInformation.setStartdate(str);
                }else if ("合同结束时间".equals(columnname.trim())) {
                    System.out.println(48);
                    String str = ExcelUtil.getStringByDateCell(cell);
                    contractInformation.setEnddate(str);
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
                    String str = ExcelUtil.getStringByDateCell(cell);
                    contractInformation.setTransdate(str);
                }
            }
        }
        return contractInformation;
    }
}
