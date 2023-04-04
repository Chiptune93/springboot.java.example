package dev.chiptune.springboot.aop;

import java.text.DecimalFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @apiNote 실행시간 계산 및 디스플레이 AOP for Spring Boot Annotation Version
 * @author DK
 * @since 2022.08.25
 * @version 1.0
 */
@Aspect
@Order(value = 1)
@Component
public class RunningTimeAspectForSpringBoot {
    // ! Logger 있을 시, Logger 사용.
    // private static final Logger logger =
    // LoggerFactory.getLogger(RunningTimeAspect.class);

    // TODO 해당 AOP를 실행할 패키지 경로를 필히 지정할 것.
    // ! 해당 AOP 를 적용할 패키지 경로 지정하면 지정 패키지 + NoLogging 어노테이션 있는 메소드는 제외하고 실행함.
    @Pointcut("execution(* com.example.demo.controller..*.*(..)) && !@annotation(com.example.demo.aop.NoLogging)")
    private void cut() {
    }

    @Around("cut()")
    public Object Around(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return jp.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            DecimalFormat df = new DecimalFormat("#.##");
            double time_d = time;
            System.out.println("▶ Finish Method Name : " + jp.toString() + " ◀");
            System.out.println("▶ Time Record Chk    : " + df.format(time_d / 1000) + " 초 ◀");
        }
    }

}
