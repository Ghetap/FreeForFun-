package com.example.freeforfun.ui.utils;

public class Paths {

//    public static final String BASE_URL = "http://192.168.100.48:8080/free_for_fun"; //Cluj
  //  public static final String BASE_URL = "http://192.168.100.8:8080/free_for_fun"; //Sibiu
    public static final String BASE_URL = "http://192.168.1.106:8080/free_for_fun"; //marga
  // public static final String BASE_URL = "http://10.0.2.2:8080/free_for_fun"; //emulator

    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String FORGOT_PASSWORD = "/forgot_password";
    public static final String CHANGE_PASSWORD = "/changePassword";
    public static final String DELETE_ACCOUNT = "/deleteAccount";
    public static final String UPLOAD = "/upload";


    public static final String UPDATE_PROFILE = "/update";
    public static final String GET_ALL_LOCALS = "/locals";
    public static final String FILTER_LOCALS = "/filter";

    public static final String RESERVATIONS = "/reservations";
    public static final String GET_RESERVATIONS_BY_ID = RESERVATIONS;
    public static final String FREE_PLACES = RESERVATIONS + "/free";
    public static final String CREATE_RESERVATION = RESERVATIONS + "/create";
    public static final String DELETE_RESERVATION = RESERVATIONS + "/delete";
    public static final String FILTER_FUTURE_RESERVATIONS_AFTER_USERS = RESERVATIONS + "future/user";
    public static final String FILTER_PAST_RESERVATIONS_AFTER_USERS = RESERVATIONS + "past/user";
    public static final String LIKE = "/upvote";
    public static final String DISLIKE = "/downvote";
    public static final String GET_LOCAL = "/getlocal";
    public static final String DELETE_VOTE = "/getlocal";
    public static final String GET_ALL_VOTED_LOCALS = "/votedlocals";
}