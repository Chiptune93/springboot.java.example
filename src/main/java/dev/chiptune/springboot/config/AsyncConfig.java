package dev.chiptune.springboot.config;

import java.util.concurrent.Executor;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 스프링 비동기 메서드 처리 설정 <SpringBoot 3.2.4 ++>
 * 
 * @version 0.1
 * @author DK
 * @since 2021.10.22
 * @apiNote Bean Name 을 프로파일마다 다르게 지정함으로써 상황에 맞는 config 도 가능.
 * 
 *          해당 빈을 등록 후, 비동기 처리를 원하는 메소드에
 *          @Async("threadPoolTaskExecutor") 어노테이션을 추가.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 기본 스레드 수
        taskExecutor.setCorePoolSize(5);
        // 최대 스레드 수
        taskExecutor.setMaxPoolSize(50);
        // 큐 사이즈
        taskExecutor.setQueueCapacity(30);
        // 스레드 네임 지정
        taskExecutor.setThreadNamePrefix("Executor-");
        // 예외 처리
        // AbortPolicy          : 기본 설정. RejectedExecutionException을 발생시킵니다.
        // DiscardOldestPolicy  : 오래된 작업을 skip 합니다. 모든 task가 무조건 처리되어야 할 필요가 없을 경우 사용합니다.
        // DiscardPolicy        : 처리하려는 작업을 skip 합니다. 역시 모든 task가 무조건 처리되어야 할 필요가 없을 경우 사용합니다.
        // CallerRunsPolicy     : shutdown 상태가 아니라면 ThreadPoolTaskExecutor에 요청한 thread에서 직접 처리합니다.
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 어플리케이션 종료 시, 큐에 남아있는 모든 작업 처리를 기다림.
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 위 옵션의 최대 종료 대기 시간
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
