package com.example;

import com.annotations.*;

public class User {

    @NotBlank
    String name;

    @NotBlank
    String email;

    @Positive
    int age;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
