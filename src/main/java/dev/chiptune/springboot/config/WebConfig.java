package dev.chiptune.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // CORS를 적용할 URL 패턴.
                /*
                 * [addMapping 설정만 했을 때의 기본 값]
                 * Allow all origins.
                 * Allow "simple" methods GET, HEAD and POST.
                 * Allow all headers.
                 * Set max age to 1800 seconds (30 minutes).
                 */
                .allowedOrigins("*") // CORS를 허용할 Origin 지정. 특정 주소에서 요청 시 허용하는 것을 의미.
                                     // .allowedOrigins("http://localhost:8080","http://localhost:80") 와 같이 직접 주소
                                     // 매핑가능.
                .allowedMethods("GET", "POST") // CORS를 허용할 메소드.
                .maxAge(3000); // 요청에 대한 캐싱 처리 시간
    }
}