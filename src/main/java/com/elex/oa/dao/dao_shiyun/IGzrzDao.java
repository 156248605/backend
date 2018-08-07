package com.elex.oa.dao.dao_shiyun;

import com.elex.oa.entity.entity_shiyun.Gzrz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:工作日志的查询
 * @Date:Created in  15:00 2018\8\2 0002
 * @Modify By:
 */
@Mapper
public interface IGzrzDao {
    /**
     *@Author:ShiYun;
     *@Description:查询哪些人有日志
     *@Date: 15:00 2018\8\2 0002
     */
    public String[] selectNamesByDateAndState(@Param("date1") Date date1, @Param("date2") Date date2);

    /**
     *@Author:ShiYun;
     *@Description:根据姓名查询部门名称
     *@Date: 11:02 2018\8\3 0003
     */
    public String selectDepnameByTruename(@Param("truename")String truename);

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询和姓名查询工作日志
     *@Date: 11:09 2018\8\3 0003
     */
    public List<Gzrz> selectGzrzWriteByDateAndStateAndTruename(@Param("date1") Date date1, @Param("date2") Date date2,@Param("status")String status,@Param("truename")String truename);
}
