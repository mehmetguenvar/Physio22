package com.example.mehme.physio22.activities;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mehme.physio22.R;
import com.example.mehme.physio22.dtos.UebungDTO;
import com.example.mehme.physio22.webservices.PhysioService;
import com.example.mehme.physio22.dtos.TokenDTO;
import com.example.mehme.physio22.dtos.UserLoginDTO;
import com.example.mehme.physio22.webservices.Rest;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UserLoginDTO ul = new UserLoginDTO();
        //ul.setPassword("user");
        //ul.setUsername("user");
        //PhysioService ps = new PhysioService();
        //ps.getService().authenticate(ul).enqueue(new Callback<TokenDTO>() {
        //    @Override
        //    public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
        //        Toast.makeText(getApplicationContext(),response.body().getId_token(),Toast.LENGTH_LONG).show();
        //    }
//
        //    @Override
        //    public void onFailure(Call<TokenDTO> call, Throwable t) {
        //        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
        //    }
        //});



        setContentView(R.layout.activity_main);

        Rest.init();

        Rest.physioService.getAllUebung(Rest.token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UebungDTO>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UebungDTO> uebungDTOS) {
                        Toast.makeText(getApplicationContext(),uebungDTOS.get(0).getBezeichnung(),Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
