package wam.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Before("execution(* wam.app.w..*.*(..))")
//	public void greeting() {
//		//logger.debug("LogAdvice -- greeting");
//	}
//	
//	@Around("execution(* wam.app.w..*.*(..))")
//    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
//
//        logger.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
//
//        Object result = pjp.proceed();
//
//        logger.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
//
//        return result;
//
//    }
	
	@AfterThrowing(pointcut="within(wam.app.w..*)", throwing="ex") 
	public void errorNotice(JoinPoint joinPoint, Exception ex){
		//ex.printStackTrace();
	    StringBuffer logBuffer = new StringBuffer();
	    logBuffer.append("\n\n");
	    logBuffer.append("ERROR_EXCEPTION      	: " + ex.getClass() + "\n");
	    logBuffer.append("ERROR_TARGET CLASS   	: " + joinPoint.getTarget().getClass() + "\n");
	    logBuffer.append("ERROR_TARGET METHOD  	: " + joinPoint.getSignature().getName() + "\n");
	    logBuffer.append("ERROR_MESSAGE        	: " + ex.getMessage() + "\n");
	    logBuffer.append("ERROR_CAUSE        	: " + ex.toString() + "\n");
	    logBuffer.append("\n");
	    logger.debug(logBuffer.toString());
	}
	

}
