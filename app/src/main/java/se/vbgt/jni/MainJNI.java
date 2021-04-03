package se.vbgt.jni;

public class MainJNI {
    public static void main(String[] args) {
        NativeRangeLib nrl = new NativeRangeLib();

        int[] lower = nrl.lowerHalf(new int[]{0,127});
        int[] upper = nrl.upperHalf(new int[]{0,127});

        System.out.printf("Lower %d %d%n", lower[0], lower[1]);
        System.out.printf("Upper %d %d%n", upper[0], upper[1]);
    }
}
