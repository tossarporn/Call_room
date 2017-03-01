package com.example.phobia.call_room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Phobia on 3/1/2017.
 */

public class Adapter_user_join extends BaseAdapter {
    private String[] nick_name,tel, path_img;
    private Context context;
    private TextView nameTextView, telTextView;
    private ImageView profile;


    public Adapter_user_join(String[] nick_name, String[] tel, String[] path_img, Context context) {
        this.nick_name = nick_name;
        this.tel = tel;
        this.path_img = path_img;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nick_name.length;
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
        View view1 = layoutInflater.inflate(R.layout.item_user1, viewGroup, false);
        nameTextView = (TextView) view1.findViewById(R.id.name_userText);
        telTextView = (TextView) view1.findViewById(R.id.tel_userText);
        profile = (ImageView) view1.findViewById(R.id.imageuser);

        nameTextView.setText(nick_name[i]);
        telTextView.setText(tel[i]);
        Picasso.with(context).load(path_img[i]).into(profile);
        return view1;
    }
}
