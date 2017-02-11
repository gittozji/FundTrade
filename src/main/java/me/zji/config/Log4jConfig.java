package me.zji.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 启动log4j
 * Created by imyu on 2017/2/11.
 */
public class Log4jConfig implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("log4jConfigLocation","classpath:me/zji/config/log4j.properties");
        servletContext.addListener(Log4jConfigListener.class);
    }
}
