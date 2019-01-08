package com.jigar.android.retrofitcall.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jigar.android.retrofitcall.Models.Get_Candidate;
import com.jigar.android.retrofitcall.R;

import java.util.List;

/**
 * Created by COMP11 on 07-Jan-19.
 */

public class Adapter_getCandidate extends BaseAdapter {
    List<Get_Candidate> list;
    Context context;
    LayoutInflater inflater;

    public Adapter_getCandidate(List<Get_Candidate> list, Context context, LayoutInflater inflater) {
        this.list = list;
        this.context = context;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_items,null);

        TextView tv_nm = convertView.findViewById(R.id.get_nm);
        TextView tv_email = convertView.findViewById(R.id.get_email);

        String name = list.get(position).getCanFirstName();
        tv_nm.setText(name);

        String email = list.get(position).getEmail();
        tv_email.setText(email);


        return convertView;
    }
}
