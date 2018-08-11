package com.martin.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午12:56
 * @Description:
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean getChinesePathFilter(){
        ChinesePathFilter filter = new ChinesePathFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        return registrationBean;
    }
}
