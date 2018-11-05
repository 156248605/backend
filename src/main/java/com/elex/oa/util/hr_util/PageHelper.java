package com.elex.oa.util.hr_util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:分页的框架模仿
 * @Date:Created in  11:25 2018\4\8 0008
 * @Modify By:
 */
public class PageHelper<T> {
    private Integer total;
    private List<T> list = new ArrayList<T>();
    private List<T> allList = new ArrayList<T>();

    public PageHelper() {
    }

    public PageHelper(Integer pageNum, Integer pageSize, List<T> list) {
        List<T> objs = new ArrayList<T>();
        int l = 0;
        if(list.size()-(pageNum-1)*pageSize >= pageSize){
            l = pageNum*pageSize;
        }else {
            l = list.size();
        }
        for(int i=(pageNum-1)*pageSize;i<l;i++ ){
            T t = list.get(i);
            objs.add(t);
        }
        this.list = objs;
        this.total = list.size();
        this.allList = list;
    }

    public List<T> getAllList() {
        return allList;
    }

    public void setAllList(List<T> allList) {
        this.allList = allList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
