package com.example.dogs;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.main();
    }

    public void main() {
//        Animal animal = new Animal();
        Dog dog = new Dog();
        Animal animal = dog;
        animal.cry();

    }

    class Animal {
        void cry() {
            System.out.println("I am an Animal");
        }
    }

    class Dog extends Animal {
        void cry() {
            System.out.println("I am a dog");
        }
    }
}
