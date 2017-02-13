package me.zji.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Listener、Filter配置
 * Created by imyu on 2017/2/11.
 */
public class ListenerFilterConfig implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("log4jConfigLocation","classpath:me/zji/config/log4j.properties");
        servletContext.addListener(Log4jConfigListener.class);

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("shiroFilter",DelegatingFilterProxy.class);
        filterRegistration.addMappingForUrlPatterns(null,false,"/*");
    }
}
