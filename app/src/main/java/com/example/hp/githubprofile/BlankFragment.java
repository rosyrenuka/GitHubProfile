package com.example.hp.githubprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {



    public interface UserSelectedCallBack
    {
        void onUserSelected(DetailUser detailUser);
    }

    UserSelectedCallBack  mCallBack;



    RecyclerView recyclerView;
    UserRecyclerAdapter recyclerAdapter;
    ArrayList<DetailUser> detailList=new ArrayList<>();

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

     try {
         mCallBack = (UserSelectedCallBack) context;
     }
       catch(ClassCastException e)
        {
          throw new ClassCastException("activity shud implement userSelectedCallBack");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View row = inflater.inflate(R.layout.fragment_blank, container, false);

        super.onCreate(savedInstanceState);

       // setContentView(R.layout.fragment_blank);

      recyclerView = row.findViewById(R.id.recycler);

        // Intent data1 = getIntent();

       // String username = data1.getStringExtra("user");

        String username = "rohanraarora";

        if (username != null) {
            fetchFollowers(username);
            recyclerAdapter = new UserRecyclerAdapter(getContext(), detailList, new UserRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    DetailUser detailUser=detailList.get(position);
                    mCallBack.onUserSelected(detailUser);

                }
            });

            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        return row;
    }
    private void fetchFollowers(String username) {

        // retrofit etc

        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/").build();


        UserProfile userProfile = retrofit.create(UserProfile.class);

        Call<ArrayList<DetailUser>> call=userProfile.getFollower(username);

        call.enqueue(new Callback<ArrayList<DetailUser>>() {
            @Override
            public void onResponse(Call<ArrayList<DetailUser>> call, Response<ArrayList<DetailUser>> response) {

                ArrayList<DetailUser> users=response.body();

                if(users!=null)
                {

                    detailList.clear();
                    detailList.addAll(users);
                    recyclerAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<DetailUser>> call, Throwable t) {

                Toast.makeText(getContext(),"Failed to load followers ",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
