package com.imooc.icake.biz;

import com.imooc.icake.entity.Cake;

import java.util.List;

public interface CakeBiz {
    void add(Cake cake);
    void remove(int id);
    void edit(Cake cake);
    Cake get(int id);
    List<Cake> getAll();
}
