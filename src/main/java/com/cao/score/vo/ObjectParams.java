package com.cao.score.vo;

public class ObjectParams extends DataTablesParams {
    /**
     * 关键字
     */
    private String keyword;
    /**
     * id
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
