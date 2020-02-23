package com.example.mehme.physio22.webservices;

import com.example.mehme.physio22.dtos.TokenDTO;
import com.example.mehme.physio22.dtos.UebungDTO;
import com.example.mehme.physio22.dtos.UserLoginDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IPhysioService {

    @GET("/api/uebungs")
    Call<List<UebungDTO>> listRepos(String authentication);

    @POST("/api/authenticate")
    Call<TokenDTO> authenticate(@Body UserLoginDTO userLoginDTO);

    // REST requests

    // z.b Post für Übung hochladen

    //z.b post für Kategorie erstellenô

    //z.b get für alle übungen anzeigen
}
