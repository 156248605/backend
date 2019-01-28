package com.elex.oa.entity.business;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\2\19 0019 16:25
 * @Version 1.0
 **/
@Table(name = "tb_hr_sequence")
public class Sequence {
    @Id
    private String id;//主键
    private String sequence_name;//索引名称
    private Integer sequence_value;//索引值
    @Column(name = "`current_time`")
    private String current_time;//当前年月值格式（YYYY-MM）

    public Sequence() {
    }

    public Sequence(String sequence_name) {
        this.sequence_name = sequence_name;
    }

    public Sequence(String id, String sequence_name, Integer sequence_value, String current_time) {
        this.id = id;
        this.sequence_name = sequence_name;
        this.sequence_value = sequence_value;
        this.current_time = current_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequence_name() {
        return sequence_name;
    }

    public void setSequence_name(String sequence_name) {
        this.sequence_name = sequence_name;
    }

    public Integer getSequence_value() {
        return sequence_value;
    }

    public void setSequence_value(Integer sequence_value) {
        this.sequence_value = sequence_value;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "id='" + id + '\'' +
                ", sequence_name='" + sequence_name + '\'' +
                ", sequence_value=" + sequence_value +
                ", current_time='" + current_time + '\'' +
                '}';
    }
}