package com.example.hp.githubprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {

    TextView tname;
    TextView tcompanyName;
    TextView tfollowers;

    Button b2;

    ImageView image;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tname=findViewById(R.id.tvName);
        tcompanyName=findViewById(R.id.tvCompany);
        tfollowers=findViewById(R.id.tvFollowers);
        image=findViewById(R.id.iv1);
        b2=findViewById(R.id.buttonF);
       progressBar=findViewById(R.id.pg);

       progressBar.setVisibility(View.VISIBLE);

        Intent intent = getIntent();

        final String username = intent.getStringExtra("userName");

        FetchProfile(username);


        b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent data1=new Intent(UserActivity.this , FollowerActivity.class);

               data1.putExtra("user",username);
               startActivity(data1);

               Toast.makeText(UserActivity.this, "going to follower activity", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void FetchProfile(String username) {

        Retrofit retrofit=new Retrofit.Builder().
                           addConverterFactory(GsonConverterFactory.create()).
                            baseUrl("https://api.github.com/").
                            build();


       UserProfile userProfile = retrofit.create(UserProfile.class);
       Call<DetailUser> call=userProfile.getProfile(username);


        call.enqueue(new Callback<DetailUser>() {
            @Override
            public void onResponse(Call<DetailUser> call, Response<DetailUser> response) {

                progressBar.setVisibility(View.GONE);
                DetailUser details=response.body();
                tname.setText(" NAME  :  "+details.getName());
                tcompanyName.setText("COMPANY :  "+details.company);
                tfollowers.setText("No. of followers :  "+details.getFollowers());

                b2.setText(details.getFollowers()+ "  Followers");

                String pathImage=details.getAvatarUrl();
                Picasso.get().load(pathImage).placeholder(R.drawable.load1).into(image);
            }

            @Override
            public void onFailure(Call<DetailUser> call, Throwable t) {

                Toast.makeText(UserActivity.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
