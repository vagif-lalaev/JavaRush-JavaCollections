package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
/* 
Используем RandomAccessFile
*/
public class Solution {

    public static void main(String... args) {
        //открыываем поток на чтение и запись
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            raf.seek(Long.parseLong(args[1])); //устанавливаем курсор согласно праметру
            byte[] buff  = new byte[args[2].length()]; //создаем массив и передаем размер переданного параметра
                raf.read(buff, 0, buff.length); //читаем и заполняем массив "buff" (c 0 до дленны массива)
                String itog = new String(buff, "utf-8"); //присваеваем прочтенный массив String с кодировкой
            System.out.println(itog);
            raf.seek(raf.length()); //устанавливаем курсор на конец текста в файле
            if (itog.equals(args[2])) { //если прочтенный текст равен тексту переданного параметра
                raf.write("true".getBytes()); //то пишим в конце текста "true"
            } else { //иначе
                raf.write("false".getBytes()); //пишим "false"
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
