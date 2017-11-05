package com.example.oyeleke.alc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oyeleke.alc.R;
import com.example.oyeleke.alc.Utils.Utils;

public class MakeConversionActivity extends AppCompatActivity {

    TextView amountConverted,currencyView;
    EditText amountToBeConverted;
    Button conversion;
    Double dBaseAmount;

    String currency, amountBeingConverted,baseAmount,coinName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_conversion);
        amountConverted = (TextView)findViewById(R.id.convertedAmount);
        currencyView = (TextView)findViewById(R.id.currency);
        amountToBeConverted = (EditText)findViewById(R.id.priceInputTextView);
        conversion = (Button)findViewById(R.id.makeConversion);

        Bundle extras = getIntent().getExtras();

        currency = extras.getString("currency");
        baseAmount=extras.getString("value");
        coinName=extras.getString("name");

        currencyView.setText(currency);
        try {

            dBaseAmount = Double.valueOf(baseAmount);
        }catch (Exception e){
            e.printStackTrace();
        }
        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountBeingConverted = amountToBeConverted.getText().toString();
                if(TextUtils.isEmpty(amountBeingConverted)){
                    amountToBeConverted.setError("Please put in a valid number");
                    return;
                }
                if(!TextUtils.isDigitsOnly(amountBeingConverted)){
                    amountToBeConverted.setError("Please put in a valid number");
                    return;
                }
                try {
                    Double damountBeingConverted = Double.valueOf(amountBeingConverted);

                    double amount = damountBeingConverted / dBaseAmount;
                    Log.d("baseAmount", String.valueOf(dBaseAmount));
                    Log.d("baseBeingConvAmount", String.valueOf(damountBeingConverted));
                    Log.d("baseAmount", String.valueOf(amount));


                    amountConverted.setText(String.valueOf(Utils.roundUpDoubleTo3SF(amount)) + " " + coinName);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
