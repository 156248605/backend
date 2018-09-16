package com.elex.oa.dao.project;

import com.elex.oa.entity.project.NProjectQuery;
import com.elex.oa.entity.project.NewProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewProjectDao {
    //保存新建的项目信息
    int insertNewProject(NewProject newProject);
    //对某人已建项目进行列表查询
    List<NewProject> listQuery(NProjectQuery nProjectQuery);
    //获取表头部分select中的内容
    List<NewProject> selectData(String proposer);
}
