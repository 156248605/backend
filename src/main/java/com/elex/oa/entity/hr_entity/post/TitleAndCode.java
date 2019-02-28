package com.elex.oa.entity.hr_entity.post;

import java.io.Serializable;

/**
 * @Author:ShiYun;
 * @Description:名称和顺序码
 * @Date:Created in  16:58 2018\6\15 0015
 * @Modify By:
 */
public class TitleAndCode implements Serializable{
    private static final long serialVersionUID = 49954826452669337L;
    private String title;
    private Integer code;//排序吗
    private String postcode;
    private Integer id;

    public TitleAndCode() {
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TitleAndCode{" +
                "title='" + title + '\'' +
                ", code=" + code +
                '}';
    }
}
