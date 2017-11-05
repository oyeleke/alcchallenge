package com.example.oyeleke.alc.parser;

import com.example.oyeleke.alc.Utils.Constants;

import org.json.JSONObject;

/**
 * Created by oyeleke on 10/29/17.
 */

public class Parser {

    public static boolean hasError(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(Constants.ResponseMessage).equals(Constants.SuccessMessage);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

}
