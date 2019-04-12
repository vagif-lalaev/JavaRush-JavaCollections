package com.javarush.task.task31.task3109;

import java.io.*;
import java.util.Properties;
/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties pop = new Properties(); //создаем объект Properties
        boolean isXML; //флаг
        //открываем поток для чтение байтов
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            pop.loadFromXML(fileInputStream); //загружаем в него данные из файла XML
            isXML = true; //устанавливаем флаг true
        } catch (IOException e) { //если ошибка
            isXML = false; //устанавливаем флаг false
        }
        if (!isXML) { //если флаг false
            //открываем поток для чтения байтов
            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                pop.load(fileInputStream); //загружаем в него данные из любого другого файла
            } catch (IOException e) { //если ошибка
                e.getStackTrace(); //Стак трасе
            }
        }
        return pop; //возвращаем обект Properties
    }
}
