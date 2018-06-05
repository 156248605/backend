package com.elex.oa.service.eqptImpl;

import com.elex.oa.entity.eqpt.Linkman;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Partner;
import com.elex.oa.entity.eqpt.Repository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    // 错误信息接收器
    private String errorMsg;
    // 构造方法
    public ReadExcel() {}
    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }
    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }
    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取信息集合
     */
    // Material
    public List<Material> getExcelInfoMaterial(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcelMaterial(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Repository
    public List<Repository> getExcelInfoRepository(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcelRepository(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Partner
    public List<Partner> getExcelInfoPartner(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        //      List<Map<String, Object>> partnerList = new LinkedList<Map<String, Object>>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcelPartner(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Linkman
    public List<Linkman> getExcelInfoLinkman(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        //      List<Map<String, Object>> partnerList = new LinkedList<Map<String, Object>>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcelLinkman(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取信息
     *
     * @param is      输入流
     * @param isExcel2003   excel是2003还是2007版本
     */
    // Material
    public List<Material> createExcelMaterial(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValueMaterial(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Repository
    public List<Repository> createExcelRepository(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValueRepository(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Partner
    public List<Partner> createExcelPartner(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValuePartner(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Linkman
    public List<Linkman> createExcelLinkman(InputStream is, boolean isExcel2003) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            return readExcelValueLinkman(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel里面的信息
     */
    // Material
    private List<Material> readExcelValueMaterial(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Material> materialList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Material material = new Material(); // 实体类方式(字段多时建议使用)
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String bn = String.valueOf(cell.getNumericCellValue());
                            material.setBn(bn.substring(0, bn.length() - 2 > 0 ? bn.length() - 2 : 1));
                        } else {
                            material.setBn(cell.getStringCellValue());
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String sn = String.valueOf(cell.getNumericCellValue());
                            material.setSn(sn.substring(0, sn.length() - 2 > 0 ? sn.length() - 2 : 1));
                        } else {
                            material.setSn(cell.getStringCellValue());
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String position = String.valueOf(cell.getNumericCellValue());
                            material.setPosition(position.substring(0, position.length() - 2 > 0 ? position.length() - 2 : 1));
                        } else {
                            material.setPosition(cell.getStringCellValue());
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String category = String.valueOf(cell.getNumericCellValue());
                            material.setCategory(category.substring(0, category.length() - 2 > 0 ? category.length() - 2 : 1));
                        } else {
                            material.setCategory(cell.getStringCellValue());
                        }
                    } else if (c == 4) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String name = String.valueOf(cell.getNumericCellValue());
                            material.setName(name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));
                        } else {
                            material.setName(cell.getStringCellValue());
                        }
                    } else if (c == 5) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String id = String.valueOf(cell.getNumericCellValue());
                            material.setId(id.substring(0, id.length() - 2 > 0 ? id.length() - 2 : 1));
                        } else {
                            material.setId(cell.getStringCellValue());
                        }
                    } else if (c == 6) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String date = String.valueOf(cell.getNumericCellValue());
                            material.setDate(date.substring(0, date.length() - 2 > 0 ? date.length() - 2 : 1));
                        } else {
                            material.setDate(cell.getStringCellValue());
                        }
                    } else if (c == 7) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String brand = String.valueOf(cell.getNumericCellValue());
                            material.setBrand(brand.substring(0, brand.length() - 2 > 0 ? brand.length() - 2 : 1));
                        } else {
                            material.setBrand(cell.getStringCellValue());
                        }
                    } else if (c == 8) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String spec = String.valueOf(cell.getNumericCellValue());
                            material.setSpec(spec.substring(0, spec.length() - 2 > 0 ? spec.length() - 2 : 1));
                        } else {
                            material.setSpec(cell.getStringCellValue());
                        }
                    } else if (c == 9) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String materials = String.valueOf(cell.getNumericCellValue());
                            material.setMaterial(materials.substring(0, materials.length() - 2 > 0 ? materials.length() - 2 : 1));
                        } else {
                            material.setMaterial(cell.getStringCellValue());
                        }
                    } else if (c == 10) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String maxlimit = String.valueOf(cell.getNumericCellValue());
                            material.setMaxlimit(maxlimit.substring(0, maxlimit.length() - 2 > 0 ? maxlimit.length() - 2 : 1));
                        } else {
                            material.setMaxlimit(cell.getStringCellValue());
                        }
                    } else if (c == 11) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String minlimit = String.valueOf(cell.getNumericCellValue());
                            material.setMinlimit(minlimit.substring(0, minlimit.length() - 2 > 0 ? minlimit.length() - 2 : 1));
                        } else {
                            material.setMinlimit(cell.getStringCellValue());
                        }
                    } else if (c == 12) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String remark = String.valueOf(cell.getNumericCellValue());
                            material.setRemark(remark.substring(0, remark.length() - 2 > 0 ? remark.length() - 2 : 1));
                        } else {
                            material.setRemark(cell.getStringCellValue());
                        }
                    } else if (c == 13) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String partner = String.valueOf(cell.getNumericCellValue());
                            material.setPartner(partner.substring(0, partner.length() - 2 > 0 ? partner.length() - 2 : 1));
                        } else {
                            material.setPartner(cell.getStringCellValue());
                        }
                    } else if (c == 14) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String price = String.valueOf(cell.getNumericCellValue());
                            material.setPrice(price.substring(0, price.length() - 2 > 0 ? price.length() - 2 : 1));
                        } else {
                            material.setPrice(cell.getStringCellValue());
                        }
                    }
                }
            }
            // 添加到list
            materialList.add(material);
        }
        return materialList;
    }

    // Repository
    private List<Repository> readExcelValueRepository(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Repository> repositoryList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Repository repository = new Repository(); // 实体类方式(字段多时建议使用)
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String reptCategory = String.valueOf(cell.getNumericCellValue());
                            repository.setReptCategory(reptCategory.substring(0, reptCategory.length() - 2 > 0 ? reptCategory.length() - 2 : 1));
                        } else {
                            repository.setReptCategory(cell.getStringCellValue());
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String reptId = String.valueOf(cell.getNumericCellValue());
                            repository.setReptId(reptId.substring(0, reptId.length() - 2 > 0 ? reptId.length() - 2 : 1));
                        } else {
                            repository.setReptId(cell.getStringCellValue());
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String position = String.valueOf(cell.getNumericCellValue());
                            repository.setPosition(position.substring(0, position.length() - 2 > 0 ? position.length() - 2 : 1));
                        } else {
                            repository.setPosition(cell.getStringCellValue());
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String num = String.valueOf(cell.getNumericCellValue());
                            repository.setNum(num.substring(0, num.length() - 2 > 0 ? num.length() - 2 : 1));
                        } else {
                            repository.setNum(cell.getStringCellValue());
                        }
                    } else if (c == 4) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String materialId = String.valueOf(cell.getNumericCellValue());
                            repository.setMaterialId(materialId.substring(0, materialId.length() - 2 > 0 ? materialId.length() - 2 : 1));
                        } else {
                            repository.setMaterialId(cell.getStringCellValue());
                        }
                    } else if (c == 5) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String sn = String.valueOf(cell.getNumericCellValue());
                            repository.setSn(sn.substring(0, sn.length() - 2 > 0 ? sn.length() - 2 : 1));
                        } else {
                            repository.setSn(cell.getStringCellValue());
                        }
                    } else if (c == 6) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String bn = String.valueOf(cell.getNumericCellValue());
                            repository.setBn(bn.substring(0, bn.length() - 2 > 0 ? bn.length() - 2 : 1));
                        } else {
                            repository.setBn(cell.getStringCellValue());
                        }
                    }
                }
            }
            // 添加到list
            repositoryList.add(repository);
        }
        return repositoryList;
    }

    // Partner
    private List<Partner> readExcelValuePartner(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        // List<Map<String, Object>> partnerList = new ArrayList<Map<String, Object>>();
        List<Partner> partnerList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            // Map<String, Object> map = new HashMap<String, Object>(); // Hash Map方式
            Partner partner = new Partner(); // 实体类方式(字段多时建议使用)
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String pnCategory = String.valueOf(cell.getNumericCellValue());
                            partner.setPnCategory(pnCategory.substring(0, pnCategory.length() - 2 > 0 ? pnCategory.length() - 2 : 1));
                            // map.put("pnCategory", pnCategory.substring(0, pnCategory.length() - 2 > 0 ? pnCategory.length() - 2 : 1));// 业务种类
                        } else {
                            partner.setPnCategory(cell.getStringCellValue());
                            // map.put("pnCategory", cell.getStringCellValue());// 业务种类
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String company = String.valueOf(cell.getNumericCellValue());
                            partner.setCompany(company.substring(0, company.length() - 2 > 0 ? company.length() - 2 : 1));
                            // map.put("company",company.substring(0, company.length() - 2 > 0 ? company.length() - 2 : 1));// 公司
                        } else {
                            partner.setCompany(cell.getStringCellValue());
                            // map.put("company",cell.getStringCellValue());// 公司
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String pjName = String.valueOf(cell.getNumericCellValue());
                            partner.setPjName(pjName.substring(0, pjName.length() - 2 > 0 ? pjName.length() - 2 : 1));
                            // map.put("pjName", pjName.substring(0, pjName.length() - 2 > 0 ? pjName.length() - 2 : 1));// 项目
                        } else {
                            partner.setPjName(cell.getStringCellValue());
                            // map.put("pjName", cell.getStringCellValue());// 项目
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String name = String.valueOf(cell.getNumericCellValue());
                            partner.setName(name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));
                            // map.put("name", name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));// 姓名
                        } else {
                            partner.setName(cell.getStringCellValue());
                            //  map.put("name", cell.getStringCellValue());// 姓名
                        }
                    }
                }
            }
            // 添加到list
            // partnerList.add(map);
            partnerList.add(partner);
        }
        return partnerList;
    }

    // Linkman
    private List<Linkman> readExcelValueLinkman(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Linkman> linkmanList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Linkman linkman = new Linkman(); // 实体类方式(字段多时建议使用)
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        // 如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String linkId = String.valueOf(cell.getNumericCellValue());
                            linkman.setLinkId(linkId.substring(0, linkId.length() - 2 > 0 ? linkId.length() - 2 : 1));
                        } else {
                            linkman.setLinkId(cell.getStringCellValue());
                        }
                    } else if (c == 1) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String job = String.valueOf(cell.getNumericCellValue());
                            linkman.setJob(job.substring(0, job.length() - 2 > 0 ? job.length() - 2 : 1));
                        } else {
                            linkman.setJob(cell.getStringCellValue());
                        }
                    } else if (c == 2) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String name = String.valueOf(cell.getNumericCellValue());
                            linkman.setName(name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1));
                        } else {
                            linkman.setName(cell.getStringCellValue());
                        }
                    } else if (c == 3) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String tel = String.valueOf(cell.getNumericCellValue());
                            linkman.setTel(tel.substring(0, tel.length() - 2 > 0 ? tel.length() - 2 : 1));
                        } else {
                            linkman.setTel(cell.getStringCellValue());
                        }
                    }else if (c == 4) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String email = String.valueOf(cell.getNumericCellValue());
                            linkman.setEmail(email.substring(0, email.length() - 2 > 0 ? email.length() - 2 : 1));
                        } else {
                            linkman.setEmail(cell.getStringCellValue());
                        }
                    }else if (c == 5) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String qqNum = String.valueOf(cell.getNumericCellValue());
                            linkman.setQqNum(qqNum.substring(0, qqNum.length() - 2 > 0 ? qqNum.length() - 2 : 1));
                        } else {
                            linkman.setQqNum(cell.getStringCellValue());
                        }
                    }else if (c == 6) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String wechatNum = String.valueOf(cell.getNumericCellValue());
                            linkman.setWechatNum(wechatNum.substring(0, wechatNum.length() - 2 > 0 ? wechatNum.length() - 2 : 1));
                        } else {
                            linkman.setWechatNum(cell.getStringCellValue());
                        }
                    }else if (c == 7) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String address = String.valueOf(cell.getNumericCellValue());
                            linkman.setAddress(address.substring(0, address.length() - 2 > 0 ? address.length() - 2 : 1));
                        } else {
                            linkman.setAddress(cell.getStringCellValue());
                        }
                    }
                }
            }
            // 添加到list
            linkmanList.add(linkman);
        }
        return linkmanList;
    }

    /**
     * 验证EXCEL文件
     *
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
