package com.example.tiamon.module;

import java.io.*;
import java.util.Date;

public class Pet implements Serializable {
    private String name = "Tiamon";
    private Integer age = 0;
    private Date birthday;

    Pet(String name){
        this.name = name;
        this.birthday = new Date();
    }

    private int hangrer = 50;
    private int sleeper = 50;
    private int player = 50;

}
