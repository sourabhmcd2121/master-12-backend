package com.master.app.pims.utils;//package app.pims.logger;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import app.pims.helper.HttpSessionHelper;
//
//
//@Aspect
//@Component
//public class LoggingAdvice {
//	
//	private final static Logger logger = Logger.getLogger(LoggingAdvice.class);
//	
//	@AfterThrowing(pointcut = "execution(* app.pims.master.controller.*.*(..))", throwing = "error")
//	public void afterThrowingAdvice(JoinPoint jp, Throwable error){
//		logger.error("method {} throw exception :{}"+ jp.getSignature(), error);
//    }
//	
//	@Pointcut(value = "execution(* app.pims.master.controller.*.*(..))")
//	public void myPointcut() {
//		
//	}
//	
////	@Before("execution(* app.pims.master.controller.*.submit*(.rowData))")
////	public void beforeAdvice(JoinPoint proceedingJoinPoint) throws Throwable{
////		String ipAddrss = HttpSessionHelper.getIPAddress();
////		String macAddrss = HttpSessionHelper.getMacAddress();
////		String methodName = proceedingJoinPoint.getSignature().getName();
////		String className = proceedingJoinPoint.getTarget().getClass().toString();
////		Object[] array = proceedingJoinPoint.getArgs();
////		Object argsss = null;
////		for(Object args:array) {
////			argsss=args;
////		}
////		logger.info("Master Called Before Aspects IPAddress : "+ipAddrss+"|| MacAddress "+macAddrss+"\n class name : "+className+"\n method name :"+methodName+"() \n arguments before : "+argsss +"\n");
////		final StopWatch stopWatch = new StopWatch();
////	}
////	
////	@Around("execution(* app.pims.master.controller.*.submit*(..))")
////	public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
////		String ipAddrss = HttpSessionHelper.getIPAddress();
////		String macAddrss = HttpSessionHelper.getMacAddress();
////		String methodName = proceedingJoinPoint.getSignature().getName();
////		String className = proceedingJoinPoint.getTarget().getClass().toString();
////		Object[] array = proceedingJoinPoint.getArgs();
////		Object argsss = null;
////		for(Object args:array) {
////			argsss=args;
////		}
////		logger.info("Master Called IPAddress : "+ipAddrss+"|| MacAddress "+macAddrss+"\n class name : "+className+"\n method name :"+methodName+"() \n arguments : "+argsss +"\n");
////		final StopWatch stopWatch = new StopWatch();
////		
////		stopWatch.start();
////        Object result = proceedingJoinPoint.proceed();
////        stopWatch.stop();
////  
////        //Log method execution time
////		//logger.info("Execution time of \n class name : "+className+"\n method name :"+methodName+"() \n arguments : "+array+" || execution time : "+stopWatch.getTotalTimeMillis()+"ms");
////		return result;
////		
////	}
//
//}
