package com.elex.oa.dao.hr_shiyun;

import com.elex.oa.entity.hr_entity.Gzrz;
import com.elex.oa.entity.hr_entity.Lysqd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    String[] selectNamesByDateAndState(@Param("date1") Date date1, @Param("date2") Date date2);

    /**
     *@Author:ShiYun;
     *@Description:根据姓名查询部门名称
     *@Date: 11:02 2018\8\3 0003
     */
    String selectDepnameByTruename(@Param("truename")String truename);

    /**
     *@Author:ShiYun;
     *@Description:根据时间查询和姓名查询工作日志
     *@Date: 11:09 2018\8\3 0003
     */
    List<Gzrz> selectGzrzWriteByDateAndStateAndTruename(@Param("date1") Date date1, @Param("date2") Date date2,@Param("status")String status,@Param("truename")String truename);

    /**
     *@Author:ShiYun;
     *@Description:将OS_USER表中STATUS_字段改为“DEL_JOB”
     *@Date: 16:51 2018\8\31 0031
     */
    void updateOS_USERWenDeleteDimission(String username);
    void updateOS_USERWenDeleteDimission2(String username);

    /**
     *@Author:ShiYun;
     *@Description:查询录用申请单
     *@Date: 14:29 2018\9\28 0028
     */
    List<Lysqd> selectLysqd();

    /**
     *@Author:ShiYun;
     *@Description:将已经录入的人员做标记（将PARENT_ID_字段变成"SUCCESS_END"）
     *@Date: 14:33 2018\9\28 0028
     */
    void updateLysqd(String id);

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询一条录用申请单
     *@Date: 17:06 2018\9\28 0028
     */
    Lysqd selectLysqdById(String id);
}
