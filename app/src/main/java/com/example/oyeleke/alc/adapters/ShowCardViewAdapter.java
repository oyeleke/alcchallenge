package com.example.oyeleke.alc.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.oyeleke.alc.R;
import com.example.oyeleke.alc.Utils.Utils;
import com.example.oyeleke.alc.activity.MakeConversionActivity;
import com.example.oyeleke.alc.models.Coins;
import com.example.oyeleke.alc.network.NetworkRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.Request.Method.GET;

/**
 * Created by oyeleke on 11/5/17.
 */

public class ShowCardViewAdapter extends RecyclerView.Adapter<ShowCardViewAdapter.MyViewHolder> {

    private Context context;
    private List<Coins>coinsList;

    private ArrayAdapter<CharSequence>arrayAdapter;
    private String currencyChange ;
    private String currentCurrency;




    public ShowCardViewAdapter(Context context, List<Coins> coinsList){
        this.context = context;
        this.coinsList = coinsList;
        arrayAdapter =  ArrayAdapter.createFromResource(context,R.array.currency,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView priceTextView,nameTextView;
        ImageView coinImageView;
        Spinner currencySpinner;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);

            priceTextView = itemView.findViewById(R.id.priceTextView);
            coinImageView = itemView.findViewById(R.id.coinImageView);
            currencySpinner = itemView.findViewById(R.id.currencySpinner);
            nameTextView = itemView.findViewById(R.id.coinName);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

    @Override
    public ShowCardViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cardviewforcoins,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ShowCardViewAdapter.MyViewHolder holder, int position) {
        final Coins coins = coinsList.get(position);
        holder.nameTextView.setText(coins.getCoinName());
        final String coin = coins.getName();
        Glide.with(context).load(coins.getImageUrl())
                .placeholder(R.drawable.ic_image_black_24dp)
                .fitCenter().into(holder.coinImageView);

        holder.currencySpinner.setAdapter(arrayAdapter);
        holder.cardView.setEnabled(false);


        holder.currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               final String currency = (String) parent.getItemAtPosition(position);
                currentCurrency = currency;
                holder.priceTextView.setText("loading...");


                String url = "https://min-api.cryptocompare.com/data/price?fsym="+coin+"&tsyms="+currency;
                Log.d("currencyCoin",currency);
                Log.d("coinCurrency",coin);

                final NetworkRequest request = new NetworkRequest("price").setCallback(new NetworkRequest.RequestCallback() {
                    @Override
                    public void onRequestStart(String tag) {

                    }

                    @Override
                    public void onRequestCompleted(String tag, String response) throws JSONException {

                        try{
                            JSONObject jsonObject =new JSONObject(response);
                            System.out.print(response);
                            Log.d("resposeCall",response);
                            String price = jsonObject.getString(currency);
                            Double dprice = Double.valueOf(price);
                            currencyChange = price;
                            Log.d("currencyCall",price);
                            holder.priceTextView.setText(Utils.roundUpDoubleTo3SF(dprice));
                            holder.cardView.setEnabled(true);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onRequestError(String tag, VolleyError volleyError) {

                    }

                    @Override
                    public void onRequestCancelled() {

                    }
                });
                request.execute(url, GET);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.setIsRecyclable(false);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MakeConversionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",coins.getName());
                intent.putExtra("value",currencyChange);
                intent.putExtra("currency",currentCurrency);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }
}
