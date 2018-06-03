package com.example.hp.githubprofile;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 20-03-2018.
 */

public class UserRecyclerAdapter  extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder> {

   public interface OnItemClickListener{

       void onItemClick(int position);
   }


    OnItemClickListener listener;

    Context context;
    ArrayList<DetailUser> users;

    public UserRecyclerAdapter(Context context,ArrayList<DetailUser> users ,  OnItemClickListener listener) {

        this.context=context;
        this.users=users;
        this.listener=listener;

    }

    @Override
    public UserRecyclerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_layout,parent,false);
        UserViewHolder holder=new UserViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UserRecyclerAdapter.UserViewHolder holder, final int position) {

        DetailUser details = users.get(position);
        holder.username.setText(details.getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition());

            }
        });

        Picasso.get().load(details.getAvatarUrl()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        View row;
        ImageView avatar;
        TextView username;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.row=itemView;
            avatar=row.findViewById(R.id.imageView);
            username=row.findViewById(R.id.textView);

        }
    }
}
