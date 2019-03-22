package com.elex.oa.service.business;

import com.elex.oa.entity.business.Clue;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IClueService {
    PageInfo<Clue> getPageInfoByCondition(Integer pageNum, Integer pageSize, Clue clue, String flag);

    Object addClueInfo(Clue clue);

    Clue getDetailClueinfo(String cluecode);

    Boolean modifyClueInfo(Clue clue);

    Object closeClueInfo(String cluecode, String trackcontent, String username);

    String getUpdateTime (HttpServletRequest request);

    List getRelativeEvent (HttpServletRequest request);
}
