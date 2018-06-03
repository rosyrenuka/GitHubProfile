package com.example.hp.githubprofile;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by HP on 20-03-2018.
 */

public interface UserProfile {

// 3 things      get    url     method callback

    @GET("users/{uname}")
    Call<DetailUser> getProfile(@Path("uname") String username);



    @GET( "users/{uname}/followers")
    Call<ArrayList<DetailUser>> getFollower(@Path("uname") String username);


}
