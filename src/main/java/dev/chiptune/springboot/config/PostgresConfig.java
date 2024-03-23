package dev.chiptune.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
// @EnableAutoConfiguration 어노테이션을 사용하여 Spring Boot의 자동 구성을 활성화하지만,
// DataSourceAutoConfiguration을 제외함으로써 데이터 소스에 대한 자동 구성을 비활성화합니다.
// 이를 통해 개발자는 데이터베이스 연결에 대한 더 세밀한 제어 및 커스텀 구성을 적용할 수 있습니다.
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableJdbcRepositories(
        basePackages = {"dev.chiptune.springboot.repository"}
        , transactionManagerRef = "postgresTransactionManager")
public class PostgresConfig {

    // PostgreSQL 데이터 소스 설정을 위한 DataSourceProperties 빈을 생성합니다.
    // 'spring.datasource.postgres' 접두사를 사용하여 application.properties 파일에서 설정한 속성을 바인딩합니다.
    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    // HikariCP 커넥션 풀을 사용하는 HikariDataSource 빈을 생성합니다.
    // 이 때, postgresDataSourceProperties()에서 생성된 DataSourceProperties를 사용하여 데이터 소스를 초기화합니다.
    // @Primary 어노테이션은 같은 타입의 빈이 여러 개 있을 경우 이 빈을 우선적으로 사용하도록 지정합니다.
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres.hikari")
    public HikariDataSource postgresDataSource(DataSourceProperties postgresDataSourceProperties) {
        return postgresDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    // PostgreSQL 데이터베이스 작업을 위한 트랜잭션 매니저를 구성합니다.
    // 이 트랜잭션 매니저는 PostgreSQL 데이터 소스에 대한 트랜잭션을 관리합니다.
    @Bean
    public DataSourceTransactionManager postgresTransactionManager(DataSource postgresDataSource) {
        return new DataSourceTransactionManager(postgresDataSource);
    }
}
