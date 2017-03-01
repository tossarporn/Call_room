package com.example.phobia.call_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Phobia on 3/1/2017.
 */

public class Adapter_listroom extends BaseAdapter{
    private Context context;
    private String[] name_roomStrings;
    private TextView name_roomTextView;


    public Adapter_listroom(Context context, String[] name_roomStrings) {
        this.context = context;
        this.name_roomStrings = name_roomStrings;
    }

    @Override
    public int getCount() {
        return name_roomStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.item_room, viewGroup, false);
        name_roomTextView = (TextView) view1.findViewById(R.id.name_room);
        name_roomTextView.setText(name_roomStrings[i]);
        return view1;
    }
}
