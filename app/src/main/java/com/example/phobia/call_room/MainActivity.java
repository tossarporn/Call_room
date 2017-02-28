package com.example.phobia.call_room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button loginButton, registerButton;
    private EditText userEditText, passEditText;
    private String userString;
    private String passwordString;
    private String jsonrespone;
    private String  datauser,message;
    private Boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindwidget();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userString = userEditText.getText().toString().trim();
                passwordString = passEditText.getText().toString().trim();
                if (userString.equals("") || passwordString.equals("")) {
                    Toast.makeText(MainActivity.this, "กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_LONG).show();

                } else {

                    Myconfig myconfig = new Myconfig();
                    Get_data get_data = new Get_data(MainActivity.this);
                    try {
                        get_data.execute(myconfig.getService_login() +
                                "user=" + userString + "&" +
                                "pass=" + passwordString

                        );

                        jsonrespone = get_data.get().toString();
                        JSONObject jsonObject = new JSONObject(jsonrespone);
                        status = jsonObject.getBoolean("status");
                        message = jsonObject.getString("message");
                        datauser = jsonObject.getString("data_user");

                        if (status == true) {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,Listroom_activity.class);
                            intent.putExtra("data_user", datauser);
                            startActivity(intent);


                        } else {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                        Log.d("login", "login==>"+ jsonrespone);
                    } catch (Exception e) {
                        Log.d("login", "login==>"+ e.toString());
                    }



                }
            }
        });


    }//main method

    private void bindwidget() {

        //Bind Widget
        registerButton = (Button) findViewById(R.id.register);
        loginButton = (Button) findViewById(R.id.login);
        userEditText = (EditText) findViewById(R.id.user);
        passEditText = (EditText) findViewById(R.id.password);




    }
}//Main Class
