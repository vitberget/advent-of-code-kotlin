#include <cstdarg>
#include "se_vbgt_jni_NativeRangeLib.h"

JNIEXPORT jintArray JNICALL
Java_se_vbgt_jni_NativeRangeLib_lowerHalf (JNIEnv *env, jclass cls, jintArray inputRange) {

    jint* range = env->GetIntArrayElements(inputRange, 0);
    jint low = range[0];
    jint high = range[1];

    jint values[2] = {
        low ,
        low + (high - low) / 2
    };

    jintArray returnMe = env->NewIntArray(2);
    env->SetIntArrayRegion(returnMe, 0, 2, values);
    env->ReleaseIntArrayElements(inputRange, range, 0);

    return returnMe;
}

JNIEXPORT jintArray JNICALL
Java_se_vbgt_jni_NativeRangeLib_upperHalf (JNIEnv *env, jclass cls, jintArray inputRange) {

    jint* range = env->GetIntArrayElements(inputRange, 0);
    jint low = range[0];
    jint high = range[1];

    jint values[2] = {
        low + 1 + (high -   low) / 2,
        high
    };

    jintArray returnMe = env->NewIntArray(2);
    env->SetIntArrayRegion(returnMe, 0, 2, values);
    env->ReleaseIntArrayElements(inputRange, range, 0);

    return returnMe;
}

