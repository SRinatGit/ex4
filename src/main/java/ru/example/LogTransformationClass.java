package ru.example;
import java.lang.reflect.Method;

public class LogTransformationClass  {
    public static void logTransformation(Object object, Method method, String errString/*, Object[] args, Object result*/) {
        LogTransformation annotation = method.getAnnotation(LogTransformation.class);
        String logFileName = annotation.logFile();

        StringBuilder messageBuilder = new StringBuilder();
        //messageBuilder.append("Class: ").append(object.getClass().getName()).append("\n");
        messageBuilder.append(errString).append(object.toString()).append("\n");
        //messageBuilder.append("Output Data: ").append(result).append("\n");

        Logger.log(messageBuilder.toString(), logFileName);
    }
}
