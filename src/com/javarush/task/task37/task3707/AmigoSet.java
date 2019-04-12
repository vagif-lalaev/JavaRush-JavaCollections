package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet <E> extends AbstractSet implements Serializable, Cloneable, Set {
    //Создай приватную константу Object PRESENT, которую инициализируй объектом Object, это будет наша заглушка.
    private static final Object PRESENT = new Object();
    //Список ключей будет нашим сэтом, а вместо значений будем пихать в мапу заглушку PRESENT.
    private transient HashMap<E, Object> map;
//Создай конструктор без параметров, в котором инициализируй поле map.
    public AmigoSet() {
        map = new HashMap<>();
    }
//Создай конструктор с одним параметром Collection<? extends E> collection.
//Для инициализации поля map воспользуйся конструктором, в который передается Capacity.
//Вычисли свою Capacity по такой формуле: максимальное из 16 и округленного в большую сторону значения (collection.size()/.75f)
    public AmigoSet(Collection <? extends E> collection) {
        map = new HashMap<>(Math.max((int) (collection.size()/.75f) + 1, 16));
        addAll(collection);

    }
//Метод add должен добавлять новый элемент в map используя полученный параметр в качестве ключа и объект PRESENT в качестве значения
    @Override
    public boolean add(Object e) {
        return null == map.put((E) e, PRESENT);
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet amigoSet = new AmigoSet<>();
            amigoSet.map = (HashMap) map.clone();
            return amigoSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }
//требуется переопределить hashCode и equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AmigoSet<?> amigoSet = (AmigoSet<?>) o;
        return Objects.equals(map, amigoSet.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), map);
    }
//Реализуй свою логику сериализации
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // вызываем дефолтный метод
        oos.defaultWriteObject();
        //записываем значение int и float, полученные с помощью рефлексии (и дополнительному классу)
        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        //пишем каждый объект ключа
        oos.writeInt(map.keySet().size());
        for (E e : map.keySet()) {
            oos.writeObject(e);
        }
    }
//Реализуй свою логику десериализации
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // вызываем дефолтный метод
        ois.defaultReadObject();
        //читаем записанные значение int и float
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        //инициализируем наше поле map с соответствующим конструктором
        map = new HashMap<>(capacity, loadFactor);
        //циклом по размеру вкладываем в созданное отображение читаемый объект и заглушку
        int size = ois.readInt();
        for (int i = 0; i < size; i++) {
            E e = (E) ois.readObject();
            map.put(e, PRESENT);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //для проверки
//        AmigoSet initialAmigoSet = new AmigoSet<>();
//
//        for (int i = 0; i < 10; i++) {
//            initialAmigoSet.add(i);
//        }
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(initialAmigoSet);
//
//        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
//        AmigoSet loadedAmigoSet = (AmigoSet) objectInputStream.readObject();
//
//        System.out.println(initialAmigoSet.size() + " " + loadedAmigoSet.size());
    }
}
