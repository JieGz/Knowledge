package com.demo.jpa.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 数据库连接池
 *
 * @author 揭光智
 * @date 2018/11/02
 */
@Configuration
public class LocationDruidConfig {

    private String username = "root";
    private String password = "jieguangzhi";
    private String restEnable = "false";

    /**
     * 白名单：registrationBean.addInitParameter("allow","192.168.1.218,127.0.0.1");
     * IP黑名单 (存在共同时，deny优先于allow) registrationBean.addInitParameter("deny","192.168.1.100"); 登录查看信息的账号密码.
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, true, "/druid/*");
        registrationBean.addInitParameter("loginUsername", username);
        registrationBean.addInitParameter("loginPassword", password);
        //是否能够重置数据.
        registrationBean.addInitParameter("resetEnable", restEnable);
        return registrationBean;
    }

    @Bean(name = "knowledgeOfSpringDataJpa")
    public FilterRegistrationBean statFilter() {
        WebStatFilter filter = new WebStatFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.my,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
