package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        if (KidBoy.MAX_AGE >= age ) {
            return new KidBoy();
        } else if (TeenBoy.MAX_AGE >= age) {
            return new TeenBoy();
        } return new Man();
    }
}
