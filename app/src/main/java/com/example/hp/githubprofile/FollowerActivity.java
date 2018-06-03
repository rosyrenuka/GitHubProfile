package com.example.hp.githubprofile;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollowerActivity extends AppCompatActivity implements BlankFragment.UserSelectedCallBack{

  boolean isLandScape=false;

  FrameLayout container;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);
        container=findViewById(R.id.container);

        if(container!= null){
            isLandScape = true;
        }
    }

    @Override
    public void onUserSelected(DetailUser detailUser) {


        Bundle bundle=new Bundle();

        bundle.putString("username", detailUser.getUsername());
        bundle.putString("avatar",detailUser.getAvatarUrl());

        if(isLandScape)
        {
            // make  a new fragment
           UserDetailFragment fragment=new UserDetailFragment();

            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container,fragment).commit();
        }

          else
        {
            // portrait mode....just simple open a  new activity

            Intent intent=new Intent(this,UserDetailPotrait.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
