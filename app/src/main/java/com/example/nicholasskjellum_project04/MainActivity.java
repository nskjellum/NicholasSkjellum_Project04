package com.example.nicholasskjellum_project04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public int numberoflines = 0;
    public double balance = 0;
    Button addBut, subBut;
    EditText noteTxt, dateTxt, amountTxt;
    TextView total;
    LinearLayout history;
    //sharedsettings
    public static final String MyPreferences = "MyPrefs";
    public static final String storedBal = "storedbal";
    public static final String collection = "collection";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get saved settings




        setContentView(R.layout.activity_main);
        addBut = findViewById(R.id.addBut);
        subBut = findViewById(R.id.subBut);
        noteTxt = findViewById(R.id.reasonIn);
        dateTxt = findViewById(R.id.dateIn);
        amountTxt = findViewById(R.id.amountIn);
        total = findViewById(R.id.currentBal);
        history = findViewById(R.id.history);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        balance = Double.parseDouble(sharedPreferences.getString("storedBal", "0.00"));
        total.setText("$" + String.valueOf(balance));
        numberoflines = (sharedPreferences.getInt("collection", 0));

        for(int i = 0; i < numberoflines; i++) {

            TextView tvR = new TextView(MainActivity.super.getBaseContext());
            tvR.setText(sharedPreferences.getString(String.valueOf(i),"empty line"));
            history.addView(tvR);

        }



        addBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView tv1 = new TextView(MainActivity.super.getBaseContext());



                if (!amountTxt.getText().toString().isEmpty()) {

                    String message1 = "Added $" + amountTxt.getText() +  " on " + dateTxt.getText() + " from " + noteTxt.getText();
                    tv1.setText(message1);
                    history.addView(tv1);
                    balance = balance + Double.parseDouble(amountTxt.getText().toString());

                    String final01 = String.format("%.2f", balance);

                    total.setText("$" + final01);
                }
                else {

                    String message1 = "Added $ 0.00 on " + dateTxt.getText() + " from " + noteTxt.getText();
                    tv1.setText(message1);
                    history.addView(tv1);

                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("storedBal", String.valueOf(balance));
                editor.putString(String.valueOf(numberoflines), tv1.getText().toString());
                editor.putInt("collection", numberoflines);
                editor.commit();
            }
        });

        subBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tv2 = new TextView(MainActivity.super.getBaseContext());

                if (!amountTxt.getText().toString().isEmpty()) {
                    String message1 = "Spent $" + amountTxt.getText() + " on " + dateTxt.getText() + " for " + noteTxt.getText();
                    tv2.setText(message1);
                    history.addView(tv2);
                    balance = balance - Double.parseDouble(amountTxt.getText().toString());

                    String final02 = String.format("%.2f", balance);

                    total.setText("$" + final02);
                }
                else {
                    String message1 = "Spent $ 0.00 on " + dateTxt.getText() + " for " + noteTxt.getText();
                    tv2.setText(message1);
                    history.addView(tv2);
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("storedBal", String.valueOf(balance));
                editor.putString(String.valueOf(numberoflines), tv2.getText().toString());
                editor.putInt("collection", numberoflines);
                editor.commit();

            }
        });


    }



}
