package com.elex.oa.controller.archive;

import com.elex.oa.entity.archive.ArchiveDownQuery;
import com.elex.oa.service.archive.ArchiveDownService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequestMapping("/archive")
public class ArchiveDownController {

    @Resource
    private ArchiveDownService archiveDownService;

    //条件查询文档列表
    @RequestMapping("/obtain_list")
    @ResponseBody
    public PageInfo obtainList(ArchiveDownQuery archiveDownQuery, int currentPage) {
        return archiveDownService.obtainList(archiveDownQuery, currentPage);
    }

    //上传文档
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadArchive(@RequestParam("file")MultipartFile file, @RequestParam("taddy")String taddy) {
        return archiveDownService.uploadArchive(file, taddy);
    }

    //下载文档
    @RequestMapping("/download")
    @ResponseBody
    public String downloadArchive(String fileName, HttpServletResponse response) {
        return archiveDownService.downloadArchive(fileName, response);
    }

    //删除文档
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteArchive(String fileName) {
        return archiveDownService.deleteArchive(fileName);
    }
}
