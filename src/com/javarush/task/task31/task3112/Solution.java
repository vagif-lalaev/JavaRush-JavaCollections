package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        //Создается объект URL, куда передается ссылка в интернете.
        URL url = new URL(urlString);
        //У объекта url открываться поток на чтение файла – InputStream
        InputStream inputStream = url.openStream();
        //С помощью метода  createTempFile создается временный файл.
        Path tempFile = Files.createTempFile("temp-",".tmp");
        //копируем во временный фаил
        Files.copy(inputStream,tempFile);
        inputStream.close();
        Path result = Paths.get(downloadDirectory.toString(), urlString.split(".+/",2)[1]);
        Files.move(tempFile,result);
        return result;
    }
}
