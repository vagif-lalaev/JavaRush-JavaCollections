package com.javarush.task.task35.task3512;

public class Generator<T> {
    private Class<T> tClass;
    T newInstance() throws IllegalAccessException, InstantiationException {
        return tClass.newInstance();
    }

    public Generator(Class<T> tClass) {
        this.tClass = tClass;
    }
}
