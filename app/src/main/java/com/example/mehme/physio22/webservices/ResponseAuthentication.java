package com.example.mehme.physio22.webservices;

public class ResponseAuthentication {

    private String id_token;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token =  "Bearer " + id_token;
    }
}
