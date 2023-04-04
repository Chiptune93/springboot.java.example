package dev.chiptune.springboot.aop;

import java.text.DecimalFormat;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @apiNote 실행시간 계산 및 디스플레이 AOP for Spring XML Config Version
 * @author DK
 * @since 2022.08.25
 * @version 1.0
 */
/* @Aspect
@Component */
public class RunningTimeAspectForSpring {
    // ! Logger 있을 시, Logger 사용.
    // private static final Logger logger =
    // LoggerFactory.getLogger(RunningTimeAspect.class);

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

// 추가 작업

// context xml 파일에 아래 내용을 추가해주어야 함.

// 1. aop 관련 설정.
// beans 추가 : xmlns:aop="http://www.springframework.org/schema/aop"
// schemaLocation 추가 : http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd

// 2. 빈 등록 및 aop 설정하기.
// <bean id="LogAspect" class="syworks.lib.RunningTimeAspect"></bean>

// <aop:config>
//     <aop:aspect ref="LogAspect">
// 		<aop:pointcut id="logPointCut" expression="execution(* syworks..*Service.*(..))" />
// 		<aop:around method="around" pointcut-ref="logPointCut" />
// 	</aop:aspect>
// </aop:config>