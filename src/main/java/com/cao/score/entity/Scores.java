package com.cao.score.entity;

import java.io.Serializable;

/**
 * 成绩表(Scores)实体类
 *
 * @author makejava
 * @since 2022-01-28 10:33:14
 */
public class Scores implements Serializable {
    private static final long serialVersionUID = -18177979129015206L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 分数
     */
    private Double score;
    /**
     * 科目
     */
    private Integer subject;
    /**
     * 合格状态
     */
    private Integer qualifiedState;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(Integer qualifiedState) {
        this.qualifiedState = qualifiedState;
    }

}