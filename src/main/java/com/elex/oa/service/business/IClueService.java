package com.elex.oa.service.business;

import com.elex.oa.entity.business.Clue;
import com.github.pagehelper.PageInfo;

public interface IClueService {
    PageInfo<Clue> getPageInfoByCondition(Integer pageNum,Integer pageSize,Clue clue);

    Boolean addClueInfo(Clue clue);
}
