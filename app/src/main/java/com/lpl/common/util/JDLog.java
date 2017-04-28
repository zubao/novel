
package com.lpl.common.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日志管理
 */
public class JDLog {

    private static final String TAG = JDLog.class.getSimpleName();

    /**
     * 一般log
     */
    public static void log(String tag, String content) {
        if (tag == null || content == null) {
            return;
        }

        Log.d(tag, buildMessageSafe(content));

    }

    /**
     * 一般log
     */
    public static void log(String content) {

        Log.d(TAG, buildMessageSafe(content));

    }



    public static void log(String content, Object... obj) {
        log(String.format(content, obj));
    }




    /**
     * 辅助函数：获取当前时间
     */
    public static String getMillTimeEx() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
        return format.format(new Date());
    }

    /**
     * 辅助函数：获取当前时间
     */
    public static String getDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.US);
        return format.format(new Date());
    }



    private static String buildMessageSafe(String msg) {
        try {
            return buildMessage(msg);
        } catch (Exception e) {
        }
        return msg;
    }

    private static String buildMessage(String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "";
        String clssName = "";
        for (int i = 3; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(JDLog.class)) {
                caller = "(" + trace[i].getFileName() + ":" + trace[i].getLineNumber() + ") "
                        + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread()
                .getId(), caller, msg==null?"":msg);
    }

    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String clssName = "";
        for (int i = 3; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(JDLog.class)) {
                clssName = trace[i].getClassName();
                clssName = clssName.substring(clssName.lastIndexOf('.') + 1);
                break;
            }
        }
        return clssName;
    }
}
