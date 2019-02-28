package com.elex.oa.service.business;

import com.elex.oa.entity.business.Clue;
import com.github.pagehelper.PageInfo;

public interface IClueService {
    PageInfo<Clue> getPageInfoByCondition(Integer pageNum, Integer pageSize, Clue clue, String flag);

    Object addClueInfo(Clue clue);

    Clue getDetailClueinfo(String cluecode);

    Boolean modifyClueInfo(Clue clue);

    Boolean closeClueInfo(String cluecode);
}
