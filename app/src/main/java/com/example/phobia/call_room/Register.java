package com.example.phobia.call_room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;
import org.json.JSONObject;

import java.io.File;

public class Register extends AppCompatActivity {

    private Button okButton, backButton;
    private String fnameString;
    private String lnameString;
    private String surnameString;
    private String telString;
    private String userString;
    private String passwordString;
    private EditText fnameEditText, lnameEditText, surnameEditText, telEditText, userEditText, passwordEditText;
    private ImageView personImageView;
    private Uri uri;
    private Boolean statusimage = true;
    private String realPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindwidget();
        buttoncontroller();

    }//main method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            statusimage = false;

            uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                personImageView.setImageBitmap(bitmap);

            } catch (Exception e) {
            }
            // SDK < API11
            if (Build.VERSION.SDK_INT < 11) {
                realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());

                // SDK >= 11 && SDK < 19
            }else if (Build.VERSION.SDK_INT < 19) {
                realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());

                // SDK > 19 (Android 4.4)
            }else {
                realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
            }
            Log.d("path", "path ==>" + realPath);
            Toast.makeText(Register.this, realPath, Toast.LENGTH_SHORT).show();


        }

    }

    private void buttoncontroller() {


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }


        });


        personImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"โปรดเลือกรูปภาพ"),1);
            }
        });



        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnameString = fnameEditText.getText().toString().trim();
                lnameString = lnameEditText.getText().toString().trim();
                surnameString = surnameEditText.getText().toString().trim();
                telString = telEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (fnameString.equals("") || lnameString.equals("") || surnameString.equals("")
                        || telString.equals("") || userString.equals("") || passwordString.equals("")) {
                    Toast.makeText(Register.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_LONG).show();

                } else if (statusimage == true) {
                    Toast.makeText(Register.this, "กรุณาเลือกรูปภาพ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Register.this, "สมัครสมาชิกสำเร็จ", Toast.LENGTH_LONG).show();

                    Adddata();

                }
                //Toast.makeText(Register.this,fnameString,Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void Adddata() { //ร้องขอการใช้โปรโตคอล port 21 ftp
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                    .Builder()
                    .permitAll()
                    .build();
            StrictMode.setThreadPolicy(policy);
            //add image to server masterung
            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.swiftcodingthai.com", 21,
                    "bsru@swiftcodingthai.com", "Abc12345");
            simpleFTP.bin();
            simpleFTP.cwd("img_user");
            simpleFTP.stor(new File(realPath));
            simpleFTP.disconnect();

            //add text to data server

            Get_data get_data = new Get_data(Register.this);
            Myconfig myconfig = new Myconfig();

            get_data.execute(myconfig.getService_register()+

                    "fname="+fnameString+"&"+
                    "lname="+lnameString+"&"+
                    "surname="+surnameString+"&"+
                    "tel="+telString+"&"+
                    "user="+userString+"&"+
                    "password="+passwordString+"&"+
                    "path_image="+realPath
            );

            String responejson = get_data.get();
            JSONObject jsonObject = new JSONObject(responejson);
            Boolean status = jsonObject.getBoolean("status");
            String message = jsonObject.getString("message");
            if (status == true) {
                Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
            }




        } catch (Exception e) {
            Toast.makeText(Register.this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }


    private void bindwidget() {
        backButton = (Button) findViewById(R.id.back);
        personImageView = (ImageView) findViewById(R.id.person);
        okButton = (Button) findViewById(R.id.ok);
        fnameEditText = (EditText) findViewById(R.id.fname);
        lnameEditText = (EditText) findViewById(R.id.lname);
        surnameEditText = (EditText) findViewById(R.id.surname);
        telEditText = (EditText) findViewById(R.id.tel);
        userEditText = (EditText) findViewById(R.id.user);
        passwordEditText = (EditText) findViewById(R.id.password);
    }
}//Class Method