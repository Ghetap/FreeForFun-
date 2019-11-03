package com.example.freeforfun.ui.inputValidations;

import com.google.android.material.textfield.TextInputLayout;

public class UserValidations {

    public static boolean isNotEmpty(String input){
        return !input.isEmpty();
    }

    public static boolean doesNotContainSpace(String input){
        return !input.contains("\\s");
    }

    public static boolean hasAtLeast3Charachters(String input){
        return input.length() > 3;
    }

    public static boolean containsOnlyLettersAndDigits(String input){
        return input.matches("[a-zA-Z0-9-_.]*");
    }
}
