package com.javarush.task.task33.task3307;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.StringReader;
/* 
Десериализация XML объекта
*/
public class Solution {
    public static void main(String[] args) throws IOException, JAXBException {
        //задаем строку, которая хранит xml для десериализации
        String xmlData = "<cat><name>Murka</name><age>5</age><weight>4</weight></cat>";
        Cat cat = convertFromXmlToNormal(xmlData, Cat.class);
        System.out.println(cat);
    }
//Метод convertFromXmlToNormal должен создать объект из xml-строки и вернуть его
    public static <T> T convertFromXmlToNormal(String xmlData, Class<T> clazz) throws IOException, JAXBException {
        //создаем JAXB-контекст, куда передаем список классов.
        JAXBContext context = JAXBContext.newInstance(clazz);
        //создаем Unmarshaller – объект, который будет выполнять десериализацию
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //оборачиваем xml-строку в StringReader
        StringReader sr = new StringReader(xmlData);
        //десериализуем xml из объекта reader и получаем объект
        T t = (T) unmarshaller.unmarshal(sr);
        return t;
    }

    @XmlType(name = "cat")
    @XmlRootElement
    public static class Cat {
        public String name;
        public int age;
        public int weight;

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }
}
