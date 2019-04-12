package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/*
Проход по дереву файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        TreeSet<File> fileList = new TreeSet<>();
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)) {

            fillFileList(path.getPath(), fileList);

            for (File file : fileList) {
                FileInputStream fileInputStream = new FileInputStream(file);
                while (fileInputStream.available() > 0) {
                    fileOutputStream.write(fileInputStream.read());
                }
                fileOutputStream.write(System.lineSeparator().getBytes());
                fileOutputStream.flush();
                fileInputStream.close();
            }
        }
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }

    //Рекурсивно пробегаем поддиректории и заполняем список файлов
    private static void fillFileList(String path, TreeSet<File> fList) {
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                fillFileList(file.getAbsolutePath(), fList);
                continue;
            }
            if (file.length() > 50)
                FileUtils.deleteFile(file);
            else
                fList.add(file);
        }
    }
}