package com.example;

import com.exceptions.ValidationException;

public class Main {
    public static void main(String[] args) {
        User user = new User("Chinmay", "chinmay@gmail.com", 21);
        try {
            ValidationProcessor.validate(user);
            System.out.println("User Validated Successfully!\n");
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
        }
    }
}