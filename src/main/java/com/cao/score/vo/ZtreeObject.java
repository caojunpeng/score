package com.cao.score.vo;

import java.util.List;

public class ZtreeObject {
    private String name;
    private Integer parentId;
    private Integer id;
    private boolean checked;
    private List<ZtreeObject> children;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ZtreeObject> getChildren() {
        return children;
    }

    public void setChildren(List<ZtreeObject> children) {
        this.children = children;
    }

    public ZtreeObject(String name, Integer parentId, Integer id, boolean checked, List<ZtreeObject> children) {
        this.name = name;
        this.parentId = parentId;
        this.id = id;
        this.checked = checked;
        this.children = children;
    }

    public ZtreeObject() {
    }
}
