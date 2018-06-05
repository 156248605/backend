package com.elex.oa.controller.eqptController;

import com.elex.oa.service.eqptService.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin
@RequestMapping("/excel")
public class ExcelController {

    @Resource
    private ImportService importService;

    //导入excel
    @RequestMapping("/importMaterial")
    @ResponseBody
    public Map<String, Object> importExcelMaterial(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String result = importService.readExcelFileMaterial(file);
        map.put("message", result);
        return map;
    }

    @RequestMapping("/importRepository")
    @ResponseBody
    public Map<String, Object> importExcelRepository(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String result = importService.readExcelFileRepository(file);
        map.put("message", result);
        return map;
    }

    @RequestMapping("/importPartner")
    @ResponseBody
    public Map<String, Object> importExcelPartner(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String result = importService.readExcelFilePartner(file);
        map.put("message", result);
        return map;
    }

    @RequestMapping("/importLinkman")
    @ResponseBody
    public Map<String, Object> importExcelLinkman(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String result = importService.readExcelFileLinkman(file);
        map.put("message", result);
        return map;
    }
}
