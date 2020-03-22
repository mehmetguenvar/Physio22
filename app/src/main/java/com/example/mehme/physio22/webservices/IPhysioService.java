package com.example.mehme.physio22.webservices;

import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.dtos.KategorieDTO;
import com.example.mehme.physio22.dtos.TokenDTO;
import com.example.mehme.physio22.dtos.UserLoginDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IPhysioService {

    @GET("/api/uebungs")
    Call<List<Uebung>> listRepos(String authentication);

    @POST("/api/authenticate")
    Observable<ResponseAuthentication> authenticate(@Body UserLoginDTO userLoginDTO);

    //@Headers("Accept: application/json", "Content-Type: application/json")
    @GET("/api/uebungs")
    Single<LinkedList<Uebung>> getAllUebung(@Header("Authorization")ResponseAuthentication responseAuthentication, @QueryMap(encoded = true) Map<String,String> requestBody);

    @GET("/api/uebungs/{id}")
    Single<Uebung> getUebung(@Header("Authorization")ResponseAuthentication responseAuthentication,@Path("id") long id);
    @POST("/api/uebungs")
    Single<Uebung> createUebung(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body Uebung uebungDTO);

    @PUT("/api/uebungs")
    Single<Uebung> saveUebung(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body Uebung uebungDTO);


    @GET("/api/kunden-datens")
    Single<LinkedList<KundenDaten>> getAllKunden(@Header("Authorization")ResponseAuthentication responseAuthentication, @QueryMap(encoded = true) Map<String,String> config);

    @GET("/api/kunden-datens/{id}")
    Single<KundenDaten> getKunde(@Header("Authorization")ResponseAuthentication responseAuthentication,@Path("id") long id);

    @POST("/api/kunden-datens")
    Single<KundenDaten> createKunde(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KundenDaten kundenDatenDTO);

    @PUT("/api/kunden-datens")
    Single<KundenDaten> saveKunde(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KundenDaten kundenDatenDTO);


    @GET("/api/kategories")
    Single<LinkedList<KategorieDTO>> getAllKategories(@Header("Authorization")ResponseAuthentication responseAuthentication,@QueryMap(encoded = true) Map<String,String> config);

    @POST("/api/kategories")
    Single<KategorieDTO> createKategorie(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KategorieDTO kategorieDTO);

    @PUT("/api/kategories")
    Single<KategorieDTO> saveKategorie(@Header("Authorization")ResponseAuthentication responseAuthentication,@Body KategorieDTO kategorieDTO);

    @GET("/api/kategories/{id}")
    Single<KategorieDTO> getKategorie(@Header("Authorization")ResponseAuthentication responseAuthentication,@Path("id") long id);
    // REST requests

    // z.b Post für Übung hochladen

    //z.b post für Kategorie erstellenô

    //z.b get für alle übungen anzeigen
}
