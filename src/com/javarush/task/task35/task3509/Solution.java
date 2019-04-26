package com.javarush.task.task35.task3509;

import java.util.*;
/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList arr = new ArrayList();
        for (Object o : elements) {
            arr.add(o);
        }
        return arr;
        //напишите тут ваш код
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet hashSet = new HashSet();
        for (Object o : elements) {
            hashSet.add(o);
        }
        return hashSet;
        //напишите тут ваш код
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        HashMap<K, V> hashMap = new HashMap<>();
        if (keys.size() == values.size()) {
            for (int i = 0; i < keys.size(); i++) {
                hashMap.put(keys.get(i), values.get(i));
            }
        } else throw new IllegalArgumentException();
        return hashMap;
        //напишите тут ваш код
    }
}
