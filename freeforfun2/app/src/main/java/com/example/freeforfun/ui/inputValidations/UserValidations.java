package com.example.freeforfun.ui.inputValidations;

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
    public static boolean validateEmail(String email){
        return email.matches("^[a-zA-Z0-9-_.]*(@gmail|@yahoo)\\.com$");
    }
    public static boolean validateROPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^(004|\\+4)?07[0-9]{8}$");
    }
    public static boolean validateName(String name){
            return name.matches("^[A-Z][a-zA-Z]{0,30}[- ]?" +
                    "[a-zA-Z]{0,30}[a-z]$");
    }

    public static boolean validateRole(String role){
        return role.matches("^[0-1]$");
    }
}
