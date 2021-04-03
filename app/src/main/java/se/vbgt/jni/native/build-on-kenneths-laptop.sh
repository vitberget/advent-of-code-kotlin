JAVA_HOME=/usr/lib/jvm/java-8-openjdk
gcc -shared  \
    -I/usr/include \
    -I$JAVA_HOME/include \
    -I$JAVA_HOME/include/linux \
    NativeRangeLib.cpp -o libnative-range.so