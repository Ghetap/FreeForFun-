package com.example.freeforfun.ui.model;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobileNumber;
    private String username;
    private ERoleType role;
}
