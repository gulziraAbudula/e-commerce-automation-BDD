package com.automation.ecommerceBDD.utilities.reportRelated;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jManager {

    public static Logger log = LogManager.getLogger(Log4jManager.class);

    /**
     * Logs info text messages to the Log file & Extent report
     *
     * @param message
     */
    public static void info(String message) {
        log.info(message);
    }

    /**
     * Logs debug text messages to the Log file
     *
     * @param message
     */
    public void debug(String message) {
        log.debug(message);
    }

    /**
     * Logs error text messages to the Log file
     *
     * @param message
     */
    public static void error(String message) {
        log.error(message);
    }
}
