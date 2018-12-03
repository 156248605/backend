package com.elex.oa.dao.restructure_hr;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.restructure_hrentity.Test;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\3 0003 15:51
 * @Version 1.0
 **/
@Mapper
public interface ITestDao extends BaseDao<Test> {
}