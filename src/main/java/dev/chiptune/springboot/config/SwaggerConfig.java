package dev.chiptune.springboot.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.classmate.TypeResolver;
import dev.chiptune.springboot.response.ApiErr;
import dev.chiptune.springboot.response.ApiNoAuth;
import dev.chiptune.springboot.response.ApiRes;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30)
                // * 실제 에러 처리로 리턴하는 클래스를 명시하고자 할 때 해당 모델을 추가해준다.
                .additionalModels(
                        typeResolver.resolve(ApiRes.class),
                        typeResolver.resolve(ApiErr.class),
                        typeResolver.resolve(ApiNoAuth.class))
                // * 스키마 멤버 변수 중, Date 관련 변수 문제 방지를 위한 설정
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .useDefaultResponseMessages(true)
                .apiInfo(apiInfo())
                // * 인증 토큰 방식이 있을때만 사용.
                // .securityContexts(Arrays.asList(securityContext()))
                // * 인증 키 사용 등록.
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                // * base package 설정
                .apis(RequestHandlerSelectors.basePackage("dev.chiptune.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Swagger API 기본 정보 세팅
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger 3.0 Api Sample")
                .description("This is Sample")
                .version("1.0")
                .build();
    }

    // 인증 토큰 방식이 있을때만 사용.
    /*
     * private SecurityContext securityContext() {
     * return SecurityContext.builder()
     * .securityReferences(defaultAuth())
     * .build();
     * }
     */

    // 인증 토큰 방식이 있을때만 사용.
    /*
     * private List<SecurityReference> defaultAuth() {
     * AuthorizationScope authorizationScope = new AuthorizationScope("global",
     * "accessEverything");
     * AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
     * authorizationScopes[0] = authorizationScope;
     * return Arrays.asList(new SecurityReference("Authorization",
     * authorizationScopes));
     * }
     */

    /**
     * API KEY 세팅
     * 
     * @return
     */
    private ApiKey apiKey() {
        return new ApiKey("apiKey", "apiKey", "header");
    }
}
