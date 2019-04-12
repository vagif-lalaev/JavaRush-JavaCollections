package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;
/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        //открываем поток для зписи и чтения в имеющийся текстовый файл
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw")) {
            long lengthFile = randomAccessFile.length(); //возвращает длинну файла
            if (lengthFile < Long.parseLong(args[1])) { //если длинна < указанной позиции
                randomAccessFile.seek(lengthFile); //то сдвинуть на эту же длинну (т.е. в конец)
                randomAccessFile.write(args[2].getBytes("UTF-8")); //записать данные(передан.параметры) в виде байтов
            } else { //иначе
                randomAccessFile.seek(Long.parseLong(args[1])); //сдвинуть на указанную поз.(перед.пара), парсим чтобы понимать число
                randomAccessFile.write(args[2].getBytes("UTF-8"));  //записать данные(передан.параметры) в виде байтов
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
/* в параметр main передаются:
"C:\Users\Вагиф\YandexDisk\JAVA\Проэкты от JavaRush\Проэкты от JavaRush (полностью рабочая)\JavaRushTasks\4.JavaCollections\src\com\javarush\task\текст для работы программ" "6" "товарищи"
 */
