package se.vbgt.jni;

import static java.lang.System.loadLibrary;

/**
 * To create header file: javac NativeRangeLib.java -h  .
 */

public class NativeRangeLib {
    static {
        loadLibrary("native-range");
        // on Linux, the name must then be libnative-range.so
    }

    static public native int[] lowerHalf(int[] range);
    static public native int[] upperHalf(int[] range);
    static public native int incOnce(int value);
}
