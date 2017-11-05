package com.example.oyeleke.alc.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by oyeleke on 11/4/17.
 */

public class Utils {

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()){
            return true;
        }
        return false;
    }

    public static String roundUpDoubleTo3SF(double j){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(j);

    }
}
