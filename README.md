# jmtrace

## Code Strcture
```text
.
├── README.md
├── build.gradle
├── jmtrace
├── settings.gradle
└── src
    └── main
        └── java
            ├── ClassVisitorAdapter.java
            ├── MethodVisitorAdapter.java
            ├── PreMainAgent.java
            └── TraceInsn.java
```

## Dependencies
+ ASM 9.0
+ Gradle
+ jdk 11

## Build
+ run `$gradle jar` in the root folder, a `jmtrace-1.0-SNAPSHOT.jar` file will be generated in `build\libs`

## Run
+ use `$./jmtrace -jar [path to something.jar]` to run.

## Description
+ jmtrace can be used to monitor memory access. For every `(getstatic/putstatic/getfield/putfield/*aload/*astore)` instruction, a log will be printed.
```
R/W ThreadId ObjectId Field
```