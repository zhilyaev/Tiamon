package com.example.tiamon.module;

import java.io.*;
import java.util.Date;

public class Pet implements Serializable {

    /* Базовые */
    private String name;
    private Integer age = 0;
    private Date birthday;
    String GetName(){
        return this.name;
    }
    /* Статусы */
    private int hangrer = 50;
    private int sleeper = 50;
    private int player = 50;

    private String path = "pet.out";
    /* Конструктор */
    Pet(String name){
        this.name = name;
        this.birthday = new Date();
    }
    Pet() {
        /* Извращение как оно есть
        * Создаем this из файла
        * Так много try для того, чтобы я не делал проверку каждый раз загружая файл*/
      try {
        FileInputStream fis = new FileInputStream(this.path);
        ObjectInputStream oin = new ObjectInputStream(fis);
          Pet tmp = null;
          try {
              tmp = (Pet) oin.readObject();
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          this.name = tmp.name;}
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Сохрнаить питомца в файл */
    void Save(){
        try {
            FileOutputStream fos = new FileOutputStream(this.path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}
