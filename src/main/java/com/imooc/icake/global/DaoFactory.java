package com.imooc.icake.global;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author Bennett_Wang on 2019/5/31
 */
public class DaoFactory {
    private static DaoFactory daoFactory;
    private SqlSessionFactory sessionFactory;
    private DaoFactory(){
        SqlSessionFactoryBuilder sessionFactoryBuilder = new SqlSessionFactoryBuilder();
        try {
            sessionFactory =  sessionFactoryBuilder.build(Resources.getResourceAsReader("/mybatis.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DaoFactory getInstance() {
        if (daoFactory == null)
            daoFactory = new DaoFactory();
        return daoFactory;
    }
    public <T> T getDao(Class<T> tClass){
        return sessionFactory.openSession(true).getMapper(tClass);
    }
}
