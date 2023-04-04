package dev.chiptune.springboot.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(value = 2)
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Log Aspect를 적용할 패키지/타입/경로
     * 
     * ex) controller 경로 / service 경로 / 메소드 등
     * 특정 폴더(패키지) : com.example.controller.menuMngt..*.*(..)
     * 특정 메소드(파라미터포함) : com.example.service.findId(HashMap<String, Object>)
     * 
     * [구조]
     * 1. * : 리턴 타입 지정 (*:anything | public string | ...)
     * 2. com.example.controller..*.* : 패키지 경로 및 메소드 명 등
     * 3. (..) : 파라미터 타입
     * 
     * .. 더 많은 정보 : https://www.baeldung.com/spring-aop-pointcut-tutorial
     * 
     * '!@annotation(com.example.aspect.NoLogging)'
     * NoLogging 어노테이션이 붙은 메소드는 제외한다.
     */
    /*
     * @Pointcut("execution(* com.example.demo.controller..*.*(..)) && !@annotation(com.example.demo.aop.NoLogging)"
     * )
     */
    @Pointcut("@annotation(com.example.demo.aop.Logging)")
    private void cut() {
    }

    /**
     * 메소드 전 구역
     * 
     * @param jp
     */
    @Around("cut()")
    public void Around(JoinPoint jp) {
        check(jp, null, null);
    }

    /**
     * 메소드 실행 전
     * 
     * @param jp
     */
    @Before("cut()")
    public void Before(JoinPoint jp) {
        check(jp, "-", "");
    }

    /**
     * 메소드 실행 후 <기본>
     * !! AfterReturning 과 Throwing 을 사용한다면, 기본 After는 사용하지 말아야 중복 실행 방지 가능함. !!
     * 
     * @param jp
     */
    /*
     * @After("cut()")
     * public void After(JoinPoint jp) {
     * String logType = "After";
     * logInsert(jp, logType);
     * }
     */

    /**
     * 메소드 정상 종료 후
     * 
     * @param jp
     */
    @AfterReturning("cut()")
    public void AfterReturning(JoinPoint jp) {
        check(jp, "200", "");
    }

    /**
     * 메소드 에러 ( Exception ) 리턴 경우
     * 
     * @param jp
     */
    @AfterThrowing(value = "cut()", throwing = "e")
    public void AfterThrowing(JoinPoint jp, Exception e) {
        check(jp, "500", e.getMessage());
    }

    /**
     * 실행된 메소드 명 가져오기
     * 
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

    /**
     * 로그 작업 구간
     * 커스터마이징 구간
     * 
     * @param jp
     * @param logType
     * @param status
     * @param errorLog
     * @return
     */
    private void check(JoinPoint jp, String status, String errorLog) {
        // 메소드 명 : 필요한 경우 사용
        Method method = getMethod(jp);
        // 메소드 실행 시 입력된 파라미터 가져오기
        Object[] args = jp.getArgs();
        // 정보 출력
        logger.debug("methods : " + method);
        logger.debug("args : " + args);
        logger.debug("status : " + status);
        logger.debug("errorLog : " + errorLog);
    }
}
