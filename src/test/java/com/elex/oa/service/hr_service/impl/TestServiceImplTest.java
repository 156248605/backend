package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.restructure_hr.ITestDao;
import com.elex.oa.entity.restructure_hrentity.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\3 0003 15:57
 * @Version 1.0
 **/
@Service
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceImplTest {
    @Resource
    ITestDao iTestDao;

    @org.junit.Test
    public void test(){
        /*Test test = new Test();
        //select接口
        test.setAge("1");
        List<Test> testList = iTestDao.select(test);//根据实体中的属性值进行查询，查询条件使用等号
        System.out.println(testList.toString());
        Test vip = iTestDao.selectOne(test);//根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
        System.out.println(vip);
        List<Test> vipList2 = iTestDao.selectAll();//查询全部结果，select(null)方法能达到同样的效果
        System.out.println(vipList2.toString());
        Test vip2 = iTestDao.selectByPrimaryKey("李四");//根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
        System.out.println(vip2.toString());
         int count = iTestDao.selectCount(test);//根据实体中的属性查询总数，查询条件使用等号
        System.out.println(count);
         //insert接口
        test.setName("smith");
         int a = iTestDao.insert(test);//保存一个实体，null的属性也会保存，不会使用数据库默认值
        System.out.println(a);
        test.setName("tom");
         int a1 = iTestDao.insertSelective(test);//保存实体，null的属性不会保存，会使用数据库默认值
        System.out.println(a1);
         //update接口
        test.setAge("3");
         int b = iTestDao.updateByPrimaryKeySelective(test);//根据主键更新属性不为null的值
        System.out.println(b);
         int c = iTestDao.updateByPrimaryKey(test);//根据主键更新实体全部字段，null值会被更新
        System.out.println(c);
         //delete接口
        test.setTest(null,"3");
        int d = iTestDao.delete(test);//根据实体属性作为条件进行删除，查询条件使用等号
        System.out.println(d);
        int e = iTestDao.deleteByPrimaryKey("张三");//根据主键字段进行删除，方法参数必须包含完整的主键属性
        System.out.println(e);
         //（2）Example方法
         Example example = new Example(Test.class);
         example.createCriteria().andEqualTo("name", "张三");
         example.createCriteria().andLike("age", "1");
         //自定义查询
         List<Test> vipList3 = iTestDao.selectByExample(example);
        System.out.println(vipList3.toString());*/
    }
}    