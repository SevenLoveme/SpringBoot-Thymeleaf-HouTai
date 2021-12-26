package com.xu.houtai.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*登陆检查
* 1.配置好拦截器要拦截哪些请求
* 2.把这些配置放在容器中
* ！注意： 会默认拦截静态资源导致页面布局失效
* */

@Slf4j//日志
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * @Author Xu
     * @Description //TODO
     * @Date 16:07 2021/12/26
     * @Param
     * @return
     */

    //目标方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //日志
        StringBuffer requestURL = request.getRequestURL();
        log.info("拦截的请求路径是："+requestURL);

        //登陆检查逻辑
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user!=null){
            //放行
            return true;
        }
        //拦截
        request.setAttribute("msg", "请先登录");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }

    //目标方法执行之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //页面渲染以后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
