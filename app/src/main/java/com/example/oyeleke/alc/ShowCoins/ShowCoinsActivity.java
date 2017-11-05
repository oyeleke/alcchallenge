package com.example.oyeleke.alc.ShowCoins;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.oyeleke.alc.R;
import com.example.oyeleke.alc.adapters.CoinsListAdapter;
import com.example.oyeleke.alc.models.Coins;

import java.util.List;

public class ShowCoinsActivity extends AppCompatActivity implements ShowCoinsContract{

    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    CoinsListAdapter coinsListAdapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coins);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        progressBar = (ProgressBar)findViewById(R.id.postProgressBar);
        progressBar.setVisibility(View.GONE);
        ShowCoinsPresenter showCoinsPresenter = new ShowCoinsPresenter(this);
        showCoinsPresenter.assessCoinsInfoBeforeChange();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(ShowCoinsActivity.this, error, Toast.LENGTH_LONG).show();

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayCoinsList(List<Coins> coinsList) {

        coinsListAdapter = new CoinsListAdapter(getApplicationContext(),coinsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(coinsListAdapter);

        coinsListAdapter.notifyDataSetChanged();
    }
}
