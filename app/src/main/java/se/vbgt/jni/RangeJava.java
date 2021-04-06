package se.vbgt.jni;

public class RangeJava {
    private final Integer lower;
    private final Integer upper;

    public RangeJava(Integer lower, Integer upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public Integer getLower() {
        return lower;
    }

    public Integer getUpper() {
        return upper;
    }
}
