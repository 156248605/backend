package com.elex.oa.service.impl;

import com.elex.oa.dao.IBpmFormViewDao;
import com.elex.oa.entity.BpmFormView;
import com.elex.oa.service.IBpmFormViewService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/15 11:31
*/
@Service
public class BpmFormViewServiceImpl extends BaseServiceImpl<BpmFormView> implements IBpmFormViewService {
    @Autowired
    private IBpmFormViewDao bpmFormViewDao;

    //查询表单数据
    public PageInfo<BpmFormView> getBpmFormView(Map<String,Object> paramMap){
        //页码
        int page = Integer.parseInt(paramMap.get("page").toString());
        //每页显示记录数
        int rows = Integer.parseInt(paramMap.get("rows").toString());

        PageHelper.startPage(page,rows);

        List<BpmFormView> list = new ArrayList<BpmFormView>();
        list = bpmFormViewDao.getAllBpmFormView(paramMap);

        return new PageInfo<BpmFormView>(list);
    }


}
