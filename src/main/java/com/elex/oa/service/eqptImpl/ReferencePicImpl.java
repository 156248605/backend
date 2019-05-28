package com.elex.oa.service.eqptImpl;

import com.elex.oa.service.eqptService.ReferencePicService;
import com.elex.oa.util.hr_util.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReferencePicImpl implements ReferencePicService {

    private static Logger logger = LoggerFactory.getLogger(ReferencePicImpl.class);

    @Resource
    private AppProperties appProperties;

    @Override
    public List saveReferencePic(HttpServletRequest request) {
        String path = appProperties.getProperty("reference.realpath") + "/" + request.getParameter("materialId") + "Pic";
        String realPath = path.replace("../","").replace("./","");
        File file = new File(realPath);
        if (!file.exists()) {
            try {
                file.mkdirs();
            }catch (Exception e){
                logger.info(e.getMessage());
            }
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = null;
        // 循环遍历，取出单个文件
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile multipartFile = entity.getValue();
            // 获得原始文件名
            fileName = multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(realPath + "/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public List showReferencePic(HttpServletRequest request) {
        String path = appProperties.getProperty("reference.realpath") + "/" + request.getParameter("materialId") + "Pic";
        String realPath = path.replace("../","").replace("./","");
        List list = new ArrayList();
        try {
            File file = new File(realPath);
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                list.add(filelist[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String deleteReferencePic(HttpServletRequest request) {
        String fileName = request.getParameter("fileName");
        String path = appProperties.getProperty("reference.realpath") + fileName;
        String realPath = path.replace("../","").replace("./","");
        File file = new File(realPath);
        if (file.exists()) {
            try {
                file.delete();
            }catch (Exception e){
                logger.info(e.getMessage());
            }
            return "success";
        } else {
            return "error";
        }
    }
}