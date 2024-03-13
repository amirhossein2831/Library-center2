package com.Library.Compunent.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

    public static void call(Class<?> className, String methodName, String[] args) throws
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        Object obj = className.getDeclaredConstructor().newInstance();
        Method method = className.getDeclaredMethod(methodName, String[].class);
        method.invoke(obj, (Object) args);
    }
}
