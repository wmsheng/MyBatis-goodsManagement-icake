package com.imooc.icake.biz.impl;

import com.imooc.icake.biz.CakeBiz;
import com.imooc.icake.dao.CakeDao;
import com.imooc.icake.entity.Cake;
import com.imooc.icake.global.DaoFactory;

import java.util.List;

/**
 * @author Bennett_Wang on 2019/6/7
 */
public class CakeBizImpl implements CakeBiz {
    CakeDao cakeDao = DaoFactory.getInstance().getDao(CakeDao.class);

    public void add(Cake cake) {
        cakeDao.insert(cake);
    }

    public void remove(int id) {
        cakeDao.delete(id);
    }

    public void edit(Cake cake) {
        cakeDao.update(cake);
    }

    public Cake get(int id) {
        return cakeDao.select(id);
    }

    public List<Cake> getAll() {
        return cakeDao.selectAll();
    }
}
