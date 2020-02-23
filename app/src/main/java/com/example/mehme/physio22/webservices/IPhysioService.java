package com.example.mehme.physio22.webservices;

import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.dtos.KundenDatenDTO;
import com.example.mehme.physio22.dtos.TokenDTO;
import com.example.mehme.physio22.dtos.UebungDTO;
import com.example.mehme.physio22.dtos.UserLoginDTO;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPhysioService {

    @GET("/api/uebungs")
    Call<List<UebungDTO>> listRepos(String authentication);

    @POST("/api/authenticate")
    Observable<ResponseAuthentication> authenticate(@Body UserLoginDTO userLoginDTO);

    //@Headers("Accept: application/json", "Content-Type: application/json")
    @GET("/api/uebungs")
    Observable<List<UebungDTO>> getAllUebung(@Header("Authorization")ResponseAuthentication responseAuthentication);



    @GET("/api/kunden-datens")
    Observable<LinkedList<KundenDatenDTO>> getAllKunden(@Header("Authorization")ResponseAuthentication responseAuthentication);

    @GET("/api/kunden-datens/{id}")
    Observable<KundenDatenDTO> getKunde(@Header("Authorization")ResponseAuthentication responseAuthentication,@Path("id") long id);

    @POST("/api/kunden-datens")
    Observable<KundenDatenDTO> createKunde(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KundenDatenDTO kundenDatenDTO);

    @PUT("/api/kunden-datens")
    Observable<KundenDatenDTO> saveKunde(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KundenDatenDTO kundenDatenDTO);


    @GET("/api/kategories")
    Observable<LinkedList<KategorieDTO>> getAllKategories(@Header("Authorization")ResponseAuthentication responseAuthentication);

    @POST("/api/kategories")
    Observable<KategorieDTO> createKategorie(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KategorieDTO kategorieDTO);

    @PUT("/api/kategories")
    Observable<KategorieDTO> saveKategorie(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KategorieDTO kategorieDTO);

    @GET("/api/kategories/{id}")
    Observable<KategorieDTO> getKategorie(@Header("Authorization")ResponseAuthentication responseAuthentication,@Path("id") long id);
    // REST requests

    // z.b Post für Übung hochladen

    //z.b post für Kategorie erstellenô

    //z.b get für alle übungen anzeigen
}
