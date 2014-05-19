package com.company.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PerformanceMonitoringAspect {
	@Around("execution (* com.company.Repository.RepositoryDAO.*(..))")
	public Object logTime(ProceedingJoinPoint method) throws Throwable {
		long startTime = System.nanoTime();
		Object result = method.proceed();
		long endTime = System.nanoTime();
		
		System.out.println("Time elapsed for method " + method.getSignature() + ": " + (endTime-startTime));
		return result;
	}
}
