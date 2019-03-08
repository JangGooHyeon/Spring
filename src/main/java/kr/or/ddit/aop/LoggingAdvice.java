package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice {
	
	private Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	public void beforeMethod(JoinPoint joinPoint){
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		logger.debug("loggingAdvice before : {} {}", className, methodName);
	}
	
	public void afterMethod(JoinPoint joinPoint){
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		logger.debug("loggingAdvice after : {} {}", className, methodName);
	}
	
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
		
		//핵심로직 호출 전
		long startTime = System.currentTimeMillis();
		
		//핵심로직 호출
		Object[] args = joinPoint.getArgs();
		Object returnObject = joinPoint.proceed();
		
		//핵심로직 호출 후
		long endTime = System.currentTimeMillis();
		logger.debug("profilingTime : {}", endTime - startTime);
//		String className = joinPoint.getTarget().getClass().getName();
//		String methodName = joinPoint.getSignature().getName();
//		logger.debug("loggingAdvice around : {} {}", className, methodName);
		
		
		return returnObject;
	}
}
