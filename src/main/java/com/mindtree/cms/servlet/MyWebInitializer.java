package com.mindtree.cms.servlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Anurag Srivastava
 *
 */
public class MyWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { com.mindtree.cms.config.MvcWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	protected void customizeRegistration(
			ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
				LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	/* Setting these variables */

	private static final String LOCATION = "D:/mytemp/";

	private static final long MAX_FILE_SIZE = 1024 * 1024 * 25;// 25MB

	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;// 30MB

	private static final int FILE_SIZE_THRESHOLD = 0;

}
