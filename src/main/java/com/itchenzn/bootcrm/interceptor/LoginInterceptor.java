package com.itchenzn.bootcrm.interceptor;

import com.itchenzn.bootcrm.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       //获取请求的url
        String url=request.getRequestURI();
        //除了登录请求以外，其他的url都进行拦截
        if (url.indexOf("/login.action")>=0){
            return true;
        }
        //获取Session
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        //判断session中是否有数据，如果有，则返回true，继续向下执行
        if (user!=null){
            return true;
        }
        //不符合条件的则给出提示消息，并转发到登录页面
        request.setAttribute("msg","您还没有登录，请先登录");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        //阻止继续向下执行
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
