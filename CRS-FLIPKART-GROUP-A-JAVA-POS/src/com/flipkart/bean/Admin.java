package com.flipkart.bean;

public class Admin extends User{
    private String dateOfJoining;

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Admin(String dateOfJoining) {
        super();
        this.dateOfJoining = dateOfJoining;
    }
    public Admin() {
        super();
    }
}
