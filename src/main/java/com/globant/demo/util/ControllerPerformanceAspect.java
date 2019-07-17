package com.globant.demo.util;

import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerPerformanceAspect {

  private Logger logger = LoggerFactory.getLogger(ControllerPerformanceAspect.class);

  @Pointcut("within(@ org.springframework.web.bind.annotation.RestController *)")
  public void controllerClassMethods() {
    // mapping of controller classes
  }

  @Around("controllerClassMethods()")
  public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint)
      throws Throwable {

    long start = System.nanoTime();
    Object returnValue = joinPoint.proceed();
    long end = System.nanoTime();
    String methodName = joinPoint.getSignature().getName();

    if (logger.isDebugEnabled()) {
      logger.info("Controller execution of " + methodName + " took " +
          TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
    }

    return returnValue;
  }
}
