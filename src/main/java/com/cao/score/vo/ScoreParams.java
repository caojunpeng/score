package com.cao.score.vo;

public class ScoreParams {
    /**
     * 年级
     */
    private Integer gradeNum;
    /**
     * 班级
     */
    private Integer classNum;

    /**
     * 学号
     */
    private String studentId;
    /**
     * 姓名
     */
    private String name;

    /**
     * 语文成绩
     */
    private double chineseScore;

    /**
     * 数学成绩
     */
    private double mathScore;

    /**
     * 英语成绩
     */
    private double englishScore;

    /**
     * 政治成绩
     */
    private double politicsScore;

    /**
     * 历史成绩
     */
    private double historyScore;

    /**
     * 地理成绩
     */
    private double geographyScore;

    /**
     * 生物成绩
     */
    private double biologicalScore;

    /**
     * 物理成绩
     */
    private double physicalScore;

    /**
     * 化学成绩
     */
    private double chemicalScore;

    /**
     * 科目
     */
    private Integer subject;

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(double chineseScore) {
        this.chineseScore = chineseScore;
    }

    public double getMathScore() {
        return mathScore;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public double getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(double englishScore) {
        englishScore = englishScore;
    }

    public double getPoliticsScore() {
        return politicsScore;
    }

    public void setPoliticsScore(double politicsScore) {
        this.politicsScore = politicsScore;
    }

    public double getHistoryScore() {
        return historyScore;
    }

    public void setHistoryScore(double historyScore) {
        this.historyScore = historyScore;
    }

    public double getGeographyScore() {
        return geographyScore;
    }

    public void setGeographyScore(double geographyScore) {
        this.geographyScore = geographyScore;
    }

    public double getBiologicalScore() {
        return biologicalScore;
    }

    public void setBiologicalScore(double biologicalScore) {
        this.biologicalScore = biologicalScore;
    }

    public double getPhysicalScore() {
        return physicalScore;
    }

    public void setPhysicalScore(double physicalScore) {
        this.physicalScore = physicalScore;
    }

    public double getChemicalScore() {
        return chemicalScore;
    }

    public void setChemicalScore(double chemicalScore) {
        this.chemicalScore = chemicalScore;
    }
}
