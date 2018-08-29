package com.elex.oa.dao.objectives;

import com.elex.oa.entity.objectives.Goal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatentDao {
    //根据年度查询发明专利信息
    Goal queryData(Goal goal);
    //删除发明专利信息
    void deleteData(Goal goal);
    //保存发明专利信息
    void saveData(Goal goal);
}
