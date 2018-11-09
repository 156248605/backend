package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.HRset;
import com.github.pagehelper.PageInfo;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * @Description: HRsetController层的测试代码
 * @Author shiyun
 * @Date 2018\11\9 0009 9:22
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HRsetControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1_addOneHRset(){
        String rest = restTemplate.postForObject("/hrset/addOneHRset",new HRset("race","维吾尔族"), String.class);
        Assertions.assertThat(rest).isEqualTo("添加成功！");
    }

    @Test
    public void test2_queryAllHRset(){
        List<HRset> hRsetList = restTemplate.postForObject("/hrset/queryAllHRset", new HRset("race"), List.class);
        Assertions.assertThat(hRsetList).isNotNull();
        Assertions.assertThat(hRsetList.size()).isEqualTo(5);
    }

    @Test
    public void test3_queryHRsetPageInfo(){
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("datatype", "race");
        paramMap.add("page",1);
        paramMap.add("rows",5);
        PageInfo<HRset> result = restTemplate.postForObject("/hrset/queryHRsetPageInfo", paramMap, PageInfo.class);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTotal()).isEqualTo(5);
    }

    @Test
    public void test4_queryValidateHRset(){
        Boolean result = restTemplate.postForObject("/hrset/queryValidateHRset", new HRset("race", "维吾尔族"), Boolean.class);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void test5_removeHRset(){
        List<Object> ids = new ArrayList<Object>();
        ids.add(1);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap();
        map.put("ids", ids);
        String s = restTemplate.postForObject("/hrset/removeHRset",map, String.class);
        Assertions.assertThat(map.size()).isEqualTo(1);
        Assertions.assertThat(map.get(1)).isEqualTo("删除成功！");
    }

    @Test
    public void test6_modifyHRset(){
        //使用@RequestBody即可绑定对象或者List
        String res = restTemplate.postForObject("/hrset/modifyHRset", new HRset(2, "新疆维吾尔族"), String.class);
        Assertions.assertThat(res).isEqualTo("修改成功！");
    }
}    