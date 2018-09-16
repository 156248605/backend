package com.elex.oa.service.impl;

import com.elex.oa.service.BaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by piaomiao on 2017/3/14.
 * 通用mapper,包括基本的CURD操作
 *
 *
 */

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private Mapper<T> mapper;

    @Override
    public int save(T entity) {
        return mapper.insertSelective(entity);
    }

    @Override
    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByCondition(T entity){return  mapper.select(entity);};
    @Override
    public T getById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectAll();
    }
    
    @Override
    public T selectOne(T entity)
    {
    	return mapper.selectOne(entity);
    }
    
    @Override
    public List<T> selectAll()
    {
    	return mapper.selectAll();
    }
    
}

