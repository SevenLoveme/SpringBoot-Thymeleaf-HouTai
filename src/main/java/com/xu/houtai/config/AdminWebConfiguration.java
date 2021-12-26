package com.xu.houtai.config;

import com.xu.houtai.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*！注意： 会默认拦截静态资源导致页面布局失效  */
@Configuration
public class AdminWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截所有资源
                .excludePathPatterns("/login","/")//放行登录页
                .excludePathPatterns("/css/**","/fonts/**","/images/**","/js/**");//放行静态资源 还可在application.properties里面执行此放行操作

    }
}
