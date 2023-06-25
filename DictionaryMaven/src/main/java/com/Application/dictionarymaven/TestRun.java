package com.Application.dictionarymaven;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

class Dog implements Serializable {

    String name;
    String breed;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }
}

class Main {
    public static void main(String[] args) {

        // Creates an object of Dog class
        Dog dog = new Dog("Tyson", "Labrador");
        Dog dog2 = new Dog("Tyson", "Helll");
        var path = "D:\\University\\Year_3\\Java Programing\\month6\\DictionaryMaven\\src\\";

        Vector<Dog> sourceDog = new Vector<>();
        sourceDog.add(dog);
        sourceDog.add(dog2);

        try {
            FileOutputStream file = new FileOutputStream(path + "source.dat");

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);

            // Writes objects to the output stream
            output.writeObject(sourceDog);

            FileInputStream fileStream = new FileInputStream(path + "source.dat");
            // Creates an ObjectInputStream
            ObjectInputStream input = new ObjectInputStream(fileStream);

            // Reads the objects
            var newDog = (Vector<Dog>) input.readObject();

            for (var dogVal : newDog){
                System.out.println(dogVal.breed);
            }

            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}