package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
/* 
Что внутри папки?
*/
public class Solution {
    //счетчики (папок, файлов и размер в байтах)
    private static long totalFolders = 0;
    private static long totalFiles = 0;
    private static long totalSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine()); //передаем с консоли путь в директорию
        reader.close();
        if (!Files.isDirectory(path)){ //если путь не является директорией
            System.out.println(path + " - не папка");
        }
        else { //иначе
            //метод позволяющимй обойти дерево файлов и поддиректории
            Files.walkFileTree(path, new MyVisitor());
            System.out.println("Всего папок - " + (totalFolders -1)); //-1 потому что в счет идет сам факт starta(его не учитываем)
            System.out.println("Всего файлов - " + totalFiles);
            System.out.println("Общий размер - " + totalSize);
        }
    }
//создаем класс для применения методов обхода файлов и папок
//подробней о классах и метадах описанно тут: https://habr.com/sandbox/108078/
    public static class MyVisitor extends SimpleFileVisitor {
        //метод для вхождений в файл
        @Override
        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
            totalFiles++; //подсчет файлов
            totalSize += attrs.size(); //подсчет размеров в байтах
            return FileVisitResult.CONTINUE; //CONTINUE продолжает обход дерева
        }
        //метод для вхождений в директории
        @Override
        public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
            totalFolders++; //подсчет папок + одна папка за вход (start)
            return FileVisitResult.CONTINUE; //CONTINUE продолжает обход дерева
        }
    }
// решение через java.io.File
//    static void dirInfo(Path path){
//        File filePath = new File(path.toUri());
//        for (File file : filePath.listFiles()){
//            if (Files.isDirectory(file.toPath())){
//                totalFolders += 1;
//                dirInfo(file.toPath());
//            }
//            else {
//                totalFiles += 1;
//                totalSize += file.length();
//            }
//        }
//    }
}