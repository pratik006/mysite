package com.prapps.app.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	
	public LogAspect() {
		if (LOG.isInfoEnabled()) {
			LOG.info("initializing LogAspect");
		}
	}

	@Around("execution(public * com.prapps.app.*.*(..))")
	public Object logAfter(ProceedingJoinPoint joinPoint) {
		Object object = null;
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("Before: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
			for(Object arg : joinPoint.getArgs()) {
				sb.append("("+arg+", ");
			}
			if(sb.indexOf(", ") > -1) {
				sb.delete(sb.length()-2, sb.length()-1);
				sb.append(")");
			}
			LOG.trace("arguements: "+sb);
			object = joinPoint.proceed();
			LOG.trace("After: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
			LOG.trace("return: "+object);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return object;
	}
	
	@Before("execution(public * com.prapps.app.trainapp.controller.TrainController.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		if (LOG.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Before: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
			for(Object arg : joinPoint.getArgs()) {
				sb.append("("+arg+", ");
			}
			if(sb.indexOf(", ") > -1) {
				sb.delete(sb.length()-2, sb.length()-1);
				sb.append(")");
			}
			LOG.trace(sb.toString());
		}
	}
}
