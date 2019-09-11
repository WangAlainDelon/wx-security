package com.wx.wxsecuritydemo.controller.config;

import com.wx.wxsecuritydemo.controller.filter.TimeFilter;
import com.wx.wxsecuritydemo.controller.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * User: wangxiang
 * Date: 2019/9/10
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private TimeInterceptor timeInterceptor;

    //不访问swagger，拦截器可以这样配置
    //    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        registry.addInterceptor(timeInterceptor);
//    }

    /**
     * 加了拦截器以后，想要访问swagger可以这样配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        addPathPatterns添加需要拦截的命名空间；
//        excludePathPatterns添加排除拦截命名空间
        registry.addInterceptor(new TimeInterceptor()).addPathPatterns("/**").excludePathPatterns("/register").excludePathPatterns("/login").excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }


    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();

        filterRegistrationBean.setFilter(timeFilter);
        //添加过滤器起作用的路径
        List<String> urls = new ArrayList<String>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

    /**
     * 解决添加过滤器后 SWAGGER 404报错
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
