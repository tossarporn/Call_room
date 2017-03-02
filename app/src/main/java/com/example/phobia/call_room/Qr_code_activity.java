package com.example.phobia.call_room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class Qr_code_activity extends AppCompatActivity {
    private String datauserString, qrpath;
    private ImageView qrcode;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_activity);
        datauserString = getIntent().getStringExtra("datauser");
        qrcode = (ImageView) findViewById(R.id.imageView3);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        try {
            JSONObject jsonObject = new JSONObject(datauserString);
            qrpath = jsonObject.getString("qr_path");
            Picasso.with(getApplicationContext()).load(qrpath).into(qrcode);
            //Toast.makeText(getApplicationContext(), qrpath, Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }


    }
}
