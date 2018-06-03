package com.example.hp.githubprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserDetailPotrait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_potrait);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            UserDetailFragment fragment = new UserDetailFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
        }
    }
}
