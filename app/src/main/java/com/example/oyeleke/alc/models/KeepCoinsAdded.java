package com.example.oyeleke.alc.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oyeleke on 11/5/17.
 */

public class KeepCoinsAdded {

    private static List<Coins> keepCoins =  new ArrayList<>();

    public KeepCoinsAdded(List<Coins> keepCoins) {
        this.keepCoins = keepCoins;
    }
    public KeepCoinsAdded(Coins coin){
        keepCoins.add(coin);
    }

    public KeepCoinsAdded() {
    }

    public static List<Coins> getKeepCoins() {
        return keepCoins;
    }

    public void setKeepCoins(List<Coins> keepCoins) {
        this.keepCoins = keepCoins;
    }
}
