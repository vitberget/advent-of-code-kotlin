package se.vbgt.jni;

/**
 * To create header file: javac NativeRangeLib.java -h  .
 */

public class NativeRangeLib {
    static {
        System.loadLibrary("native-range");
    }

    public native int[] lowerHalf(int[] range);
    public native int[] upperHalf(int[] range);
}
