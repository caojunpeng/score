package com.cao.score.entity;

import java.io.Serializable;

/**
 * (GradeClass)实体类
 *
 * @author makejava
 * @since 2022-03-02 14:44:54
 */
public class GradeClass implements Serializable {
    private static final long serialVersionUID = -99803352984449903L;

    private Long id;
    /**
     * 班级
     */
    private Integer classNum;
    /**
     * 年级
     */
    private Integer gradeNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

}