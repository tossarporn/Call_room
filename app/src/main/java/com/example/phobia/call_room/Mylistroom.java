package com.example.phobia.call_room;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Mylistroom extends AppCompatActivity {
    private static final String STRING = "com.google.zxing.client.android.SCAN";
    private String[] modeStrings = new String[]{"QR_CODE_MODE", "BAR_CODE_MODE"};
    private int[] modeInts = new int[]{0,1};
    private String nameroomString;
    private String roomidString;
    private String data_userString;
    private String respone_server;
    private Boolean status;
    private String jsonarray_userString;
    private ListView mylist;
    private Button insert;
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistroom);
        data_userString = getIntent().getStringExtra("datauser").toString();
        nameroomString = getIntent().getStringExtra("nameroom").toString();
        roomidString = getIntent().getStringExtra("roomid").toString();
        //Toast.makeText(getApplicationContext(),nameroomString+" "+roomidString, Toast.LENGTH_SHORT).show();
        mylist = (ListView) findViewById(R.id.mylist);
        Get_data get_data = new Get_data(Mylistroom.this);
        Myconfig myconfig = new Myconfig();
        insert = (Button) findViewById(R.id.insert);

        get_data.execute(myconfig.getService_userjoinroom() + "roomid=" + roomidString);

        try {

            respone_server = get_data.get().toString();
            JSONObject jsonObject = new JSONObject(respone_server);
            status = jsonObject.getBoolean("status");
            jsonarray_userString = jsonObject.getString("data");

            if (status == true) {

                JSONArray jsonArray = new JSONArray(jsonarray_userString);
                final String name_user[] = new String[jsonArray.length()];
                final String tel[] = new String[jsonArray.length()];
                final String path_image[] = new String[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    name_user[i] = jsonObject1.getString("surname");
                    tel[i] = jsonObject1.getString("tel");
                    path_image[i] = jsonObject1.getString("path_image");
                    //Toast.makeText(getApplicationContext(), name_user[i] + "==" + tel[i] + "==" + path_image[i], Toast.LENGTH_SHORT).show();

                }

                Adapter_user_join adapter_user_join = new Adapter_user_join(name_user, tel, path_image, Mylistroom.this);
                mylist.setAdapter(adapter_user_join);
                mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + tel[i]));

                        if (ActivityCompat.checkSelfPermission(Mylistroom.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(intent);
                    }
                });

                //Toast.makeText(getApplicationContext(), jsonarray_userString, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), status.toString(), Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
        }

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScan(modeStrings[1],modeInts[1]);
            }
        });
    }//Main Method


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            String[] strings = new String[]{"QR code = ", "BAR code = "};
            result = data.getStringExtra("SCAN_RESULT");
            Intent intent = new Intent(getApplicationContext(), Show_add_user.class);
            intent.putExtra("userid",result);
            intent.putExtra("roomidString", roomidString);
            startActivity(intent);
            finish();







            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();




        }   // if

    }

    private void myScan(String modeString, int modeInt) {

        try {

            Intent intent = new Intent(STRING);
            intent.putExtra("SCAN_MODE", modeString);
            startActivityForResult(intent, modeInt);


        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Please Install Barcode Scanner",
                    Toast.LENGTH_SHORT).show();
        }

    }   // myScan

}//Main Class
