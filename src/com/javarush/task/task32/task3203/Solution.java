package com.javarush.task.task32.task3203;

import java.io.PrintWriter;
import java.io.StringWriter;
/*
Пишем стек-трейс
*/
public class Solution {
    public static void main(String[] args) {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
        System.out.println(text);
    }
//метода, который в виде одной строки возвращает весь стек-трейс переданного исключения
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter(); //создаем и инициализируем StringWriter
        //вызываем у класса Throwable метод .printStackTrace который принимает объект PrintWriter
        //и адаптирует переменую sw, для возврата весего стек-трейс переданного исключения
        throwable.printStackTrace(new PrintWriter(sw));
        String str = sw.toString(); //сохраняем все в строку
        return str; //возвращаем строку
    }
}