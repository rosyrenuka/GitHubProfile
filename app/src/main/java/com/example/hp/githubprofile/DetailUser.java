package com.example.hp.githubprofile;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 19-03-2018.
 */


 @Entity(tableName = "user")
 public class DetailUser {

     @ColumnInfo(name = "user_name")
    String name;

     @ColumnInfo(name = "company_name")
    String company;

    int followers;

    @SerializedName("avatar_url")
    String avatarUrl;

    @PrimaryKey
    @SerializedName("login")
    String username;



    public DetailUser(String name, String company, int followers,String avatarUrl,String username) {
        this.name = name;
        this.company = company;
        this.followers = followers;
        this.avatarUrl=avatarUrl;
        this.username=username;
    }


    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getFollowers() {
        return followers;
    }

    public String getCompany() {
        return company;
    }

    public String getUsername() {
        return username;
    }


}
