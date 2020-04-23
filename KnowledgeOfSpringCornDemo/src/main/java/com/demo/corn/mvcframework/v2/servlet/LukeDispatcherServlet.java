package com.demo.corn.mvcframework.v2.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 揭光智
 * @date 2020/03/17
 */
public class LukeDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //6.调用,运行阶段了
        doDispatcher(req, resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {
    }

    /**
     * 初始化阶段
     *
     * @throws ServletException Servlet异常
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化扫描到类,并且将它们放入到IOC容器当中
        doInstance();

        //4.完成依赖注入
        doAutoWired();

        //5.初始化HandlerMapping
        initHandlerMapping();


    }

    /**
     * 1.加载配置文件
     *
     * @param contextConfigLocation 配置参数
     */
    private void doLoadConfig(String contextConfigLocation) {
        //直接从类路径下找到Spring主配置文件(application.properties)所在的路径
        //并且将其读取出来放到Properties中
        //相当于把scanPackage=com.demo.corn.demo 从文件中读取到内存中 ===>contextConfig当中了
        try (InputStream fis = getClass().getClassLoader().getResourceAsStream(contextConfigLocation)) {
            contextConfig.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2.扫描相关的类
     *
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
    }

    /**
     * 3.初始化扫描到的相关类，并将它们加入到IOC容器当中
     */
    private void doInstance() {

    }

    /**
     * 4.完成依赖注入
     */
    private void doAutoWired() {
    }

    /**
     * 5.初始化HandlerMapping
     */
    private void initHandlerMapping() {
    }

}
