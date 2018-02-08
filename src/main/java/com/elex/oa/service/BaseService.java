package com.elex.oa.service;
import java.util.List;

public interface BaseService<T> {
    int save(T entity);

    int deleteById(Object id);

    int update(T entity);
    List<T> selectByCondition(T entity);

    T getById(Object id);
    List<T> selectPage(int pageNum, int pageSize);
    
    T selectOne(T entity);
    
    List<T> selectAll();
}
