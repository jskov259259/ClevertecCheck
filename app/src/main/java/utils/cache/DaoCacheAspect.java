package utils.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class DaoCacheAspect {

    @Pointcut("@annotation(RetryMethod) && execution(* *(..))")
    public void retriableMethod() {
    }

    @Around("retriableMethod()")
    public Object retryMethodAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("11");
        return joinPoint.proceed();
    }
}