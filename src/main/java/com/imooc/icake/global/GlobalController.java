package com.imooc.icake.global;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Bennett_Wang on 2019/5/31
 */
public class GlobalController extends GenericServlet {
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        /*
        .do
        不属于前台和后台的操作：/login.do       DefaultController login
        属于前台的操作：/Cake/detail.do         CakeController    detail
        属于后台的操作：/admin/Cake/add.do      CakeController    add
         */
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        if (path.indexOf("/admin") != -1){
            path = path.substring(7);
        }else {
            path = path.substring(1);
        }
        /*
        login.do       DefaultController login
        Cake/detail.do         CakeController    detail
        Cake/add.do      CakeController    add
         */
        int index = path.indexOf("/");
        String className = null;
        String methodName = null;
        if (index != -1){
            className = "com.imooc.icake.controller." + path.substring(0,index)+"Controller";
            methodName = path.substring(index+1,path.indexOf(".do"));
        }else {
            className = "com.imooc.icake.controller.DefaultController";
            methodName = path.substring(0,path.indexOf(".do"));
        }
        try {
            Class cla = Class.forName(className);
            Object object = cla.newInstance();
            Method method = cla.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(object,request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
