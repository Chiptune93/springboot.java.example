package dev.chiptune.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.chiptune.springboot.interceptor.ApiInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* 실행 시, defaultInterceptor를 가져와 주입하여 사용 */
    @Autowired
    private ApiInterceptor apiInterceptor;

    /* 인터셉터 추가하는 메소드 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor) // 인터셉터 추가
                .addPathPatterns("/api") // 적용할 패턴 추가
                .excludePathPatterns("/swagger-ui/**"); // 제외할 패턴 추가
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }

}
