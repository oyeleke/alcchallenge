package com.example.oyeleke.alc.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oyeleke.alc.activity.MainActivity;
import com.example.oyeleke.alc.R;
import com.example.oyeleke.alc.models.Coins;
import com.example.oyeleke.alc.models.KeepCoinsAdded;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyeleke on 11/5/17.
 */

public class CoinsListAdapter extends RecyclerView.Adapter<CoinsListAdapter.MyViewHolder>{

    private Context context;
    private List<Coins>coinsList;
    private List<Coins>addToCardViewCoins = new ArrayList<>();

    public CoinsListAdapter(Context context , List<Coins>coinsList){
        this.coinsList = coinsList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView longCoinsNameTextView, shortCoinsNameTextView;
        ImageView coinsImageView;
        ViewGroup coinLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            longCoinsNameTextView = itemView.findViewById(R.id.coinFullName);
            shortCoinsNameTextView = itemView.findViewById(R.id.shortcoinnameTextView);
            coinsImageView = itemView.findViewById(R.id.coinImageView);
            coinLayout = itemView.findViewById(R.id.coinDetails);

        }
    }

    @Override
    public CoinsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.coin_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CoinsListAdapter.MyViewHolder holder, int position) {
        final Coins coins = coinsList.get(position);
        holder.shortCoinsNameTextView.setText(coins.getName());
        holder.longCoinsNameTextView.setText(coins.getCoinName());
        Glide.with(context).load(coins.getImageUrl())
                .placeholder(R.drawable.ic_image_black_24dp)
                .fitCenter().into(holder.coinsImageView);

        holder.coinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check to see if coin has been clicked
                boolean coinHasBeenSelectedBefore = false;
                for(Coins cname : KeepCoinsAdded.getKeepCoins()){
                    if(cname.getId().equals(coins.getId())){
                        coinHasBeenSelectedBefore = true;
                    }
                }
                if(coinHasBeenSelectedBefore){
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else {
                    KeepCoinsAdded keepCoinsAdded = new KeepCoinsAdded(coins);
                    coinsList.remove(coins);
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return coinsList.size();
    }
}
