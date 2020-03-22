package com.example.mehme.physio22.webservices;

import com.example.mehme.physio22.dtos.UserLoginDTO;
import com.google.gson.Gson;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    private static String server = "http://10.0.0.34:80";
    public static IPhysioService physioService;
    public static Gson gson;
    public static UserLoginDTO userLoginDTO;
    public static Retrofit retrofit;
    public static Flowable<ResponseAuthentication> flo;
    public static ResponseAuthentication token = new ResponseAuthentication("");

    public static void init(){
        userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("admin");
        userLoginDTO.setPassword("admin");

        IPhysioService webservicex = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create()).build().create(IPhysioService.class);

        gson = new Gson();

        flo = webservicex.authenticate(userLoginDTO).doOnNext(new Consumer<ResponseAuthentication>() {
            @Override
            public void accept(ResponseAuthentication responseAuthentication) throws Exception {
                if(responseAuthentication != null) token.setId_token(responseAuthentication.getId_token());
            }
        }).toFlowable(BackpressureStrategy.LATEST).share();

        //retrofit = new Retrofit.Builder()

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(new RxJava2ReauthCallAdapterFactory(flo,throwable -> { if(throwable != null && throwable.getMessage() != null) return throwable.getMessage().toLowerCase().contains("unauthorized"); else return true;},2))
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        physioService = retrofit.create(IPhysioService.class);

    }

    public static String getTokenForAuth(){
        return token.getId_token();
    }
}
