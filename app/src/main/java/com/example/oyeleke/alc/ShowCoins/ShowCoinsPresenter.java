package com.example.oyeleke.alc.ShowCoins;

import com.android.volley.VolleyError;
import com.example.oyeleke.alc.application.App;
import com.example.oyeleke.alc.Utils.Utils;
import com.example.oyeleke.alc.models.Coins;
import com.example.oyeleke.alc.network.NetworkRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

/**
 * Created by oyeleke on 11/4/17.
 */

public class ShowCoinsPresenter {

    private ShowCoinsContract view;
    private List<Coins> coinsList = new ArrayList<>();
    String coinsName[] = {"BTC","ETH"};

    public ShowCoinsPresenter(ShowCoinsContract view){
        this.view = view;
    }

    public void assessCoinsInfoBeforeChange(){

        view.showProgressBar();
        String url = "https://min-api.cryptocompare.com/data/all/coinlist";

        getCoinsList(url);
    }

    public void getCoinsList(String url){

        if(!Utils.isConnected(App.getAppContext())){
            view.showErrorMessage("Sorry it looks like your network is down");
            return;
        }

        final NetworkRequest request = new NetworkRequest("Coins").setCallback(new NetworkRequest.RequestCallback() {
            @Override
            public void onRequestStart(String tag) {

            }

            @Override
            public void onRequestCompleted(String tag, String response) throws JSONException {
                view.hideProgressBar();
                try{
                    JSONObject object = new JSONObject(response);
                    JSONObject data = object.getJSONObject("Data");
                    System.out.println(data);


                      for(String coinsNames : coinsName){
                        JSONObject coins = data.getJSONObject(coinsNames);

                        String id = coins.getString("Id");
                          String imageUrl ="";
                          try {
                               imageUrl = coins.getString("ImageUrl");
                          }catch (Exception e){
                              e.printStackTrace();
                          }
                        String name = coins.getString("Name");
                        String coinName = coins.getString("CoinName");
                        String symbol = coins.getString("Symbol");


                        Coins coin = new Coins(id,imageUrl,name,coinName,symbol);
                        coinsList.add(coin);
                        System.out.println(coinsList.size());
                          System.out.println(imageUrl);
                      }



                }catch (JSONException e){
                    e.printStackTrace();
                }

                view.displayCoinsList(coinsList);
            }

            @Override
            public void onRequestError(String tag, VolleyError volleyError) {
                view.hideProgressBar();
                String errorMessage = volleyError.getMessage();
                view.showErrorMessage(errorMessage == null ? "Oops an error occurred please try again,from error request":errorMessage);

            }

            @Override
            public void onRequestCancelled() {

            }
        });

     request.execute(url,GET);
    }
}
