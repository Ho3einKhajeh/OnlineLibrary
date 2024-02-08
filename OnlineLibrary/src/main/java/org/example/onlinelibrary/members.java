package org.example.onlinelibrary;

import javafx.event.ActionEvent;

public class members  {

    private  int id;
    private  String name;
    private  String family;
    private  String email;
    private  String username;
    private  String password;

    public String getEmail() {
        return email;
    }



    public void AddMember(ActionEvent event){

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    private  String tell;
}
