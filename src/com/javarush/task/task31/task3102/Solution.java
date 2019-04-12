package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;
/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>(); //создаем массив array
        File file = new File(root); //создаем File и передаем в него путь (каталога)
        Queue<File> queue = new PriorityQueue<>(); //создаем каталог
        Collections.addAll(queue, file.listFiles());//добовляем все из дироктирии file.listFiles() в queue
        while (!queue.isEmpty()) { //если в каталоге есть что читать то
            File removFolder = queue.remove(); //удалям папку
            if (removFolder.isDirectory()) { //Является ли «объект файла» директорией
                Collections.addAll(queue, removFolder.listFiles()); //добовлем все в queue
            } else list.add(removFolder.getAbsolutePath()); //иначе возвращает полный путь файла и добовлем в list
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        String r = "C:\\Users\\Вагиф\\YandexDisk\\JAVA\\Проэкты от JavaRush\\Проэкты от JavaRush (полностью рабочая)\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31";
    //   цикл для прохода по list и отображение найденных путей
        for (String x : getFileTree(r)) {
            System.out.println(x);
        }
    }
}
