package com.master.app.pims.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AppLogger {

    private final Logger logger;

    public AppLogger() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void warn(String message) {
        logger.warn(message);
    }
}
