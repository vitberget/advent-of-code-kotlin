package se.vbgt.jni;

import static java.lang.System.out;
import static se.vbgt.jni.NativeRangeLib.lowerHalf;
import static se.vbgt.jni.NativeRangeLib.upperHalf;

/**
 * -Djava.library.path=/home/k/src/aoc-kotlin/app/src/main/java/se/vbgt/jni/native
 */
public class MainJNI {
    public static void main(String[] args) {
        int[] startingRange = {0, 127};

        int[] lower = lowerHalf(startingRange);
        RangeJava rangeJava = upperHalf(new RangeJava(0, 127));

        out.printf("Lower %d %d%n", lower[0], lower[1]);
        out.println("Upper o " + rangeJava);
        Integer o1 = rangeJava.getLower();
        Integer o2 = rangeJava.getUpper();
        out.println("Upper o1 " + o1);
        out.println("Upper o2 " + o2);
    }
}
