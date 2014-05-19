package com.company.Aspects;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.company.Model.Student;


@Aspect
public class CachingAspect {
	private Map<Integer, Student> repoCache;
	
	@Around("execution (java.util.Map<Integer, com.company.Model.Student> com.company.Repository.RepositoryDAO.allElements())")
	public Map<Integer, Student> getCache(ProceedingJoinPoint method) throws Throwable {
		System.out.println("Before getAll()");
		if (this.repoCache != null) {
			return this.repoCache;
		}
		
		Map<Integer, Student> result = (Map<Integer, Student>)method.proceed();
		this.repoCache = result;
		System.out.println("After getAll()");
		return result;
	}
	
	@Before("execution (void com.company.Repository.RepositoryDAO.addElement(com.company.Model.Student))")
	public void clearCache() {
		this.repoCache = null;
	}
}
