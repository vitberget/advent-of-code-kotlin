#include <cstdarg>
#include "se_vbgt_jni_NativeRangeLib.h"

/*
 * Class:     se_vbgt_jni_NativeRangeLib
 * Method:    incOnce
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL
Java_se_vbgt_jni_NativeRangeLib_incOnce (JNIEnv *env, jclass cls, jint input) {
    return input + 1;
}


/*
 * Class:     se_vbgt_jni_NativeRangeLib
 * Method:    lowerHalf
 * Signature: ([I)[I
 */
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


/*
 * Class:     se_vbgt_jni_NativeRangeLib
 * Method:    upperHalf
 * Signature: (Lse/vbgt/jni/RangeJava;)Lse/vbgt/jni/RangeJava;
 */
JNIEXPORT jobject JNICALL
Java_se_vbgt_jni_NativeRangeLib_upperHalf (JNIEnv *env, jclass cls, jobject inputRangeObject) {

    // Get class of RangeJava, and the tho methods we need
    jclass inputRangeClass = env->GetObjectClass(inputRangeObject);
    jmethodID getLowerMethod = env->GetMethodID(inputRangeClass, "getLower", "()Ljava/lang/Integer;");
    jmethodID getUpperMethod = env->GetMethodID(inputRangeClass, "getUpper", "()Ljava/lang/Integer;");

    // We also need to get the int out of the Integer
    jclass integerClass = env->FindClass("java/lang/Integer");
    jmethodID getValueMethod = env->GetMethodID(integerClass, "intValue", "()I");

    // Call to get the lower and upper Integers
    jobject lowerObject = env->CallObjectMethod(inputRangeObject, getLowerMethod);
    jobject upperObject = env->CallObjectMethod(inputRangeObject, getUpperMethod);
    // Get the ints out the Integers
    jint lower = env->CallIntMethod(lowerObject, getValueMethod);
    jint upper = env->CallIntMethod(upperObject, getValueMethod);

    // Calculate newLower
    jint newLower = lower + 1 + (upper - lower) / 2;
    // Need to get constructor method of Integer
    jmethodID integerConstructorMethod = env->GetMethodID(integerClass, "<init>", "(I)V");
    // Create Integer with new newLower value
    jobject newLowerObject = env->NewObject(integerClass, integerConstructorMethod, newLower);

    // Get constructor of RangeJava
    jmethodID inputRangeConstructorMethod = env->GetMethodID(inputRangeClass, "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;)V");
    // Call the constructor with two Intgers, newLower and (old) Upper
    jobject newRangeObject = env->NewObject(inputRangeClass, inputRangeConstructorMethod, newLowerObject, upperObject);
    return newRangeObject;
}