package com.shuyu.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.AbstractLogger;

/**
 * @Title: Log.java 
 * @Package com.hc.utils 
 * @Description: TODO
 * @author Shuyu.Wang
 * @date Creation time: 2017��9��6��
 * @version V1.0   
 */
public class Log {
    private static final String FQCN = Log.class.getName();
    private static final boolean isAbstractLogge;
    private static Logger logger;


    static {
        logger = LogManager.getLogger(FQCN);
        if (logger instanceof AbstractLogger) {
            isAbstractLogge = true;
        } else {
            isAbstractLogge = false;
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void d(final CharSequence... cs) {
        StringBuilder str = new StringBuilder(cs.length);
        for (CharSequence charSequence : cs) {
            str.append(charSequence);
        }
        d(str);
    }

    public static void i(final CharSequence... cs) {
        StringBuilder str = new StringBuilder(cs.length);
        for (CharSequence charSequence : cs) {
            str.append(charSequence);
        }
        i(str);
    }

    public static void w(final CharSequence... cs) {
        StringBuilder str = new StringBuilder(cs.length);
        for (CharSequence charSequence : cs) {
            str.append(charSequence);
        }
        w(str);
    }

    public static void e(final CharSequence... cs) {
        StringBuilder str = new StringBuilder(cs.length);
        for (CharSequence charSequence : cs) {
            str.append(charSequence);
        }
        e(str);
    }

    public static void e(Exception e) {
        if (isAbstractLogge) {
            ((AbstractLogger) logger).logIfEnabled(FQCN, Level.ERROR, null, e.getMessage(), e);
        } else {
            logger.error(e.getMessage(), e);
        }
    }

    private static void d(final CharSequence cs) {
        if (isAbstractLogge) {
            ((AbstractLogger) logger).logIfEnabled(FQCN, Level.DEBUG, null, cs, null);
        } else {
            logger.debug(cs);
        }
    }

    private static void i(final CharSequence cs) {
        if (isAbstractLogge) {
            ((AbstractLogger) logger).logIfEnabled(FQCN, Level.INFO, null, cs, null);
        } else {
            logger.info(cs);
        }
    }

    private static void w(final CharSequence cs) {
        if (isAbstractLogge) {
            ((AbstractLogger) logger).logIfEnabled(FQCN, Level.WARN, null, cs, null);
        } else {
            logger.warn(cs);
        }
    }

    private static void e(final CharSequence cs) {
        if (isAbstractLogge) {
            ((AbstractLogger) logger).logIfEnabled(FQCN, Level.ERROR, null, cs, null);
        } else {
            logger.error(cs);
        }
    }
}
