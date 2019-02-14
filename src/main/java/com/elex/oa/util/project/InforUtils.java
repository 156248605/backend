package com.elex.oa.util.project;

import com.elex.oa.entity.project.ProjectInfor;
import com.elex.oa.entity.project.ProjectVarious;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class InforUtils {

    public static List<ProjectInfor> obtainList(MultipartFile file) throws IOException {
        List<ProjectInfor> list = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        boolean isExcel2003 = fileName.contains(".xlsx");
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if(isExcel2003) {
            wb = new XSSFWorkbook(is);
        } else {
            wb = new HSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        ProjectInfor projectInfor;
        int rowNumber = sheet.getLastRowNum();
        Row firstRow = sheet.getRow(0);
        int cellNumber = firstRow.getPhysicalNumberOfCells();
        Row row;
        String character;
        String cellValue;
        for(int r = 1; r<= rowNumber; r ++) {
            row = sheet.getRow(r);
            if(row == null) {
                continue;
            }
            projectInfor = new ProjectInfor();
            for(int c = 0; c < cellNumber; c ++){
                character = firstRow.getCell(c).getStringCellValue();
                if(row.getCell(c) == null) {
                    continue;
                }
                cellValue = row.getCell(c).getStringCellValue();
                if(StringUtils.isNotBlank(cellValue)) {
                    switch (character) {
                        case "甲方地址" : {
                            projectInfor.setPartyAddress(cellValue);
                            break;
                        }
                        case "甲方名称" : {
                            projectInfor.setPartyName(cellValue);
                            break;
                        }
                        case "项目概况" : {
                            projectInfor.setGeneralSituation(cellValue);
                            break;
                        }
                        case "相关成员" : {
                            projectInfor.setRelatedMembers(cellValue);
                            break;
                        }
                        case "项目成员" : {
                            projectInfor.setProjectMembers(cellValue);
                            break;
                        }
                        case "项目来源" : {
                            projectInfor.setProjectSource(cellValue);
                            break;
                        }
                        case "项目类型" : {
                            projectInfor.setProjectType(cellValue);
                            break;
                        }
                        case "申请人" : {
                            projectInfor.setProposer(cellValue);
                            break;
                        }
                        case "立项时间" : {
                            projectInfor.setWriteDate(cellValue);
                            break;
                        }
                        case "项目经理" : {
                            projectInfor.setProjectManager(cellValue);
                            break;
                        }
                        case "部门经理" : {
                            projectInfor.setDeptManager(cellValue);
                            break;
                        }
                        case "商务经理" : {
                            projectInfor.setBusinessManager(cellValue);
                            break;
                        }
                        case "立项部门" : {
                            projectInfor.setInDepartment(cellValue);
                            break;
                        }
                        case "项目名称" : {
                            projectInfor.setProjectName(cellValue);
                            break;
                        }
                        case "项目编号" : {
                            projectInfor.setProjectCode(cellValue);
                            break;
                        }
                        case "项目状态" : {
                            projectInfor.setProjectStatus(cellValue);
                            break;
                        }
                        case "立项日期" : {
                            projectInfor.setWriteDate(cellValue);
                            break;
                        }
                        case "相关人员" : {
                            projectInfor.setRelatedMembers(cellValue);
                            break;
                        }
                        case "甲方电话" : {
                            projectInfor.setPartyPhone(cellValue);
                            break;
                        }
                        case "甲方传真" : {
                            projectInfor.setPartyFax(cellValue);
                            break;
                        }
                        case "负责人" : {
                            projectInfor.setHeadName(cellValue);
                            break;
                        }
                        case "负责人职务" : {
                            projectInfor.setHeadPosition(cellValue);
                            break;
                        }
                        case "负责人手机" : {
                            projectInfor.setHeadMobile(cellValue);
                            break;
                        }
                        case "负责人邮件" : {
                            projectInfor.setHeadEmail(cellValue);
                            break;
                        }
                        case "联系人" : {
                            projectInfor.setContactName(cellValue);
                            break;
                        }
                        case "联系人职务" : {
                            projectInfor.setContactPosition(cellValue);
                            break;
                        }
                        case "联系人手机" : {
                            projectInfor.setContactMobile(cellValue);
                            break;
                        }
                        case "联系人邮件" : {
                            projectInfor.setContactEmail(cellValue);
                            break;
                        }
                    }
                }
            }
            list.add(projectInfor);
        }
        return list;
    }

    public static void exportExcel(HttpServletResponse response, String fileName, List<ProjectInfor> data,
                                   List<ProjectVarious> statusList, List<ProjectVarious> sourceList, List<ProjectVarious> typeList) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        exportExcel(data, response.getOutputStream(), statusList, sourceList, typeList);
    }

    public static void exportExcel(List<ProjectInfor> data, OutputStream out, List<ProjectVarious> statusList,
                                   List<ProjectVarious> sourceList, List<ProjectVarious> typeList) throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = "Sheet1";
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel(wb, sheet, data, statusList, sourceList, typeList);
            wb.write(out);
        } finally {
            wb.close();
        }
    }

    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, List<ProjectInfor> data, List<ProjectVarious> statusList,
                                   List<ProjectVarious> sourceList, List<ProjectVarious> typeList) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet);
        writeRowsToExcel(wb, sheet, rowIndex, data, statusList, sourceList, typeList);
        autoSizeColumns(sheet, data.size() + 1);
    }

    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet) {
        int rowIndex = 0;
        int colIndex = 0;

        Row titleRow = sheet.createRow(rowIndex);

        colIndex = 0;
        List<String> titles = new ArrayList<>();
        titles.add("项目编号");
        titles.add("项目名称");
        titles.add("立项部门");
        titles.add("项目状态");
        titles.add("部门经理");
        titles.add("申请人");
        titles.add("立项日期");
        titles.add("商务经理");
        titles.add("项目来源");
        titles.add("交付经理");
        titles.add("项目类型");
        titles.add("项目成员");
        titles.add("相关人员");
        titles.add("甲方名称");
        titles.add("甲方地址");
        titles.add("甲方电话");
        titles.add("甲方传真");
        titles.add("负责人");
        titles.add("负责人职务");
        titles.add("负责人手机");
        titles.add("负责人邮件");
        titles.add("联系人");
        titles.add("联系人职务");
        titles.add("联系人手机");
        titles.add("联系人邮件");
        titles.add("项目概况");

        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            colIndex++;
        }

        rowIndex++;
        return rowIndex;
    }

    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, int rowIndex, List<ProjectInfor> data, List<ProjectVarious> statusList,
                                        List<ProjectVarious> sourceList, List<ProjectVarious> typeList) {
        for (ProjectInfor rowData : data) {
            Row dataRow = sheet.createRow(rowIndex);
            for(int colIndex = 0; colIndex < 26; colIndex ++) {
                Cell cell = dataRow.createCell(colIndex);
                String cellData = "";
                if(colIndex == 0) {
                    cellData = rowData.getProjectCode();
                } else if(colIndex == 1) {
                    cellData = rowData.getProjectName();
                } else if(colIndex == 2) {
                    cellData = rowData.getInDepartment();
                } else if(colIndex == 3) {
                    for(ProjectVarious status: statusList) {
                        String code = String.valueOf(status.getCode());
                        if(code.equals(rowData.getProjectStatus())) {
                            cellData = status.getName(); //项目状态
                        }
                    }
                } else if(colIndex == 4) {
                    cellData = rowData.getDeptManager();
                } else if(colIndex == 5) {
                    cellData = rowData.getProposer();
                } else if(colIndex == 6) {
                    cellData = rowData.getWriteDate();
                } else if(colIndex == 7) {
                    cellData = rowData.getBusinessManager();
                } else if(colIndex == 8) {
                    for(ProjectVarious source: sourceList) {
                        String code = String.valueOf(source.getCode());
                        if(code.equals(rowData.getProjectSource())) {
                            cellData = source.getName(); //项目来源
                        }
                    }
                } else if(colIndex == 9) {
                    cellData = rowData.getProjectManager();
                } else if(colIndex == 10) {
                    for(ProjectVarious type: typeList) {
                        String code = String.valueOf(type.getCode());
                        if(code.equals(rowData.getProjectType())) {
                            cellData = type.getName();  //项目类型
                        }
                    }
                } else if(colIndex == 11) {
                    cellData = rowData.getProjectMembers();
                } else if(colIndex == 12) {
                    cellData = rowData.getRelatedMembers();
                } else if(colIndex == 13) {
                    cellData = rowData.getPartyName();
                } else if(colIndex == 14) {
                    cellData = rowData.getPartyAddress();
                } else if(colIndex == 15) {
                    cellData = rowData.getPartyPhone();
                } else if(colIndex == 16) {
                    cellData = rowData.getPartyFax();
                } else if(colIndex == 17) {
                    cellData = rowData.getHeadName();
                } else if(colIndex == 18) {
                    cellData = rowData.getHeadPosition();
                } else if(colIndex == 19) {
                    cellData = rowData.getHeadMobile();
                } else if(colIndex == 20) {
                    cellData = rowData.getHeadEmail();
                } else if(colIndex == 21) {
                    cellData = rowData.getContactName();
                } else if(colIndex == 22) {
                    cellData = rowData.getContactPosition();
                } else if(colIndex == 23) {
                    cellData = rowData.getContactMobile();
                } else if(colIndex == 24) {
                    cellData = rowData.getContactEmail();
                } else if(colIndex == 25) {
                    cellData = rowData.getGeneralSituation();
                }
                cell.setCellValue(cellData);
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }
}
