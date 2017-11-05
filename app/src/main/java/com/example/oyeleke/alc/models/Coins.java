package com.example.oyeleke.alc.models;

/**
 * Created by oyeleke on 11/4/17.
 */

public class Coins {

    private String id;
    private String imageUrl;
    private String name;
    private String coinName;
    private String symbol;

    private String initialUrl  = "https://min-api.cryptocompare.com/data/all/coinlist";

    public Coins(String id, String imageUrl, String name, String coinName, String symbol) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.coinName = coinName;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return initialUrl+imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
