package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.ZipInputStream;
/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        String resultFileName = args[0]; //итоговый файл
        int filePart = args.length - 1; //количество частей без итогово
        String[] fileNamePart = new String[filePart]; //созд.массив с колич.частей
        for (int i = 0; i < filePart; i++) { //проход циклом по массиву
            fileNamePart[i] = args[i + 1]; //заполняем String-овый массив именами частей
        }
        //сортируем список part по возрастанию
        Arrays.sort(fileNamePart);
        ArrayList<FileInputStream> arrFIS = new ArrayList<>(); //создаем массив потоков
        //заполняем массив arrFIS отсортированными part0
        for (String x : fileNamePart) {
            arrFIS.add(new FileInputStream(x));
        }

        try (SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(arrFIS));
             ZipInputStream zipInput = new ZipInputStream(sis);
             FileOutputStream fileOut = new FileOutputStream(resultFileName)) {
            byte[] buff = new byte[1024]; //массив для буфера
            while (zipInput.getNextEntry() != null) { //чтение
                int len;
                while ((len = zipInput.read(buff)) != -1) {
                    fileOut.write(buff, 0, len); //запись
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
