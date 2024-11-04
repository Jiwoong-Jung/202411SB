package edu.du.sb1030.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/view/main/**")
                .excludePathPatterns("/css/**", "/js/**");
        registry.addInterceptor(new AuthCheckInterceptor())
                .addPathPatterns("/edit/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/view/main");
    }

//    @Bean
//    public Java8TimeDialect java8TimeDialect() {
//        return new Java8TimeDialect();
//    }
}
