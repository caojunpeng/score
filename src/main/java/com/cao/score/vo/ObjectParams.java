package com.cao.score.vo;

public class ObjectParams extends DataTablesParams {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * user。id
     */
    private Long userId;

    /**
     * role。id
     */
    private Long roleId;

    /**
     * menu。id
     */
    private Long menuId;
    private Integer sex;//性别
    private Integer gradeNum;//年级
    private Integer classNum;//班级
    private Integer subject;//科目

    private boolean exportType;//导出状态 true为导出

    public boolean isExportType() {
        return exportType;
    }

    public void setExportType(boolean exportType) {
        this.exportType = exportType;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    /**
     * 学号
     */
    private String studentId;

    /**
     * 考试编号
     */
    private String scoreNum;

    public String getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
