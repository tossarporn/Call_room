package com.example.phobia.call_room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Crateroom extends AppCompatActivity {
    private String useridString,nameroomString;
    private String responeString,message;
    private Boolean status;
    private EditText nameEditText;
    private Button okButton,backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crateroom);
        useridString = getIntent().getStringExtra("userid");
        nameEditText = (EditText) findViewById(R.id.editText3);
        okButton = (Button) findViewById(R.id.sure);
        backButton = (Button) findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //nameroomString = nameEditText.getText().toString();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameEditText.getText().toString().equals("")) {
                    Get_data get_data = new Get_data(getApplicationContext());
                    Myconfig myconfig = new Myconfig();
                    get_data.execute(myconfig.getService_create_room()+"userid="+useridString+"&"+"nameroom="+nameEditText.getText().toString());
                    try {
                        responeString= get_data.get().toString();

                        JSONObject jsonObject = new JSONObject(responeString);
                        status = jsonObject.getBoolean("status");
                        message = jsonObject.getString("message");

                        if (status == true) {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Toast.makeText(getApplicationContext(), useridString, Toast.LENGTH_LONG).show();



    }
}
