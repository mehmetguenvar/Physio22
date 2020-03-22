package com.example.mehme.physio22.webservices;


public class Respone<T> {
    enum State{
        success,
        error
    }
    public int totalSize;
    public int pageSize;
    public int pageNumber;
    public State responeState;
    public T respone;

    public boolean nextPage(){
        int r = (pageNumber+1) * pageSize;
        return r < totalSize;
    }
}