package com.cao.score.entity;

import java.io.Serializable;

/**
 * (ScoreRanking)实体类
 *
 * @author makejava
 * @since 2022-04-28 09:46:39
 */
public class ScoreRanking implements Serializable {
    private static final long serialVersionUID = 606358642692216120L;
    
    private Long id;
    /**
     * 学生编号
     */
    private String studentId;
    /**
     * 学生总分
     */
    private Double scoreSum;
    /**
     * 年级排名
     */
    private Integer gradeRanking;
    /**
     * 班级排名
     */
    private Integer classRanking;
    /**
     * 考试场次
     */
    private String scoreNum;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(Double scoreSum) {
        this.scoreSum = scoreSum;
    }

    public Integer getGradeRanking() {
        return gradeRanking;
    }

    public void setGradeRanking(Integer gradeRanking) {
        this.gradeRanking = gradeRanking;
    }

    public Integer getClassRanking() {
        return classRanking;
    }

    public void setClassRanking(Integer classRanking) {
        this.classRanking = classRanking;
    }

    public String getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
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

