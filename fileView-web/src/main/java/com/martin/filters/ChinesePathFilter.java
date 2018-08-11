package com.martin.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午12:48
 * @Description:
 * 对中文路径的过滤处理
 */
public class ChinesePathFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
                .append(request.getServerPort()).append(((HttpServletRequest)request).getContextPath()).append("/");
        request.setAttribute("baseUrl", pathBuilder.toString());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
