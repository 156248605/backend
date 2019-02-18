package com.elex.oa.service.archive.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.archive.ArchiveDownDao;
import com.elex.oa.entity.archive.ArchiveDown;
import com.elex.oa.entity.archive.ArchiveDownQuery;
import com.elex.oa.service.archive.ArchiveDownService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArchiveDownImpl implements ArchiveDownService {

    @Resource
    private ArchiveDownDao archiveDownDao;

    //条件查询文档列表
    @Override
    public PageInfo<ArchiveDown> obtainList(ArchiveDownQuery archiveDownQuery, int currentPage) {
        List<String> timeList = JSONArray.parseArray(archiveDownQuery.getPostTime(), String.class); //获取时间
        if(timeList == null) {

        } else {
            if(timeList.size() > 0) {
                if(StringUtils.isNotBlank(timeList.get(0))) {
                    archiveDownQuery.setPostTimeA(timeList.get(0));
                    archiveDownQuery.setPostTimeB(timeList.get(1));
                }
            }
        }
        PageHelper.startPage(currentPage, 10);
        List<ArchiveDown> list = archiveDownDao.obtainList(archiveDownQuery);
        return new PageInfo<ArchiveDown>(list);
    }

    //上传文档
    @Override
    public String uploadArchive(MultipartFile file, String taddy) {
        String fileName = file.getOriginalFilename();
        if(fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        String filePath = "/usr/local/static/archive/";
        /*String filePath = "D:\\archive/";*/
        File targetFile = new File(filePath);
        if(!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(filePath + fileName);
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        ArchiveDown archiveDown = new ArchiveDown();
        archiveDown.setArchiveName(fileName);
        archiveDown.setTaddy(taddy);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        archiveDown.setPostTime(date);
        archiveDownDao.addArchive(archiveDown);
        return "success";
    }

    //下载文档
    @Override
    public String downloadArchive(String fileName, HttpServletResponse response) {
        if(StringUtils.isNotBlank(fileName)) {
            //设置文件路径
            File file = new File("/usr/local/static/archive/", fileName);
            if(file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                try {
                    response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(), "iso-8859-1"));// 设置文件名
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "success";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "failure";
    }

    //删除文档
    @Override
    public String deleteArchive(String fileName) {

        File file = new File("/usr/local/static/archive/"+fileName);
        if(file.exists()) {
            if(file.isFile()) {
                file.delete();
                archiveDownDao.deleteArchive(fileName);//删除记录
                return "success";
            }
        }
        return "failure";
    }
}
