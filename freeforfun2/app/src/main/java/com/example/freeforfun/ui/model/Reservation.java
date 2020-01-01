package com.example.freeforfun.ui.model;

public class Reservation {

    private Long id;
    private User user;
    private Local local;
    private Integer numberOfPlaces;
    private LocalTable table;
    private String dateTimeReservation;
    private String dateTimeCreation;
    private EReservationType reservationType;
    private String dateTimeLeave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Integer getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(Integer numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public LocalTable getTable() {
        return table;
    }

    public void setTable(LocalTable table) {
        this.table = table;
    }

    public String getDateTimeReservation() {
        return dateTimeReservation;
    }

    public void setDateTimeReservation(String dateTimeReservation) {
        this.dateTimeReservation = dateTimeReservation;
    }

    public String getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(String dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public EReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(EReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public String getDateTimeLeave() {
        return dateTimeLeave;
    }

    public void setDateTimeLeave(String dateTimeLeave) {
        this.dateTimeLeave = dateTimeLeave;
    }
}
