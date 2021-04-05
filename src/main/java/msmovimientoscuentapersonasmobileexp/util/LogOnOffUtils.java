package msmovimientoscuentapersonasmobileexp.util;

public class LogOnOffUtils {
    private LogOnOffUtils(){}

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

}
