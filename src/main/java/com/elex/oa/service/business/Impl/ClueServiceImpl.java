package com.elex.oa.service.business.Impl;

import com.elex.oa.entity.business.Clue;
import com.elex.oa.service.business.IClueService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:32
 * @Version 1.0
 **/
@Service
public class ClueServiceImpl implements IClueService {


    @Override
    public PageInfo<Clue> getPageInfoByCondition(Integer pageNum, Integer pageSize, Clue clue) {
        return null;
    }
}