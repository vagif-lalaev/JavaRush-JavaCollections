package com.javarush.task.task33.task3312;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    public List<Animal> animals = new ArrayList<>();
//указывает детали того, какой тип информации включить в сериализацию
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
//указывает подтипы аннотированного типа
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")})

    public static class Animal {
        public Animal(String name) {
            this.name = name;
        }

        public String name;
    }
    //определяет логическое имя типа для использования с аннотированным классом
    @JsonTypeName("dog")
    public static class Dog extends Animal {

        public double barkVolume;

        public Dog(String name) {
            super(name);
        }
    }
    //определяет логическое имя типа для использования с аннотированным классом
    @JsonTypeName("cat")
    public static class Cat extends Animal {
        boolean likesCream;
        public int lives;

        public Cat(String name) {
            super(name);
        }
    }
}