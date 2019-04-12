package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
/* 
Шифр Цезаря
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder sb = new StringBuilder(); //создание и инициализация для соеденения символов
        //условие чтобы небыло возвыата null
        if (reader == null) {
            return "";
        }
        long x; // для подсчета байтов
        while ((x = reader.read()) != -1) { //читаем байты пока не вернется -1
            sb.append((char)(x + key)); //соеденяем и сохроняем символы с байтовым сдвигом key, для отображения букв приводим к char
        }
        return sb.toString(); //выводим полученный результат в виде Stringa
    }
}
