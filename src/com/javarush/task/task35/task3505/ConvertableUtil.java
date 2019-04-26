package com.javarush.task.task35.task3505;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {
//должен возвращать словарь, значениями которого являются элементы переданного cписка, а ключами являются объекты
    public static <T extends Convertable> Map convert(List<T> list) {
        Map result = new HashMap();
        for (T obj : list) {
            result.put(obj.getKey(), obj);
        }
        return result;
    }
}
