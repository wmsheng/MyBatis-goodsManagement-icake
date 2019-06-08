package com.imooc.icake.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.icake.biz.CakeBiz;
import com.imooc.icake.biz.CatalogBiz;
import com.imooc.icake.biz.impl.CakeBizImpl;
import com.imooc.icake.biz.impl.CatalogBizImpl;
import com.imooc.icake.entity.Cake;
import com.imooc.icake.entity.Catalog;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Bennett_Wang on 2019/6/7
 */
public class CakeController {

    //实例化商品处理对象:CakeBiz
    private CakeBiz cakeBiz = new CakeBizImpl();
    //分类的业务处理对象：CatalogBiz
    private CatalogBiz catalogBiz = new CatalogBizImpl();

    // /admin/Cake/list.do
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) pageNum="1";
        PageHelper.startPage(Integer.parseInt(pageNum), 20);
        List<Cake> list = cakeBiz.getAll();
        PageInfo pageInfo = PageInfo.of(list);
        request.setAttribute("pageInfo",pageInfo);
        request.getRequestDispatcher("/WEB-INF/pages/admin/cake_list.jsp").forward(request,response);
    }

    // /admin/Cake/toAdd.do
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/admin/cake_add.jsp").forward(request,response);
    }
    // /admin/Cake/add.do
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
        Cake cake = this.builderCake(request);
        cakeBiz.add(cake);
        response.sendRedirect("list.do");
    }
    // /admin/Cake/toEdit.do
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cake cake = cakeBiz.get(id);
        request.setAttribute("cake",cake);
        request.getRequestDispatcher("/WEB-INF/pages/admin/cake_edit.jsp").forward(request,response);
    }
    // /admin/Cake/edit.do
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
        Cake cake = this.builderCake(request);
        cakeBiz.edit(cake);
        response.sendRedirect("list.do");
    }
    private Cake builderCake(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
        Cake cake = new Cake();
        cake.setTitle(request.getParameter("title"));
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(request);
        for(FileItem item:list){
            if (item.isFormField()){
                //先设置普通的属性
                if (item.getFieldName().equals("title"))
                    cake.setTitle(item.getString("UTF-8"));
                if (item.getFieldName().equals("status"))
                    cake.setStatus(item.getString("UTF-8"));
                if (item.getFieldName().equals("cid"))
                    cake.setCid(Integer.parseInt(item.getString("UTF-8")));
                if (item.getFieldName().equals("taste"))
                    cake.setTaste(item.getString("UTF-8"));
                if (item.getFieldName().equals("sweetness"))
                    cake.setSweetness(Integer.parseInt(item.getString("UTF-8")));
                if (item.getFieldName().equals("price"))
                    cake.setPrice(Double.parseDouble(item.getString("UTF-8")));
                if (item.getFieldName().equals("weight"))
                    cake.setWeight(Double.parseDouble(item.getString("UTF-8")));
                if (item.getFieldName().equals("size"))
                    cake.setSize(Integer.parseInt(item.getString("UTF-8")));
                if (item.getFieldName().equals("material"))
                    cake.setMaterial(item.getString("UTF-8"));
            }else {
                if (item.getFieldName().equals("image")){
                    if (item.getSize() <= 100) continue;
                    String rootPath = request.getServletContext().getRealPath("/");
                    //path是上传的图片文件的名字
                    String path = item.getName();
                    String type = ".jpg";
                    if (path.indexOf(".") != -1){
                        type = path.substring(path.lastIndexOf("."));
                    }
                    //图片在硬盘上的绝对路径
                    path = "/download/images" + System.currentTimeMillis() + type;
                    try {
                        item.write(new File(rootPath + path));
                        cake.setImagePath(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return cake;
    }

    // /admin/Cake/remove.do
    public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cakeBiz.remove(id);
        response.sendRedirect("list.do");
    }
    // /admin/Cake/detail.do
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cake cake = cakeBiz.get(id);
        request.setAttribute("cake",cake);
        request.getRequestDispatcher("/WEB-INF/pages/admin/cake_detail.jsp").forward(request,response);
    }
}
