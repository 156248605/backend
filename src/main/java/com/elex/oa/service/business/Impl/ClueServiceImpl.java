package com.elex.oa.service.business.Impl;

import com.elex.oa.dao.business.IClueDao;
import com.elex.oa.dao.business.ITrackInfoDao;
import com.elex.oa.entity.business.Clue;
import com.elex.oa.entity.business.TrackInfo;
import com.elex.oa.service.business.IClueService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\5 0005 16:32
 * @Version 1.0
 **/
@Service
public class ClueServiceImpl implements IClueService {
    @Resource
    IClueDao iClueDao;
    @Resource
    ITrackInfoDao iTrackInfoDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;

    @Override
    public PageInfo<Clue> getPageInfoByCondition(Integer pageNum, Integer pageSize, Clue clue) {
        PageHelper.startPage(pageNum,pageSize);
        List<Clue> clueList = iClueDao.select(clue);
        for (Clue c:clueList
             ) {
            c = getClueByClue(c);
        }
        return new PageInfo<Clue>(clueList);
    }

    private Clue getClueByClue(Clue c) {
        if(null==c)return null;
        //获得最新的跟踪描述
        c.setTrackcontent(hrUtilsTemp.getTrackcontentByTrackid(c.getTrackid()));
        //获得销售人姓名
        c.setSale_truename(hrUtilsTemp.getTruenameByEmployeenumber(c.getSale_employeenumber()));
        //获得方案人姓名
        c.setScheme_truename(hrUtilsTemp.getTruenameByEmployeenumber(c.getScheme_employeenumber()));
        return c;
    }


}