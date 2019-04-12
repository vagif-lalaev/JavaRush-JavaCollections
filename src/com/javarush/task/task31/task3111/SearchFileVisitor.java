package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles;

    public SearchFileVisitor() {
        foundFiles = new ArrayList<Path>();
        partOfName = null;
        partOfContent = null;
        minSize = -1;
        maxSize = -1;
    }

    public void setFoundFiles(List<Path> foundFiles) {
        this.foundFiles = foundFiles;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfContent (String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean ofMinSize = minSize == -1 || content.length >= minSize;
        boolean ofMaxSize = maxSize == -1 || content.length <= maxSize;
        boolean ofName = partOfName == null || file.getFileName().toString().contains(partOfName);
        boolean ofContent = partOfContent == null || new String(content).contains(partOfContent);

        if (ofMinSize && ofMaxSize && ofName && ofContent) {
            foundFiles.add(file);
        }

        return super.visitFile(file, attrs);
    }
}
