package services.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class LoggerSystem {

    private static Logger log = Logger.getLogger(LoggerSystem.class);

    @Before("within(rest.*RestService) && execution(public * *(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Metodo: " + joinPoint.getSignature().getName() + ", Args: " + Arrays.toString(joinPoint.getArgs())) ;
    }
}
