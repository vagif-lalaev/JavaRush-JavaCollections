package com.javarush.task.task35.task3501;

import java.sql.SQLOutput;

/*
Вызов статического метода
*/
public class Solution {
    public static void main(String[] args) {
        Number number = GenericStatic.<Number>someStaticMethod(new Integer(3));
    }

    static class GenericStatic {
        public static <T> T someStaticMethod(Integer x) {
            System.out.println(x);
            T xx2 = (T) x;
            return xx2;
        }
    }
}
