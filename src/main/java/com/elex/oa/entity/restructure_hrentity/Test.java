package com.elex.oa.entity.restructure_hrentity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\3 0003 15:51
 * @Version 1.0
 **/
@Table(name = "tb_id_test")
public class Test implements Serializable {
    private static final long serialVersionUID = 8286235329186118984L;
    @Id
    private String name;
    private String age;

    public Test() {
    }

    public void setTest(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}