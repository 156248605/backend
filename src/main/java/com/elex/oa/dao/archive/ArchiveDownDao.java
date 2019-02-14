package com.elex.oa.dao.archive;

import com.elex.oa.entity.archive.ArchiveDown;
import com.elex.oa.entity.archive.ArchiveDownQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArchiveDownDao {
    //条件查询文档列表
    List<ArchiveDown> obtainList(ArchiveDownQuery archiveDownQuery);
    //添加文档上传记录
    void addArchive(ArchiveDown archiveDown);
    //删除文档
    void deleteArchive(String fileName);
}
