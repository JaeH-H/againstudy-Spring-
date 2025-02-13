package com.spring.basicjpa.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 관점 등록해주고 WebConfig에서 Bean으로 등록해준다.
 */
@Slf4j
@Aspect
public class AspectPractice {
    //포인트컷

    /**
     * 패키지 기반 포인트컷
     */
    @Pointcut("execution(* com.spring.basicjpa.service..*(..))") // 범위 설정
    private void serviceLayerPointCut() {}

    /**
     * 어노테이션 기반 포인트 컷
     */
    @Pointcut("@annotation(com.spring.basicjpa.aop.TrackTime)")
    private void trackTimePointCut() {}


    //어드바이스
    /**
     * @Before : 핵심기능 실행 전에 수행되는 부가기능을 처리할 때 사용.
     */
    @Before("serviceLayerPointCut()")
    public void beforeMethod() {
        log.info("::: Before 실행 :::");
    }

    /**
     * @AfterReturning : 메서드가 정상적으로 반환된 후 실행되는 부가기능.
     * returning = "result" : 메서드가 끝나고 해당 DTO를 반환한다.
     */
    @AfterReturning(pointcut = "serviceLayerPointCut()", returning = "result")
    public void afterReturning(Object result) {
        log.info("::: AFTER RETURNING :::");
        log.info("::: result : {}", result.getClass());
    }

    /**
     * @AfterThrowing : 핵심기능 수행중에 예외가 발생했을 때 실행되는 부가기능.
     * throwing = "ex" : 예외가 발생 때 예외가 반환
     */
    @AfterThrowing(pointcut = "serviceLayerPointCut()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable ex) {
        log.info("::: AFTER THROWING :::");
        log.info("::: ex : {}", ex.getClass());
    }

    /**
     * @After
     * 핵심기능이 정상적으로 실행 또는 예외가 발생해도 완료된 후에 항상 실행되는 어드바이스
     */
    @After("serviceLayerPointCut()")
    public void afterMethod() {
        log.info("::: AFTER");
    }

    /**
     * @Around
     * 다른 어드바이스의 기능을 한 번에 사용할 수 있다.
     */
    @Around("serviceLayerPointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("::: BEFORE");
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();//핵심 기능이 수행되는 곳
            log.info("::: AFTER RETURNING");
            log.info("result : {}", result.getClass());
            return result;

        } catch (Throwable e) {
            log.info("::: AFTER THROWING");
            throw e;

        } finally {
            log.info(":::: AFTER");
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;
            log.info("::: 실행 시간 : {}ms", executeTime);
        }
    }

}
