package com.imooc.icake.entity;

import org.apache.taglibs.standard.tag.common.core.CatchTag;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于分类管理
 * @author Bennett_Wang on 2019/5/31
 */
public class Catalog {
    private int id;
    private String title;
    private int pid;
    private String info;

    //当前类关联的所有的子类
    List<Catalog> children = new ArrayList<Catalog>();

    public List<Catalog> getChildren(){
        return children;
    }

    public void setChildren(List<Catalog> children){
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
