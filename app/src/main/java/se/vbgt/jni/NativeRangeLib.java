package se.vbgt.jni;

import static java.lang.System.loadLibrary;

/**
 * To create header file:
 *   javac RangeJava.java NativeRangeLib.java -h native/
 */

public class NativeRangeLib {
    static {
        loadLibrary("native-range");
        // on Linux, the name must then be libnative-range.so
    }

    static public native int incOnce(int value);
    static public native int[] lowerHalf(int[] range);
    static public native RangeJava upperHalf(RangeJava range);
}
