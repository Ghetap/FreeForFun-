package com.example.freeforfun.ui.model;


public class Local {

	private Long id;
	private String name;
	private String location;
	private String mobileNumber;
	private String timetable;
	private Float rating;
	private String description;
	private Boolean smokingRestriction;
	private Boolean petRestriction;
	private Boolean wifi;
	private User owner;


	private ELocalType type;

	public Long getId() {
		return id;
	}









	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTimetable() {
		return timetable;
	}

	public void setTimetable(String timetable) {
		this.timetable = timetable;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getSmokingRestriction() {
		return smokingRestriction;
	}

	public void setSmokingRestriction(Boolean smokingRestriction) {
		this.smokingRestriction = smokingRestriction;
	}

	public Boolean getPetRestriction() {
		return petRestriction;
	}

	public void setPetRestriction(Boolean petRestriction) {
		this.petRestriction = petRestriction;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ELocalType getType() {
		return type;
	}

	public void setType(ELocalType type) {
		this.type = type;
	}
}
