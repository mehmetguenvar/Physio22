package com.example.mehme.physio22;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mehme.physio22.Retrofit.PhysioService;
import com.example.mehme.physio22.Retrofit.TokenDTO;
import com.example.mehme.physio22.Retrofit.UserLoginDTO;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserLoginDTO ul = new UserLoginDTO();
        ul.setPassword("user");
        ul.setUsername("user");
        PhysioService ps = new PhysioService();
        ps.getService().authenticate(ul).enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                Toast.makeText(getApplicationContext(),response.body().getId_token(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        setContentView(R.layout.activity_main);
    }

}
