package com.example.oyeleke.alc.ShowCoins;

import com.example.oyeleke.alc.models.Coins;

import java.util.List;

/**
 * Created by oyeleke on 11/4/17.
 */

public interface ShowCoinsContract {

    void showProgressBar();
    void showErrorMessage(String error);
    void hideProgressBar();
    void displayCoinsList(List<Coins>coinsList);
}
