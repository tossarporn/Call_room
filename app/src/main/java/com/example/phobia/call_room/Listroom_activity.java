package com.example.phobia.call_room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Listroom_activity extends AppCompatActivity {
    private String datauserString;
    private String useridString;
    private String room_responeString;
    private Boolean status;
    private String roomresponearrayString;
    private ListView list_roomListView;
    private Button qrcodeButton,createroomButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listroom_activity);
        datauserString=getIntent().getStringExtra("data_user");
        list_roomListView = (ListView) findViewById(R.id.listroom);
        qrcodeButton = (Button) findViewById(R.id.myqr_code);
        createroomButton = (Button) findViewById(R.id.button4);


        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Qr_code_activity.class);
                intent.putExtra("datauser",datauserString);
                startActivity(intent);
            }
        });

        try {
            JSONObject jsonObject = new JSONObject(datauserString);
            useridString = jsonObject.getString("id");

            createroomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Crateroom.class);
                    intent.putExtra("userid",useridString);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
        }

        Get_data get_data = new Get_data(Listroom_activity.this);
        Myconfig myconfig = new Myconfig();


        try {
            get_data.execute(myconfig.getService_showlistroom() + "userid=" + useridString);
            room_responeString = get_data.get().toString();
            JSONObject jsonObject = new JSONObject(room_responeString);
            status = jsonObject.getBoolean("status");
            roomresponearrayString = jsonObject.getString("roomlist");

            if (status == true) {
                //Toast.makeText(getApplicationContext(), roomresponearrayString.toString(), Toast.LENGTH_LONG).show();
                try {
                    JSONArray jsonArray = new JSONArray(roomresponearrayString);
                    final String nameroom[] = new String[jsonArray.length()];
                    final String idroom[] = new String[jsonArray.length()];

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        nameroom[i] = jsonObject1.getString("name_room");
                        idroom[i] = jsonObject1.getString("id");
                        //Toast.makeText(getApplicationContext(), nameroom[i], Toast.LENGTH_SHORT).show();
                    }
                    Adapter_listroom adapter_listroom = new Adapter_listroom(Listroom_activity.this,nameroom);
                list_roomListView.setAdapter(adapter_listroom);


                    list_roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(Listroom_activity.this, Mylistroom.class);
                            intent.putExtra("nameroom",nameroom[i]);
                            intent.putExtra("roomid",idroom[i]);
                            intent.putExtra("datauser", datauserString);
                            startActivity(intent);

                        }
                    });

                } catch (Exception e) {
                }


            } else {
                Toast.makeText(getApplicationContext(), "เกิดความขัดข้อง", Toast.LENGTH_SHORT).show();
            }



        } catch (Exception e) {
        }


    }
}
