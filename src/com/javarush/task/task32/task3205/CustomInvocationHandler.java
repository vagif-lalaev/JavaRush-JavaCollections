package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods original;

    public CustomInvocationHandler(SomeInterfaceWithMethods x) {
        this.original = x;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");
        Object result = method.invoke(original, args);
        System.out.println(method.getName() + " out");
        return result;
    }
}
