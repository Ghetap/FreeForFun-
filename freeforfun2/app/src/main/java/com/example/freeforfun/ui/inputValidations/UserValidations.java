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
    static boolean validateEmail(String email){
        return email.matches("^[a-zA-Z0-9-_.]*(@gmail|@yahoo)\\.com$");
    }
    static boolean validateROPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(004|\\+4)?07[0-9]{8}$");
    }
    static boolean validateName(String name){
        return name.matches("^[A-ZÜÄÖÂÎĂȚȘÁÉÓŐÚŰ][a-zA-Zșțăîâäöüßáéóőúű]{0,30}[- ]?" +
                "[a-zșțăîâäöüáéóőúűßA-ZÜÄÖÂÎĂȚȘÁÉÓŐÚŰ]{0,30}[a-zșțăîâäöüßáéóőúű]$");
    }
}
