package com.elex.oa.export.model;
import java.util.ArrayList;
import java.util.List;

import com.elex.oa.ui.model.FieldColumn;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *@author hugo.zhao
 *@since 2018/4/20 16:33
*/
public class PoiTableHeader {
    private int startRowIndex;
    private int startColIndex;
    private Workbook workbook;
    private Sheet sheet;
    private int maxRowSpan = 1;
    private List<FieldColumn> fieldColumns = new ArrayList();

    public Workbook getWorkbook() {
        return this.workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Sheet getSheet() {
        return this.sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public int getStartRowIndex() {
        return this.startRowIndex;
    }

    public void setStartRowIndex(int startRowIndex) {
        this.startRowIndex = startRowIndex;
    }

    public int getStartColIndex() {
        return this.startColIndex;
    }

    public void setStartColIndex(int startColIndex) {
        this.startColIndex = startColIndex;
    }

    public List<FieldColumn> getFieldColumns() {
        return this.fieldColumns;
    }

    public void setFieldColumns(List<FieldColumn> fieldColumns) {
        this.fieldColumns = fieldColumns;
    }

    public PoiTableHeader(int startRowIndex, int startColIndex, int maxRowSpan, Workbook workbook, Sheet sheet) {
        this.startRowIndex = startRowIndex;
        this.startColIndex = startColIndex;
        this.maxRowSpan = maxRowSpan;
        this.workbook = workbook;
        this.sheet = sheet;
    }

    public PoiTableHeader() {
    }

    public int getMaxRowSpan() {
        return this.maxRowSpan;
    }

    public void setMaxRowSpan(int maxRowSpan) {
        this.maxRowSpan = maxRowSpan;
    }
}