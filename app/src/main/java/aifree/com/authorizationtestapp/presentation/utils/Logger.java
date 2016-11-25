package aifree.com.authorizationtestapp.presentation.utils;


import android.util.Log;

public class Logger {

    private static final int TOP_STACK_TRACE = 0;
    private static final String LOG_TAG = "++++";

    public static void logError(Throwable e) {
        String logRecord = getExceptionPlace(e);
        logRecord += " error: "+e.getMessage();
        Log.e(LOG_TAG, logRecord);
        e.printStackTrace();
    }

    private static String getExceptionPlace(Throwable e) {
        StackTraceElement topstackTraceElement = e.getStackTrace()[TOP_STACK_TRACE];
        return topstackTraceElement.getClassName()+" "+topstackTraceElement.getMethodName()+" on line "+topstackTraceElement.getLineNumber();
    }
}
