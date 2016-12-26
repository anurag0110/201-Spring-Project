/**
 * 
 */
package com.mindtree.cms.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Anurag Srivastava
 *
 */
@Aspect
@Component
public class MyLogger {

	private final Log log = LogFactory.getLog(getClass());

	public MyLogger() {
	}

	/**
	 * 
	 */
	@Pointcut("execution( * com.mindtree..*.*(..))")
	private void selectAll() {
	}

	/**
	 * @param joinPoint
	 */
	@AfterReturning("selectAll()")
	public void logMethodAccessAfter(JoinPoint joinPoint) {
		log.info("***** Completed: " + joinPoint.getSignature().getName()
				+ " *****");
		System.out.println("***** Completed: "
				+ joinPoint.getSignature().getName() + " *****");
	}

	/**
	 * @param joinPoint
	 */
	@Before("selectAll()")
	public void logMethodAccessBefore(JoinPoint joinPoint) {
		log.info("***** Starting: " + joinPoint.getSignature().getName()
				+ " *****");
		System.out.println("***** Starting: "
				+ joinPoint.getSignature().getName() + " *****");
	}
}
