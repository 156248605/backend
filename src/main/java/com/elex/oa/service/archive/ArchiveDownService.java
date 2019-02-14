package com.elex.oa.service.archive;

import com.elex.oa.entity.archive.ArchiveDown;
import com.elex.oa.entity.archive.ArchiveDownQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ArchiveDownService {
    //条件查询文档列表
    PageInfo<ArchiveDown> obtainList(ArchiveDownQuery archiveDownQuery, int currentPage);
    //上传文档
    String uploadArchive(MultipartFile file, String taddy);
    //下载文档
    String downloadArchive(String fileName, HttpServletResponse response);
    //删除文档
    String deleteArchive(String fileName);
}
