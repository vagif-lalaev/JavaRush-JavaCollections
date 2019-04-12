package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
        System.out.println(s);
        System.out.println(f);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        //это объект Jackson, который выполняет сериализацию
        ObjectMapper objectMapper = new ObjectMapper();
        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter sw = new StringWriter();
        // сама сериализация: 1-куда, 2-что
        objectMapper.writeValue(sw, one);
        //преобразуем в строку
        String oneStr = one.getClass().getSimpleName().toLowerCase();
        String resultClassStr = resultClassObject.getSimpleName().toLowerCase();
        //преобразовываем все записанное во StringWriter в строку
        String jsonResult = sw.toString().replaceFirst(oneStr,resultClassStr);

        return objectMapper.readValue(jsonResult,resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i;
        public String name;
    }
}
