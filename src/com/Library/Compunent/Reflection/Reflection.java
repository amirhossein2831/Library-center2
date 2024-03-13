package com.Library.Compunent.Reflection;

import com.Library.Controller.Controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    private Object object;

    public Reflection(Class<?> className) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        setObj(className);
    }

    private void setObj(Class<?> className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.object = className.getDeclaredConstructor().newInstance();
    }

    public void call(Class<?> className,String methodName, String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = className.getDeclaredMethod(methodName, String[].class);
        method.invoke(this.object, (Object) args);
    }
}
