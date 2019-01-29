package com.douzon.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextLoaderListener() {
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
     	// 톰캣이 뜨면 리스너 실행
    	String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	
    	System.out.println("Container starts..!!!");
    	System.out.println("ContextLoaderListener : " + contextConfigLocation);
   
    }
	
}
