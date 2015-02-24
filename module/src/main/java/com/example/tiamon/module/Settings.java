package com.example.tiamon.module;


import java.io.*;

public class Settings{
    public String path = "pet.out";

    /* Получить настйроки питомца из файла */
    Pet GetPet(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (Pet) oin.readObject();
    }

    Pet GetPet() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(this.path);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (Pet) oin.readObject();
    }

    /* Сохрнаить питомца в файл */
    void SavePet(Pet pet, String path) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(pet);
        oos.flush();
        oos.close();
    }

    void SavePet(Pet pet) throws IOException{
        FileOutputStream fos = new FileOutputStream(this.path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(pet);
        oos.flush();
        oos.close();
    }


}
