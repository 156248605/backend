package com.elex.oa.service;

import com.elex.oa.entity.SysTree;

import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/14 9:33
*/
public interface ISysTreeService extends BaseService<SysTree> {
    //根据treeId查询表单分类
    public SysTree getTreeById(String id);

    public List<SysTree> selectByCatKey(Map<String,String> map);
    //保存表单分类
    public int addFormCategory(String formCategoryName,String formCategoryLabelKey,String formCategoryCode,String formCategoryNumber,String formCategoryDesc,String parentId,String parentDepth,String type);
    //删除表单分类
    public int deleteFormCategory(String id);
}
