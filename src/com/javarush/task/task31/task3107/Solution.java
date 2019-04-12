package com.javarush.task.task31.task3107;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        Path path = Paths.get(pathToFile);
        try {
            fileData = new ConcreteFileData(Files.isHidden(path), Files.isExecutable(path), Files.isDirectory(path), Files.isWritable(path));
            /*
            в замен одной строчки, можно и так написать:
            boolean hidden = Files.isHidden(path);
            boolean executable = Files.isExecutable(path);
            boolean directory = Files.isDirectory(path);
            boolean writable = Files.isWritable(path);
            fileData = new ConcreteFileData(hidden, executable, directory, writable);
             */
        } catch (Exception e) {
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
