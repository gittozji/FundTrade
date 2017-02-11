package me.zji.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * SpringMVC 主配置
 * Created by imyu on 2017/1/20.
 */
@Configuration
@EnableWebMvc
@ComponentScan("me.zji.web")
public class WebConfig extends WebMvcConfigurerAdapter {
    /******************Thymeleaf部分配置*******************/
    // Thymeleaf视图解析器
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");// 解决Thymeleaf中文乱码问题
        return viewResolver;
    }
    // 模板引擎
    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    // 模板解析器
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);// 修改html后不用重启动
        templateResolver.setCharacterEncoding("UTF-8");// 解决Thymeleaf中文乱码问题
        return templateResolver;
    }

    /******************配置静态资源的处理*******************/
    // 配置静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /******************格式转换部分配置*******************/
    // 格式转换配置
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(stringHttpMessageConverter());
        converters.add(jsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        Charset charset = Charset.forName("UTF-8");
        return new StringHttpMessageConverter(charset);
    }
    @Bean
    public FastJsonHttpMessageConverter jsonHttpMessageConverter() {
        return new FastJsonHttpMessageConverter();
    }
}
