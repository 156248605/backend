package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.HRset;
import com.github.pagehelper.PageInfo;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: HRsetService测试
 * @Author shiyun
 * @Date 2018\11\8 0008 9:59
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HRsetTests {
    @Autowired
    private IHRsetService ihRsetService;

    @Test
    public void test1_addOne() {
//        ihRsetService.addOne(new HRset("race", "藏族"));
//        List<HRset> hRsetList = ihRsetService.queryByConditions(new HRset("race", "藏族"));
//        Assert.assertNotNull("test1_addOne hRsetList is null", hRsetList);
//        Assert.assertEquals("hRsetList.size is wrong", 1, hRsetList.size());
    }

    @Test
    public void test2_queryAll() {
//        List<HRset> hRsetList = ihRsetService.queryAll();
//        Assert.assertNotNull("test2_queryAll hRsetList is null", hRsetList);
//        Assert.assertEquals("test2_queryAll hRsetList.size is wrong", 3, hRsetList.size());
    }

    @Test
    public void test3_queryByConditions() {
//        List<HRset> hRsetList = ihRsetService.queryByConditions(new HRset("race"));
//        Assert.assertNotNull("test3_queryByConditions hRsetList is null", hRsetList);
//        Assert.assertEquals("test3_queryByConditions hRsetList.size is wrong", 3, hRsetList.size());
    }

    @Test
    public void test4_queryById() {
//        HRset hRset = ihRsetService.queryById(1);
//        Assert.assertNotNull("test4_queryById hRset is null", hRset);
    }

    @Test
    public void test5_queryByParam() {
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("pageNum", 1);
//        paramMap.put("pageSize", 5);
//        paramMap.put("entity", new HRset("race"));
//        PageInfo pageInfo = ihRsetService.queryByParam(paramMap);
//        Assert.assertEquals("test5_queryByParam pageInfo.total is wrong", 3, pageInfo.getTotal());
    }

    @Test
    public void test6_removeOne() {
//        Boolean res = ihRsetService.removeOne(1);
//        Assert.assertTrue("test6_removeOne delete fail", res);
    }

    @Test
    public void test7_modifyOne() {
//        HRset hRset = ihRsetService.modifyOne(new HRset(2, "汉族"));
//        Assert.assertNotNull("test7_modifyOne hRset is null", hRset);
//        Assert.assertEquals("test7_modifyOne hRset.datavalue", "汉族", hRset.getDatavalue());
    }

    @Test
    public void test8_modifyHRset() {
//        Boolean aBoolean = ihRsetService.modifyHRset(new HRset(2, "藏族"));
//        Assertions.assertThat(aBoolean).isTrue();
    }

    @Test
    public void test9_removeMultiple() {
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        Map<Integer, String> map = ihRsetService.removeMultiple(ids);
//        Assertions.assertThat(map.size()).isEqualTo(2);
//        Assertions.assertThat(map.get(1)).isEqualTo("删除成功！");
//        Assertions.assertThat(map.get(2)).isEqualTo("删除成功！");
    }

    @Test
    public void test10_queryValidateHRset(){
//        Boolean rset1 = ihRsetService.queryValidateHRset(new HRset("race", "汉族"));
//        Assertions.assertThat(rset1).isTrue();
//        Boolean rset2 = ihRsetService.queryValidateHRset(new HRset("race", "蒙古族"));
//        Assertions.assertThat(rset2).isTrue();
    }
}    