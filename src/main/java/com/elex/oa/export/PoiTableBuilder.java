package com.elex.oa.export;

/**
 *@author hugo.zhao
 *@since 2018/4/20 16:18
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.elex.oa.export.model.PoiTableHeader;
import com.elex.oa.json.JSONUtil;
import com.elex.oa.ui.model.ExportFieldColumn;
import com.elex.oa.ui.model.FieldColumn;
import com.elex.oa.ui.model.GroupColumn;
import com.elex.oa.ui.model.IGridColumn;
import com.elex.oa.util.BeanUtil;
import com.elex.oa.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PoiTableBuilder {
    public PoiTableBuilder() {
    }

    public int calHeaderMaxRows(List<IGridColumn> columns) {
        int maxChildRows = 1;
        Iterator var3 = columns.iterator();

        while(var3.hasNext()) {
            IGridColumn col = (IGridColumn)var3.next();
            int tmpRows = this.calColumnRows(col);
            if(maxChildRows < tmpRows) {
                maxChildRows = tmpRows;
            }
        }

        return maxChildRows;
    }

    private int calColumnRows(IGridColumn col) {
        byte rows = 1;
        int subRows = 0;
        if(col instanceof GroupColumn) {
            subRows = 1;
            GroupColumn gc = (GroupColumn)col;

            for(int i = 0; i < gc.getColumns().size(); ++i) {
                int maxSubRows = this.calColumnRows((IGridColumn)gc.getColumns().get(i));
                if(maxSubRows > subRows) {
                    subRows = maxSubRows;
                }
            }
        }

        return rows + subRows;
    }

    private int calColumnChilds(IGridColumn col) {
        if(col instanceof FieldColumn) {
            return 1;
        } else if(!(col instanceof GroupColumn)) {
            return 0;
        } else {
            GroupColumn gc = (GroupColumn)col;
            int cn = 0;

            IGridColumn gcn;
            for(Iterator var4 = gc.getColumns().iterator(); var4.hasNext(); cn += this.calColumnChilds(gcn)) {
                gcn = (IGridColumn)var4.next();
            }

            return cn;
        }
    }

    public void genTableHeader(List<IGridColumn> gridColumns, PoiTableHeader header) {
        int maxLevl = this.calHeaderMaxRows(gridColumns);
        int rowIndex = header.getStartRowIndex();
        HSSFCellStyle cellStyle = (HSSFCellStyle)header.getWorkbook().createCellStyle();
        cellStyle.setVerticalAlignment(Short.valueOf("1"));
        cellStyle.setAlignment(Short.valueOf("2"));
        cellStyle.setBorderBottom(Short.valueOf("1"));
        cellStyle.setBorderLeft(Short.valueOf("1"));
        cellStyle.setBorderTop(Short.valueOf("1"));
        cellStyle.setBorderRight(Short.valueOf("1"));
        cellStyle.setFillPattern(Short.valueOf("1"));
        cellStyle.setFillForegroundColor(Short.valueOf("13"));
        HSSFFont headerFont = (HSSFFont)header.getWorkbook().createFont();
        headerFont.setBoldweight(Short.valueOf("700"));
        headerFont.setFontName("宋体");
        headerFont.setFontHeightInPoints(Short.valueOf("12"));
        cellStyle.setFont(headerFont);
        Iterator var7 = gridColumns.iterator();

        while(var7.hasNext()) {
            IGridColumn gc = (IGridColumn)var7.next();
            Row row = header.getSheet().getRow(header.getStartRowIndex());
            if(row == null) {
                row = header.getSheet().createRow(header.getStartRowIndex());
            }

            int endRow;
            if(gc instanceof GroupColumn) {
                endRow = this.calColumnChilds(gc);
                System.out.println("colNums:" + endRow);
                int cra = header.getStartColIndex() + endRow - 1;
                GroupColumn cell = (GroupColumn)gc;
                CellRangeAddress cra1 = new CellRangeAddress(header.getStartRowIndex(), header.getStartRowIndex(), header.getStartColIndex(), cra);
                header.getSheet().addMergedRegion(cra1);
                Cell cell1 = row.createCell(header.getStartColIndex());
                cell1.setCellValue(gc.getHeader());
                cell1.setCellStyle(cellStyle);
                RegionUtil.setBorderBottom(1, cra1, header.getSheet(), header.getWorkbook());
                RegionUtil.setBorderTop(1, cra1, header.getSheet(), header.getWorkbook());
                RegionUtil.setBorderLeft(1, cra1, header.getSheet(), header.getWorkbook());
                RegionUtil.setBorderRight(1, cra1, header.getSheet(), header.getWorkbook());
                header.setStartRowIndex(header.getStartRowIndex() + 1);
                this.genTableHeader(cell.getColumns(), header);
                header.setStartRowIndex(rowIndex);
            } else if(gc instanceof FieldColumn) {
                endRow = header.getStartRowIndex() + maxLevl - 1;
                if(endRow < header.getMaxRowSpan() - 1) {
                    endRow = header.getMaxRowSpan() - 1;
                }

                CellRangeAddress cra2 = new CellRangeAddress(header.getStartRowIndex(), endRow, header.getStartColIndex(), header.getStartColIndex());
                header.getSheet().addMergedRegion(cra2);
                Cell cell2 = row.createCell(header.getStartColIndex());
                RegionUtil.setBorderBottom(1, cra2, header.getSheet(), header.getWorkbook());
                RegionUtil.setBorderTop(1, cra2, header.getSheet(), header.getWorkbook());
                RegionUtil.setBorderLeft(1, cra2, header.getSheet(), header.getWorkbook());
                RegionUtil.setBorderRight(1, cra2, header.getSheet(), header.getWorkbook());
                cell2.setCellStyle(cellStyle);
                header.getSheet().setColumnWidth(header.getStartColIndex(), gc.getWidth().intValue() * 256 / 7);
                cell2.setCellValue(gc.getHeader());
                header.getFieldColumns().add((FieldColumn)gc);
                header.setStartColIndex(header.getStartColIndex() + 1);
            }
        }

    }

    public void writeTableData(PoiTableHeader header, List<?> dataList) {
        HSSFCellStyle cellStyle = (HSSFCellStyle)header.getWorkbook().createCellStyle();
        cellStyle.setAlignment(Short.valueOf("1"));
        cellStyle.setBorderBottom(Short.valueOf("1"));
        cellStyle.setBorderLeft(Short.valueOf("1"));
        cellStyle.setBorderTop(Short.valueOf("1"));
        cellStyle.setBorderRight(Short.valueOf("1"));
        int startRow = header.getMaxRowSpan();

        for(int i = 0; i < dataList.size(); ++i) {
            Object obj = dataList.get(i);
            Row row = header.getSheet().createRow(startRow + i);

            for(int j = 0; j < header.getFieldColumns().size(); ++j) {
                FieldColumn fieldColumn = (FieldColumn)header.getFieldColumns().get(j);
                Cell cell = row.createCell(j);
                String field = fieldColumn.getField();
                Object val = BeanUtil.getFieldValueFromObject(obj, field);
                if(val != null) {
                    cell.setCellValue(val.toString());
                }

                cell.setCellStyle(cellStyle);
            }
        }

    }

    public Workbook writeTable(List<IGridColumn> columns, List<?> dataList) {
        Integer rowIndex = Integer.valueOf(0);
        Integer colIndex = Integer.valueOf(0);
        int maxRows = this.calHeaderMaxRows(columns);
        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        PoiTableHeader header = new PoiTableHeader(rowIndex.intValue(), colIndex.intValue(), maxRows, wb, sheet);
        this.genTableHeader(columns, header);
        this.writeTableData(header, dataList);
        return header.getWorkbook();
    }

    public List<IGridColumn> constructColumns(String jsonColumns) {
        JSONArray colArr = JSONArray.fromObject(jsonColumns);
        ArrayList columns = new ArrayList();

        for(int i = 0; i < colArr.size(); ++i) {
            JSONObject obj = colArr.getJSONObject(i);
            String header = JSONUtil.getString(obj, "header");
            String fieldName = JSONUtil.getString(obj, "field");
            String allowSort = JSONUtil.getString(obj, "allowSort");
            String sWidth = JSONUtil.getString(obj, "width");
            String visible = JSONUtil.getString(obj, "visible");
            int index = sWidth.lastIndexOf("px");
            int width = 100;
            if(index != -1) {
                sWidth = sWidth.substring(0, index);
                width = (new Integer(sWidth)).intValue();
            }

            String cols = JSONUtil.getString(obj, "columns");
            if(StringUtils.isNotEmpty(cols)) {
                GroupColumn fc = new GroupColumn();
                fc.setHeader(header);
                fc.setHeaderAlign("center");
                fc.setVisible("true".equals(visible));
                fc.setWidth(Integer.valueOf(width));
                List subColumns = this.constructColumns(cols);
                fc.setColumns(subColumns);
                columns.add(fc);
            } else {
                FieldColumn var16 = new FieldColumn();
                var16.setAllowSort("true".equals(allowSort));
                var16.setField(fieldName);
                var16.setHeader(header);
                var16.setHeaderAlign("center");
                var16.setVisible("true".equals(visible));
                var16.setWidth(Integer.valueOf(width));
                columns.add(var16);
            }
        }

        return columns;
    }

    public List<ExportFieldColumn> constructMutiExportFieldColumns(String jsonColumns) {
        JSONArray colArr = JSONArray.fromObject(jsonColumns);
        ArrayList columns = new ArrayList();

        for(int i = 0; i < colArr.size(); ++i) {
            Object childColumn = new ArrayList();
            JSONObject obj = colArr.getJSONObject(i);
            String header = JSONUtil.getString(obj, "header");
            String fieldName = JSONUtil.getString(obj, "field");
            String allowSort = JSONUtil.getString(obj, "allowSort");
            String sWidth = JSONUtil.getString(obj, "width");
            String visible = JSONUtil.getString(obj, "visible");
            String childColumns = JSONUtil.getString(obj, "children");
            int index = sWidth.lastIndexOf("px");
            int width = 100;
            int colspan = 1;
            if(index != -1) {
                sWidth = sWidth.substring(0, index);
                width = (new Integer(sWidth)).intValue();
            }

            if(StringUtil.isNotEmpty(childColumns) && !"[]".equals(childColumns)) {
                childColumn = this.constructMutiExportFieldColumns(childColumns);
                colspan = this.getRowSpan((List)childColumn, 0);
            }

            ExportFieldColumn fc = new ExportFieldColumn();
            fc.setAllowSort("true".equals(allowSort));
            fc.setField(fieldName);
            fc.setHeader(header);
            fc.setHeaderAlign("center");
            fc.setVisible("true".equals(visible));
            fc.setWidth(Integer.valueOf(width));
            fc.setChildColumn((List)childColumn);
            fc.setColspan(colspan);
            columns.add(fc);
        }

        return columns;
    }

    public List<ExportFieldColumn> constructSingleExportFieldColumns(String jsonColumns, List<ExportFieldColumn> columns) {
        JSONArray colArr = JSONArray.fromObject(jsonColumns);

        for(int i = 0; i < colArr.size(); ++i) {
            ArrayList childColumn = new ArrayList();
            JSONObject obj = colArr.getJSONObject(i);
            String header = JSONUtil.getString(obj, "header");
            String fieldName = JSONUtil.getString(obj, "field");
            String allowSort = JSONUtil.getString(obj, "allowSort");
            String sWidth = JSONUtil.getString(obj, "width");
            String visible = JSONUtil.getString(obj, "visible");
            String childColumns = JSONUtil.getString(obj, "children");
            int index = sWidth.lastIndexOf("px");
            int width = 100;
            byte colspan = 1;
            if(index != -1) {
                sWidth = sWidth.substring(0, index);
                width = (new Integer(sWidth)).intValue();
            }

            if(StringUtil.isNotEmpty(childColumns) && !"[]".equals(childColumns)) {
                this.constructSingleExportFieldColumns(childColumns, columns);
            } else {
                ExportFieldColumn fc = new ExportFieldColumn();
                fc.setAllowSort("true".equals(allowSort));
                fc.setField(fieldName);
                fc.setHeader(header);
                fc.setHeaderAlign("center");
                fc.setVisible("true".equals(visible));
                fc.setWidth(Integer.valueOf(width));
                fc.setChildColumn(childColumn);
                fc.setColspan(colspan);
                columns.add(fc);
            }
        }

        return columns;
    }

    private int getRowSpan(List<ExportFieldColumn> childColumns, int colspan) {
        Iterator var3 = childColumns.iterator();

        while(var3.hasNext()) {
            ExportFieldColumn childColumn = (ExportFieldColumn)var3.next();
            if(childColumn.getChildColumn().size() > 0) {
                List subChildColumns = childColumn.getChildColumn();
                colspan = this.getRowSpan(subChildColumns, colspan);
            } else {
                ++colspan;
            }
        }

        return colspan;
    }
}