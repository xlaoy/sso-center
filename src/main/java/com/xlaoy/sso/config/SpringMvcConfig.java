package com.xlaoy.sso.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2018/6/27 0027.
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 设置跨域（但是并没有什么卵用，所以需要配置下面的Filter）
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        this.configCorsParams(registry.addMapping("/**"));
    }

    private void configCorsParams(CorsRegistration corsRegistration) {
        corsRegistration.allowedOrigins(CorsConfiguration.ALL)
                        .allowedMethods(
                                HttpMethod.PATCH.name(),
                                HttpMethod.HEAD.name(),
                                HttpMethod.OPTIONS.name(),
                                HttpMethod.GET.name(),
                                HttpMethod.HEAD.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name())
                        .allowedHeaders(CorsConfiguration.ALL);
    }

    @Bean
    public CorsFilter corsFilter() {
        WebCorsRegistration corsRegistration = new WebCorsRegistration("/**");
        this.configCorsParams(corsRegistration);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsRegistration.getCorsConfiguration());
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return corsFilter;
    }

    private static class WebCorsRegistration extends CorsRegistration {

        public WebCorsRegistration(String pathPattern) {
            super(pathPattern);
        }

        @Override
        public CorsConfiguration getCorsConfiguration() {
            return super.getCorsConfiguration();
        }
    }

    /**
     * 注册跨域CorsFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean(CorsFilter corsFilter) {
        FilterRegistrationBean corsFilterRegistrationBean = new FilterRegistrationBean(corsFilter);
        corsFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsFilterRegistrationBean;
    }

}
