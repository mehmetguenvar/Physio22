package com.example.mehme.physio22.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhysioService {

    public IPhysioService retrofit;
    Retrofit retrofits;

    public PhysioService(){
        getService();
    }

    public IPhysioService getService(){
        if(retrofit == null || retrofits == null){
            retrofits = new Retrofit.Builder()
                    .baseUrl("https://physio-server.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
             retrofit = retrofits.create(IPhysioService.class);
            return retrofit;
        }
        else return retrofit;
    }
}
