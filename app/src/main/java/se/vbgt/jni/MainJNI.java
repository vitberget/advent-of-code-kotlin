package se.vbgt.jni;

import static java.lang.System.out;
import static se.vbgt.jni.NativeRangeLib.lowerHalf;
import static se.vbgt.jni.NativeRangeLib.upperHalf;

public class MainJNI {
    public static void main(String[] args) {
        int[] startingRange = {0, 127};

        int[] lower = lowerHalf(startingRange);
        int[] upper = upperHalf(startingRange);

        out.printf("Lower %d %d%n", lower[0], lower[1]);
        out.printf("Upper %d %d%n", upper[0], upper[1]);
    }
}
