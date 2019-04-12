package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        StringBuffer sb = new StringBuffer(); //для объеденения символов
// 6 строчек для создания и добавления в массив списка символов
        String strB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String strL = "abcdefghijklmnopqrstuvwxyz";
        String strChisla = "0123456789";
        String [] strBm = strB.split("");
        String [] strLm = strL.split("");
        String [] strChislam = strChisla.split("");

        String strResult = null; //для добовления и конвертации строк
        Random random = new Random(); //класс для набора случайных чисел
        for (int i = 0; i < 3; i++) { //создания три итерации
            sb.append(strBm[random.nextInt(strBm.length)]); //случайное добавления букв верхнего регистра
            sb.append(strLm[random.nextInt(strLm.length)]); //случайное добавления букв нижнего регистра
            strResult = new String(sb); //перевод в String
            //проверка на колич. символов если больше 8 выход
            if (sb.length() >= 8) {
                break;
            }
            sb.append(strChislam[random.nextInt(strChislam.length)]); //случайное добавления цифр
        }
        baos.write(strResult.getBytes()); //запись в ByteArrayOutputStream и перевод в байты
        return baos;
    }
}