package com.example.phobia.call_room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class Show_add_user extends AppCompatActivity {
    private String iduser, jsonrespone,nameuser,pathimage,roomid;
    private Boolean status;
    private String message;
    private TextView nameTextView;
    private ImageView profile;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_add_user);

        iduser = getIntent().getStringExtra("userid");
        roomid = getIntent().getStringExtra("roomidString");
        final Get_data get_data =new Get_data(getApplicationContext());
        final Myconfig myconfig = new Myconfig();
        get_data.execute(myconfig.getService_show_add_user() + "userid=" + iduser);
        nameTextView = (TextView) findViewById(R.id.textView3);
        profile = (ImageView) findViewById(R.id.imageView6);
        addButton = (Button) findViewById(R.id.button);


        try {

            jsonrespone = get_data.get();
            JSONObject jsonObject = new JSONObject(jsonrespone);
            nameuser = jsonObject.getString("id");
            pathimage = jsonObject.getString("path_image");
            status = jsonObject.getBoolean("status");
            message = jsonObject.getString("message");


            if (status == true) {

                Picasso.with(getApplicationContext()).load(pathimage).into(profile);
                nameTextView.setText(nameuser);




            } else {
                Toast.makeText(getApplicationContext(),  message, Toast.LENGTH_LONG).show();

            }



        } catch (Exception e) {

        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Get_data get_data1 = new Get_data(getApplicationContext());
                Myconfig myconfig1 = new Myconfig();
                get_data1.execute(myconfig1.getService_add_user()+"userid="+nameuser+"&"+"roomid="+roomid);
                final String statusserver1;
                final String message1;
                final String responeserver1;
                try {

                    responeserver1 = get_data1.get();
                    Toast.makeText(getApplicationContext(),  responeserver1, Toast.LENGTH_LONG).show();

                } catch (Exception e) {

                }
            }
        });



    }
}
