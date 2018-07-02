package com.elex.oa.dao.project;

import com.elex.oa.entity.project.AListQuery;
import com.elex.oa.entity.project.ApprovalList;
import com.elex.oa.entity.project.ProjectInfor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListingDao {

    //审批清单列表查询
    List<ApprovalList> listQuery(AListQuery aListQuery);
    //在已通过审批的项目中查询某人主管的项目编号
    List<String> queryCodeByName(String name);
    //根据编号查询某已通过审批的项目基本信息
    ApprovalList queryContentByCode(String projectCode);
    //修改已审批清单中的信息
    int modifyAppListContent(ProjectInfor projectInfor);
}
