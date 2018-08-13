//
// Created by mahdi on 2018/8/13.
//

/*
 * Class:     com_selfimpr_android_HelloJNI
 * Method:    greet
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_selfimpr_android_HelloJNI_greet
  (JNIEnv *, jclass){
  char* result = "this is call from JNI C++ side";
      jstring param3 = env->NewStringUTF(result);
      return param3;
  }

/*
 * Class:     com_selfimpr_android_HelloJNI
 * Method:    sum
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_selfimpr_android_HelloJNI_sum
 /* (JNIEnv *, jclass, jint, jint) */
  (JNIEnv *env, jclass thiz, jint a, jint b) {
      jint sum = pow(a, 2) + pow(b, 2);
      return sum;
  }

