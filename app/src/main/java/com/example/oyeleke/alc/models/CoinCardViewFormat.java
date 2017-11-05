package com.example.oyeleke.alc.models;

/**
 * Created by oyeleke on 11/5/17.
 */

public class CoinCardViewFormat {

    private String coinName;
    private String imageUrl;
    private String price;
    private String currency;




    public CoinCardViewFormat(String coinName, String imageUrl, String price, String currency) {

        this.coinName = coinName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.currency = currency;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
