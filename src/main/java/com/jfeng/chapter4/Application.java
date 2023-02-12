package com.jfeng.chapter4;

import com.jfeng.framework.core.InitHelper;
import com.jfeng.framework.mvc.DispatcherServlet;
import com.jfeng.framework.mvc.FileUploadHelper;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * 应用启动类
 */
public class Application {

    public static int TOMCAT_PORT = 8080;
    public static String TOMCAT_HOSTNAME = "127.0.0.1";
    public static String WEBAPP_PATH = "src/main/webapp";
    public static String ASSET_PATH = "src/main/webapp/WEB-INF/view";
    public static String CONTEXT_PATH = "";
    public static String PATTERN = "/*";
    public static String CODE = "UTF-8";

    public static void main(String[] args) {
        try {
            Application.run();
        }
        catch (Exception e) {
            System.out.println("应用启动失败");
            e.printStackTrace();
        }
    }

    public static void run() throws LifecycleException {
        // 获取目录绝对路径
        String classPath = new File("").getAbsolutePath().replaceAll("\\\\", "/");

        Tomcat tomcat = new Tomcat();
        tomcat.setHostname(TOMCAT_HOSTNAME);

        // 是否设置Tomcat自动部署
        tomcat.getHost().setAutoDeploy(false);

        // 创建连接器
        Connector conn = tomcat.getConnector();
        conn.setPort(TOMCAT_PORT);
        conn.setURIEncoding(CODE);

        // 设置Host
        Host host = tomcat.getHost();
        host.setAppBase(WEBAPP_PATH);

        // 配置tomcat上下文
        Context context = tomcat.addContext(host, CONTEXT_PATH, classPath);

        // 配置请求拦截转发
        Wrapper wrapper = tomcat.addServlet(CONTEXT_PATH, "DispatcherServlet", new DispatcherServlet());
        wrapper.addMapping(PATTERN);

        // 设置静态路径
//        tomcat.addWebapp("/index.html", classPath + "/" + WEBAPP_PATH + "/" + "asset");
//        tomcat.addWebapp("/favicon.ico", classPath + "/" + WEBAPP_PATH + "/" + "asset");
//        tomcat.addWebapp("/WEB-INF", classPath + "/" + WEBAPP_PATH);

        // 初始化相关 Helper 类
        InitHelper.init();
        // 初始化文件上传 Helper 类
        FileUploadHelper.init(context.getServletContext());

        tomcat.start();
        tomcat.getServer().await();
    }
}