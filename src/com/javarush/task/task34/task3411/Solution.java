package com.javarush.task.task34.task3411;
/* 
Ханойские башни
*/
public class Solution {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        if (numRings > 0) {
            moveRing(a, c, b, numRings - 1);
            System.out.println("from " + a + " to " + b);
            moveRing(c, b, a, numRings - 1);
            //Перекладывание стека из 3 дисков — это:
            //1. Перекладывание стека из 2х дисков на независимую ось
            //2. Перекладывание 3-го диска на нужную нам ось
            //3. Перекладывание стека из 2х дисков на нужную нам ось
        }
    }
}