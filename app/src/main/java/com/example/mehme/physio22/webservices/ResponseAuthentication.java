package com.example.mehme.physio22.webservices;

import androidx.annotation.NonNull;

public class ResponseAuthentication {

    private String id_token;

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token =  id_token;
    }

    public ResponseAuthentication(String token){
        setId_token(token);
    }

    @NonNull
    @Override
    public String toString() {
        return "Bearer "+id_token;
    }
}
