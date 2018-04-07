package com.prapps.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	private static final Logger LOG = Logger.getLogger(LogAspect.class);

	@Around("execution(public * com.prapps.app.*.*(..))")
	/*@After("execution(public * com.prapps.tutorials.service.*.*(..))")*/
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
			LOG.debug("After: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
			LOG.trace("return: "+object);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		 return object;
	}
}
