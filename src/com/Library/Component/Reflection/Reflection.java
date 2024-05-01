package com.Library.Component.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    private final Class<?> className;
    private Object object;

    public Reflection(Class<?> className) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.className = className;
        setObj(className);
    }

    private void setObj(Class<?> className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.object = className.getDeclaredConstructor().newInstance();
    }

    public Object call(String methodName, String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = this.className.getDeclaredMethod(methodName, String[].class);
        return method.invoke(this.object, (Object) args);
    }
}
