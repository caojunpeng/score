package com.cao.score.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试场次(ScoreNumber)实体类
 *
 * @author makejava
 * @since 2022-04-11 10:45:31
 */
public class ScoreNumber implements Serializable {
    private static final long serialVersionUID = -70866345139663199L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 考试编号
     */
    private String scoreNum;
    /**
     * 场次名称
     */
    private String scoreName;
    /**
     * 考试日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date scoreTime;
    private String scoreTimeStr;

    public String getScoreTimeStr() {
        return scoreTimeStr;
    }

    public void setScoreTimeStr(String scoreTimeStr) {
        this.scoreTimeStr = scoreTimeStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

}