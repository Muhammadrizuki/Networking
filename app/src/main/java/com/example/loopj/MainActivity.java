package com.example.loopj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvName, tvEmail;
    private ImageView ivAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        ivAvatar = findViewById(R.id.iv_avatar);

        UserJob userJob = new UserJob();
        userJob.setName("Rahmatullah");
        userJob.setJob("Pilot");

        Call<UserJob> client = ApiConfig.getApiService().postUser(userJob);
        client.enqueue(new Callback<UserJob>() {
            @Override
            public void onResponse(Call<UserJob> call, Response<UserJob>
                    response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UserJob user = response.body();
                        System.out.println(user.getName());
                        System.out.println(user.getJob());
                        System.out.println(user.getId());
                        System.out.println(user.getCreateAt());


                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<UserJob> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
}