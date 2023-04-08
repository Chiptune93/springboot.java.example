package dev.chiptune.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.SimpleSpringPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/* spring framework 에서 Bean 에서 하던 설정을 java로 하게 되며 설정을 이 곳에서 한다. */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /* 정적 리소스 파일 경로 지정 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/**")
                .addResourceLocations("/static/");
    }

    // tiles 설정 파일 매핑 후 리턴.
    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer tiles = new TilesConfigurer();
        // 타일즈 정의 위치 지정 (여러 개 지정 가능)
        tiles.setDefinitions(new String[] { "/WEB-INF/tiles/tiles.xml" });
        // 재시작 가능 여부
        tiles.setCheckRefresh(true);
        // 지정된 타일즈 설정에 대한 Autowired 기능 실행
        tiles.setPreparerFactoryClass(SimpleSpringPreparerFactory.class);
        return tiles;
    }

    // tilesView Resolver
    // 사용자가 요청한 View와 tiles의 View를 매핑해주는 역할.
    // spring <action-servlet> bean 등록 부분 참고.
    /*
     * <bean id="negotiatingViewResolver"
     * class="org.springframework.web.servlet.view.ContentNegotiatingViewResolver">
     * <property name="order" value="1" />
     * <property name="viewResolvers">
     * <list>
     * <bean id="tilesViewResolver"
     * class="org.springframework.web.servlet.view.UrlBasedViewResolver">
     * <property name="viewClass"
     * value="org.springframework.web.servlet.view.tiles3.TilesView" />
     * <property name="redirectHttp10Compatible" value="false"/>
     * </bean>
     * <bean
     * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * <property name="contentType" value="text/html; charset=UTF-8" />
     * <property name="viewClass"
     * value="org.springframework.web.servlet.view.JstlView" />
     * <property name="prefix" value="/WEB-INF/views/" />
     * <property name="suffix" value=".jsp" />
     * <property name="redirectHttp10Compatible" value="false"/>
     * </bean>
     * </list>
     * </property>
     * <property name="defaultViews">
     * <list>
     * <ref bean="jsonView" />
     * </list>
     * </property>
     * </bean>
     */
    @Bean
    public TilesViewResolver tilesViewResolver() {
        // 뷰 리졸버
        final TilesViewResolver tilesViewResolver = new TilesViewResolver();
        // 뷰 클래스 타입 지정
        tilesViewResolver.setViewClass(TilesView.class);
        // 리다이렉트 시 https --> http 이동 방지
        tilesViewResolver.setRedirectHttp10Compatible(false);
        // 뷰 컨텐츠 타입 지정
        tilesViewResolver.setContentType("text/html; charset=UTF-8");
        // 뷰 기본 경로 지정
        tilesViewResolver.setPrefix("/WEB-INF/views/");
        // 뷰 파일 형식 지정
        tilesViewResolver.setSuffix(".jsp");
        // 뷰 리졸버 순서 지정
        tilesViewResolver.setOrder(0);
        return tilesViewResolver;
    }

}
