package com.imooc.icake.biz;

import com.imooc.icake.entity.Catalog;

import java.util.List;

public interface CatalogBiz {
    void add(List<Catalog> list);
    void remove(int id);
    Catalog getRoot();
}
