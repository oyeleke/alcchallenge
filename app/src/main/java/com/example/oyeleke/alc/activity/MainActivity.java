package com.example.oyeleke.alc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.oyeleke.alc.R;
import com.example.oyeleke.alc.ShowCoins.ShowCoinsActivity;
import com.example.oyeleke.alc.adapters.ShowCardViewAdapter;
import com.example.oyeleke.alc.models.Coins;
import com.example.oyeleke.alc.models.KeepCoinsAdded;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Coins>coinsList = new ArrayList<>();
    RecyclerView recyclerView;
    ShowCardViewAdapter cardViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        KeepCoinsAdded keepCoinsAdded = new KeepCoinsAdded();
        for(Coins vcoin: keepCoinsAdded.getKeepCoins() ){
            coinsList.add(vcoin);
        }

        try{
        cardViewAdapter = new ShowCardViewAdapter(this,coinsList);
        }catch (Exception e){
            e.printStackTrace();
        }


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardViewAdapter);
        try{
        cardViewAdapter.notifyDataSetChanged();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowCoinsActivity.class));
            }
        });
    }






}
