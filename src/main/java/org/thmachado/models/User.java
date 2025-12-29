package org.thmachado.models;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private String firstname;
    private String lastname;
    private String email;

    public User(String firstname, String lastname, String email){
        this.uuid = UUID.randomUUID();
        this.changeFirstname(firstname);
        this.changeLastname(lastname);
        this.changeEmail(email);
    }

    public void changeFirstname(String firstname){
        if(firstname == null || firstname.isBlank()){
            throw new IllegalArgumentException("Firstname is required");
        }

        this.firstname = firstname;
    }

    public void changeLastname(String lastname){
        if(lastname == null || lastname.isBlank()){
            throw new IllegalArgumentException("Lastname is required");
        }

        this.lastname = lastname;
    }

    public void changeEmail(String email){
        if(email == null || email.isBlank() || !email.contains("@")){
            throw new IllegalArgumentException("Invalid email");
        }

        this.email = email;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getEmail(){
        return this.email;
    }

    public UUID getUuid(){
        return this.uuid;
    }
}
