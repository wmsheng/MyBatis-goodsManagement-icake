package com.imooc.icake.global;

import com.imooc.icake.biz.CatalogBiz;
import com.imooc.icake.biz.impl.CatalogBizImpl;
import com.imooc.icake.entity.Catalog;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * @author Bennett_Wang on 2019/6/8
 */
public class CatalogTreeListener implements ServletContextListener {
    private CatalogBiz catalogBiz = new CatalogBizImpl();

    public void contextInitialized(ServletContextEvent sce){
        Catalog root = catalogBiz.getRoot();
        sce.getServletContext().setAttribute("root",root);
    }
}
