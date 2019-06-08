package com.imooc.icake.controller;

import com.imooc.icake.biz.CatalogBiz;
import com.imooc.icake.biz.impl.CatalogBizImpl;
import com.imooc.icake.entity.Catalog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bennett_Wang on 2019/6/5
 */
public class CatalogController {
    //声明业务处理对象
    private CatalogBiz catalogBiz = new CatalogBizImpl();

    //用户可能有的操作方法有这四个：list.do、toAdd.do、add.do、remove.do
    //打开分类列表：/admin/Catalog/list.do
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Catalog root = catalogBiz.getRoot();
        request.getServletContext().setAttribute("root", root);
        request.getRequestDispatcher("/WEB-INF/pages/admin/catalog_list.jsp").forward(request, response);
    }

    //打开添加界面：/admin/Catalog/toAdd.do
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Catalog root = catalogBiz.getRoot();
        request.setAttribute("root", root);
        request.getRequestDispatcher("/WEB-INF/pages/admin/catalog_add.jsp").forward(request, response);
    }

    //实际添加处理,处理toAdd里面填充的数据：/admin/Catalog/add.do
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] titles = request.getParameterValues("title");
        String[] pids = request.getParameterValues("pid");
        String[] infos = request.getParameterValues("info");
        //用这三个内容组装成Catalog，弄成集合数组，传到页面里
        List<Catalog> list = new ArrayList<Catalog>();
        //这三个数组的长度一定是一样的，因为是一起传入的
        for (int i = 0; i < titles.length; i++) {
            Catalog catalog = new Catalog();
            catalog.setTitle(titles[i]);
            catalog.setPid(Integer.parseInt(pids[i]));
            catalog.setInfo(infos[i]);
            list.add(catalog);
        }
        catalogBiz.add(list);
        response.sendRedirect("list.do");

    }

    //删除：/admin/Catalog/remove.do
    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        catalogBiz.remove(id);
        response.sendRedirect("list.do");
    }


}
