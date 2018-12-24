package com.elex.oa.entity.restructure_hrentity;

import com.elex.oa.entity.hr_entity.ReadBaseExcel;
import com.elex.oa.util.hr_util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ReadPostloginfoExcel extends ReadBaseExcel<Postloginfo>{
    @Override
    public Object getObject(Sheet sheet, Row row) {
        Postloginfo postloginfo = new Postloginfo();
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
                if ("变更项目".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setChangeinformation(str);
                }else if ("岗位名称".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setPostname(str);
                }else if ("岗位编号".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setPostcode(str);
                }else if ("变更前内容".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setBeforeinformation(str);
                }else if ("变更后内容".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setAfterinformation(str);
                }else if ("变更原因".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setChangereason(str);
                }else if ("变更日期".equals(columnname.trim())) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    postloginfo.setChangedate(str);
                }else if ("办理人".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setTransactortruename(str);
                }else if ("办理人员工号".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    postloginfo.setTransactoruserid(str);
                }
            }
        }
        return postloginfo;
    }
}
