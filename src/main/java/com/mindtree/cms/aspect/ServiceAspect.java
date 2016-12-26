package com.mindtree.cms.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Anurag Srivastava
 *
 */
@Aspect
public class ServiceAspect {
	private final Log log = LogFactory.getLog(getClass());

	@Pointcut("execution( * com.mindtree.service.*.*(..)))")
	private void selectAll() {
	}

	@After("selectAll()")
	public void afterAdvice() {
		log.info("***** Service Transaction : Completed *****");
		System.out.println("***** Service Transaction : Completed *****");
	}

	@AfterThrowing(pointcut = "selectAll()", throwing = "ex")
	public void AfterThrowingAdvice(Exception ex) {
		System.out.println("There has been an exception: " + ex.toString());
	}
}
