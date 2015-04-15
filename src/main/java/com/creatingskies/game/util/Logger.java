package com.creatingskies.game.util;

import java.util.concurrent.ConcurrentHashMap;

public final class Logger {

	private Logger(){}
	
	public enum Level{
		FATAL,
		ERROR,
		WARN,
		INFO,
		DEBUG,
		TRACE,
		ALL;
	}

	private static ConcurrentHashMap<String, org.apache.log4j.Logger> loggers = new ConcurrentHashMap<String, org.apache.log4j.Logger>();
	
	public static void log(Class<?> clazz,Level level,String message){
		log(clazz, level, message);
	}
	
	public static void log(Class<?> clazz,Level level,String message,Throwable thrown){
		org.apache.log4j.Logger logger = getLogger(clazz);
		switch (level) {
		case FATAL:
			logger.fatal(message, thrown);
			break;
		case ERROR:
			logger.error(message, thrown);
			break;
		case WARN:
			logger.warn(message, thrown);
			break;
		case INFO:
			logger.info(message, thrown);
			break;
		case DEBUG:
			logger.debug(message, thrown);
			break;
		case TRACE:
			logger.trace(message, thrown);
			break;
		default:
			break;
		}
	}
	
	public static boolean isTraceEnable(Class<?> clazz){
		return getLogger(clazz).isTraceEnabled();
	}
	
	public static boolean isDebugEnable(Class<?> clazz){
		return getLogger(clazz).isDebugEnabled();
	}
	
	public static boolean isInfoEnable(Class<?> clazz){
		return getLogger(clazz).isInfoEnabled();
	}
	
	private static org.apache.log4j.Logger getLogger(Class<?> clazz){
		org.apache.log4j.Logger logger = loggers.get(clazz.getName());
		if(logger == null){
			logger = org.apache.log4j.Logger.getLogger(clazz.getName());
			loggers.put(clazz.getName(), logger);
		}
		return logger;
	}
	
}
