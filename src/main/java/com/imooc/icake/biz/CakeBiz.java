package com.imooc.icake.biz;

import com.imooc.icake.entity.Cake;

import java.util.List;

public interface CakeBiz {
    void add(Cake cake);
    void remove(int id);
    void edit(Cake cake);
    Cake get(int id);
    List<Cake> getAll();

    //获取单个推荐商品
    Cake getSpecial();
    //获取特卖场品列表
    List<Cake> getForIndex();
    //为分类获取商品列表
    List<Cake> getForCatalog(int cid);
}
