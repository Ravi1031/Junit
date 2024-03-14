package com.rv;

public class Contact {

    private String firstName;
    private String lastName;
    private String contactNo;

    public Contact(){

    }
    public Contact(String firstName, java.lang.String lastName, java.lang.String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    //Additional methods
    public void validateFirstName(){
        if (this.firstName.isBlank()){
            throw new RuntimeException("First name should not be blank");
        }
    }
    public void validateLastName(){
        if (this.lastName.isBlank()){
            throw new RuntimeException("Last name should not be blank");
        }
    }
    public void validateContactNumber(){
        if (this.contactNo.isBlank()){
            throw new RuntimeException("contact should not be blank");
        }
        if (this.contactNo.length() != 10){
            throw new RuntimeException("Contact number should be of 10 digit");
        }
        if (!this.contactNo.startsWith("0")){
            throw new RuntimeException("contact number should start with 0");
        }
    }
}
